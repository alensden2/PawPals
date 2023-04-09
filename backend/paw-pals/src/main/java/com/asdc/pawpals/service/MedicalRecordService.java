package com.asdc.pawpals.service;

import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.UserNameNotFound;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.List;

@Service
public interface MedicalRecordService {

    public List<MedicalHistoryDto> retrieveMedicalRecord(Long animalId);

    MedicalHistoryDto postMedicalRecord(MedicalHistoryDto medicalHistoryDto) throws InvalidObjectException, UserNameNotFound, InvalidAnimalId;
}
