package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.repository.MedicalRecordRepository;
import com.asdc.pawpals.service.MedicalRecordService;
import com.asdc.pawpals.utils.Transformations;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component
@Lazy
public class MedicalRecordServiceImpl implements MedicalRecordService {

    Logger logger = LogManager.getLogger(MedicalRecordServiceImpl.class);

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalHistoryDto> retrieveMedicalRecord(Long animalId){
        List<MedicalHistory> medicalRecords = medicalRecordRepository != null ? medicalRecordRepository.findByAnimalId(animalId) : null;
        logger.debug("MedicalRecordService :: retrieveMedicalRecord :: medicalRecords are : {}", medicalRecords);
        List<MedicalHistoryDto> medicalHistoryDto = null;
        if(medicalRecords != null && !medicalRecords.isEmpty()){
            medicalHistoryDto = medicalRecords.stream().map(Transformations.MODEL_TO_DTO_CONVERTER::medicalHistory).collect(Collectors.toList());
        }
        return medicalHistoryDto;
    }

}
