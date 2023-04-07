package com.asdc.pawpals.dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class PetMedicalHistoryDtoTest {

    @Test
    public void testGettersAndSetters() {
        MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto("2022-01-01", "Ailment", "Prescription", "Vaccines", "vetId", 1L);
        AnimalDto animalDto = new AnimalDto();
        VetDto vetDto = new VetDto();

        PetMedicalHistoryDto petMedicalHistoryDto = new PetMedicalHistoryDto();
        petMedicalHistoryDto.setMedicalHistoryDto(medicalHistoryDto);
        petMedicalHistoryDto.setAnimalDto(animalDto);
        petMedicalHistoryDto.setVetDto(vetDto);

        assertEquals(medicalHistoryDto, petMedicalHistoryDto.getMedicalHistoryDto());
        assertEquals(animalDto, petMedicalHistoryDto.getAnimalDto());
        assertEquals(vetDto, petMedicalHistoryDto.getVetDto());
    }

}
