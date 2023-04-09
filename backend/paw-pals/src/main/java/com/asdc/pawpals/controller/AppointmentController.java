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

/**
 * This class represents the AppointmentController which handles all HTTP requests related to appointments.
 * <p>
 * It is annotated with @RestController to indicate that it is a RESTful web service controller.
 * <p>
 * The base path for all HTTP requests is "/auth/appointment".
 */
@RestController()
@RequestMapping("/auth/appointment")
public class AppointmentController {
    private static final Logger logger = LogManager.getLogger(AppointmentController.class);

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ApiResponse apiResponse;

    /**
     * This method updates the status of an appointment with the given appointment ID and action.
     * The updated status is returned in the response body.
     *
     * @param appointmentId the ID of the appointment to update
     * @param action        the action to perform on the appointment status
     * @return a ResponseEntity object containing the updated appointment status in the response body and status code OK
     * @throws InvalidObjectException if the input appointment ID or action is invalid
     * @throws InvalidAppointmentId   if the input appointment ID is invalid
     */
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

    /**
     * Handles a POST request to book an appointment.
     *
     * @param requestBody The request body containing the appointment details.
     * @return A ResponseEntity object with a ApiResponse object in its body and the HTTP status code 201 (CREATED).
     * @throws InvalidAnimalId        If the animal ID provided in the request body is invalid.
     * @throws InvalidObjectException If the object provided in the request body is invalid.
     * @throws InvalidVetID           If the vet ID provided in the request body is invalid.
     * @throws UserNameNotFound       If the username provided in the request body is not found in the system.
     */
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
