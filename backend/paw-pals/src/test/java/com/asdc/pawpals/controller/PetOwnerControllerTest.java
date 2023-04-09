package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.*;
import com.asdc.pawpals.service.implementation.PetOwnerImpl;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

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
                new byte[]{12}
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

        ResponseEntity<ApiResponse> response = petOwnerController.registerUser(petOwnerDto, image);
        ApiResponse apiResponse = response.getBody();

        assertNotNull(response.getBody().getBody());
        assertEquals(petOwnerDto, response.getBody().getBody());
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());

    }

    @Test
    public void testGetPetsByOwnerId() throws Exception {
        // Arrange
        String ownerId = "1";
        PetOwnerDto pet1 = new PetOwnerDto();
        AnimalDto animal1 = new AnimalDto();
        animal1.setId(1L);
        animal1.setName("Chris");
        animal1.setType("Dog");
        animal1.setAge(4);
        animal1.setGender("Male");
        animal1.setOwnerId("123");
        MockMultipartFile image = new MockMultipartFile(
                "image.jpg",
                "image.jpg",
                "image/jpeg",
                new byte[]{12}
        );

        // Act
        Byte[] result = CommonUtils.getBytes(image);
        try {
            animal1.setPhotoUrl(CommonUtils.getBytes(image));
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        animal1.setMedicalHistory(null);
        pet1.setPhoneNo("42112322212");
        pet1.setAddress("123 street");


        PetOwnerDto pet2 = new PetOwnerDto();
        AnimalDto animal2 = new AnimalDto();
        pet2.setPhoneNo("400000000012");
        pet2.setAddress("123 ssttrreett");
        animal2.setId(1L);
        animal2.setName("Chris");
        animal2.setType("Dog");
        animal2.setAge(4);
        animal2.setGender("Male");
        animal2.setOwnerId("123");
        MockMultipartFile image2 = new MockMultipartFile(
                "image.jpg",
                "image.jpg",
                "image/jpeg",
                new byte[]{12}
        );

        // Act
        Byte[] result2 = CommonUtils.getBytes(image2);
        try {
            animal2.setPhotoUrl(CommonUtils.getBytes(image2));
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        animal2.setMedicalHistory(null);


        List<AnimalDto> pets = new ArrayList<>();
        pets.add(animal1);
        pets.add(animal2);

        when(petOwnerServiceMock.retrieveAllPets(ownerId)).thenReturn(pets);
        when(apiResponseMock.getBody()).thenReturn(pets);
        when(apiResponseMock.isSuccess()).thenReturn(true);
        when(apiResponseMock.isError()).thenReturn(false);

        // Act
        ResponseEntity<ApiResponse> response = petOwnerController.getPetsByOwnerId(ownerId);
        ApiResponse apiResponse = response.getBody();

        // Assert
        assertNotNull(response.getBody().getBody());
        assertEquals(pets, response.getBody().getBody());
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());
    }

    @Test
    public void testUpdatePetOwner() throws Exception {
        // Arrange
        String id = "1";
        PetOwnerDto updatedPetOwner = new PetOwnerDto();
        updatedPetOwner.setFirstName("Jane");
        updatedPetOwner.setLastName("Doe");
        updatedPetOwner.setEmail("jane.doe@example.com");
        updatedPetOwner.setPassword("newpassword");
        updatedPetOwner.setPhoneNo("1234567890");
        updatedPetOwner.setAddress("1234 North St");
        updatedPetOwner.setPets(null);
        MockMultipartFile image = new MockMultipartFile(
                "image.jpg",
                "image.jpg",
                "image/jpeg",
                new byte[]{12}
        );

        // Act
        when(petOwnerServiceMock.updatePetOwner(eq(id), any(PetOwnerDto.class), any(MultipartFile.class)))
                .thenReturn(updatedPetOwner);
        when(apiResponseMock.getBody()).thenReturn(updatedPetOwner);
        when(apiResponseMock.isSuccess()).thenReturn(true);
        when(apiResponseMock.isError()).thenReturn(false);

        ResponseEntity<ApiResponse> response = petOwnerController.updatePetOwner(id, updatedPetOwner, image);
        ApiResponse apiResponse = response.getBody();

        // Assert
        assertNotNull(response.getBody().getBody());
        assertEquals(updatedPetOwner, response.getBody().getBody());
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());
    }

    @Test
    public void testDeletePetOwner() throws Exception {
        // Arrange
        String ownerId = "1";
        PetOwnerDto petOwnerDto = new PetOwnerDto();
        petOwnerDto.setFirstName("John");
        petOwnerDto.setLastName("Doe");
        petOwnerDto.setEmail("john.doe@example.com");
        petOwnerDto.setPassword("password");
        petOwnerDto.setPhoneNo("1234567890");
        petOwnerDto.setAddress("1223 South St");
        petOwnerDto.setPets(null);
        // Act
        when(petOwnerServiceMock.deletePetOwner(ownerId)).thenReturn(petOwnerDto);
        when(apiResponseMock.getBody()).thenReturn(true);
        when(apiResponseMock.isSuccess()).thenReturn(true);
        when(apiResponseMock.isError()).thenReturn(false);

        ResponseEntity<ApiResponse> response = petOwnerController.deletePetOwner(ownerId);
        ApiResponse apiResponse = response.getBody();

        // Assert
        assertNotNull(response.getBody().getBody());
        assertTrue((Boolean) response.getBody().getBody());
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());
    }

    @Test
    public void testGetPetsAppointmentsByOwnerId() throws Exception {
        // Arrange
        String ownerId = "1";
        List<AppointmentDto> appointments = new ArrayList<>();
        AppointmentDto appointment1 = new AppointmentDto();
        appointment1.setId(1);
        appointment1.setDate("2023-05-01");
        appointment1.setStartTime("10:00");
        appointment1.setEndTime("Appointment 1");
        appointment1.setAnimalId(1L);
        appointment1.setVetUserId(ownerId);
        appointments.add(appointment1);

        AppointmentDto appointment2 = new AppointmentDto();
        appointment2.setId(2);
        appointment2.setDate("2023-05-05");
        appointment2.setStartTime("14:00");
        appointment2.setEndTime("Appointment 2");
        appointment2.setAnimalId(2L);
        appointment2.setVetUserId(ownerId);
        appointments.add(appointment2);

        PetAppointmentsDto ap1 = new PetAppointmentsDto();
        PetAppointmentsDto ap2 = new PetAppointmentsDto();

        ap1.setAnimalDto(null);
        ap1.setAppointmentDto(appointment1);
        ap1.setVetDto(null);

        ap2.setAnimalDto(null);
        ap2.setAppointmentDto(appointment2);
        ap2.setVetDto(null);

        List<PetAppointmentsDto> petAppointments = new ArrayList<>();

        petAppointments.add(ap2);
        petAppointments.add(ap1);

        when(petOwnerServiceMock.retrievePetsAppointments(ownerId)).thenReturn(petAppointments);
        when(apiResponseMock.getBody()).thenReturn(appointments);
        when(apiResponseMock.isSuccess()).thenReturn(true);
        when(apiResponseMock.isError()).thenReturn(false);

        // Act
        ResponseEntity<ApiResponse> response = petOwnerController.getPetsAppointmentsByOwnerId(ownerId);
        ApiResponse apiResponse = response.getBody();

        // Assert
        assertNotNull(response.getBody().getBody());
        assertEquals(appointments, response.getBody().getBody());
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());
    }

    @Test
    public void testGetPetMedicalHistoryByOwnerId() throws Exception {
        // Arrange
        String ownerId = "1";
        List<MedicalHistoryDto> medicalHistoryList = new ArrayList<>();
        MedicalHistoryDto medicalHistory1 = new MedicalHistoryDto();
        medicalHistory1.setDateDiagnosed("2022-04-07");
        medicalHistory1.setAilmentName("checkup");
        medicalHistory1.setPrescription("some prescription");
        medicalHistory1.setVaccines("some vaccines");
        medicalHistory1.setVetUserId("vet123");
        medicalHistory1.setAnimalId(1L);
        medicalHistoryList.add(medicalHistory1);

        PetMedicalHistoryDto p1 = new PetMedicalHistoryDto();
        p1.setAnimalDto(null);
        p1.setMedicalHistoryDto(medicalHistory1);
        p1.setVetDto(null);


        List<PetMedicalHistoryDto> petAppointments = new ArrayList<>();


        when(petOwnerServiceMock.retrievePetsMedicalHistory(ownerId)).thenReturn(petAppointments);
        when(apiResponseMock.getBody()).thenReturn(medicalHistoryList);
        when(apiResponseMock.isSuccess()).thenReturn(true);
        when(apiResponseMock.isError()).thenReturn(false);

        // Act
        ResponseEntity<ApiResponse> response = petOwnerController.getPetMedicalHistoryByOwnerId(ownerId);
        ApiResponse apiResponse = response.getBody();

        // Assert
        assertNotNull(response.getBody().getBody());
        assertEquals(medicalHistoryList, response.getBody().getBody());
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());
    }


}
