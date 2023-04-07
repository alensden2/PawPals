package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.service.implementation.PetOwnerImpl;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class PetOwnerControllerTest {
  @Mock
  PetOwnerImpl petOwnerServiceMock;

  @InjectMocks
  PetOwnerController petOwnerController;

  @Mock
  ApiResponse apiResponseMock;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void objectCreated() {
    assertNotNull(petOwnerServiceMock);
    assertNotNull(petOwnerController);
    assertNotNull(apiResponseMock);
  }

  @Test
  public void testRegisterUserForValidInput() throws Exception {
    // Arrange
    PetOwnerDto petOwnerDto = new PetOwnerDto();
    petOwnerDto.setFirstName("John");
    petOwnerDto.setLastName("Doe");
    petOwnerDto.setEmail("john.doe@example.com");
    petOwnerDto.setPassword("password");
    petOwnerDto.setPhoneNo("1234567890");
    petOwnerDto.setAddress("1223 South St");
    petOwnerDto.setPets(null);
    MockMultipartFile image = new MockMultipartFile(
      "image.jpg",
      "image.jpg",
      "image/jpeg",
      new byte[] { 12 }
    );
    Byte[] result = CommonUtils.getBytes(image);
    try {
      petOwnerDto.setPhotoUrl(result);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    when(petOwnerServiceMock.registerPetOwner(any(PetOwnerDto.class)))
      .thenReturn(petOwnerDto);
    when(apiResponseMock.getBody()).thenReturn(petOwnerDto);
    when(apiResponseMock.isSuccess()).thenReturn(true);
    when(apiResponseMock.isError()).thenReturn(false);

    ResponseEntity<ApiResponse> response = petOwnerController.registerUser(petOwnerDto,image);
        ApiResponse apiResponse = response.getBody();

        assertNotNull(response.getBody().getBody());
        assertEquals(petOwnerDto, response.getBody().getBody());
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());

  }

}
