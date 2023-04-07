package com.asdc.pawpals.dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class PetAppointmentsDtoTest {

    @Test
    public void testGetVetDto() {
        VetDto vetDto = new VetDto();
        PetAppointmentsDto dto = new PetAppointmentsDto();
        dto.setVetDto(vetDto);
        assertEquals(vetDto, dto.getVetDto());
    }

    @Test
    public void testGetAppointmentDto() {
        AppointmentDto appointmentDto = new AppointmentDto();
        PetAppointmentsDto dto = new PetAppointmentsDto();
        dto.setAppointmentDto(appointmentDto);
        assertEquals(appointmentDto, dto.getAppointmentDto());
    }

    @Test
    public void testGetAnimalDto() {
        AnimalDto animalDto = new AnimalDto();
        PetAppointmentsDto dto = new PetAppointmentsDto();
        dto.setAnimalDto(animalDto);
        assertEquals(animalDto, dto.getAnimalDto());
    }

}
