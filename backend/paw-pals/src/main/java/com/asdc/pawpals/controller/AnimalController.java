package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.exception.*;
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
@CrossOrigin
@RequestMapping("/unauth/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Autowired
    ApiResponse apiResponse;

    private static final Logger logger = LogManager.getLogger(AnimalController.class);

    // post animal // pet owner user id
    // put animal 
    // delete animal

    @PostMapping({"/register"})
    public ResponseEntity<ApiResponse> registerAnimal(@RequestPart("animal") Object requestBody, @RequestPart("image") MultipartFile image) throws  IOException, UserNameNotFound, InvalidImage, InvalidAnimalObject {
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

}
