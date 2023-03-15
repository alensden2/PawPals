package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.AdminPostAnimalRepository;
import com.asdc.pawpals.repository.AdminReadAllAnimalsRepository;
import com.asdc.pawpals.repository.AdminReadAllUserRepository;
import com.asdc.pawpals.repository.AdminReadAllVetsRepository;
import com.asdc.pawpals.repository.AdminReadRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.service.AdminReadService;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import com.asdc.pawpals.utils.Transformations;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminReadServiceImpl implements AdminReadService {
  Logger logger = LogManager.getLogger(AdminReadServiceImpl.class);

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
      //Animal animal = Transformations.DTO_TO_MODEL_CONVERTER.animal(animalDto);
      // if (adminPostAnimalRepository.existsById(null)) {
      //   //throw new AnimalAlreadyExist("user exist in the system");
      // }

      // Check if user already exists if this pet owner exists
      //animal.getOwner
      Optional<User> user = userRepository.findById(
        animal.getOwner().getUser().getUserId()
      );

      if (!user.isEmpty()) {
        User userDetails = null;
        userDetails = user.get();
        animal.setId(userDetails.getOwner().getId());
        animal = adminPostAnimalRepository.save(animal);
        returnedDto = Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
      } else {
        throw new PetOwnerAlreadyDoesNotExists(
          "Pet Owner does not exist" + animal
        );
      }
    }

    return returnedDto;
  }
}
