package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.PetAppointmentsDto;
import com.asdc.pawpals.dto.PetMedicalHistoryDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.InvalidPetOwnerObject;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.NoPetRegisterUnderPetOwner;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.AppointmentRepository;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.PetOwnerService;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.Transformations;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Lazy
public class PetOwnerImpl implements PetOwnerService {
  Logger logger = LogManager.getLogger(PetOwnerImpl.class);

  @Autowired
  PetOwnerRepository petOwnerRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  AnimalRepository animalRepository;

  @Autowired
  AppointmentRepository appointmentRepository;

  @Autowired
  VetRepository vetRepository;

  /**

     *   Registers a new pet owner with the given details.
     *   @param petOwnerDto the PetOwnerDto object containing the details of the pet owner to be registered
     *   @return a PetOwnerDto object representing the registered pet owner
     *   @throws UserNameNotFound if the username provided in the PetOwnerDto does not exist in the user repository
     *   @throws InvalidUserDetails if the details provided in the PetOwnerDto are invalid
     *   @throws UserAlreadyExist if a pet owner with the provided username already exists in the pet owner repository
*/
  @Override
  public PetOwnerDto registerPetOwner(PetOwnerDto petOwnerDto)
    throws UserNameNotFound, InvalidUserDetails, UserAlreadyExist {
    logger.debug("Register pet Owner", petOwnerDto);
    boolean isPetOwnerDtoNotNull = null != petOwnerDto;
    boolean isPetOwnerDtoUsernameNotNull = null != petOwnerDto.getUsername();
    boolean isPetOwnerDtoPhotoUrlNotNull = null != petOwnerDto.getPhotoUrl();
    boolean isPetOwnerDtoPhoneNoNotNull = null != petOwnerDto.getPhoneNo();
    boolean isPetOwnerDtoAddressNotNull = null != petOwnerDto.getAddress();
    /*
 * old code - 
 * if (
      null != petOwnerDto &&
      null != petOwnerDto.getUsername() &&
      null != petOwnerDto.getPhotoUrl() &&
      null != petOwnerDto.getPhoneNo() &&
      null != petOwnerDto.getAddress()
    ) 
 */
    if (
      isPetOwnerDtoNotNull &&
      isPetOwnerDtoUsernameNotNull &&
      isPetOwnerDtoPhotoUrlNotNull &&
      isPetOwnerDtoPhoneNoNotNull &&
      isPetOwnerDtoAddressNotNull
    ) {
      User user = userRepository
        .findById(petOwnerDto.getUsername())
        .orElseThrow(UserNameNotFound::new);

      if (petOwnerRepository.existsByUser_UserId(petOwnerDto.getUsername())) {
        throw new UserAlreadyExist("Pet Owner Already Exist");
      }

      PetOwner petOwner = Transformations.DTO_TO_MODEL_CONVERTER.petOwner(
        petOwnerDto
      );
      petOwner.setUser(user);
      petOwner = petOwnerRepository.save(petOwner);
      return Transformations.MODEL_TO_DTO_CONVERTER.petOwner(petOwner);
    } else {
      throw new InvalidUserDetails("incorrect pet owner data");
    }
  }

  /**

      *  Retrieves all the pets of the given pet owner ID
      *  @param ownerId the ID of the pet owner whose pets need to be retrieved
      *  @return a list of AnimalDto objects representing the pets of the given pet owner ID
      *  @throws NoPetRegisterUnderPetOwner if no pets are registered for the given pet owner
      *  @throws UserNameNotFound if the given pet owner ID is not found in the system
*/
  @Override
  public List<AnimalDto> retrieveAllPets(String ownerId)
    throws NoPetRegisterUnderPetOwner, UserNameNotFound {
    logger.debug("Get All Pets By owner Id", ownerId);

    PetOwner petOwner = petOwnerRepository
      .findByUser_UserId(ownerId)
      .orElseThrow(UserNameNotFound::new);
    List<AnimalDto> animalDtoList = petOwner
      .getAnimals()
      .stream()
      .map(Transformations.MODEL_TO_DTO_CONVERTER::animal)
      .collect(Collectors.toList());
    if (animalDtoList.isEmpty()) {
      throw new NoPetRegisterUnderPetOwner(
        "No Pet Registered for Owner " +
        petOwner.getFirstName() +
        " " +
        petOwner.getLastName()
      );
    }
    return animalDtoList;
  }

