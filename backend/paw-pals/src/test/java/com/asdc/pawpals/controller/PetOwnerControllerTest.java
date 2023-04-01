package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.InvalidPetOwnerObject;
import com.asdc.pawpals.exception.NoPetRegisterUnderPetOwner;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.PetOwnerService;
import com.asdc.pawpals.service.implementation.PetOwnerImpl;
import com.asdc.pawpals.utils.ApiResponse;
import java.io.IOException;
import java.util.Collections;
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class PetOwnerControllerTest {
  @Autowired
  PetOwnerController petOwnerController;

  PetOwnerImpl petOwnerServiceMock;

  @BeforeEach
  public void setup() {
    petOwnerServiceMock = mock(PetOwnerImpl.class);
    petOwnerController.petOwnerService = petOwnerServiceMock;
  }

  @Test
  public void objectCreated() {
    assertNotNull(petOwnerController);
  }

  @Test
  public void shouldReturnGreeting()
    throws NoPetRegisterUnderPetOwner, UserNameNotFound {
    assertEquals("Hello 1", petOwnerController.getPetsByOwnerId("Hello 1"));
  }

  @Test
  public void shouldRegisterPetOwner() {
    // PetOwnerDto petToRegister = new PetOwnerDto();
    // when(petOwnerServiceMock.registerPetOwner(any(PetOwnerDto.class)));
    //     petToRegister.setUserName("jD");
    // ResponseEntity<String> responese = petOwnerController.registerUser(petToRegister, null);
  }

  public void testUpdatedPetOwner()
    throws InvalidPetOwnerObject, UserNameNotFound, InvalidImage, IOException {
    PetOwnerDto petOwnerDto = new PetOwnerDto();
    petOwnerDto.setAddress("new address");
    MultipartFile image = new MockMultipartFile(
      "image",
      "test-image.jpg",
      "image/jpeg",
      new byte[10]
    );

    String id = "12";
    when(
        petOwnerServiceMock.updatePetOwner(
          eq(id),
          eq(petOwnerDto),
          any(MultipartFile.class)
        )
      )
      .thenReturn(new PetOwnerDto());
    Object requestBody = petOwnerDto;
    ResponseEntity<ApiResponse> response = petOwnerController.updatePetOwner(
      id,
      requestBody,
      image
    );

    verify(petOwnerServiceMock)
      .updatePetOwner(eq(id), eq(petOwnerDto), any(MultipartFile.class));
    ApiResponse apiResponse = response.getBody();
    assertTrue(apiResponse.isSuccess());
    assertFalse(apiResponse.isError());
  }

  @Test
  void testGetPetsByOwnerId()
    throws NoPetRegisterUnderPetOwner, UserNameNotFound {
    // Mock dependencies
    PetOwnerService petOwnerService = mock(PetOwnerService.class);
    ApiResponse expectedResponse = new ApiResponse();
    expectedResponse.setMessage("successfully retrieve list");
    expectedResponse.setSuccess(true);
    expectedResponse.setError(false);
    when(petOwnerService.retrieveAllPets("123"))
      .thenReturn(Collections.emptyList());

    // Invoke method under test

    ResponseEntity<ApiResponse> response = new PetOwnerController()
    .getPetsByOwnerId("123");

    // Verify behavior and assertions
    verify(petOwnerService).retrieveAllPets("123");
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(expectedResponse, response.getBody());
  }
}
