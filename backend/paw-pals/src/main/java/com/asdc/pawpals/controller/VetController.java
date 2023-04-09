package com.asdc.pawpals.controller;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.Map;

/**
 * This class is a RestController responsible for handling requests related to Vet entity.
 * <p>
 * It maps the incoming HTTP requests to appropriate methods of VetService to perform the required operations.
 * <p>
 * The base path for the requests handled by this controller is "/auth/vet".
 */
@RestController()
@RequestMapping("/auth/vet")
public class VetController {
  private static final Logger logger = LogManager.getLogger(
    VetController.class
  );

  @Autowired
  VetService vetService;

  @Autowired
  ApiResponse apiResponse;

  /**
   * Retrieves the Vet object by user ID.
   *
   * @param id the ID of the user
   * @return the ResponseEntity with the ApiResponse as the response body
   * @throws UserNameNotFound if the user ID is not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getVetById(@PathVariable String id)
    throws UserNameNotFound {
    logger.info("Get vet By user Id", id);
    if (vetService != null && id != null) {
      apiResponse.setBody(vetService.getVetByUserId(id));
      apiResponse.setMessage("successfully retrieve list");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  /**
   * This method is used to register a new Vet. The method consumes multipart form data and requires two parts in the request:
   * The first part contains the Vet details in the form of a map.
   * The second part is a file containing the photo of the Vet's clinic.
   * If the input provided is invalid, the method returns a bad request response.
   * If the provided username is invalid, the method returns a bad request response.
   * If there is an error while registering the Vet, the method returns an internal server error response.
   *
   * @param requestBody a map containing the Vet details in the form of a map
   * @param clinicPhoto a multipart file containing the photo of the Vet's clinic
   * @return a ResponseEntity object containing the username of the registered Vet if registration was successful,
   * otherwise an error message with a corresponding HTTP status code
   * @throws IOException  if there was an error in reading the clinic photo
   * @throws InvalidImage if the clinic photo is invalid
   */
  @PostMapping(
    value = "/register",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public ResponseEntity<String> registerVet(
    @RequestPart("vet") Map<String, Object> requestBody,
    @RequestPart("clinicPhoto") MultipartFile clinicPhoto
  )
    throws IOException, InvalidImage {
    Boolean vetRegistered = false;
    ResponseEntity<String> response = null;
    try {
      VetDto vet = null;
      if (CommonUtils.isStrictTypeOf(requestBody, VetDto.class)) {
        vet =
          ObjectMapperWrapper
            .getInstance()
            .convertValue(requestBody, VetDto.class);
        vet.setClinicUrl(CommonUtils.getBytes(clinicPhoto));
        vetRegistered = vetService.registerVet(vet);
        if (vetRegistered) {
          response = ResponseEntity.ok(vet.getUsername());
        } else {
          ResponseEntity
            .internalServerError()
            .body("There was some error, please try again");
        }
        // old code : response = vetRegistered ? ResponseEntity.ok(vet.getUsername()) : ResponseEntity.internalServerError().body("There was some error, please try again");
      } else {
        response = ResponseEntity.badRequest().body("Invalid input provided");
      }
    } catch (UsernameNotFoundException e) {
      response =
        ResponseEntity.badRequest().body("User name provided is invalid");
    }
    return response;
  }

  /**
   * This method is used to retrieve the availability of a vet on a specific day.
   * It takes a user ID and a request body as parameters and returns a ResponseEntity object containing
   * a VetAvailabilityDto object.
   *
   * @param userId      The user ID of the vet whose availability needs to be retrieved.
   * @param requestBody The request body containing the date for which the availability needs to be retrieved.
   * @return A ResponseEntity object containing a VetAvailabilityDto object.
   */
  @PostMapping("/availability/{id}")
  public ResponseEntity<VetAvailabilityDto> getAvailability(
    @PathVariable(value = "id") String userId,
    @RequestBody Object requestBody
  ) {
    VetAvailabilityDto availability = null;
    ResponseEntity<VetAvailabilityDto> response = null;

    boolean isRequestBodyValid = CommonUtils.isStrictTypeOf(
      requestBody,
      new TypeReference<Map<String, String>>() {}
    );
    boolean isUserIdValid = userId != null && !userId.isEmpty();

    /** 
     * Old Code - 
     * 
     * if (
      CommonUtils.isStrictTypeOf(
        requestBody,
        new TypeReference<Map<String, String>>() {}
      ) &&
      userId != null &&
      !userId.isEmpty()
    )
     */
    if (isRequestBodyValid && isUserIdValid) {
      Map<String, String> request = ObjectMapperWrapper
        .getInstance()
        .convertValue(requestBody, new TypeReference<Map<String, String>>() {});
      String date = request.get("date");
      availability = vetService.getVetAvailabilityOnSpecificDay(userId, date);
      response = ResponseEntity.ok(availability);
    } else {
      response = ResponseEntity.badRequest().build();
    }
    return response;
  }

  /**
   * POST endpoint to add vet availability for specific dates
   *
   * @param requestBody Object containing a list of VetAvailabilityDto objects to be added
   * @return ResponseEntity with ApiResponse body containing a message and success status
   * @throws InvalidObjectException    if the requestBody is not a list of VetAvailabilityDto objects
   * @throws UsernameNotFoundException if the username of the vet is not found
   * @throws UserNameNotFound          if the user name is not found
   */
  @PostMapping("/availability/post")
  public ResponseEntity<ApiResponse> postAvailability(
    @RequestBody Object requestBody
  )
    throws InvalidObjectException, UsernameNotFoundException, UserNameNotFound {
    logger.debug("Posting availability");
    if (
      CommonUtils.isStrictTypeOf(
        requestBody,
        new TypeReference<List<VetAvailabilityDto>>() {}
      )
    ) {
      List<VetAvailabilityDto> vetAvailability = ObjectMapperWrapper
        .getInstance()
        .convertValue(
          requestBody,
          new TypeReference<List<VetAvailabilityDto>>() {}
        );
      vetAvailability = vetService.postVetAvailability(vetAvailability);
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
      apiResponse.setBody(vetAvailability);
      apiResponse.setMessage("Vet availability added successfully");
    } else {
      throw new InvalidObjectException("Invalid availability provided");
    }
    return ResponseEntity.ok(apiResponse);
  }

  /**
   * This method retrieves the schedule of a Vet on a specific day
   *
   * @param userId      the user id of the vet
   * @param requestBody the request body containing the date of the schedule
   * @return ResponseEntity<VetScheduleDto> containing the schedule of the vet on the specified day
   */
  @PostMapping("/schedule/{id}")
  public ResponseEntity<VetScheduleDto> getVetSchedule(
    @PathVariable(value = "id") String userId,
    @RequestBody Object requestBody
  ) {
    VetScheduleDto vetScheduleDto = null;
    ResponseEntity<VetScheduleDto> response = null;
    /**
     * old code 
     * if (
      CommonUtils.isStrictTypeOf(
        requestBody,
        new TypeReference<Map<String, String>>() {}
      ) &&
      userId != null &&
      !userId.isEmpty()
    )
     */

    boolean isRequestBodyValid = CommonUtils.isStrictTypeOf(
      requestBody,
      new TypeReference<Map<String, String>>() {}
    );
    boolean isUserIdValid = userId != null && !userId.isEmpty();

    if (isRequestBodyValid && isUserIdValid) {
      Map<String, String> request = ObjectMapperWrapper
        .getInstance()
        .convertValue(requestBody, new TypeReference<Map<String, String>>() {});
      String date = request.get("date");
      vetScheduleDto = vetService.getVetScheduleOnSpecificDay(userId, date);
      response = ResponseEntity.ok(vetScheduleDto);
    } else {
      response = ResponseEntity.badRequest().build();
    }
    return response;
  }

  /**
   * Update the status of a particular appointment by ID
   *
   * @param requestBody Object representing the appointment to be updated
   * @param id          Integer representing the ID of the appointment to be updated
   * @return ResponseEntity with ApiResponse containing the updated appointment, success message, and success status code
   * @throws InvalidAppointmentId if the provided ID is not valid
   */
  @PutMapping({ "status/{id}" })
  public ResponseEntity<ApiResponse> changeStatus(
    @RequestBody Object requestBody,
    @PathVariable Integer id
  )
    throws InvalidAppointmentId {
    logger.info("Received request as :", requestBody.toString());
    AppointmentDto appointmentDto = null;
    if (CommonUtils.isStrictTypeOf(requestBody, AppointmentDto.class)) {
      appointmentDto =
        ObjectMapperWrapper
          .getInstance()
          .convertValue(requestBody, AppointmentDto.class);
      apiResponse.setBody(vetService.changeStatus(appointmentDto, id));
      apiResponse.setMessage("Successful change status to approve");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  /**
   * Retrieves all appointments for a given vet ID.
   *
   * @param vetId the ID of the vet for whom to retrieve the appointments
   * @return a ResponseEntity object containing an ApiResponse with a success status, body, and message if the request is successful
   * @throws UserNameNotFound   if the given vet ID is not found in the system
   * @throws NoAppointmentExist if there are no appointments for the given vet ID
   */
  @GetMapping("/appointments/{vet_id}")
  public ResponseEntity<ApiResponse> getPetsByOwnerId(
    @PathVariable(value = "vet_id") String vetId
  )
    throws UserNameNotFound, NoAppointmentExist {
    logger.info("Get All appointments for vet:", vetId);
    if (vetService != null && vetId != null) {
      apiResponse.setBody(vetService.retrieveAllPets(vetId));
      apiResponse.setMessage("successfully retrieve list");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  /**
   * Retrieve all pending status vets.
   *
   * @return A ResponseEntity containing ApiResponse object with a list of pending status vets and success status,
   * or an error message if an exception occurs.
   */
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

  /**
   * Handles HTTP PUT requests for updating a veterinarian's details and profile image.
   *
   * @param requestBody the request body containing the updated veterinarian details in JSON format
   * @param id          the ID of the veterinarian to update
   * @param image       the multipart file containing the new profile image
   * @return a ResponseEntity containing the updated veterinarian details and an ApiResponse message
   * @throws UserNameNotFound if the specified veterinarian ID is not found
   * @throws InvalidImage     if the uploaded image is invalid
   * @throws IOException      if there is an I/O error while processing the uploaded image
   */
  @PutMapping({ "/{id}" })
  public ResponseEntity<ApiResponse> updateVet(
    @RequestBody Object requestBody,
    @PathVariable String id,
    @RequestPart("image") MultipartFile image
  )
    throws UserNameNotFound, InvalidImage, IOException {
    logger.info("Received request as :", requestBody.toString());
    VetDto vetDto = null;
    if (CommonUtils.isStrictTypeOf(requestBody, VetDto.class)) {
      vetDto =
        ObjectMapperWrapper
          .getInstance()
          .convertValue(requestBody, VetDto.class);
      apiResponse.setBody(vetService.updateVet(vetDto, id, image));
      apiResponse.setMessage("Successful update the data");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  /**
   * Updates the profile status of a veterinarian by the given ID.
   *
   * @param requestBody the request body of the update request.
   * @param id          the ID of the veterinarian whose profile status needs to be updated.
   * @return a ResponseEntity containing an ApiResponse with the updated data.
   * @throws UserNameNotFound if the veterinarian with the given ID is not found.
   * @throws IOException      if there is an issue with reading/writing the image file.
   */
  @PutMapping({ "profile_status/{id}" })
  public ResponseEntity<ApiResponse> updateVetStatus(
    @RequestBody Object requestBody,
    @PathVariable String id
  )
    throws UserNameNotFound, IOException {
    logger.info("Updating Vet Profile Status:", requestBody.toString());
    VetDto vetDto = null;
    if (CommonUtils.isStrictTypeOf(id, String.class)) {
      vetDto =
        ObjectMapperWrapper
          .getInstance()
          .convertValue(requestBody, VetDto.class);
      apiResponse.setBody(vetService.updateProfileStatus(vetDto, id));
      apiResponse.setMessage("Successful update profile status for Vet ");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }
}
