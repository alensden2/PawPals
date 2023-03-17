package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.asdc.pawpals.dto.VetAvailabilityDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.dto.VetScheduleDto;
import com.asdc.pawpals.service.implementation.VetServiceImpl;

@SpringBootTest
public class VetControllerTest {
    @Autowired
    VetController vetController;
    VetServiceImpl vetServiceMock;

    @BeforeEach
    public void setup(){
        vetServiceMock = mock(VetServiceImpl.class);
        vetController.vetService = vetServiceMock;
    }
    
    @Test
    public void objectCreated(){
        assertNotNull(vetController);
    }

    @Test
    public void shouldReturnGreeting(){
        assertEquals("Hello 1", vetController.getVetById(1));
    }

    @Test
    public void shouldRegisterVet(){
        //Arrange
        VetDto vetToRegister = new VetDto();
        when(vetServiceMock.registerVet(any(VetDto.class))).thenReturn(true);
        vetToRegister.setUserName("jDoe");
        //Act
        ResponseEntity<String> response = vetController.registerVet(vetToRegister);
        //Assert
        assertEquals("jDoe", response.getBody());
    }

    @Test
    public void shouldReturnInternalServerError(){
        //Arrange
        VetDto vetToRegister = new VetDto();
        when(vetServiceMock.registerVet(any(VetDto.class))).thenReturn(false);
        vetToRegister.setUserName("jDoe");
        //Act
        ResponseEntity<String> response = vetController.registerVet(vetToRegister);
        //Assert
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    public void shouldReturnBadRequestWhenUserNotFound(){
        //Arrange
        VetDto vetToRegister = new VetDto();
        when(vetServiceMock.registerVet(any(VetDto.class))).thenThrow(new UsernameNotFoundException("Invalid Username"));
        vetToRegister.setUserName("jDoe");
        //Act
        ResponseEntity<String> response = vetController.registerVet(vetToRegister);
        //Assert
        assertEquals("User name provided is invalid", response.getBody());
        assertEquals(400, response.getStatusCode().value()); 
    }

    @Test
    public void shouldReturnBadRequestWhenInvalidInput(){
        //Act
        ResponseEntity<String> response = vetController.registerVet("Invalid Input");
        //Assert
        assertEquals("Invalid input provided", response.getBody());
        assertEquals(400, response.getStatusCode().value()); 
    }

    @Test
    public void shouldReturnVetAvailability(){
        //Arrange
        String userId="jDoe";
        VetAvailabilityDto availability = new VetAvailabilityDto();
        availability.setAvailabilityId(1);
        when(vetServiceMock.getVetAvailabilityOnSpecificDay(anyString(), anyString())).thenReturn(availability);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("date", "13-03-2023");
        //Act
        ResponseEntity<VetAvailabilityDto> actual = vetController.getAvailability(userId, requestBody);

        //Assert
        assertEquals(1, actual.getBody().getAvailabilityId());
    }

    @Test
    public void shouldReturnBadResponseWhenInvalidVetAvailability(){
        //Arrange
        String userId="jDoe";
        //Act
        ResponseEntity<VetAvailabilityDto> actual = vetController.getAvailability(userId, "Invalid RequestBody");

        //Assert
        assertEquals(400, actual.getStatusCode().value()); 
    }

    @Test
    public void shouldReturnBadResponseWhenInvalidUserName(){
        //Arrange
        String userId=null;
        //Act
        ResponseEntity<VetAvailabilityDto> actual = vetController.getAvailability(userId, new HashMap<>());

        //Assert
        assertEquals(400, actual.getStatusCode().value()); 
    }

    @Test
    public void shouldReturnBadResponseWhenUserNameEmpty(){
        //Arrange
        String userId="";
        //Act
        ResponseEntity<VetAvailabilityDto> actual = vetController.getAvailability(userId, new HashMap<>());

        //Assert
        assertEquals(400, actual.getStatusCode().value()); 
    }

    @Test
    public void shouldReturnVetSchedule(){
        //Arrange
        String userId="jDoe";
        VetScheduleDto schedule = new VetScheduleDto();
        schedule.setVetUserId("1");
        schedule.setSlotsBooked(Arrays.asList(Pair.of("10:00","10:30")));
        when(vetServiceMock.getVetScheduleOnSpecificDay(anyString(), anyString())).thenReturn(schedule);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("date", "13-03-2023");
        //Act
        ResponseEntity<VetScheduleDto> actual = vetController.getVetSchedule(userId, requestBody);

        //Assert
        assertEquals("1", actual.getBody().getVetUserId());
        assertNotNull(actual.getBody().getSlotsBooked());
        assertEquals(1,actual.getBody().getSlotsBooked().size());
    }

    @Test
    public void shouldReturnBadResponseWhenInvalidVetScheduleReqBody(){
        //Arrange
        String userId="jDoe";
        //Act
        ResponseEntity<VetScheduleDto> actual = vetController.getVetSchedule(userId, "Invalid RequestBody");

        //Assert
        assertEquals(400, actual.getStatusCode().value()); 
    }

    @Test
    public void shouldReturnBadResponseWhenInvalidUserNameForVetSchedule(){
        //Arrange
        String userId=null;
        //Act
        ResponseEntity<VetScheduleDto> actual = vetController.getVetSchedule(userId, new HashMap<>());

        //Assert
        assertEquals(400, actual.getStatusCode().value()); 
    }

    @Test
    public void shouldReturnBadResponseWhenUserNameEmptyForVetSchedule(){
        //Arrange
        String userId="";
        //Act
        ResponseEntity<VetScheduleDto> actual = vetController.getVetSchedule(userId, new HashMap<>());

        //Assert
        assertEquals(400, actual.getStatusCode().value()); 
    }
}
