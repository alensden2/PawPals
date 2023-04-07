package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.MedicalRecordRepository;
import com.asdc.pawpals.repository.VetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MedicalRecordServiceImplTest {

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @Mock
    AnimalRepository animalRepository;

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    MedicalRecordServiceImpl medicalRecordService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void retrieveMedicalRecordTest() {
        Long animalId = 1L;
        List<MedicalHistory> medicalHistoryList = new ArrayList<>();
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setAilmentName("fever");
        medicalHistoryList.add(medicalHistory);
        when(medicalRecordRepository.findByAnimalId(animalId)).thenReturn(medicalHistoryList);
        List<MedicalHistoryDto> medicalHistoryDtoList = medicalRecordService.retrieveMedicalRecord(animalId);
        assertNotNull(medicalHistoryDtoList);
        assertEquals(medicalHistoryDtoList.size(), medicalHistoryList.size());
        assertEquals(medicalHistoryDtoList.get(0).getAilmentName(), medicalHistoryList.get(0).getAilmentName());
    }

    @Test
    public void retrieveMedicalRecordNoDataTest() {
        Long animalId = 1L;
        when(medicalRecordRepository.findByAnimalId(animalId)).thenReturn(null);
        List<MedicalHistoryDto> medicalHistoryDtoList = medicalRecordService.retrieveMedicalRecord(animalId);
        assertNull(medicalHistoryDtoList);
    }

    @Test(expected = InvalidObjectException.class)
    public void postMedicalRecordWithInvalidInputTest() throws InvalidObjectException, UserNameNotFound, InvalidAnimalId {
        MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto();
        medicalRecordService.postMedicalRecord(medicalHistoryDto);
    }

    @Test(expected = UserNameNotFound.class)
    public void postMedicalRecordWithInvalidVetTest() throws InvalidObjectException, UserNameNotFound, InvalidAnimalId {
        MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto();
        medicalHistoryDto.setVetUserId("invalid");
        medicalHistoryDto.setAilmentName("abd");
        medicalHistoryDto.setPrescription("pres");
        medicalHistoryDto.setDateDiagnosed("12-10-2023");
        medicalHistoryDto.setVaccines("2");
        when(vetRepository.findByUser_UserId(any())).thenReturn(Optional.empty());
        medicalRecordService.postMedicalRecord(medicalHistoryDto);
    }

    @Test(expected = InvalidAnimalId.class)
    public void postMedicalRecordWithInvalidAnimalTest() throws InvalidObjectException, UserNameNotFound, InvalidAnimalId {
        MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto();
        medicalHistoryDto.setAnimalId(0L);
        medicalHistoryDto.setVetUserId("invalid");
        medicalHistoryDto.setAilmentName("abd");
        medicalHistoryDto.setPrescription("pres");
        medicalHistoryDto.setDateDiagnosed("12-10-2023");
        medicalHistoryDto.setVaccines("2");
        when(vetRepository.findByUser_UserId(medicalHistoryDto.getVetUserId())).thenReturn(Optional.of(new Vet()));
        when(animalRepository.findById(anyLong())).thenReturn(Optional.empty());
        medicalRecordService.postMedicalRecord(medicalHistoryDto);
    }
}