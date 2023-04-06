package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidAnimalObject;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.implementation.AnimalServiceImpl;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import io.jsonwebtoken.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@RunWith(MockitoJUnitRunner.class)
public class AnimalControllerTest {
  @Mock
  AnimalServiceImpl animalServiceMock;

  @InjectMocks
  AnimalController animalController;

  @Mock
  ApiResponse apiResponseMock;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void objectCreated() {
    assertNotNull(animalController);
    assertNotNull(apiResponseMock);
    assertNotNull(animalServiceMock);
  }

  @Test
  public void registerAnimalTest()
    throws IOException, UserNameNotFound, InvalidImage, InvalidAnimalObject, java.io.IOException {
    //Arrange
    AnimalDto animalDto = new AnimalDto();
    animalDto.setId(1L);
    animalDto.setName("Chris");
    animalDto.setType("Dog");
    animalDto.setAge(4);
    animalDto.setGender("Male");
    animalDto.setOwnerId("123");
    MockMultipartFile image = new MockMultipartFile(
      "image.jpg",
      "image.jpg",
      "image/jpeg",
      new byte[] { 12 }
    );

    // Act
    Byte[] result = CommonUtils.getBytes(image);
    try {
      animalDto.setPhotoUrl(CommonUtils.getBytes(image));
    } catch (java.io.IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    animalDto.setMedicalHistory(null);

    //Act
    when(animalServiceMock.registerPet(any(AnimalDto.class)))
      .thenReturn(animalDto);
    when(apiResponseMock.getBody()).thenReturn(animalDto);
    when(apiResponseMock.isSuccess()).thenReturn(true);
    when(apiResponseMock.isError()).thenReturn(false);

    ResponseEntity<ApiResponse> response = animalController.registerAnimal(
      animalDto,
      image
    );
    ApiResponse apiResponse = response.getBody();

    //Assert
    assertNotNull(response.getBody().getBody());
    assertEquals(animalDto, response.getBody().getBody());
    assertTrue(apiResponse.isSuccess());
    assertFalse(apiResponse.isError());
  }

  @Test
  public void updateAnimalTest()
    throws IOException, UserNameNotFound, InvalidImage, InvalidAnimalObject, java.io.IOException, InvalidAnimalId {
    //Arrange
    AnimalDto animalDto = new AnimalDto();
    Long _id = 1L;
    animalDto.setId(1L);
    animalDto.setName("Chris");
    animalDto.setType("Dog");
    animalDto.setAge(4);
    animalDto.setGender("Male");
    animalDto.setOwnerId("123");
    MockMultipartFile image = new MockMultipartFile(
      "image.jpg",
      "image.jpg",
      "image/jpeg",
      new byte[] { 12 }
    );

    // Act
    Byte[] result = CommonUtils.getBytes(image);
    try {
      animalDto.setPhotoUrl(CommonUtils.getBytes(image));
    } catch (java.io.IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    animalDto.setMedicalHistory(null);

    //Act
    when(animalServiceMock.updateAnimal(any(AnimalDto.class), anyLong(), any(MultipartFile.class)))
      .thenReturn(animalDto);
    when(apiResponseMock.getBody()).thenReturn(animalDto);
    when(apiResponseMock.isSuccess()).thenReturn(true);
    when(apiResponseMock.isError()).thenReturn(false);

    ResponseEntity<ApiResponse> response = animalController.updateAnimal(
      animalDto,
      image,
      _id
    );
    ApiResponse apiResponse = response.getBody();

    //Assert
    assertNotNull(response.getBody().getBody());
    assertEquals(animalDto, response.getBody().getBody());
    assertTrue(apiResponse.isSuccess());
    assertFalse(apiResponse.isError());
  }
}
