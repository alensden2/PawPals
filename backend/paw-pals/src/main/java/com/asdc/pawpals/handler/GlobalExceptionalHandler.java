package com.asdc.pawpals.handler;

import com.asdc.pawpals.controller.AnimalController;
import com.asdc.pawpals.controller.PetOwnerController;
import com.asdc.pawpals.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.asdc.pawpals.controller.UserController;
import com.asdc.pawpals.utils.ApiResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InvalidObjectException;

@RestControllerAdvice(assignableTypes = { UserController.class, PetOwnerController.class, AnimalController.class})
public class GlobalExceptionalHandler {
	
Logger logger=LogManager.getLogger(GlobalExceptionalHandler.class);

@Autowired
ApiResponse apiResponse;

@ExceptionHandler(UserNameNotFound.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForUserNotFound(Exception e) {
        
        logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("Search Unsuccessfully");
		apiResponse.setError(true);
        apiResponse.setSuccess(false);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);

	}

	@ExceptionHandler(InvalidUserDetails.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidUser(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("User Details in Invalid");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

	}

	@ExceptionHandler(UserAlreadyExist.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForUserAlreadyExist(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("User Already Registered in the System");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);

	}

	@ExceptionHandler(InvalidOwnerID.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidOwnerID(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("Invalid Owner Id");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

	}

	@ExceptionHandler(NoPetRegisterUnderPetOwner.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForNoPetRegisterUnderPetOwner(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("No Pet register...");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);

	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForIOException(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("IO Exception ");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

	}

	@ExceptionHandler(InvalidImage.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInValidImage(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("IO Exception ");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

	}

	@ExceptionHandler(InvalidAnimalId.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidAnimalId(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("Invalid Animal Id entered ");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

	}

	@ExceptionHandler(InvalidVetID.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidVetID(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("Invalid Vet Id entered");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

	}

	@ExceptionHandler(InvalidObjectException.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidObjectException(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("Invalid object parameters");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

	}

	@ExceptionHandler(InvalidAnimalObject.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForInvalidAnimalObject(Exception e) {

		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("Invalid animal object parameters");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiResponse);

	}
}

