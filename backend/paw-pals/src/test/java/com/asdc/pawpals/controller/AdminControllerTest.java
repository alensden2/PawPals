package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.service.implementation.AdminReadServiceImpl;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

@SpringBootTest
public class AdminControllerTest {
  @Autowired
  AdminController adminController;

  AdminReadServiceImpl adminReadServiceMock;

  @BeforeEach
  public void setup() {
    adminReadServiceMock = mock(AdminReadServiceImpl.class);
    adminController.adminReadService = adminReadServiceMock;
  }

  @Test
  public void objectCreated() {
    assertNotNull(adminController);
  }

  @Test
  public void TestallAnimalRecords(){
    assertEquals("hello 1", adminController.getAllAnimalRecords());
  }

  @Test 
  public void TestAddVet(){
    Vet addVet = new Vet();
    when(adminReadServiceMock.addVet(any(Pet.class))).thenReturn(true);
    addVet.setVetUserId("john");
    ResponseEntity<String> response = adminController.addVet(addVet);

    assertEquals("Hello 1", response.getBody());
  }
}