  /**

   * Updates the information of a pet owner.
   * @param id the ID of the pet owner to update
   * @param petOwnerDto the new data to update the pet owner with
   * @param image the profile image of the pet owner
   * @return a PetOwnerDto object with the updated information
   * @throws UserNameNotFound if the provided ID does not correspond to a pet owner in the system
   * @throws InvalidPetOwnerObject if the provided pet owner data is invalid or incomplete
   * @throws InvalidImage if the provided image file is invalid or cannot be processed
   * @throws IOException if an I/O error occurs while processing the image file
*/
  @Override
  public PetOwnerDto updatePetOwner(
    String id,
    PetOwnerDto petOwnerDto,
    MultipartFile image
  )
    throws UserNameNotFound, InvalidPetOwnerObject, InvalidImage, IOException {
    boolean isIdNotNullOrEmpty = null != id && !id.isEmpty();
    boolean isPetOwnerDtoNotNull = petOwnerDto != null;
    boolean isConditionMet = isIdNotNullOrEmpty && isPetOwnerDtoNotNull;
    /**
     * Old Code -
     * if (null != id && !id.isEmpty() && petOwnerDto != null)
     */
    if (isConditionMet) {
      PetOwner petOwner = petOwnerRepository
        .findByUser_UserId(id)
        .orElseThrow(UserNameNotFound::new);

      if (petOwnerDto.getAddress() != null) {
        petOwner.setAddress(petOwnerDto.getAddress());
      }
      if (petOwnerDto.getPhotoUrl() != null) {
        petOwner.setPhotoUrl(CommonUtils.getBytes(image));
      }
      if (petOwnerDto.getPhoneNo() != null) {
        petOwner.setPhoneNo(petOwner.getPhoneNo());
      }
      if (petOwnerDto.getFirstName() != null) {
        petOwner.setFirstName(petOwnerDto.getFirstName());
      }
      if (petOwnerDto.getLastName() != null) {
        petOwner.setLastName(petOwnerDto.getLastName());
      }

      petOwnerRepository.saveAndFlush(petOwner);

      return Transformations.MODEL_TO_DTO_CONVERTER.petOwner(petOwner);
    } else if (petOwnerDto == null) {
      throw new InvalidPetOwnerObject("Invalid pet owner object body");
    } else {
      throw new UserNameNotFound("User name is not found for " + id);
    }
  }

  /**

   * Deletes a pet owner by their user ID.
   * @param id The user ID of the pet owner to delete.
   * @return The PetOwnerDto object that was deleted.
   * @throws UserNameNotFound If a pet owner with the given ID is not found.
*/

  @Override
  public PetOwnerDto deletePetOwner(String id) throws UserNameNotFound {
    if (null != id && !id.isEmpty()) {
      PetOwner petOwner = petOwnerRepository
        .findByUser_UserId(id)
        .orElseThrow(UserNameNotFound::new);
      petOwnerRepository.delete(petOwner.getId());
      petOwnerRepository.flush();
      //            PetOwner petOwner1 = petOwnerRepository.findByUser_UserId(id).orElseThrow(UserNameNotFound::new);
      return Transformations.MODEL_TO_DTO_CONVERTER.petOwner(petOwner);
    } else {
      throw new UserNameNotFound("invalid id");
    }
  }

