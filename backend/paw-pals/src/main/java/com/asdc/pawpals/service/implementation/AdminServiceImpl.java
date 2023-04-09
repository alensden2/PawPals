package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.AdminService;
import com.asdc.pawpals.utils.Transformations;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
  Logger logger = LogManager.getLogger(AdminServiceImpl.class);

  @Autowired
  VetRepository vetRepository;

  @Autowired
  AnimalRepository animalRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PetOwnerRepository petOwnerRepository;

  /**
   * Returns a list of AnimalDto objects that represent all animal records from the animal repository.
   * @return A list of AnimalDto objects representing all animal records.
   */
  @Override
  public List<AnimalDto> getAllAnimalRecords() {
    List<Animal> animals = animalRepository.findAll();
    logger.debug(
      "AdminReadService :: getAllVets :: userRecordsAre are : {}",
      animals
    );
    List<AnimalDto> animalsDto = null;
    if (animals != null && !animals.isEmpty()) {
      animalsDto =
        animals
          .stream()
          .map(Transformations.MODEL_TO_DTO_CONVERTER::animal)
          .collect(Collectors.toList());
    }
    return animalsDto;
  }

  /**
   * Returns a list of all the VetDto objects from the database.
   *
   * @return A list of VetDto objects
   */
  public List<VetDto> getAllVetRecords() {
    List<Vet> vets = vetRepository.findAll();
    logger.debug(
      "AdminReadService :: getAllVets :: userRecordsAre are : {}",
      vets
    );
    List<VetDto> vetsDto = null;
    if (vets != null && !vets.isEmpty()) {
      vetsDto =
        vets
          .stream()
          .map(Transformations.MODEL_TO_DTO_CONVERTER::vet)
          .collect(Collectors.toList());
    }
    return vetsDto;
  }

  /**
   * Returns a list of PetOwnerDto objects that represent all pet owner records from the pet owner repository.
   *
   * @return A list of PetOwnerDto objects representing all pet owner records.
   */
  @Override
  public List<PetOwnerDto> getAllPetOwnerRecords() {
    List<PetOwner> petOwner = petOwnerRepository.findAll();
    logger.debug(
      "AdminReadService :: getAllVets :: petOwnerRecordsAre are : {}",
      petOwner
    );
    List<PetOwnerDto> PetOwnerDto = null;
    if (!petOwner.isEmpty()) {
      PetOwnerDto =
        petOwner
          .stream()
          .map(Transformations.MODEL_TO_DTO_CONVERTER::petOwner)
          .collect(Collectors.toList());
    }
    return PetOwnerDto;
  }

  /**
   * Returns a list of UserDto objects that represent all user records from the user repository.
   *
   * @return A list of UserDto objects representing all user records.
   */
  public List<UserDto> getAllUserRecords() {
    List<User> userRecords = userRepository.findAll();
    logger.debug(
      "AdminReadService :: getAllUsers :: userRecordsAre are : {}",
      userRecords
    );
    List<UserDto> userRecordsDto = null;
    if (userRecords != null && !userRecords.isEmpty()) {
      userRecordsDto =
        userRecords
          .stream()
          .map(Transformations.MODEL_TO_DTO_CONVERTER::user)
          .collect(Collectors.toList());
    }
    return userRecordsDto;
  }

  /**
   * Adds a new animal record to the animal repository.
   *
   * @param animal The Animal object representing the new animal record to add.
   * @return An AnimalDto object representing the newly added animal record.
   * @throws PetOwnerAlreadyDoesNotExists If the pet owner associated with the new animal record does not exist in the user repository.
   */
  @Override
  public AnimalDto addAnimal(Animal animal)
    throws PetOwnerAlreadyDoesNotExists {
    AnimalDto returnedDto = null;
    if (animal != null && animal.getOwner() != null) {
      // add to check if user is already existing throw an exception
      // userRepository.save(animal.getOwner().getUser());
      Optional<User> user = userRepository.findById(
        animal.getOwner().getUser().getUserId()
      );

      if (!user.isEmpty()) {
        User userDetails = null;
        userDetails = user.get();
        Long countOfPetOwners = petOwnerRepository.count();
        countOfPetOwners = countOfPetOwners + 1;
        animal.setId(countOfPetOwners);
        animal = animalRepository.save(animal);
        returnedDto = Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
      } else {
        throw new PetOwnerAlreadyDoesNotExists( // change name of error
          "Pet Owner does not exist" + animal
        );
      }
    }

    return returnedDto;
  }

  /**

* Adds a new Vet to the database and returns the created VetDto object.
* @param vet The Vet object to be added to the database
* @return The created VetDto object
* @throws PetOwnerAlreadyExistsException If the Vet's user already exists in the database
* @throws UserNotFoundException If the Vet's user is not found in the database
*/

  @Override
  public VetDto addVet(Vet vet) {
    VetDto vetDto = null;
    // adding user to user table
    // add to check if user is already existing throw an exception
    //add a user first
    if (vet != null && vet.getUser().getUserId() != null) {
      userRepository.save(vet.getUser());
      Optional<User> user = userRepository.findById(vet.getUser().getUserId());
      if (!user.isEmpty()) {
        User userDetails = null;
        userDetails = user.get();
        vet.setUser(userDetails);
        vet = vetRepository.save(vet);
        vetDto = Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);
      } else {
        //custom user does not exit create}
      }
    }
    return vetDto;
  }

  /**
   * Updates an existing Vet with the given id with the information from the updatedVet object.
   *
   * @param id The id of the Vet to update.
   * @param updatedVet The updated Vet object containing the new information.
   * @return The updated VetDto object.
   */
  @Override
  public VetDto updateVet(Long id, Vet updatedVet) {
    VetDto vetDto = null;
    Optional<Vet> optionalVet = vetRepository.findById(id);
    if (optionalVet.isPresent()) {
      // populating the vet
      Vet vet = optionalVet.get();
      vet.setFirstName(updatedVet.getFirstName());
      vet.setLastName(updatedVet.getLastName());
      vet.setLicenseNumber(updatedVet.getLicenseNumber());
      vet.setClinicAddress(updatedVet.getClinicAddress());
      vet.setExperience(updatedVet.getExperience());
      vet.setQualification(updatedVet.getQualification());
      vet.setUser(vet.getUser()); // user details remain the same
      vetRepository.save(vet);
      vetDto = Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);
    } else {
      //throw new EntityNotFoundException("Vet with id " + id + " not found");
    }
    return vetDto;
  }

  /**
   * Returns a list of all user records as UserDto objects.
   *
   * @return a list of all user records as UserDto objects
   */
  @Override
  public VetDto deleteVet(Long id) {
    VetDto vetDto = null;
    Optional<Vet> optionalVet = vetRepository.findById(id);
    if (optionalVet.isPresent()) {
      Vet vet = optionalVet.get();
      vetRepository.delete(vet);
      vetDto = Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);
    } else {
      // throw exception user not found
    }
    return vetDto;
  }

  /**
   * Deletes the animal with the specified ID.
   *
   * @param id the ID of the animal to delete
   * @return the DTO representation of the deleted animal
   * @throws EntityNotFoundException if the animal with the specified ID is not found
   */
  @Override
  public AnimalDto deleteAnimal(Long id) {
    AnimalDto animalDto = null;
    Optional<Animal> optionalAnimal = animalRepository.findById(id);
    if (optionalAnimal.isPresent()) {
      Animal animal = optionalAnimal.get();
      animalRepository.delete(animal);
      animalDto = Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
    } else {
      //throw new EntityNotFoundException("Animal with id " + id + " not found");
    }
    return animalDto;
  }

  /**
   * Updates the animal with the specified id with the provided information.
   *
   * @param id the id of the animal to update
   * @param updatedAnimal the updated information for the animal
   * @return the updated animal information as a DTO
   * @throws EntityNotFoundException if the animal with the specified id is not found
   */
  @Override
  public AnimalDto updateAnimal(Long id, Animal updatedAnimal) {
    AnimalDto animalDto = null;
    Optional<Animal> optionalAnimal = animalRepository.findById(id);
    if (optionalAnimal.isPresent()) {
      Animal animal = optionalAnimal.get();
      animal.setName(updatedAnimal.getName());
      animal.setType(updatedAnimal.getType());
      animal.setAge(updatedAnimal.getAge());
      animal.setGender(updatedAnimal.getGender());
      animalRepository.save(animal);
      animalDto = Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
    } else {
      // throw new exception
    }
    return animalDto;
  }
}
