package com.asdc.pawpals.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AnimalTest {

    private Animal animal;
    private PetOwner owner;
    private MedicalHistory medicalHistory;
    private List<MedicalHistory> medicalHistories;
    private Appointment appointment;
    private List<Appointment> appointments;

    @Before
    public void setUp() {
        animal = new Animal();
        owner = new PetOwner();
        medicalHistory = new MedicalHistory();
        medicalHistories = new ArrayList<>();
        appointment = new Appointment();
        appointments = new ArrayList<>();
    }

    @Test
    public void testSetAndGetId() {
        animal.setId(1L);
        Assert.assertEquals(1L, animal.getId().longValue());
    }

    @Test
    public void testSetAndGetName() {
        animal.setName("Rufus");
        Assert.assertEquals("Rufus", animal.getName());
    }

    @Test
    public void testSetAndGetAge() {
        animal.setAge(2);
        Assert.assertEquals(2, animal.getAge().intValue());
    }

    @Test
    public void testSetAndGetType() {
        animal.setType("Dog");
        Assert.assertEquals("Dog", animal.getType());
    }

    @Test
    public void testSetAndGetGender() {
        animal.setGender("Male");
        Assert.assertEquals("Male", animal.getGender());
    }

    @Test
    public void testSetAndGetOwner() {
        animal.setOwner(owner);
        Assert.assertEquals(owner, animal.getOwner());
    }

    @Test
    public void testSetAndGetMedicalHistories() {
        medicalHistories.add(medicalHistory);
        animal.setMedicalHistories(medicalHistories);
        Assert.assertEquals(medicalHistories, animal.getMedicalHistories());
    }

    @Test
    public void testSetAndGetAppointments() {
        appointments.add(appointment);
        animal.setAppointment(appointments);
        Assert.assertEquals(appointments, animal.getAppointment());
    }

    @Test
    public void testSetAndGetPhotoUrl() {
        Byte[] photoUrl = new Byte[]{1, 2, 3};
        animal.setPhotoUrl(photoUrl);
        Assert.assertEquals(photoUrl, animal.getPhotoUrl());
    }

}
