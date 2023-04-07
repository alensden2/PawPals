package com.asdc.pawpals.service.implementation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.Transformations;

import java.io.IOException;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

@RunWith(MockitoJUnitRunner.class)
public class PetOwnerImplTest {
  @Mock
  PetOwnerRepository petOwnerRepository;

  @Mock
  UserRepository userRepository;

  @InjectMocks
  PetOwnerImpl petOwnerImpl;

  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void registerPetOwner()  // fix
    throws UserNameNotFound, InvalidUserDetails, UserAlreadyExist, IOException {
    // Arrange
    PetOwnerDto petOwnerDto = new PetOwnerDto();
    petOwnerDto.setUserName("testUser");
    MultipartFile invalidImage = null;
    try {
      CommonUtils.getBytes(invalidImage);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidImage e) {
      // Assert that InvalidImage is thrown
      assertEquals("invalid image uploaded", e.getMessage());
    }

    // Set the invalid image to the animal DTO
    petOwnerDto.setPhotoUrl(new Byte[1]);
    petOwnerDto.setPhoneNo("1234567890");
    petOwnerDto.setAddress("Test Address");
    petOwnerDto.setPassword("password");
    petOwnerDto.setFirstName("John");
    petOwnerDto.setLastName("Doe");
    petOwnerDto.setRole("ROLE_USER");
    petOwnerDto.setEmail("john.doe@example.com");

    User user = new User();
    user.setUserId("testUser");

    when(userRepository.findById("testUser")).thenReturn(Optional.of(user));
    when(petOwnerRepository.existsByUser_UserId("testUser")).thenReturn(false);
    when(petOwnerRepository.save(any(PetOwner.class)))
      .thenReturn(new PetOwner());

    // Act
    PetOwnerDto response = petOwnerImpl.registerPetOwner(petOwnerDto);

    // Assert
    assertNotNull(response);
    assertEquals(petOwnerDto.getUsername(), response.getUsername());
    assertArrayEquals(petOwnerDto.getPhotoUrl(), response.getPhotoUrl());
    assertEquals(petOwnerDto.getPhoneNo(), response.getPhoneNo());
    assertEquals(petOwnerDto.getAddress(), response.getAddress());
  }

  @Test
  public void testInvalidUserDetails() {
    // Arrange
    PetOwnerDto petOwnerDto = new PetOwnerDto();
    petOwnerDto.setUserName("testUser");

    when(userRepository.findById("testUser")).thenReturn(Optional.empty());

    // Act and Assert
    assertThrows(
      InvalidUserDetails.class,
      () -> petOwnerImpl.registerPetOwner(petOwnerDto)
    );
  }

  @Test
public void testDeletePetOwnerWithValidId() throws UserNameNotFound {
// Arrange
String userId = "testUser";
PetOwner petOwner = new PetOwner();
petOwner.setId(1L);
User user = new User();
user.setUserId(userId);
petOwner.setUser(user);
when(petOwnerRepository.findByUser_UserId(userId)).thenReturn(Optional.of(petOwner));
PetOwnerDto expectedResponse = Transformations.MODEL_TO_DTO_CONVERTER.petOwner(petOwner);

// Act
PetOwnerDto response = petOwnerImpl.deletePetOwner(userId);

// Assert
assertNotNull(response);
assertEquals(expectedResponse.getUsername(), response.getUsername());
assertEquals(expectedResponse.getPhotoUrl(), response.getPhotoUrl());
assertEquals(expectedResponse.getPhoneNo(), response.getPhoneNo());
assertEquals(expectedResponse.getAddress(), response.getAddress());
}

@Test
public void testDeletePetOwnerWithInvalidId() throws UserNameNotFound {
// Arrange
String userId = "invalidUser";
when(petOwnerRepository.findByUser_UserId(userId)).thenReturn(Optional.empty());


// Act and Assert
assertThrows(UserNameNotFound.class, () -> petOwnerImpl.deletePetOwner(userId));
}

}
