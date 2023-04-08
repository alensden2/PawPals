package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.*;
import com.asdc.pawpals.service.PetOwnerService;
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

@RequestMapping("/auth/pet-owner")
public class PetOwnerController {

    @Autowired
    PetOwnerService petOwnerService;

    @Autowired
    ApiResponse apiResponse;

    private static final Logger logger = LogManager.getLogger(PetOwnerController.class);


    /**
     * Endpoint to register a new pet owner with a profile image.
     *
     * @param requestBody An Object representing the pet owner details in JSON format.
     * @param image       A MultipartFile containing the profile image of the pet owner.
     * @return ResponseEntity with ApiResponse body, which includes the response status, message, success flag, and the pet owner object.
     * @throws UserAlreadyExist   if the provided username or email is already registered in the system.
     * @throws InvalidUserDetails if the provided pet owner details are invalid or incomplete.
     * @throws IOException        if there is an error while reading or writing the image file.
     * @throws UserNameNotFound   if the username is not found.
     * @throws InvalidImage       if the image is not in a valid format or exceeds the allowed size limit.
     */

    @PostMapping({"/register"})
    public ResponseEntity<ApiResponse> registerUser(@RequestPart("petowner") Object requestBody, @RequestPart("image") MultipartFile image) throws UserAlreadyExist, InvalidUserDetails, IOException, UserNameNotFound, InvalidImage {
        logger.info("Received request as :", requestBody.toString());
        PetOwnerDto petOwnerDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, PetOwnerDto.class)) {
            petOwnerDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, PetOwnerDto.class);
            petOwnerDto.setPhotoUrl(CommonUtils.getBytes(image));
            apiResponse.setBody(petOwnerService.registerPetOwner(petOwnerDto));
            apiResponse.setMessage("successfully inserted object");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

    /**
     * This method retrieves all pets for a given owner ID.
     *
     * @param ownerId The ID of the owner whose pets need to be retrieved
     * @return ResponseEntity containing ApiResponse which has a list of pets as the body, or an error message if no pets are registered under the given owner ID
     * @throws NoPetRegisterUnderPetOwner if there are no pets registered under the given owner ID
     * @throws UserNameNotFound           if the owner ID is not found in the database
     */
    @GetMapping("/pets/{owner_id}")
    public ResponseEntity<ApiResponse> getPetsByOwnerId(
            @PathVariable(value = "owner_id") String ownerId
    ) throws NoPetRegisterUnderPetOwner, UserNameNotFound {
        logger.info("Received request for fetching pets for owner :", ownerId);
        if (petOwnerService != null && ownerId != null) {
            apiResponse.setBody(petOwnerService.retrieveAllPets(ownerId));
            apiResponse.setMessage("successfully retrieve list");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    /**
     * Handles PUT requests to update an existing pet owner with a given ID.
     *
     * @param id          the ID of the pet owner to be updated
     * @param requestBody the request body containing the updated pet owner information in JSON format
     * @param image       the image file of the updated pet owner
     * @return a ResponseEntity containing an ApiResponse with the updated pet owner information and success message, or an error message if the update fails
     * @throws InvalidPetOwnerObject if the pet owner object is invalid
     * @throws UserNameNotFound      if the pet owner's username is not found
     * @throws InvalidImage          if the image file is invalid
     * @throws IOException           if there is an error reading the image file
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePetOwner(
            @PathVariable("id") String id,
            @RequestBody Object requestBody,
            @RequestPart("image") MultipartFile image)
            throws InvalidPetOwnerObject, UserNameNotFound, InvalidImage, IOException {
        logger.info("Received request as: {}", requestBody.toString());
        PetOwnerDto petOwnerDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, PetOwnerDto.class)) {
            petOwnerDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, PetOwnerDto.class);
            apiResponse.setBody(petOwnerService.updatePetOwner(id, petOwnerDto, image));
            apiResponse.setMessage("Successfully updated object");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    /**
     * Handles HTTP DELETE requests to delete a Pet Owner by ID
     *
     * @param id The ID of the Pet Owner to be deleted
     * @return A ResponseEntity with ApiResponse containing the deleted Pet Owner object and a success message
     * @throws UserNameNotFound if the Pet Owner with given ID is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePetOwner(@PathVariable String id) throws UserNameNotFound {
        logger.info("Received delete request for Pet Owner with id: {}", id.toString());
        if (CommonUtils.isStrictTypeOf(id, String.class)) {
            id = ObjectMapperWrapper.getInstance().convertValue(id, String.class);
            apiResponse.setBody(petOwnerService.deletePetOwner(id));
            apiResponse.setMessage("successfully deleted object");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    /**
     * Retrieves a list of all appointments for pets registered to a specific pet owner.
     *
     * @param ownerId the ID of the pet owner to retrieve the appointments for
     * @return a ResponseEntity containing an ApiResponse with the list of appointments and status information
     * @throws NoPetRegisterUnderPetOwner if there are no pets registered to the specified pet owner
     * @throws UserNameNotFound           if the specified pet owner does not exist
     */
    @GetMapping("/pets/appointments/{owner_id}")
    public ResponseEntity<ApiResponse> getPetsAppointmentsByOwnerId(
            @PathVariable(value = "owner_id") String ownerId
    ) throws NoPetRegisterUnderPetOwner, UserNameNotFound {
        logger.info("Received request for fetching pets for owner :", ownerId);
        if (petOwnerService != null && ownerId != null) {
            apiResponse.setBody(petOwnerService.retrievePetsAppointments(ownerId));
            apiResponse.setMessage("successfully retrieve list");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    /**
     * Retrieves the medical histories of all pets owned by the given pet owner.
     *
     * @param ownerId the ID of the pet owner
     * @return a ResponseEntity containing an ApiResponse with the retrieved medical histories, success message, and success status code
     * @throws NoPetRegisterUnderPetOwner if the given pet owner does not have any registered pets
     * @throws UserNameNotFound           if the given pet owner ID is not found in the system
     */
    @GetMapping("/pets/medicalHistory/{owner_id}")
    public ResponseEntity<ApiResponse> getPetMedicalHistoryByOwnerId(
            @PathVariable(value = "owner_id") String ownerId
    ) throws NoPetRegisterUnderPetOwner, UserNameNotFound {
        logger.info("Received request for fetching Medical Histories for All pets for pet owner:", ownerId);
        if (petOwnerService != null && ownerId != null) {
            apiResponse.setBody(petOwnerService.retrievePetsMedicalHistory(ownerId));
            apiResponse.setMessage("successfully retrieve list");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

}