  /**

   * Retrieves all appointments of pets owned by a pet owner.
   * @param ownerId The ID of the pet owner whose pets' appointments to retrieve.
   * @return A list of PetAppointmentsDto representing the appointments of the pets.
   * @throws UserNameNotFound if the given pet owner ID is not found in the repository.
   * @throws NoPetRegisterUnderPetOwner if the given pet owner has not registered any pets.
*/
  @Override
  public List<PetAppointmentsDto> retrievePetsAppointments(String ownerId)
    throws UserNameNotFound, NoPetRegisterUnderPetOwner {
    logger.debug("Get All Pets Appointment By owner Id", ownerId);

    PetOwner petOwner = petOwnerRepository
      .findByUser_UserId(ownerId)
      .orElseThrow(UserNameNotFound::new);

    if (petOwner.getAnimals().isEmpty()) {
      throw new NoPetRegisterUnderPetOwner(
        "No Pet Registered for Owner " +
        petOwner.getFirstName() +
        " " +
        petOwner.getLastName()
      );
    }

    List<PetAppointmentsDto> petAppointmentsDtos = petOwner
      .getAnimals()
      .stream()
      .flatMap(
        animal ->
          animal
            .getAppointment()
            .stream()
            .map(
              appointment -> {
                PetAppointmentsDto petAppointmentsDto = new PetAppointmentsDto();
                petAppointmentsDto.setAppointmentDto(
                  Transformations.MODEL_TO_DTO_CONVERTER.appointment(
                    appointment
                  )
                );
                petAppointmentsDto.setVetDto(
                  Transformations.MODEL_TO_DTO_CONVERTER.vet(
                    appointment.getVet()
                  )
                );
                petAppointmentsDto.setAnimalDto(
                  Transformations.MODEL_TO_DTO_CONVERTER.animal(animal)
                );
                return petAppointmentsDto;
              }
            )
      )
      .collect(Collectors.toList());

    return petAppointmentsDtos;
  }

  /**

   * Retrieve all pets medical history for a given pet owner.
   * @param ownerId The ID of the pet owner.
   * @return List of PetMedicalHistoryDto representing the pets' medical history.
   * @throws UserNameNotFound If no user found with the given ID.
   * @throws NoPetRegisterUnderPetOwner If the pet owner has no registered pets.
*/
  @Override
  public List<PetMedicalHistoryDto> retrievePetsMedicalHistory(String ownerId)
    throws UserNameNotFound, NoPetRegisterUnderPetOwner {
    logger.debug("Get All Pets Medical Records By owner Id", ownerId);

    PetOwner petOwner = petOwnerRepository
      .findByUser_UserId(ownerId)
      .orElseThrow(UserNameNotFound::new);

    if (petOwner.getAnimals().isEmpty()) {
      throw new NoPetRegisterUnderPetOwner(
        "No Pet Registered for Owner " +
        petOwner.getFirstName() +
        " " +
        petOwner.getLastName()
      );
    }

    List<PetMedicalHistoryDto> petMedicalHistoryDtos = petOwner
      .getAnimals()
      .stream()
      .flatMap(
        animal ->
          animal
            .getMedicalHistories()
            .stream()
            .map(
              medicalHistory -> {
                PetMedicalHistoryDto petMedicalHistoryDto = new PetMedicalHistoryDto();
                petMedicalHistoryDto.setMedicalHistoryDto(
                  Transformations.MODEL_TO_DTO_CONVERTER.medicalHistory(
                    medicalHistory
                  )
                );
                petMedicalHistoryDto.setVetDto(
                  Transformations.MODEL_TO_DTO_CONVERTER.vet(
                    medicalHistory.getVet()
                  )
                );
                petMedicalHistoryDto.setAnimalDto(
                  Transformations.MODEL_TO_DTO_CONVERTER.animal(animal)
                );
                return petMedicalHistoryDto;
              }
            )
      )
      .collect(Collectors.toList());

    return petMedicalHistoryDtos;
  }
}
