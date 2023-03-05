package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.service.MedicalRecordService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical-history-management")
public class MedicalRecordController {
    Logger logger = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    MedicalRecordService medicalRecordService;

    // @GetMapping("/records/{record-id}")
    // public ResponseEntity<Object> get

    @GetMapping("/animals/{animal_id}")
    public ResponseEntity<List<MedicalHistoryDto>> getMedicalHistory(
        @PathVariable(value = "animal_id") Long animalId
    ){
        List<MedicalHistoryDto> medicalRecords = null;
        if(medicalRecordService != null && animalId != null){
            medicalRecords = medicalRecordService.retrieveMedicalRecord(animalId);
        }
        return ResponseEntity.ok().body(medicalRecords);
    }
}
