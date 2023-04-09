package com.asdc.pawpals.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VetDtoTest {

    private VetDto vetDto;

    @Before
    public void setUp() {
        vetDto = new VetDto();
        vetDto.setUserName("testuser");
        vetDto.setPhoneNo("1234567890");
        vetDto.setLicenseNumber("AB12345");
        vetDto.setClinicAddress("123 Main St.");
        vetDto.setExperience(5);
        vetDto.setQualification("DVM");
    }

    @Test
    public void testGetUserName() {
        assertEquals("testuser", vetDto.getUsername());
    }

    @Test
    public void testGetPhoneNo() {
        assertEquals("1234567890", vetDto.getPhoneNo());
    }

    @Test
    public void testGetLicenseNumber() {
        assertEquals("AB12345", vetDto.getLicenseNumber());
    }

    @Test
    public void testGetClinicAddress() {
        assertEquals("123 Main St.", vetDto.getClinicAddress());
    }

    @Test
    public void testGetExperience() {
        assertEquals(5, vetDto.getExperience().intValue());
    }

    @Test
    public void testGetQualification() {
        assertEquals("DVM", vetDto.getQualification());
    }

    @Test
    public void testGetClinicUrl_Null() {
        assertNull(vetDto.getClinicUrl());
    }

    @Test
    public void testGetProfileStatus_Null() {
        assertNull(vetDto.getProfileStatus());
    }

    @Test
    public void testSetAndGetClinicUrl() {
        Byte[] image = { 0x01, 0x02, 0x03 };
        vetDto.setClinicUrl(image);
        assertNotNull(vetDto.getClinicUrl());
        assertEquals(3, vetDto.getClinicUrl().length);
        assertEquals(Byte.valueOf((byte)0x01), vetDto.getClinicUrl()[0]);
        assertEquals(Byte.valueOf((byte)0x02), vetDto.getClinicUrl()[1]);
        assertEquals(Byte.valueOf((byte)0x03), vetDto.getClinicUrl()[2]);
    }

    @Test
    public void testSetAndGetProfileStatus() {
        vetDto.setProfileStatus("ACTIVE");
        assertEquals("ACTIVE", vetDto.getProfileStatus());
    }

}
