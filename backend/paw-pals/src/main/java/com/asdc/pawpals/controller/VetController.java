package com.asdc.pawpals.controller;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.dto.VetAvailabilityDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.dto.VetScheduleDto;
import com.asdc.pawpals.exception.InvalidAppointmentId;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.NoAppointmentExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.VetService;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import com.fasterxml.jackson.core.type.TypeReference;

@RestController()
 
@RequestMapping("/auth/vet")
public class VetController {

    private static final Logger logger = LogManager.getLogger(VetController.class);

    @Autowired
    VetService vetService;

    @Autowired
    ApiResponse apiResponse;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getVetById(@PathVariable String id) throws UserNameNotFound {
        logger.info("Get vet By user Id", id);
        if (vetService != null && id != null) {
            apiResponse.setBody(vetService.getVetByUserId(id));
            apiResponse.setMessage("successfully retrieve list");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registerVet(@RequestPart("vet") Map<String, Object> requestBody, @RequestPart("clinicPhoto") MultipartFile clinicPhoto) throws IOException, InvalidImage {
        Boolean vetRegistered = false;
        ResponseEntity<String> response = null;
        try {
            VetDto vet = null;
            if (CommonUtils.isStrictTypeOf(requestBody, VetDto.class)) {
                vet = ObjectMapperWrapper.getInstance().convertValue(requestBody, VetDto.class);
                vet.setClinicUrl(CommonUtils.getBytes(clinicPhoto));
                vetRegistered = vetService.registerVet(vet);
                response = vetRegistered ? ResponseEntity.ok(vet.getUsername()) : ResponseEntity.internalServerError().body("There was some error, please try again");
            } else {
                response = ResponseEntity.badRequest().body("Invalid input provided");
            }
        } catch (UsernameNotFoundException e) {
            response = ResponseEntity.badRequest().body("User name provided is invalid");
        }
        return response;
    }

    /**
     * /availability
     * request body:{
     * "date":"<value>"
     * }
     */
    @PostMapping("/availability/{id}")
    public ResponseEntity<VetAvailabilityDto> getAvailability(@PathVariable(value = "id") String userId, @RequestBody Object requestBody) {
        VetAvailabilityDto availability = null;
        ResponseEntity<VetAvailabilityDto> response = null;
        if (CommonUtils.isStrictTypeOf(requestBody, new TypeReference<Map<String, String>>() {
        }) &&
                userId != null && !userId.isEmpty()) {
            Map<String, String> request = ObjectMapperWrapper.getInstance().convertValue(requestBody, new TypeReference<Map<String, String>>() {
            });
            String date = request.get("date");
            availability = vetService.getVetAvailabilityOnSpecificDay(userId, date);
            response = ResponseEntity.ok(availability);
        } else {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }


    @PostMapping("/availability/post")
    public ResponseEntity<ApiResponse> postAvailability(@RequestBody Object requestBody) throws InvalidObjectException, UsernameNotFoundException, UserNameNotFound{
        logger.debug("Posting availability");
        if(CommonUtils.isStrictTypeOf(requestBody, new TypeReference<List<VetAvailabilityDto>>(){})){
            List<VetAvailabilityDto> vetAvailability = ObjectMapperWrapper.getInstance().convertValue(requestBody, new TypeReference<List<VetAvailabilityDto>>(){});
            vetAvailability = vetService.postVetAvailability(vetAvailability);
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
            apiResponse.setBody(vetAvailability);
            apiResponse.setMessage("Vet availability added successfully");
        }
        else{
            throw new InvalidObjectException("Invalid availability provided");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/schedule/{id}")
    public ResponseEntity<VetScheduleDto> getVetSchedule(@PathVariable(value = "id") String userId, @RequestBody Object requestBody) {
        VetScheduleDto vetScheduleDto = null;
        ResponseEntity<VetScheduleDto> response = null;
        if (CommonUtils.isStrictTypeOf(requestBody, new TypeReference<Map<String, String>>() {
        }) &&
                userId != null && !userId.isEmpty()) {
            Map<String, String> request = ObjectMapperWrapper.getInstance().convertValue(requestBody, new TypeReference<Map<String, String>>() {
            });
            String date = request.get("date");
            vetScheduleDto = vetService.getVetScheduleOnSpecificDay(userId, date);
            response = ResponseEntity.ok(vetScheduleDto);
        } else {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @PutMapping({"status/{id}"})
    public ResponseEntity<ApiResponse> changeStatus(@RequestBody Object requestBody, @PathVariable Integer id) throws InvalidAppointmentId {
        logger.info("Received request as :", requestBody.toString());
        AppointmentDto appointmentDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, AppointmentDto.class)) {
            appointmentDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, AppointmentDto.class);
            apiResponse.setBody(vetService.changeStatus(appointmentDto, id));
            apiResponse.setMessage("Successful change status to approve");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/appointments/{vet_id}")
    public ResponseEntity<ApiResponse> getPetsByOwnerId(
            @PathVariable(value = "vet_id") String vetId
    ) throws UserNameNotFound, NoAppointmentExist {
        logger.info("Get All appointments for vet:", vetId);
        if (vetService != null && vetId != null) {
            apiResponse.setBody(vetService.retrieveAllPets(vetId));
            apiResponse.setMessage("successfully retrieve list");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("pending/vets")
    public ResponseEntity<ApiResponse> getVetsByPendingStatus() {
        logger.info("Get All pending status vets:");
        if (vetService != null) {
            apiResponse.setBody(vetService.retrieveAllVets());
            apiResponse.setMessage("successfully retrieve list");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<ApiResponse> updateVet(@RequestBody Object requestBody, @PathVariable String id, @RequestPart("image") MultipartFile image) throws UserNameNotFound, InvalidImage, IOException {
        logger.info("Received request as :", requestBody.toString());
        VetDto vetDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, VetDto.class)) {
            vetDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, VetDto.class);
            apiResponse.setBody(vetService.updateVet(vetDto, id, image));
            apiResponse.setMessage("Successful update the data");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping({"profile_status/{id}"})
    public ResponseEntity<ApiResponse> updateVetStatus(@RequestBody Object requestBody, @PathVariable String id) throws UserNameNotFound, IOException {
        logger.info("Updating Vet Profile Status:", requestBody.toString());
        VetDto vetDto = null;
        if (CommonUtils.isStrictTypeOf(id, String.class)) {
            vetDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, VetDto.class);
            apiResponse.setBody(vetService.updateProfileStatus(vetDto, id));
            apiResponse.setMessage("Successful update profile status for Vet ");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

}
