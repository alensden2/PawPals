package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidAnimalObject;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.service.AnimalService;
import com.asdc.pawpals.service.PetOwnerService;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.Transformations;
import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Lazy
public class AnimalServiceImpl implements AnimalService {
  Logger logger = LogManager.getLogger(AnimalServiceImpl.class);

  @Autowired
  AnimalRepository animalRepository;

  @Autowired
  PetOwnerRepository petOwnerRepository;

  @Autowired
  PetOwnerService petOwnerService;

  /**

Registers a new pet with the system.
* @param animalDto the data transfer object containing the pet's information
* @return the data transfer object for the newly registered pet
* @throws UserNameNotFound if the owner associated with the given ID does not exist
* @throws InvalidAnimalObject if the animalDto parameter is null or contains invalid information
*/
  @Override
  public AnimalDto registerPet(AnimalDto animalDto)
    throws UserNameNotFound, InvalidAnimalObject {
    logger.debug("Register Animal", animalDto);
    /**
     * Old code
     * 
     * if (
      null != animalDto &&
      null != animalDto.getAge() &&
      null != animalDto.getGender() &&
      null != animalDto.getName() &&
      null != animalDto.getType() &&
      null != animalDto.getPhotoUrl()
    ) 
     */
    boolean isAnimalDtoNotNull = null != animalDto;
    boolean isAgeNotNull = null != animalDto.getAge();
    boolean isGenderNotNull = null != animalDto.getGender();
    boolean isNameNotNull = null != animalDto.getName();
    boolean isTypeNotNull = null != animalDto.getType();
    boolean isPhotoUrlNotNull = null != animalDto.getPhotoUrl();
    if (
      isAnimalDtoNotNull &&
      isAgeNotNull &&
      isGenderNotNull &&
      isNameNotNull &&
      isTypeNotNull &&
      isPhotoUrlNotNull
    ) {
      Optional<PetOwner> petOwnerOptional = petOwnerRepository.findByUser_UserId(
        animalDto.getOwnerId()
      );

      if (!petOwnerOptional.isPresent()) {
        throw new UserNameNotFound("Pet owner not found");
      }
      PetOwner petOwner = petOwnerOptional.get();
      Animal animal = Transformations.DTO_TO_MODEL_CONVERTER.animal(animalDto);
      animal.setOwner(petOwner);
      animal = animalRepository.save(animal);
      return Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
    } else {
      throw new InvalidAnimalObject("animal object is invalid");
    }
  }

  /**

   * Updates an existing animal with the given ID and information from the provided DTO and image file.
   * @param animalDto the DTO containing the updated animal information
   * @param id the ID of the animal to update
   * @param image the image file to use as the animal's new photo, or null to leave the photo unchanged
   * @return the DTO representation of the updated animal
   * @throws InvalidImage if the provided image is invalid
   * @throws IOException if there is an error reading the image file
   * @throws InvalidAnimalId if the provided animal ID is invalid
   * @throws InvalidAnimalObject if the provided animal DTO is invalid or null
*/
  @Override
  public AnimalDto updateAnimal(
    AnimalDto animalDto,
    Long id,
    MultipartFile image
  )
    throws InvalidImage, IOException, InvalidAnimalId, InvalidAnimalObject {
    if (null != id && null != animalDto) {
      Animal animal = animalRepository
        .findById(id)
        .orElseThrow(InvalidAnimalId::new);
      if (image != null) {
        animal.setPhotoUrl(CommonUtils.getBytes(image));
      }
      updateAnimalData(animalDto, animal);
      animal = animalRepository.saveAndFlush(animal);
      return Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
    } else {
      throw new InvalidAnimalObject(
        "Null parameter passed in the Animal Object"
      );
    }
  }

  /**

Deletes an animal with the given ID from the database.
   * @param id the ID of the animal to be deleted
   * @return the DTO representation of the deleted animal
   * @throws InvalidAnimalId if the given ID is null or does not match any animal in the database
*/
  @Override
  public AnimalDto deleteAnimal(Long id) throws InvalidAnimalId {
    if (null != id) {
      Animal animal = animalRepository
        .findById(id)
        .orElseThrow(InvalidAnimalId::new);
      animalRepository.delete(animal);
      animalRepository.flush();
      return Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
    } else {
      throw new InvalidAnimalId("Id is null");
    }
  }

  /**

  * Updates the data of an existing animal object.
   * @param animalDto The updated AnimalDto object to be saved.
   * @param id The id of the animal object to be updated.
    * @return The updated AnimalDto object.
    * @throws InvalidAnimalObject If the animalDto parameter is null.
    * @throws InvalidAnimalId If the id parameter is null or does not correspond to any existing animal object.
*/
  @Override
  public AnimalDto updateAnimalObject(AnimalDto animalDto, Long id)
    throws InvalidAnimalObject, InvalidAnimalId {
    if (null != id && null != animalDto) {
      Animal animal = animalRepository
        .findById(id)
        .orElseThrow(InvalidAnimalId::new);
      updateAnimalData(animalDto, animal);
      animal = animalRepository.saveAndFlush(animal);
      return Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
    } else {
      throw new InvalidAnimalObject(
        "Null parameter passed in the Animal Object"
      );
    }
  }

  /**

  *  Updates the data of an Animal entity with values from an AnimalDto
   * @param animalDto the AnimalDto containing the updated data
   * @param animal the Animal entity to update
*/
  private static void updateAnimalData(AnimalDto animalDto, Animal animal) {
    if (animalDto.getName() != null) {
      animal.setName(animalDto.getName());
    }
    if (animalDto.getGender() != null) {
      animal.setGender(animalDto.getGender());
    }
    if (animalDto.getAge() != null) {
      animal.setAge(animalDto.getAge());
    }
    if (animalDto.getType() != null) {
      animal.setType(animalDto.getType());
    }
  }
}
