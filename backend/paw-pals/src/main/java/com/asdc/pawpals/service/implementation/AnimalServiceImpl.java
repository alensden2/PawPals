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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

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
     * @param animalDto
     * @return
     */
    @Override
    public AnimalDto registerPet(AnimalDto animalDto) throws UserNameNotFound, InvalidAnimalObject {
        logger.debug("Register Animal", animalDto);
        if (null != animalDto && null != animalDto.getAge() && null != animalDto.getGender() && null != animalDto.getName() &&
                null != animalDto.getType() && null != animalDto.getPhotoUrl()) {

            Optional<PetOwner> petOwnerOptional = petOwnerRepository.findByUser_UserId(animalDto.getOwnerId());

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
     * @param animalDto
     * @param id
     * @param image
     * @return
     */
    @Override
    public AnimalDto updateAnimal(AnimalDto animalDto, Long id, MultipartFile image) throws InvalidImage, IOException, InvalidAnimalId, InvalidAnimalObject {

        if (null != id && null != animalDto && null != image) {
            Animal animal = animalRepository.findById(id).orElseThrow(InvalidAnimalId::new);

            if (animalDto.getName() != null) {
                animal.setName(animalDto.getName());
            }
            if (animalDto.getGender() != null) {
                animal.setGender(animalDto.getGender());
            }
            if (image != null) {
                animal.setPhotoUrl(CommonUtils.getBytes(image));
            }
            if (animalDto.getAge() != null) {
                animal.setAge(animalDto.getAge());
            }
            if (animalDto.getType() != null) {
                animal.setType(animalDto.getType());
            }

            animal = animalRepository.saveAndFlush(animal);
            return Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
        } else {
            throw new InvalidAnimalObject("Null parameter passed in the Animal Object");
        }

    }

    /**
     * @param id
     * @return
     */
    @Override
    public AnimalDto deleteAnimal(Long id) throws InvalidAnimalId {
        if (null != id) {
            Animal animal = animalRepository.findById(id).orElseThrow(InvalidAnimalId::new);
            animalRepository.delete(animal);
            animalRepository.flush();
            return Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
        } else {
            throw new InvalidAnimalId("Id is null");
        }
    }
}
