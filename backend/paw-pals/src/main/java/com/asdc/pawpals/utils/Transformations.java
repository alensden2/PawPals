package com.asdc.pawpals.utils;

import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.model.Vet;

import java.util.stream.Collectors;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.dto.VetDto;

public class Transformations {

    public class MODEL_TO_DTO_CONVERTER{
        public static AnimalDto animal(Animal animal){
            AnimalDto animalDto = new AnimalDto();
            if(animal != null){
                animalDto.setName(animal.getName());
                animalDto.setType(animal.getType());
                animalDto.setAge(animal.getAge());
                animalDto.setGender(animal.getGender());
                if(animal.getMedicalHistories() != null 
                    && !animal.getMedicalHistories().isEmpty()){
                        animalDto.setMedicalHistory(
                            animal.getMedicalHistories().stream().map(
                                MODEL_TO_DTO_CONVERTER::medicalHistory
                            ).collect(Collectors.toList())
                        );
                }
            }
            return animalDto;
        }

        public static MedicalHistoryDto medicalHistory(MedicalHistory medicalHistory){
            MedicalHistoryDto  medicalHistoryDto = new MedicalHistoryDto();
            if(medicalHistory != null){
                medicalHistoryDto.setDateDiagnosed(medicalHistory.getDateDiagnosed());
                medicalHistoryDto.setAilmentName(medicalHistory.getAilmentName());
                medicalHistoryDto.setPrescription(medicalHistory.getPrescription());
                medicalHistoryDto.setVaccines(medicalHistory.getVaccines());
                medicalHistoryDto.setVet(MODEL_TO_DTO_CONVERTER.vet(medicalHistory.getVet()));
            }
            return medicalHistoryDto;
        }

        public static VetDto vet(Vet vet){
            VetDto vetDto = new VetDto();
            if(vet != null){
                vetDto.setName(vet.getName());
                vetDto.setLicenseNumber(vet.getLicenseNumber());
                vetDto.setExperience(vet.getExperience());
                vetDto.setQualification(vet.getQualification());
                vetDto.setClinicAddress(vet.getClinicAddress());
            }
            return vetDto;
        }
    }

    public class DTO_TO_MODEL_CONVERTER{

    }

}
