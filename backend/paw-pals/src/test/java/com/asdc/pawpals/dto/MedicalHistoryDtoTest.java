package com.asdc.pawpals.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MedicalHistoryDtoTest {

    @Test
    public void testConstructorAndGetters() {
        MedicalHistoryDto medicalHistory = new MedicalHistoryDto("2022-03-15", "Fever", "Ibuprofen", "Rabies", "VET001", 123L);
        assertEquals("2022-03-15", medicalHistory.getDateDiagnosed());
        assertEquals("Fever", medicalHistory.getAilmentName());
        assertEquals("Ibuprofen", medicalHistory.getPrescription());
        assertEquals("Rabies", medicalHistory.getVaccines());
        assertEquals("VET001", medicalHistory.getVetUserId());
        assertEquals(Long.valueOf(123), medicalHistory.getAnimalId());
    }

    @Test
    public void testSetters() {
        MedicalHistoryDto medicalHistory = new MedicalHistoryDto();
        medicalHistory.setDateDiagnosed("2022-03-15");
        medicalHistory.setAilmentName("Fever");
        medicalHistory.setPrescription("Ibuprofen");
        medicalHistory.setVaccines("Rabies");
        medicalHistory.setVetUserId("VET001");
        medicalHistory.setAnimalId(123L);

        assertEquals("2022-03-15", medicalHistory.getDateDiagnosed());
        assertEquals("Fever", medicalHistory.getAilmentName());
        assertEquals("Ibuprofen", medicalHistory.getPrescription());
        assertEquals("Rabies", medicalHistory.getVaccines());
        assertEquals("VET001", medicalHistory.getVetUserId());
        assertEquals(Long.valueOf(123), medicalHistory.getAnimalId());
    }

}
