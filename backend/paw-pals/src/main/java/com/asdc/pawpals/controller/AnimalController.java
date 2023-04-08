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

    /**
     * Endpoint for registering a new animal with its photo. This method accepts two RequestPart parameters, the first one is an "animal" object of type Object and the second one is a "photo" object of type MultipartFile. This method throws IOException, UserNameNotFound, InvalidImage, and InvalidAnimalObject exceptions.
     *
     * @param requestBody An object of type Object that represents the animal to be registered.
     * @param image       An object of type MultipartFile that represents the photo of the animal to be registered.
     * @return ResponseEntity object with ApiResponse object in its body.
     * @throws IOException         Signals that an I/O exception of some sort has occurred.
     * @throws UserNameNotFound    Signals that username of the owner of animal is not found in the database.
     * @throws InvalidImage        Signals that the image provided is invalid.
     * @throws InvalidAnimalObject Signals that the animal object provided is invalid.
     */


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

    /**
     * Handles PUT requests to update an animal's information and photo by ID.
     *
     * @param requestBody The animal object to update in the request body.
     * @param image       The image of the animal in the request body.
     * @param id          The ID of the animal to update in the path.
     * @return A ResponseEntity containing an ApiResponse with updated animal information and a success message.
     * @throws IOException         If there is an error reading the image.
     * @throws InvalidImage        If the image file is not valid.
     * @throws InvalidAnimalObject If the animal object is not valid.
     * @throws InvalidAnimalId     If the animal ID is not valid.
     */
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

    /**
     * Handles HTTP DELETE requests for deleting an animal by its ID.
     *
     * @param id the ID of the animal to be deleted
     * @return a ResponseEntity containing an ApiResponse with information about the success or failure of the operation
     * @throws InvalidAnimalId if the provided ID is not valid
     */
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
