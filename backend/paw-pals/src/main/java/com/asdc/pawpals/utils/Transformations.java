package com.asdc.pawpals.utils;

import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.MedicalHistoryDto;

public class Transformations {

    public class MODEL_TO_DTO_CONVERTER{
        public static AnimalDto animal(Animal animal){
            //hello
            AnimalDto animalDto = new AnimalDto();
            if(animal != null){
                if(animal.getName() != null && !animal.getName().isEmpty()){
                    animalDto.setName(animal.getName());
                }
                if(animal.getType() != null && !animal.getType().isEmpty()){
                    animalDto.setType(animal.getType());
                }
                if(animal.getAge() != null){
                    animalDto.setAge(animal.getAge());
                }
                if(animal.getGender() != null && !animal.getGender().isEmpty()){
                    animalDto.setGender(animal.getGender());
                }
                if(animal.getMedicalHistories() != null && !animal.getMedicalHistories().isEmpty()){
                    animalDto.setMedicalHistory(
                        animal.getMedicalHistories().stream().map(
                            MODEL_TO_DTO_CONVERTER::medicalHistory
                        ).toList()
                    );
                }
            }
            return animalDto;
        }

        public static MedicalHistoryDto medicalHistory(MedicalHistory medicalHistory){
            return null;
        }
    }

    public class DTO_TO_MODEL_CONVERTER{

    }

}
