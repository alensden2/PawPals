package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidAppointmentId;
import com.asdc.pawpals.exception.InvalidVetID;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.implementation.AppointmentServiceImpl;
import com.asdc.pawpals.utils.ApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.io.InvalidObjectException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentControllerTest {

    @Mock
    AppointmentServiceImpl appointmentServiceMock;

    @InjectMocks
    AppointmentController appointmentController;

    @Mock
    ApiResponse apiResponseMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void objectCreated() {
        assertNotNull(appointmentServiceMock);
        assertNotNull(appointmentController);
        assertNotNull(apiResponseMock);
    }

    @Test
    public void setStatus() throws InvalidObjectException, InvalidAppointmentId {
        // Arrange
        Integer appointmentId = 1;
        String action = "pending";
        Boolean status = true;

        // Act
        when(appointmentServiceMock.updateAppointmentStatus(anyInt(), anyString())).thenReturn(status);
        when(apiResponseMock.getBody()).thenReturn(status);
        when(apiResponseMock.isSuccess()).thenReturn(true);
        when(apiResponseMock.isError()).thenReturn(false);

        ResponseEntity<ApiResponse> response = appointmentController.setStatus(appointmentId, action);
        ApiResponse apiResponse = response.getBody();

        //Assert
        assertNotNull(response.getBody().getBody());
        assertEquals(true, response.getBody().getBody());
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());
    }

    @Test
    public void bookAppointment() throws InvalidAnimalId, UserNameNotFound, InvalidObjectException, InvalidVetID {
        // Arrange
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setStatus("Pending");
        appointmentDto.setId(1);
        appointmentDto.setDate("02-10-2023");
        appointmentDto.setEndTime("10:20");
        appointmentDto.setStartTime("07:20");
        appointmentDto.setAnimalId(1l);
        appointmentDto.setVetUserId("Vet1");

        //Act
        when(appointmentServiceMock.bookAppointment(any(AppointmentDto.class))).thenReturn(appointmentDto);
        when(apiResponseMock.getBody()).thenReturn(appointmentDto);
        when(apiResponseMock.isSuccess()).thenReturn(true);
        when(apiResponseMock.isError()).thenReturn(false);


        ResponseEntity<ApiResponse> response = appointmentController.bookAppointment(appointmentDto);
        ApiResponse apiResponse = response.getBody();

        // Assert
        assertNotNull(response.getBody().getBody());
        assertEquals(appointmentDto, response.getBody().getBody());
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());

    }


}

