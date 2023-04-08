package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidAnimalObject;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.utils.CommonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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
                new byte[]{12}
        );
        Byte[] result = CommonUtils.getBytes(image);
        animalDto.setPhotoUrl(CommonUtils.getBytes(image));

        animalDto.setMedicalHistory(null);
        // Act
        when(petOwnerRepositoryMock.findByUser_UserId(anyString()))
                .thenReturn(Optional.of(pet));
        Animal animal = new Animal();
        PetOwner petOwner = new PetOwner();
        petOwner.setUser(new User());
        animal.setOwner(petOwner);
        animal.setId(1l);
        animal.setName("tom");
        animal.setType("dog");
        when(animalRepositoryMock.save(any(Animal.class)))
                .thenReturn(animal);
        animalDto = animalServiceImpl.registerPet(animalDto);
        // failing due to register pet

        // Assert
        assertNotNull(animalDto);
        assertNotNull(animalDto.getId());
        assertNotNull(animalDto.getName());
        assertNotNull(animalDto.getType());
    }

    @Test
    public void testRegisterAnimalForInvalidPetOwner()
            throws UserNameNotFound, InvalidImage, IOException {
        // Arrange
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Fluffy");
        animalDto.setType("Cat");
        animalDto.setAge(3);
        animalDto.setGender("Female");
        animalDto.setOwnerId("123456");
        MockMultipartFile image = new MockMultipartFile(
                "image.jpg",
                "image.jpg",
                "image/jpeg",
                new byte[]{12}
        );
        Byte[] result = CommonUtils.getBytes(image);
        try {
            animalDto.setPhotoUrl(CommonUtils.getBytes(image));
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        when(petOwnerRepositoryMock.findByUser_UserId(any(String.class)))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                UserNameNotFound.class,
                () -> {
                    animalServiceImpl.registerPet(animalDto);
                }
        );
    }

    @Test
    public void testRegisterAnimalForInvalidObject() {
        // Arrange
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Fluffy");
        animalDto.setType("");
        animalDto.setAge(3);
        animalDto.setGender("Female");
        animalDto.setOwnerId("123456");

        // Act & Assert
        assertThrows(
                InvalidAnimalObject.class,
                () -> {
                    animalServiceImpl.registerPet(animalDto);
                }
        );
    }

    @Test
    public void testUploadAnimalPhotoForInvalidImage() {
        // Arrange
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Fluffy");
        animalDto.setType("Cat");
        animalDto.setAge(3);
        animalDto.setGender("Female");
        animalDto.setOwnerId("123456");

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
        animalDto.setPhotoUrl(new Byte[0]);
    }
    
    @Test
    public void testUpdateAnimalForValidInput() throws InvalidAnimalId, IOException, InvalidImage, InvalidAnimalObject {
        // Arrange
        Long animalId = 1L;
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Buddy");
        animalDto.setAge(2);
        animalDto.setGender("male");
        animalDto.setType("Dog");
        MockMultipartFile image = new MockMultipartFile("image", "dog.png", "image/png", new byte[]{0x01, 0x02, 0x03});

        Animal animal = new Animal();
        animal.setId(animalId);
        PetOwner petOwner = new PetOwner();
        petOwner.setUser(new User());
        animal.setOwner(petOwner);
        when(animalRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(animal));
        when(animalRepositoryMock.saveAndFlush(any(Animal.class))).thenReturn(animal);

        // Act
        AnimalDto updatedAnimalDto = animalServiceImpl.updateAnimal(animalDto, animalId, image);

        // Assert
        assertNotNull(updatedAnimalDto);
        assertEquals(animalDto.getName(), updatedAnimalDto.getName());
        assertEquals(animalDto.getAge(), updatedAnimalDto.getAge());
        assertEquals(animalDto.getGender(), updatedAnimalDto.getGender());
        assertEquals(animalDto.getType(), updatedAnimalDto.getType());
    }

    @Test
    public void testUpdateAnimalObjectForValidInput() throws InvalidAnimalId, IOException, InvalidImage, InvalidAnimalObject {
        // Arrange
        Long animalId = 1L;
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Buddy");
        animalDto.setAge(2);
        animalDto.setGender("male");
        animalDto.setType("Dog");
        Animal animal = new Animal();
        animal.setId(animalId);
        PetOwner petOwner = new PetOwner();
        petOwner.setUser(new User());
        animal.setOwner(petOwner);
        when(animalRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(animal));
        when(animalRepositoryMock.saveAndFlush(any(Animal.class))).thenReturn(animal);

        // Act
        AnimalDto updatedAnimalDto = animalServiceImpl.updateAnimalObject(animalDto, animalId);

        // Assert
        assertNotNull(updatedAnimalDto);
        assertEquals(animalDto.getName(), updatedAnimalDto.getName());
        assertEquals(animalDto.getAge(), updatedAnimalDto.getAge());
        assertEquals(animalDto.getGender(), updatedAnimalDto.getGender());
        assertEquals(animalDto.getType(), updatedAnimalDto.getType());
    }

    @Test(expected = InvalidAnimalObject.class)
    public void testUpdateAnimalObjectForInvalidInput() throws InvalidAnimalId, IOException, InvalidImage, InvalidAnimalObject {
        // Arrange
        Long animalId = null;
        AnimalDto animalDto = null;

        // Act
        animalServiceImpl.updateAnimalObject(animalDto, animalId);

        // Assert
        // Exception is expected to be thrown
    }
    @Test(expected = InvalidAnimalObject.class)
    public void testUpdateAnimalForInvalidInput() throws InvalidAnimalId, IOException, InvalidImage, InvalidAnimalObject {
        // Arrange
        Long animalId = null;
        AnimalDto animalDto = null;
        MockMultipartFile image = null;

        // Act
        animalServiceImpl.updateAnimal(animalDto, animalId, image);

        // Assert
        // Exception is expected to be thrown
    }

    @Test(expected = InvalidAnimalId.class)
    public void testUpdateAnimalObjectForInvalidAnimalId() throws InvalidAnimalId, IOException, InvalidImage, InvalidAnimalObject {
        // Arrange
        Long animalId = 1L;
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Buddy");
        animalDto.setAge(2);
        animalDto.setGender("male");
        animalDto.setType("Dog");
        when(animalRepositoryMock.findById(any(Long.class))).thenReturn(Optional.empty());

        // Act
        animalServiceImpl.updateAnimalObject(animalDto, animalId);

        // Assert
        // Exception is expected to be thrown
    }

    @Test(expected = InvalidAnimalId.class)
    public void testUpdateAnimalForInvalidAnimalId() throws InvalidAnimalId, IOException, InvalidImage, InvalidAnimalObject {
        // Arrange
        Long animalId = 1L;
        AnimalDto animalDto = new AnimalDto();
        animalDto.setName("Buddy");
        animalDto.setAge(2);
        animalDto.setGender("male");
        animalDto.setType("Dog");
        MockMultipartFile image = new MockMultipartFile("image", "dog.png", "image/png", new byte[]{});

        when(animalRepositoryMock.findById(any(Long.class))).thenReturn(Optional.empty());

        // Act
        animalServiceImpl.updateAnimal(animalDto, animalId, image);

        // Assert
        // Exception is expected to be thrown
    }

    @Test
    public void testDeleteAnimal() throws InvalidAnimalId {
        // Arrange
        Animal animal = new Animal();
        animal.setName("Fluffy");
        animal.setType("Cat");
        animal.setAge(3);
        animal.setGender("Female");
        animal.setId(1L);
        PetOwner owner = new PetOwner();
        owner.setUser(new User());
        animal.setOwner(owner);

        when(animalRepositoryMock.findById(1L)).thenReturn(Optional.of(animal));

        // Act
        AnimalDto deletedAnimalDto = animalServiceImpl.deleteAnimal(1L);

        // Assert
        assertEquals(animal.getName(), deletedAnimalDto.getName());
        assertEquals(animal.getType(), deletedAnimalDto.getType());
        assertEquals(animal.getAge(), deletedAnimalDto.getAge());
        assertEquals(animal.getGender(), deletedAnimalDto.getGender());
    }


}
