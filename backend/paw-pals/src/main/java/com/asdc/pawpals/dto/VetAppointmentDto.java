package com.asdc.pawpals.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class VetAppointmentDto {

    AppointmentDto appointmentDto;
    AnimalDto animalDto;

    PetOwnerDto petOwnerDto;

    List<MedicalHistoryDtoInline> medicalHistoryDtos;


}
