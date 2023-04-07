package com.asdc.pawpals.service.implementation;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.Appointment;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.validators.AppointmentValidators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.MedicalRecordRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.MedicalRecordService;
import com.asdc.pawpals.utils.Transformations;
@Component
@Lazy
public class MedicalRecordServiceImpl implements MedicalRecordService {

    Logger logger = LogManager.getLogger(MedicalRecordServiceImpl.class);

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    VetRepository vetRepository;

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

    /**
     * @param medicalHistoryDto
     * @return
     * @throws UserNameNotFound
     * @throws InvalidAnimalId
     */
    @Override
    public MedicalHistoryDto postMedicalRecord(MedicalHistoryDto medicalHistoryDto) throws InvalidObjectException, UserNameNotFound, InvalidAnimalId {
        logger.debug("Book pet owner appointment with vet", medicalHistoryDto.toString());
        if(medicalHistoryDto!=null && medicalHistoryDto.getAilmentName()!=null && medicalHistoryDto.getPrescription()!=null &&
        medicalHistoryDto.getDateDiagnosed()!=null && medicalHistoryDto.getVaccines()!=null)
        {
            Optional<Vet> optionalVetRepository=vetRepository.findByUser_UserId(medicalHistoryDto.getVetUserId());
            if(!optionalVetRepository.isPresent()){
                throw new UserNameNotFound("Vet does not exist");
            }
            if(!animalRepository.findById(medicalHistoryDto.getAnimalId()).isPresent()){
                throw new InvalidAnimalId("Animal does not exist");
            }
            MedicalHistory medicalHistory=Transformations.DTO_TO_MODEL_CONVERTER.medicalHistory(medicalHistoryDto);
            medicalHistory.setVet(optionalVetRepository.get()); // this needs to be done because CascadeType.MERGE is not working with vet->USer->userId
            medicalHistory=medicalRecordRepository.save(medicalHistory);
            return Transformations.MODEL_TO_DTO_CONVERTER.medicalHistory(medicalHistory);
        } else {
            throw  new InvalidObjectException("Invalid Medical History Object");
        }

    }

}
