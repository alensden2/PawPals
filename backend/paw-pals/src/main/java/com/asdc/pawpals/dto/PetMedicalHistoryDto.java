package com.asdc.pawpals.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PetMedicalHistoryDto {

    MedicalHistoryDto medicalHistoryDto;
    AnimalDto animalDto;
    VetDto vetDto;


}
