package com.asdc.pawpals.handler;

import com.asdc.pawpals.exception.*;
import com.asdc.pawpals.utils.ApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GlobalExceptionalHandlerTest {

    @Mock
    private ApiResponse apiResponseMock;

    @InjectMocks
    private GlobalExceptionalHandler globalExceptionHandler;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGlobalUserExceptionHandlerForUserNotFound() {
        Exception e = new UserNameNotFound("User not found");
        when(apiResponseMock.getMessage()).thenReturn("User not found");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.GlobalUserExceptionHandlerForUserNotFound(e);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("User not found", responseEntity.getBody().getMessage());
        assertEquals(false, responseEntity.getBody().isSuccess());
        assertEquals(null, responseEntity.getBody().getBody());
    }

    @Test
    public void testGlobalUserExceptionHandlerForInvalidUser() {
        Exception e = new InvalidUserDetails("User Details in Invalid");
        when(apiResponseMock.getMessage()).thenReturn("User Details in Invalid");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.GlobalUserExceptionHandlerForInvalidUser(e);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
        assertEquals("User Details in Invalid", responseEntity.getBody().getMessage());
        assertEquals(false, responseEntity.getBody().isSuccess());
        assertEquals(null, responseEntity.getBody().getBody());
    }

    @Test
    public void testGlobalUserExceptionHandlerForUserAlreadyExist() {
        Exception e = new UserAlreadyExist("User Already Registered in the System");
        when(apiResponseMock.getMessage()).thenReturn("User Already Registered in the System");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.GlobalUserExceptionHandlerForUserAlreadyExist(e);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User Already Registered in the System", responseEntity.getBody().getMessage());
        assertEquals(false, responseEntity.getBody().isSuccess());
        assertEquals(null, responseEntity.getBody().getBody());
    }

    @Test
    public void testGlobalUserExceptionHandlerForPetOwnerDoesNotExists() {
        Exception e = new PetOwnerAlreadyDoesNotExists("Pet owner does not exist");
        when(apiResponseMock.getMessage()).thenReturn("Pet owner does not exist");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.GlobalUserExceptionHandlerForPetOwnerDoesNotExists(e);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Pet owner does not exist", responseEntity.getBody().getMessage());
        assertEquals(false, responseEntity.getBody().isSuccess());
        assertEquals(null, responseEntity.getBody().getBody());
    }

    @Test
    public void testGlobalUserExceptionHandlerForInvalidOwnerID() {
        Exception e = new InvalidOwnerID("Invalid Owner Id");
        when(apiResponseMock.getMessage()).thenReturn("Invalid Owner Id");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.GlobalUserExceptionHandlerForInvalidOwnerID(e);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
        assertEquals("Invalid Owner Id", responseEntity.getBody().getMessage());
        assertEquals(false, responseEntity.getBody().isSuccess());
        assertEquals(null, responseEntity.getBody().getBody());
    }

    @Test
    public void testGlobalUserExceptionHandlerForNoPetRegisterUnderPetOwner() {
        Exception e = new NoPetRegisterUnderPetOwner("No Pet register...");
        when(apiResponseMock.getMessage()).thenReturn("No Pet register...");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.GlobalUserExceptionHandlerForNoPetRegisterUnderPetOwner(e);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No Pet register...", responseEntity.getBody().getMessage());
        assertEquals(false, responseEntity.getBody().isSuccess());
        assertEquals(null, responseEntity.getBody().getBody());
    }

    @Test
    public void testGlobalUserExceptionHandlerForIOException() {
        Exception e = new IOException("IO Exception ");
        when(apiResponseMock.getMessage()).thenReturn("IO Exception ");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.GlobalUserExceptionHandlerForIOException(e);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
        assertEquals("IO Exception ", responseEntity.getBody().getMessage());
        assertEquals(false, responseEntity.getBody().isSuccess());
        assertEquals(null, responseEntity.getBody().getBody());
    }

    @Test
    public void testGlobalUserExceptionHandlerForInValidImage() {
        Exception e = new InvalidImage("Invalid Image");
        when(apiResponseMock.getMessage()).thenReturn("Invalid Image");

        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler.GlobalUserExceptionHandlerForInValidImage(e);

        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
        assertEquals("Invalid Image", responseEntity.getBody().getMessage());
        assertEquals(false, responseEntity.getBody().isSuccess());
        assertEquals(null, responseEntity.getBody().getBody());
    }
}
