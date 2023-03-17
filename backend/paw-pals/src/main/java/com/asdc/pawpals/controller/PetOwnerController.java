package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AppointmentDto;
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



}
