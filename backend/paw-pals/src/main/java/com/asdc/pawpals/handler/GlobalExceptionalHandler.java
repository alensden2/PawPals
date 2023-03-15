package com.asdc.pawpals.handler;

import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.exception.UserAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.asdc.pawpals.controller.AdminController;
import com.asdc.pawpals.controller.UserController;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.utils.ApiResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestControllerAdvice(assignableTypes = { UserController.class,  AdminController.class})
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

    @ExceptionHandler(PetOwnerAlreadyDoesNotExists.class)
	public ResponseEntity<ApiResponse> GlobalUserExceptionHandlerForPetOwnerDoesNotExists(Exception e){
		logger.trace(e.getMessage());
		apiResponse.setBody(e.getMessage());
		apiResponse.setMessage("Pet owner does not exist");
		apiResponse.setError(true);
		apiResponse.setSuccess(false);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
	}

}

