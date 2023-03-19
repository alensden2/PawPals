package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.InvalidAnimalObject;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.service.AnimalService;
import com.asdc.pawpals.service.PetOwnerService;
import com.asdc.pawpals.utils.Transformations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
            if(!petOwnerRepository.findByUser_UserId(animalDto.getOwnerId()).isPresent())
            {
                throw new UserNameNotFound("Pet owner not found");
            }
            PetOwner petOwner = petOwnerRepository.findByUser_UserId(animalDto.getOwnerId()).get();
            Animal animal = Transformations.DTO_TO_MODEL_CONVERTER.animal(animalDto);
            animal.setOwner(petOwner);
            animal = animalRepository.save(animal);
            return Transformations.MODEL_TO_DTO_CONVERTER.animal(animal);
        }
        else
        {
           throw new InvalidAnimalObject("animal object is invalid");
        }

    }


}
