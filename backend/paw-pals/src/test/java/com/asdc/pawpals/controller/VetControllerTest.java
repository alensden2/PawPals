package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.asdc.pawpals.dto.VetDto;
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
}
