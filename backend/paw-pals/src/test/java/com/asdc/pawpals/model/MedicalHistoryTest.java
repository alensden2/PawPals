package com.asdc.pawpals.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MedicalHistoryTest {

    private MedicalHistory medicalHistory;

    @Before
    public void setUp() {
        medicalHistory = new MedicalHistory();
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        assertNull(medicalHistory.getId());
        medicalHistory.setId(id);
        assertEquals(id, medicalHistory.getId());
    }

    @Test
    public void testSetAndGetDateDiagnosed() {
        String dateDiagnosed = "2022-01-01";
        assertNull(medicalHistory.getDateDiagnosed());
        medicalHistory.setDateDiagnosed(dateDiagnosed);
        assertEquals(dateDiagnosed, medicalHistory.getDateDiagnosed());
    }

    @Test
    public void testSetAndGetAilmentName() {
        String ailmentName = "Fever";
        assertNull(medicalHistory.getAilmentName());
        medicalHistory.setAilmentName(ailmentName);
        assertEquals(ailmentName, medicalHistory.getAilmentName());
    }

    @Test
    public void testSetAndGetPrescription() {
        String prescription = "Paracetamol";
        assertNull(medicalHistory.getPrescription());
        medicalHistory.setPrescription(prescription);
        assertEquals(prescription, medicalHistory.getPrescription());
    }

    @Test
    public void testSetAndGetVaccines() {
        String vaccines = "Vaccine A, Vaccine B";
        assertNull(medicalHistory.getVaccines());
        medicalHistory.setVaccines(vaccines);
        assertEquals(vaccines, medicalHistory.getVaccines());
    }

    @Test
    public void testSetAndGetAnimal() {
        Animal animal = new Animal();
        assertNull(medicalHistory.getAnimal());
        medicalHistory.setAnimal(animal);
        assertEquals(animal, medicalHistory.getAnimal());
    }

    @Test
    public void testSetAndGetVet() {
        Vet vet = new Vet();
        assertNull(medicalHistory.getVet());
        medicalHistory.setVet(vet);
        assertEquals(vet, medicalHistory.getVet());
    }

}
