package com.asdc.pawpals.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.service.AdminService;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.Transformations;
import com.asdc.pawpals.utils.Transformations.MODEL_TO_DTO_CONVERTER;
import java.util.Collections;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {
  @InjectMocks
  private AdminController adminController;

  @Mock
  private AdminService adminService;

  private Vet validVet;
  VetDto expectedVetDto = new VetDto();

  @Before
  public void setUp() {
    validVet = new Vet();
    validVet.setId(1L);
    validVet.setFirstName("Alen John");
  }

  @Test
  public void addVet_ValidRequestBody_ReturnsCreatedStatus() {
    VetDto expectedVetDto = new VetDto();
    when(adminService.addVet(any(Vet.class))).thenReturn(expectedVetDto);

    ResponseEntity<ApiResponse> response = adminController.addVet(validVet);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void addVet_InvalidRequestBody_ReturnsBadRequestStatus() {
    ResponseEntity<ApiResponse> response = adminController.addVet(new Object());

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void addVet_NullRequestBody_ReturnsBadRequestStatus() {
    ResponseEntity<ApiResponse> response = adminController.addVet(null);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void addVet_ValidRequestBody_ReturnsSuccessResponse() {
    VetDto expectedVetDto = new VetDto();

    when(adminService.addVet(any(Vet.class))).thenReturn(expectedVetDto);

    ResponseEntity<ApiResponse> response = adminController.addVet(validVet);

    ApiResponse apiResponse = response.getBody();

    assertEquals(true, apiResponse.isSuccess());
    assertEquals(false, apiResponse.isError());
    assertEquals("successfully inserted object", apiResponse.getMessage());
    assertEquals(Collections.singletonList(validVet), apiResponse.getBody());
  }

  @Test
  public void addVet_InvalidRequestBody_ReturnsErrorResponse() {
    ResponseEntity<ApiResponse> response = adminController.addVet(new Object());

    ApiResponse apiResponse = response.getBody();

    assertEquals(false, apiResponse.isSuccess());
    assertEquals(true, apiResponse.isError());
    assertEquals("Invalid request body", apiResponse.getMessage());
    assertEquals(Collections.emptyList(), apiResponse.getBody());
  }

  @Test
  public void addVet_NullRequestBody_ReturnsErrorResponse() {
    ResponseEntity<ApiResponse> response = adminController.addVet(null);

    ApiResponse apiResponse = response.getBody();

    assertEquals(false, apiResponse.isSuccess());
    assertEquals(true, apiResponse.isError());
    assertEquals("Request body cannot be null", apiResponse.getMessage());
    assertEquals(Collections.emptyList(), apiResponse.getBody());
  }

  @Test
  public void deleteVet_ValidId_ReturnsSuccessResponse() {
    Long validId = 1L;
    VetDto expectedVetDto = new VetDto();
    when(adminService.deleteVet(validId)).thenReturn(expectedVetDto);

    ResponseEntity<ApiResponse> response = adminController.deleteVet(validId);

    ApiResponse apiResponse = response.getBody();

    assertEquals(true, apiResponse.isSuccess());
    assertEquals(false, apiResponse.isError());
    assertEquals("successfully deleted object", apiResponse.getMessage());
    assertEquals(
      Collections.singletonList(expectedVetDto),
      apiResponse.getBody()
    );
  }

  @Test
  public void deleteVet_InvalidId_ReturnsErrorResponse() {
    Long invalidId = null;
    ResponseEntity<ApiResponse> response = adminController.deleteVet(invalidId);

    ApiResponse apiResponse = response.getBody();

    assertEquals(false, apiResponse.isSuccess());
    assertEquals(true, apiResponse.isError());
    assertEquals("Invalid request parameter", apiResponse.getMessage());
    assertEquals(Collections.emptyList(), apiResponse.getBody());
  }

  @Test
  public void deleteVet_IdNotFound_ReturnsErrorResponse() {
    Long nonExistingId = 100L;
    //when(adminService.addVet(nonExistingId)).thenThrow(EntityNotFoundException.class);

    ResponseEntity<ApiResponse> response = adminController.deleteVet(
      nonExistingId
    );

    ApiResponse apiResponse = response.getBody();

    assertEquals(false, apiResponse.isSuccess());
    assertEquals(true, apiResponse.isError());
    assertEquals("Object not found", apiResponse.getMessage());
    assertEquals(Collections.emptyList(), apiResponse.getBody());
  }

  @Test
  public void testDeleteAnimal() {
    Long id = 1L;
    AnimalDto animalDto = null;
    when(adminService.deleteAnimal(id)).thenReturn(animalDto);

    ResponseEntity<ApiResponse> responseEntity = adminController.deleteAnimal(
      id
    );

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertTrue(responseEntity.getBody().isSuccess());
    assertFalse(responseEntity.getBody().isError());
    assertEquals(
      "successfully deleted object",
      responseEntity.getBody().getMessage()
    );
    verify(adminService, times(1)).deleteAnimal(id);
  }

  @Test
  public void testDeleteAnimalWithInvalidId() {
    Long id = 1L;

    ResponseEntity<ApiResponse> responseEntity = adminController.deleteAnimal(
      id
    );

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertFalse(responseEntity.getBody().isSuccess());
    assertTrue(responseEntity.getBody().isError());
    assertNull(responseEntity.getBody().getBody());
    assertEquals("Invalid type of id", responseEntity.getBody().getMessage());
  }

  @Test
  public void addVet_ServiceException_ReturnsErrorResponse() {
    when(adminService.addVet(any(Vet.class)))
      .thenThrow(new ServiceException("Error adding Vet"));

    ResponseEntity<ApiResponse> response = adminController.addVet(validVet);

    ApiResponse apiResponse = response.getBody();

    assertEquals(false, apiResponse.isSuccess());
    assertEquals(true, apiResponse.isError());
    assertEquals("Error adding Vet", apiResponse.getMessage());
    assertEquals(Collections.emptyList(), apiResponse.getBody());
  }

  @Test
  public void deleteVet_ServiceException_ReturnsErrorResponse() {
    Long validId = 1L;
    when(adminService.deleteVet(validId))
      .thenThrow(new ServiceException("Error deleting Vet"));

    ResponseEntity<ApiResponse> response = adminController.deleteVet(validId);

    ApiResponse apiResponse = response.getBody();

    assertEquals(false, apiResponse.isSuccess());
    assertEquals(true, apiResponse.isError());
    assertEquals("Error deleting Vet", apiResponse.getMessage());
    assertEquals(Collections.emptyList(), apiResponse.getBody());
  }

  @Test
  public void deleteAnimal_ServiceException_ReturnsErrorResponse() {
    Long validId = 1L;
    when(adminService.deleteAnimal(validId))
      .thenThrow(new ServiceException("Error deleting Animal"));

    ResponseEntity<ApiResponse> response = adminController.deleteAnimal(
      validId
    );

    ApiResponse apiResponse = response.getBody();

    assertEquals(false, apiResponse.isSuccess());
    assertEquals(true, apiResponse.isError());
    assertEquals("Error deleting Animal", apiResponse.getMessage());
    assertEquals(Collections.emptyList(), apiResponse.getBody());
  }

  @Test
  public void testUpdateAnimalSuccess() throws PetOwnerAlreadyDoesNotExists {
    Long animalId = 1L;
    Animal existingAnimal = new Animal();
    existingAnimal.setId(animalId);
    existingAnimal.setName("Old Name");
    existingAnimal.setAge(5);
    existingAnimal.setType("Old Breed");
    existingAnimal.setGender("male");
    existingAnimal.setOwner(new PetOwner());

    Animal updatedAnimal = new Animal();
    updatedAnimal.setName("New Name");
    updatedAnimal.setAge(10);
    updatedAnimal.setType("New Breed");
    updatedAnimal.setGender("female");

    AnimalDto updatedAnimalDto = Transformations.MODEL_TO_DTO_CONVERTER.animal(
      updatedAnimal
    );
    when(adminService.updateAnimal(animalId, updatedAnimal))
      .thenReturn(updatedAnimalDto);

    ResponseEntity<ApiResponse> apiResponse = adminController.updateAnimal(
      animalId,
      updatedAnimal
    );

    verify(adminService, times(1)).updateAnimal(animalId, updatedAnimal);

    assertNotNull(apiResponse);

    ApiResponse updatedAnimalFromResponse = apiResponse.getBody();
    assertNotNull(updatedAnimalFromResponse);
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
