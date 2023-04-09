package com.asdc.pawpals.handler;

import com.asdc.pawpals.controller.*;
import com.asdc.pawpals.exception.*;
import com.asdc.pawpals.utils.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.InvalidObjectException;

@RestControllerAdvice(
        assignableTypes = {
                UserController.class,
                PetOwnerController.class,
                AnimalController.class,
                AdminController.class,
                VetController.class,
                MedicalRecordController.class,
                AppointmentController.class,
        }
)
public class GlobalExceptionalHandler {
    Logger logger = LogManager.getLogger(GlobalExceptionalHandler.class);

    @Autowired
    ApiResponse apiResponse;

    /**
     * Handles UserNameNotFound exceptions and returns a ResponseEntity with a customized message
     *
     * @param e the UserNameNotFound exception
     * @return a ResponseEntity with ApiResponse body containing an error message and status code NOT_FOUND
     */
    @ExceptionHandler(UserNameNotFound.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForUserNotFound(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("User not found");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     * Handles the InvalidUserDetails exception and returns an appropriate ResponseEntity.
     *
     * @param e the InvalidUserDetails exception object
     * @return a ResponseEntity with an ApiResponse body
     */
    @ExceptionHandler(InvalidUserDetails.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidUser(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("User Details in Invalid");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * Handles the UserAlreadyExist exception and returns a ResponseEntity with an ApiResponse.
     * Sets the status code to 400 (BAD_REQUEST) and sets the body of the ApiResponse to the error message.
     *
     * @param e The exception that was thrown
     * @return A ResponseEntity with an ApiResponse
     */
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForUserAlreadyExist(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("User Already Registered in the System");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    /**
     * Exception handler for the case where the requested operation involves a pet owner that does not exist.
     * Returns an HTTP response with an error status code and a message indicating that the pet owner does not exist.
     *
     * @param e The exception that triggered the error response
     * @return A ResponseEntity containing an ApiResponse with an error message indicating that the pet owner does not exist and a corresponding HTTP status code.
     */
    @ExceptionHandler(PetOwnerAlreadyDoesNotExists.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForPetOwnerDoesNotExists(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("Pet owner does not exist");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    /**
     * Handles the InvalidOwnerID exception and returns a ResponseEntity with an error message.
     * The error message contains the exception message and a custom message stating "Invalid Owner Id".
     *
     * @param e The InvalidOwnerID exception thrown
     * @return A ResponseEntity with ApiResponse object containing an error message and status code 406 (Not Acceptable)
     */
    @ExceptionHandler(InvalidOwnerID.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidOwnerID(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("Invalid Owner Id");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * Exception handler for when there is no pet registered under a given pet owner.
     *
     * @param e The exception being handled.
     * @return A ResponseEntity containing an ApiResponse indicating that no pet is registered under the given pet owner.
     */
    @ExceptionHandler(NoPetRegisterUnderPetOwner.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForNoPetRegisterUnderPetOwner(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("No Pet register...");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     * Exception handler for IOException. Returns a ResponseEntity with an error message and status code.
     *
     * @param e The IOException that occurred
     * @return ResponseEntity with ApiResponse body containing error message and status code
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForIOException(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("IO Exception ");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * Exception handler for handling InvalidImage exception that is thrown when an image file is invalid.
     * Logs the exception message and creates a custom ApiResponse object with an error message and status code.
     * Returns the ApiResponse object as a ResponseEntity object with HTTP status code 406 (NOT_ACCEPTABLE).
     *
     * @param e the InvalidImage exception to be handled
     * @return a ResponseEntity object with ApiResponse as body and HTTP status code 406 (NOT_ACCEPTABLE)
     */
    @ExceptionHandler(InvalidImage.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInValidImage(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("IO Exception ");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * Exception handler method to handle InvalidAnimalId exception
     *
     * @param e the InvalidAnimalId exception
     * @return the ResponseEntity with an error message, status code, and API response object
     */
    @ExceptionHandler(InvalidAnimalId.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidAnimalId(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("Invalid Animal Id entered ");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * Global exception handler for InvalidVetID exception.
     *
     * @param e The InvalidVetID exception that needs to be handled.
     * @return A ResponseEntity containing the ApiResponse with an error status and message.
     */
    @ExceptionHandler(InvalidVetID.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidVetID(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("Invalid Vet Id entered");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * This method handles the exception thrown when an invalid object is received as input.
     * It sets a message to "Invalid object parameters" in the ApiResponse object and returns
     * a ResponseEntity with HTTP status code NOT_ACCEPTABLE.
     *
     * @param e the exception that was thrown
     * @return a ResponseEntity containing an ApiResponse object with the error message and status code
     */
    @ExceptionHandler(InvalidObjectException.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidObjectException(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("Invalid object parameters");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * Handles the exception thrown when an invalid animal object is encountered.
     * Returns a ResponseEntity object containing an ApiResponse object with error status.
     *
     * @param e The exception to be handled.
     * @return ResponseEntity object with ApiResponse object containing error message and status.
     */
    @ExceptionHandler(InvalidAnimalObject.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidAnimalObject(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("Invalid animal object parameters");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * Handles exceptions of type InvalidPetOwnerObject.
     *
     * @param e The exception to be handled.
     * @return A ResponseEntity containing an ApiResponse with an error message and a NOT_ACCEPTABLE status code.
     */
    @ExceptionHandler(InvalidPetOwnerObject.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidPetOwnerObject(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("Invalid pet owner object parameters");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * Exception handler for InvalidAppointmentId exception.
     *
     * @param e InvalidAppointmentId exception to be handled
     * @return ResponseEntity containing ApiResponse with error details
     */

    @ExceptionHandler(InvalidAppointmentId.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidAppointmentId(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("Invalid Appointment Id entered");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }

    /**
     * Handles the exception when no appointment exists for a veterinarian.
     *
     * @param e the NoAppointmentExist exception to handle.
     * @return a ResponseEntity with an ApiResponse containing an error message and status code.
     */
    @ExceptionHandler(NoAppointmentExist.class)
    public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForNoAppointmentExist(
            Exception e
    ) {
        logger.trace(e.getMessage());
        apiResponse.setBody(e.getMessage());
        apiResponse.setMessage("No Appointment exist for this vet");
        apiResponse.setError(true);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);
    }
}
