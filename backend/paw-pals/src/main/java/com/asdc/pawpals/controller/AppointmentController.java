package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidAppointmentId;
import com.asdc.pawpals.exception.InvalidVetID;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.AppointmentService;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;

@RestController()
@RequestMapping("/unauth/appointment")
public class AppointmentController {
    private static final Logger logger = LogManager.getLogger(AppointmentController.class);

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ApiResponse apiResponse;

    @GetMapping("/update-status/{appointment_id}")
    public ResponseEntity<ApiResponse> setStatus(
            @PathVariable(value = "appointment_id") Integer appointmentId,
            @RequestParam(value = "action") String action
    ) throws InvalidObjectException, InvalidAppointmentId {
        logger.info("set appointment status ", appointmentId, action);
        Boolean statusUpdated = appointmentService.updateAppointmentStatus(appointmentId, action);
        apiResponse.setBody(statusUpdated);
        apiResponse.setError(false);
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Status updated successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping({"/book-appointment"})
    public ResponseEntity<ApiResponse> bookAppointment(@RequestBody Object requestBody) throws InvalidAnimalId, InvalidObjectException, InvalidVetID, UserNameNotFound {
        logger.info("Received request as :", requestBody);
        AppointmentDto appointmentDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, AppointmentDto.class)) {
            appointmentDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, AppointmentDto.class);
            apiResponse.setBody(appointmentService.bookAppointment(appointmentDto));
            apiResponse.setMessage("Successfully created Appointment");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


}
