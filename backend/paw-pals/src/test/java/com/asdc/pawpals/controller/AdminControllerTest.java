package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.service.implementation.AdminServiceImpl;
import com.asdc.pawpals.utils.ApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AdminControllerTest {
    @Mock
    AdminServiceImpl adminServiceImpl;

    @InjectMocks
    AdminController adminController;


    @Mock
    ApiResponse apiResponse;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateAnimalWrongRequestBodyType()
            throws PetOwnerAlreadyDoesNotExists, InvalidAnimalId {
        Long animalId = 1L;

        Object requestBody = new Object();

        ResponseEntity<ApiResponse> apiResponse = adminController.updateAnimal(
                animalId,
                requestBody
        );

        verify(adminServiceImpl, never()).updateAnimal(any(), any());

        assertNotNull(apiResponse);
    }


    @Test
    public void objectCreated() {
        assertNotNull(adminServiceImpl);
        assertNotNull(adminController);
        assertNotNull(apiResponse);
    }

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
        animal2.setPhotoUrl(new Byte[]{6, 7, 8, 9, 10});

        AnimalDto animal1 = new AnimalDto();
        animal1.setId(1L);
        animal1.setName("Max");
        animal1.setType("Dog");
        animal1.setAge(3);
        animal1.setGender("Male");
        animal1.setOwnerId("1234");
        animal1.setPhotoUrl(new Byte[]{1, 2, 3, 4, 5});

        animalList.add(animal1);
        animalList.add(animal2);

        when(adminServiceImpl.getAllAnimalRecords()).thenReturn(animalList);

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

    @Test
    public void testGetAllVetRecords() {
        List<VetDto> vetList = new ArrayList<>();

        VetDto vet1 = new VetDto();
        vet1.setFirstName("John");
        vet1.setLastName("Doe");
        vet1.setEmail("johndoe@example.com");
        vet1.setPassword("password123");
        vet1.setPhoneNo("1234567890");
        vet1.setLicenseNumber("ABCD1234");
        vet1.setClinicAddress("123 Main St");
        vet1.setExperience(5);
        vet1.setQualification("Doctor of Veterinary Medicine");
        vet1.setClinicUrl(new Byte[]{1, 2, 3, 4, 5});
        vet1.setProfileStatus("active");

        VetDto vet2 = new VetDto();
        vet2.setFirstName("Jane");
        vet2.setLastName("Smith");
        vet2.setEmail("janesmith@example.com");
        vet2.setPassword("password123");
        vet2.setPhoneNo("9876543210");
        vet2.setLicenseNumber("EFGH5678");
        vet2.setClinicAddress("456 Main St");
        vet2.setExperience(10);
        vet2.setQualification("Doctor of Veterinary Medicine");
        vet2.setClinicUrl(new Byte[]{6, 7, 8, 9, 10});
        vet2.setProfileStatus("active");

        vetList.add(vet1);
        vetList.add(vet2);

        when(adminServiceImpl.getAllVetRecords()).thenReturn(vetList);

        ResponseEntity<List<VetDto>> responseEntity = adminController.getAllVetRecords();
        List<VetDto> vetDetails = responseEntity.getBody();

        // assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(vetDetails);
        assertEquals(2, vetDetails.size());
        assertEquals("John", vetDetails.get(0).getFirstName());
        assertEquals("Doe", vetDetails.get(0).getLastName());
        assertEquals("johndoe@example.com", vetDetails.get(0).getEmail());
        assertEquals("password123", vetDetails.get(0).getPassword());
        assertEquals("1234567890", vetDetails.get(0).getPhoneNo());
        assertEquals("ABCD1234", vetDetails.get(0).getLicenseNumber());
        assertEquals("123 Main St", vetDetails.get(0).getClinicAddress());
        assertEquals(Integer.valueOf(5), vetDetails.get(0).getExperience());
        assertEquals(
                "Doctor of Veterinary Medicine",
                vetDetails.get(0).getQualification()
        );
        assertArrayEquals(
                new Byte[]{1, 2, 3, 4, 5},
                vetDetails.get(0).getClinicUrl()
        );
        assertEquals("active", vetDetails.get(0).getProfileStatus());

        assertEquals("Jane", vetDetails.get(1).getFirstName());
        assertEquals("Smith", vetDetails.get(1).getLastName());
        assertEquals("janesmith@example.com", vetDetails.get(1).getEmail());
        assertEquals("password123", vetDetails.get(1).getPassword());
        assertEquals("9876543210", vetDetails.get(1).getPhoneNo());
        assertEquals("EFGH5678", vetDetails.get(1).getLicenseNumber());
        assertEquals("456 Main St", vetDetails.get(1).getClinicAddress());
        assertEquals(Integer.valueOf(10), vetDetails.get(1).getExperience());
        assertEquals(
                "Doctor of Veterinary Medicine",
                vetDetails.get(1).getQualification()
        );
    }

    @Test
    public void testGetAllPetOwnerRecords() {
        List<PetOwnerDto> petOwnerList = new ArrayList<>();
        PetOwnerDto petOwner1 = new PetOwnerDto();
        petOwner1.setFirstName("John");
        petOwner1.setLastName("Doe");
        petOwner1.setEmail("johndoe@example.com");
        petOwner1.setPassword("password");
        petOwner1.setPhoneNo("1234567890");
        petOwner1.setPhotoUrl(new Byte[]{1, 2, 3, 4, 5});
        petOwner1.setAddress("123 Main St, Anytown USA");
        petOwner1.setPets(Arrays.asList(new AnimalDto()));

        PetOwnerDto petOwner2 = new PetOwnerDto();
        petOwner2.setFirstName("Jane");
        petOwner2.setLastName("Doe");
        petOwner2.setEmail("janedoe@example.com");
        petOwner2.setPassword("password");
        petOwner2.setPhoneNo("0987654321");
        petOwner2.setPhotoUrl(new Byte[]{6, 7, 8, 9, 10});
        petOwner2.setAddress("456 Elm St, Anytown USA");
        petOwner2.setPets(Arrays.asList(new AnimalDto()));

        petOwnerList.add(petOwner1);
        petOwnerList.add(petOwner2);

        when(adminServiceImpl.getAllPetOwnerRecords()).thenReturn(petOwnerList);

        ResponseEntity<List<PetOwnerDto>> responseEntity = adminController.getAllPetOwnerRecords();
        List<PetOwnerDto> petOwnerDetails = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(petOwnerDetails);
        assertEquals(2, petOwnerDetails.size());
        assertEquals("johndoe@example.com", petOwnerDetails.get(0).getEmail());
        assertEquals(
                "123 Main St, Anytown USA",
                petOwnerDetails.get(0).getAddress()
        );
        assertEquals("janedoe@example.com", petOwnerDetails.get(1).getEmail());
        assertEquals(
                "456 Elm St, Anytown USA",
                petOwnerDetails.get(1).getAddress()
        );
    }
}
