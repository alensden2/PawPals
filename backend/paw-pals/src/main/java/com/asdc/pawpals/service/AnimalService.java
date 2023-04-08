package com.asdc.pawpals.service;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidAnimalObject;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.UserNameNotFound;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface AnimalService {
    AnimalDto registerPet(AnimalDto animalDto) throws UserNameNotFound, InvalidAnimalObject;

    AnimalDto updateAnimal(AnimalDto animalDto, Long id, MultipartFile image) throws InvalidImage, IOException, InvalidAnimalId, InvalidAnimalObject;

    AnimalDto deleteAnimal(Long id) throws InvalidAnimalId;

    AnimalDto updateAnimalObject(AnimalDto animalDto, Long id) throws InvalidAnimalObject, InvalidAnimalId;
}
