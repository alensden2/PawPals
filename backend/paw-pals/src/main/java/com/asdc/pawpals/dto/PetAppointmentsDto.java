package com.asdc.pawpals.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PetAppointmentsDto {


    VetDto vetDto;
    AppointmentDto appointmentDto;

    AnimalDto animalDto;

}
