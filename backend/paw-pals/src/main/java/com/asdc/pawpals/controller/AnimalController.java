package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidAnimalObject;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.AnimalService;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
 
@RequestMapping("/auth/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Autowired
    ApiResponse apiResponse;

    private static final Logger logger = LogManager.getLogger(AnimalController.class);


    @PostMapping({"/register"})
    public ResponseEntity<ApiResponse> registerAnimal(@RequestPart("animal") Object requestBody, @RequestPart("image") MultipartFile image) throws IOException, UserNameNotFound, InvalidImage, InvalidAnimalObject {
        logger.info("Received request as :", requestBody.toString());
        AnimalDto animalDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, AnimalDto.class)) {
            animalDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, AnimalDto.class);
            animalDto.setPhotoUrl(CommonUtils.getBytes(image));
            apiResponse.setBody(animalService.registerPet(animalDto));
            apiResponse.setMessage("successfully inserted object");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @PutMapping({"/{id}"})
    public ResponseEntity<ApiResponse> updateAnimal(@RequestPart("animal") Object requestBody, @RequestPart("image") MultipartFile image, @PathVariable("id") Long id) throws IOException, InvalidImage, InvalidAnimalObject, InvalidAnimalId {
        logger.info("Received request as :", requestBody.toString());
        AnimalDto animalDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, AnimalDto.class)) {
            animalDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, AnimalDto.class);
            apiResponse.setBody(animalService.updateAnimal(animalDto, id, image));
            apiResponse.setMessage("successfully updated object");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAnimal(@PathVariable Long id) throws InvalidAnimalId {
        logger.info("Received delete request for Pet Owner with id: {}", id.toString());
        if (CommonUtils.isStrictTypeOf(id, String.class)) {
            id = ObjectMapperWrapper.getInstance().convertValue(id, Long.class);
            apiResponse.setBody(animalService.deleteAnimal(id));
            apiResponse.setMessage("successfully deleted object");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
