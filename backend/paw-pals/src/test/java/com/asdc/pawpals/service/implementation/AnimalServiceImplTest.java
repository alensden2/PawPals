package com.asdc.pawpals.service.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.exception.InvalidAnimalObject;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.Transformations;

public class AnimalServiceImplTest {
  @Mock
  AnimalRepository animalRepositoryMock;

  @Mock
  PetOwnerRepository petOwnerRepositoryMock;

  @InjectMocks
  AnimalServiceImpl animalServiceImpl;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testUpdateAppointmentStatus()
    throws UserNameNotFound, InvalidAnimalObject, InvalidImage, IOException {
    // Arrange
    AnimalDto animalDto = new AnimalDto();
    animalDto.setOwnerId("123");
    User user = new User();
    user.setUserId("123");
    PetOwner pet = new PetOwner();
    pet.setUser(user);
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
    Byte[] result = CommonUtils.getBytes(image);
    try {
      animalDto.setPhotoUrl(CommonUtils.getBytes(image));
    } catch (java.io.IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    animalDto.setMedicalHistory(null);
    // Act
    when(petOwnerRepositoryMock.findByUser_UserId(anyString())).thenReturn(Optional.of(pet));

    when(animalRepositoryMock.save(any(Animal.class))).thenReturn(Transformations.DTO_TO_MODEL_CONVERTER.animal(animalDto));
    animalDto = animalServiceImpl.registerPet(animalDto);
    // failing due to register pet

    // Assert
    assertNotNull(animalDto);
    assertNotNull(animalDto.getId());
    assertNotNull(animalDto.getName());
    assertNotNull(animalDto.getType());
    assertNotNull(animalDto.getAge());
    assertNotNull(animalDto.getGender());
    assertNotNull(animalDto.getOwnerId());
    
    
  }


}
