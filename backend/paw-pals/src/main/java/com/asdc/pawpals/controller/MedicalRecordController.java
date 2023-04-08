package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.service.MedicalRecordService;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.util.List;

@RestController
@RequestMapping("/auth/medical-history-management")
public class MedicalRecordController {
    Logger logger = LogManager.getLogger(MedicalRecordController.class);

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    ApiResponse apiResponse;


    /**
     * Retrieves the medical history of an animal with the given ID.
     *
     * @param animalId the ID of the animal to retrieve medical history for
     * @return a ResponseEntity containing a list of MedicalHistoryDto objects representing the animal's medical history
     */
    @GetMapping("/animals/{animal_id}")
    public ResponseEntity<List<MedicalHistoryDto>> getMedicalHistory(
            @PathVariable(value = "animal_id") Long animalId
    ) {
        logger.info("Get Medical Animal Medical records by animal Id :", animalId);
        List<MedicalHistoryDto> medicalRecords = null;
        if (medicalRecordService != null && animalId != null) {
            medicalRecords = medicalRecordService.retrieveMedicalRecord(animalId);
        }
        return ResponseEntity.ok().body(medicalRecords);
    }

    /**
     * This method creates a new medical record for an animal. It takes a JSON object as the request body, converts it to a
     * MedicalHistoryDto object, and passes it to the medicalRecordService to be persisted in the database.
     *
     * @param requestBody JSON object representing a MedicalHistoryDto object
     * @return ResponseEntity with an ApiResponse object containing the newly created medical record and success status
     * @throws InvalidObjectException if the request body cannot be converted to a MedicalHistoryDto object
     * @throws UserNameNotFound       if the username provided in the MedicalHistoryDto object does not exist in the database
     * @throws InvalidAnimalId        if the animal id provided in the MedicalHistoryDto object does not exist in the database
     */
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
