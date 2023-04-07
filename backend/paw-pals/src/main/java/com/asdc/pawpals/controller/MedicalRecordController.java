package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.MedicalRecordService;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;

import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth/medical-history-management")
public class MedicalRecordController {
    Logger logger = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    ApiResponse apiResponse;

    // @GetMapping("/records/{record-id}")
    // public ResponseEntity<Object> get

    @GetMapping("/animals/{animal_id}")
    public ResponseEntity<List<MedicalHistoryDto>> getMedicalHistory(
        @PathVariable(value = "animal_id") Long animalId
    ){
        logger.info("Get Medical Animal Medical records by animal Id :", animalId);
        List<MedicalHistoryDto> medicalRecords = null;
        if(medicalRecordService != null && animalId != null){
            medicalRecords = medicalRecordService.retrieveMedicalRecord(animalId);
        }
        return ResponseEntity.ok().body(medicalRecords);
    }

    @PostMapping({"/create"})
    public ResponseEntity<ApiResponse> createMedicalRecord(@RequestBody Object requestBody) throws InvalidObjectException, UserNameNotFound, InvalidAnimalId {
        logger.info("Received request as to create medical Record:", requestBody.toString());
        MedicalHistoryDto medicalHistoryDto = null;
        if (CommonUtils.isStrictTypeOf(requestBody, MedicalHistoryDto.class)) {
            medicalHistoryDto = ObjectMapperWrapper.getInstance().convertValue(requestBody, MedicalHistoryDto.class);
            apiResponse.setBody(medicalRecordService.postMedicalRecord(medicalHistoryDto));
            apiResponse.setMessage("successfully inserted object");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }
}
