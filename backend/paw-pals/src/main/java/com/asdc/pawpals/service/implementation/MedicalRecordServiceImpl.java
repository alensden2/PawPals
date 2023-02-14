package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.repository.MedicalRecordRepository;
import com.asdc.pawpals.service.MedicalRecordService;

import java.util.List;

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
        List<MedicalHistory> medicalRecords = medicalRecordRepository != null ? medicalRecordRepository.findByAnimalId(animalId) : null;
        logger.debug("MedicalRecordService :: retrieveMedicalRecord :: medicalRecords are : {}", medicalRecords);
        if(medicalRecords != null && !medicalRecords.isEmpty()){
            
        }
        // String output = "";
        return medicalRecords.stream().map(MedicalHistory::getAnimal).map(Animal::getName).reduce("", (prev, current)->{
            return prev.concat(" "+current);
        });
    }

}
