package com.asdc.pawpals.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VetAppointmentDtoTest {

    private VetAppointmentDto vetAppointmentDto;
    private AppointmentDto appointmentDto;
    private AnimalDto animalDto;
    private PetOwnerDto petOwnerDto;
    private MedicalHistoryDtoInline medicalHistoryDto;
    private List<MedicalHistoryDtoInline> medicalHistoryDtos;

    @Before
    public void setUp() {
        appointmentDto = new AppointmentDto();
        animalDto = new AnimalDto();
        petOwnerDto = new PetOwnerDto();
        medicalHistoryDto = new MedicalHistoryDtoInline();
        medicalHistoryDtos = new ArrayList<>();

        vetAppointmentDto = new VetAppointmentDto();
        vetAppointmentDto.setAppointmentDto(appointmentDto);
        vetAppointmentDto.setAnimalDto(animalDto);
        vetAppointmentDto.setPetOwnerDto(petOwnerDto);
        vetAppointmentDto.setMedicalHistoryDtos(medicalHistoryDtos);
    }

    @Test
    public void testGetAppointmentDto() {
        Assert.assertEquals(appointmentDto, vetAppointmentDto.getAppointmentDto());
    }

    @Test
    public void testSetAppointmentDto() {
        AppointmentDto newAppointmentDto = new AppointmentDto();
        vetAppointmentDto.setAppointmentDto(newAppointmentDto);
        Assert.assertEquals(newAppointmentDto, vetAppointmentDto.getAppointmentDto());
    }

    @Test
    public void testGetAnimalDto() {
        Assert.assertEquals(animalDto, vetAppointmentDto.getAnimalDto());
    }

    @Test
    public void testSetAnimalDto() {
        AnimalDto newAnimalDto = new AnimalDto();
        vetAppointmentDto.setAnimalDto(newAnimalDto);
        Assert.assertEquals(newAnimalDto, vetAppointmentDto.getAnimalDto());
    }

    @Test
    public void testGetPetOwnerDto() {
        Assert.assertEquals(petOwnerDto, vetAppointmentDto.getPetOwnerDto());
    }

    @Test
    public void testSetPetOwnerDto() {
        PetOwnerDto newPetOwnerDto = new PetOwnerDto();
        vetAppointmentDto.setPetOwnerDto(newPetOwnerDto);
        Assert.assertEquals(newPetOwnerDto, vetAppointmentDto.getPetOwnerDto());
    }

    @Test
    public void testGetMedicalHistoryDtos() {
        Assert.assertEquals(medicalHistoryDtos, vetAppointmentDto.getMedicalHistoryDtos());
    }

    @Test
    public void testSetMedicalHistoryDtos() {
        List<MedicalHistoryDtoInline> newMedicalHistoryDtos = new ArrayList<>();
        vetAppointmentDto.setMedicalHistoryDtos(newMedicalHistoryDtos);
        Assert.assertEquals(newMedicalHistoryDtos, vetAppointmentDto.getMedicalHistoryDtos());
    }
}
