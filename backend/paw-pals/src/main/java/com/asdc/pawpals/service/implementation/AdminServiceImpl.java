package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.AdminPostAnimalRepository;
import com.asdc.pawpals.repository.AdminPostVetRepository;
import com.asdc.pawpals.repository.AdminReadAllAnimalsRepository;
import com.asdc.pawpals.repository.AdminReadAllUserRepository;
import com.asdc.pawpals.repository.AdminReadAllVetsRepository;
import com.asdc.pawpals.repository.AdminReadRepository;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.repository.UserRepository;
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
  AdminReadRepository adminReadRepository;

  @Autowired
  AdminReadAllVetsRepository adminReadAllVetsRepository;

  @Autowired
  AdminReadAllUserRepository adminReadAllUserRepository;

  @Autowired
  AdminReadAllAnimalsRepository adminReadAllAnimalsRepository;

  @Autowired
  AdminPostAnimalRepository adminPostAnimalRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  AdminPostVetRepository adminPostVetRepository;

  @Autowired
  PetOwnerRepository petOwnerRepository;

  /**
   * fetches all the animal records
   */
  @Override
  public List<AnimalDto> getAllAnimalRecords() {
    List<Animal> animals = adminReadAllAnimalsRepository.findAll();
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
   * fetches all the vet records
   */
  public List<VetDto> getAllVetRecords() {
    List<Vet> vets = adminReadAllVetsRepository.findAll();
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
   * fetches all the user records
   *
   */
  public List<UserDto> getAllUserRecords() {
    List<User> userRecords = adminReadAllUserRepository.findAll();
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
        animal = adminPostAnimalRepository.save(animal);
        returnedDto = Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
      } else {
        throw new PetOwnerAlreadyDoesNotExists( // change name of error
          "Pet Owner does not exist" + animal
        );
      }
    }

    return returnedDto;
  }

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
        vet = adminPostVetRepository.save(vet);
        vetDto = Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);
      } else {
        //custom user does not exit create}
      }
    }
    return vetDto;
  }

  @Override
  public VetDto updateVet(Long id, Vet updatedVet) {
    VetDto vetDto = null;
    Optional<Vet> optionalVet = adminPostVetRepository.findById(id);
    if (optionalVet.isPresent()) {
      // populating the vet
      Vet vet = optionalVet.get();
      vet.setName(updatedVet.getName());
      vet.setLicenseNumber(updatedVet.getLicenseNumber());
      vet.setClinicAddress(updatedVet.getClinicAddress());
      vet.setExperience(updatedVet.getExperience());
      vet.setQualification(updatedVet.getQualification());
      vet.setUser(vet.getUser()); // user details remain the same
      adminPostVetRepository.save(vet);
      vetDto = Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);
    } else {
      //throw new EntityNotFoundException("Vet with id " + id + " not found");
    }
    return vetDto;
  }

  @Override
  public VetDto deleteVet(Long id) {
    VetDto vetDto = null;
    Optional<Vet> optionalVet = adminPostVetRepository.findById(id);
    if (optionalVet.isPresent()) {
      Vet vet = optionalVet.get();
      adminPostVetRepository.delete(vet);
      vetDto = Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);
    } else {
      // throw exception user not found
    }
    return vetDto;
  }

  @Override
  public AnimalDto deleteAnimal(Long id) {
    AnimalDto animalDto = null;
    Optional<Animal> optionalAnimal = adminPostAnimalRepository.findById(id);
    if (optionalAnimal.isPresent()) {
      Animal animal = optionalAnimal.get();
      adminPostAnimalRepository.delete(animal);
      animalDto = Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
    } else {
      //throw new EntityNotFoundException("Animal with id " + id + " not found");
    }
    return animalDto;
  }

  @Override
  public AnimalDto updateAnimal(Long id, Animal updatedAnimal) {
    AnimalDto animalDto = null;
    Optional<Animal> optionalAnimal = adminPostAnimalRepository.findById(id);
    if (optionalAnimal.isPresent()) {
      Animal animal = optionalAnimal.get();
      animal.setName(updatedAnimal.getName());
      animal.setType(updatedAnimal.getType());
      animal.setAge(updatedAnimal.getAge());
      animal.setGender(updatedAnimal.getGender());
      adminPostAnimalRepository.save(animal);
      animalDto = Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
    } else {
      // throw new exception
    }
    return animalDto;
  }
}
