package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.service.AdminService;
import com.asdc.pawpals.service.implementation.AdminServiceImpl;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.Transformations;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.Test;
@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {
  @InjectMocks
  private AdminController adminController;

  @Mock
  private AdminService adminService;

  @Test
public void testGetAllAnimalRecords() {
    List<AnimalDto> animalList = new ArrayList<>();
    AnimalDto animal2 = new AnimalDto();
    animal2.setId(2L);
    animal2.setName("Fluffy");
    animal2.setType("Cat");
    animal2.setAge(2);
    animal2.setGender("Female");
    animal2.setOwnerId("5678");
    animal2.setPhotoUrl(new Byte[] { 6, 7, 8, 9, 10 });

    AnimalDto animal1 = new AnimalDto();
    animal1.setId(1L);
    animal1.setName("Max");
    animal1.setType("Dog");
    animal1.setAge(3);
    animal1.setGender("Male");
    animal1.setOwnerId("1234");
    animal1.setPhotoUrl(new Byte[] { 1, 2, 3, 4, 5 });

    animalList.add(animal1);
    animalList.add(animal2);

    when(adminService.getAllAnimalRecords()).thenReturn(animalList);

    ResponseEntity<List<AnimalDto>> responseEntity = adminController.getAllAnimalRecords();
    List<AnimalDto> animalDetails = responseEntity.getBody();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(animalDetails);
    assertEquals(2, animalDetails.size());
    assertEquals("Max", animalDetails.get(0).getName());
    assertEquals("Dog", animalDetails.get(0).getType());
    assertEquals("Fluffy", animalDetails.get(1).getName());
    assertEquals("Cat", animalDetails.get(1).getType());
}

  private Vet validVet;
  VetDto expectedVetDto = new VetDto();

  @Before
  public void setUp() {
    validVet = new Vet();
    validVet.setId(1L);
    validVet.setFirstName("John Doe");
  }

  

    @Test
  public void testUpdateAnimalWrongRequestBodyType()
    throws PetOwnerAlreadyDoesNotExists {
    Long animalId = 1L;

    Object requestBody = new Object();

    ResponseEntity<ApiResponse> apiResponse = adminController.updateAnimal(
      animalId,
      requestBody
    );

    verify(adminService, never()).updateAnimal(any(), any());

    assertNotNull(apiResponse);
  }
}
