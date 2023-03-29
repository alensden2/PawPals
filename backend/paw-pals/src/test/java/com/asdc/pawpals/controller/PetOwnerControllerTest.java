package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.NoPetRegisterUnderPetOwner;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.implementation.PetOwnerImpl;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

@SpringBootTest
public class PetOwnerControllerTest {

@Autowired
PetOwnerController petOwnerController;
PetOwnerImpl petOwnerServiceMock;

@BeforeEach
public void setup(){
    petOwnerServiceMock = mock(PetOwnerImpl.class);
    petOwnerController.petOwnerService = petOwnerServiceMock;
}

@Test
public void objectCreated(){
    assertNotNull(petOwnerController);
}
@Test
public void shouldReturnGreeting() throws NoPetRegisterUnderPetOwner, UserNameNotFound{
    assertEquals("Hello 1", petOwnerController.getPetsByOwnerId("Hello 1"));
}

@Test
public void shouldRegisterPetOwner(){
    // PetOwnerDto petToRegister = new PetOwnerDto();
    // when(petOwnerServiceMock.registerPetOwner(any(PetOwnerDto.class)));
    //     petToRegister.setUserName("jD");
    // ResponseEntity<String> responese = petOwnerController.registerUser(petToRegister, null);
}
}
