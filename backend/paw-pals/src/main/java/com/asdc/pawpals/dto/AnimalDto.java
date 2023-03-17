package com.asdc.pawpals.dto;

import java.util.List;

import com.asdc.pawpals.model.MedicalHistory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    private String name;
    private String type;
    private Integer age;
    private String gender;
    private PetOwnerDto owner;
    private Byte[] photoUrl;
    List<MedicalHistoryDto> medicalHistory;
}
