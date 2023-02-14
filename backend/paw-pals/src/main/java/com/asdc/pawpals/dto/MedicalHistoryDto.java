package com.asdc.pawpals.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDto {
    private String dateDiagnosed;
    private String ailmentName;
    private String prescription;
    private String vaccines;
    private AnimalDto animal;
    private VetDto vet;

}
