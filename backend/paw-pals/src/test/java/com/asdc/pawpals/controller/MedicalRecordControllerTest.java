package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.MedicalRecordService;
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

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MedicalRecordControllerTest {

    @Mock
    private MedicalRecordService medicalRecordServiceMock;

    @Mock
    private ApiResponse apiResponseMock;

    @InjectMocks
    private MedicalRecordController medicalRecordController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void objectCreated() {
        assertNotNull(medicalRecordServiceMock);
        assertNotNull(apiResponseMock);
        assertNotNull(medicalRecordController);
    }

    @Test
    public void testGetMedicalHistory() throws UserNameNotFound, InvalidAnimalId {
        // Arrange
        Long animalId = 1l;
        List<MedicalHistoryDto> medicalRecords = new ArrayList<>();
        MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto();
        medicalHistoryDto.setAnimalId(1l);

        medicalRecords.add(medicalHistoryDto);

        // Act
        when(medicalRecordServiceMock.retrieveMedicalRecord(anyLong())).thenReturn(medicalRecords);

        ResponseEntity<List<MedicalHistoryDto>> response = medicalRecordController.getMedicalHistory(animalId);
        List<MedicalHistoryDto> medicalHistoryDtos = response.getBody();

        //Assert
        assertNotNull(medicalHistoryDtos);
        assertTrue(medicalHistoryDtos.size() > 0);
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void testCreateMedicalRecord() throws UserNameNotFound, InvalidAnimalId, InvalidObjectException {
        // Arrange
        MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto();
        medicalHistoryDto.setAnimalId(1l);


        // Act
        when(medicalRecordServiceMock.postMedicalRecord(any(MedicalHistoryDto.class))).thenReturn(medicalHistoryDto);
        when(apiResponseMock.isSuccess()).thenReturn(true);
        when(apiResponseMock.getBody()).thenReturn(medicalHistoryDto);

        ResponseEntity<ApiResponse> response = medicalRecordController.createMedicalRecord(medicalHistoryDto);
        ApiResponse apiResponse = response.getBody();

        //Assert
        assertTrue(apiResponse.isSuccess());
        assertTrue(response.getStatusCode
                ().equals(HttpStatus.CREATED));
        assertNotNull(apiResponse.getBody());
        assertTrue(apiResponse.getBody() instanceof MedicalHistoryDto);
    }

}