package com.asdc.pawpals.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class VetTest {

    private Vet vet;

    @Before
    public void setUp() {
        vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("John");
        vet.setLastName("Doe");
        vet.setLicenseNumber("123456");
        vet.setClinicAddress("123 Main Street");
        vet.setExperience(5);
        vet.setQualification("DVM");
        vet.setProfileStatus("Active");
        vet.setPhoneNo("555-555-5555");
        vet.setClinicUrl(new Byte[] { 0x01, 0x02, 0x03 });
        User user = new User();
        user.setUserId("johndoe");
        user.setRole("Vet");
        user.setEmail("johndoe@example.com");
        user.setPassword("password");
        vet.setUser(user);
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment1 = new Appointment();
        appointment1.setId(1);
        appointment1.setVet(vet);
        appointments.add(appointment1);
        vet.setAppointment(appointments);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, vet.getId().longValue());
        assertEquals("John", vet.getFirstName());
        assertEquals("Doe", vet.getLastName());
        assertEquals("123456", vet.getLicenseNumber());
        assertEquals("123 Main Street", vet.getClinicAddress());
        assertEquals(5, vet.getExperience().intValue());
        assertEquals("DVM", vet.getQualification());
        assertEquals("Active", vet.getProfileStatus());
        assertEquals("555-555-5555", vet.getPhoneNo());
        assertArrayEquals(new Byte[] { 0x01, 0x02, 0x03 }, vet.getClinicUrl());
        assertNotNull(vet.getUser());
        assertEquals("johndoe", vet.getUser().getUserId());
        assertEquals("Vet", vet.getUser().getRole());
        assertEquals("johndoe@example.com", vet.getUser().getEmail());
        assertEquals("password", vet.getUser().getPassword());
        assertNotNull(vet.getAppointment());
        assertEquals(1, vet.getAppointment().size());
        assertEquals(1, vet.getAppointment().get(0).getId().intValue());
    }


}
