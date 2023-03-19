package com.asdc.pawpals.service;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.exception.InvalidAnimalObject;
import com.asdc.pawpals.exception.UserNameNotFound;
import org.springframework.stereotype.Service;

@Service
public interface AnimalService {
    AnimalDto registerPet(AnimalDto animalDto) throws UserNameNotFound, InvalidAnimalObject;
}
