package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.repository.MedicalRecordRepository;
import com.asdc.pawpals.service.MedicalRecordService;

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
    public Object retrieveMedicalRecord(Long animalId){
        Object medicalRecords = medicalRecordRepository != null ? medicalRecordRepository.findByAnimalId(animalId) : null;
        logger.debug("MedicalRecordService :: retrieveMedicalRecord :: medicalRecords are : {}", medicalRecords);
        return medicalRecords;
    }

}
