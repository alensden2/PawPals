package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.service.AdminService;
import com.asdc.pawpals.utils.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
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
    validVet.setName("Alen John");
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
}
