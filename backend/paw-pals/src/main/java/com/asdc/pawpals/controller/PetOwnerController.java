package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.*;
import com.asdc.pawpals.model.Animal;
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
import java.io.InvalidObjectException;

@RestController
@CrossOrigin
@RequestMapping("/unauth/pet-owner")
public class PetOwnerController {

    @Autowired
    PetOwnerService petOwnerService;

    @Autowired
    ApiResponse apiResponse;

    private static final Logger logger = LogManager.getLogger(PetOwnerController.class);


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


    @GetMapping("/pets/{owner_id}")
    public ResponseEntity<ApiResponse> getPetsByOwnerId(
            @PathVariable(value = "owner_id") Long ownerId
    ) throws InvalidOwnerID, NoPetRegisterUnderPetOwner {
        logger.info("Received request for fetching pets for owner :", ownerId);
        if (petOwnerService != null && ownerId != null) {
            apiResponse.setBody(petOwnerService.retrieveAllPets(ownerId));
            apiResponse.setMessage("successfully retrieve list");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping({"/book-appointment"})
    public ResponseEntity<ApiResponse> bookAppointment(@RequestBody Object requestBody) throws InvalidAnimalId, InvalidObjectException, InvalidVetID {
        logger.info("Received request as :", requestBody.toString());
        AppointmentDto appointmentDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, AppointmentDto.class)) {
            appointmentDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, AppointmentDto.class);
            apiResponse.setBody(petOwnerService.bookAppointment(appointmentDto));
            apiResponse.setMessage("Successfully created Appointment");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePetOwner(
            @PathVariable("id") String id,
            @RequestBody Object requestBody)
            throws PetOwnerAlreadyDoesNotExists, InvalidPetOwnerObject, UserNameNotFound {
        logger.info("Received request as: {}", requestBody.toString());
        PetOwnerDto petOwnerDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, PetOwnerDto.class)) {
            petOwnerDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, PetOwnerDto.class);
//            animal.setId(id); // Set the ID of the updated animal
            apiResponse.setBody(petOwnerService.updatePetOwner(id, petOwnerDto));
            apiResponse.setMessage("Successfully updated object");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

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

    @GetMapping("/pets/appointments/{owner_id}")
    public ResponseEntity<ApiResponse> getPetsAppointmentsByOwnerId(
            @PathVariable(value = "owner_id") String ownerId
    ) throws  NoPetRegisterUnderPetOwner, UserNameNotFound {
        logger.info("Received request for fetching pets for owner :", ownerId);
        if (petOwnerService != null && ownerId != null) {
            apiResponse.setBody(petOwnerService.retrievePetsAppointments(ownerId));
            apiResponse.setMessage("successfully retrieve list");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

}
