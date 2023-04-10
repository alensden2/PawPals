package com.asdc.pawpals.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppointmentValidatorsTest {

    @Test
    public void testIsValidDate() {
        assertTrue(AppointmentValidators.isValidDate("06-04-2023"));
        assertFalse(AppointmentValidators.isValidDate("31-04-2023")); // April has only 30 days
        assertFalse(AppointmentValidators.isValidDate("2023/04/06")); // Invalid date format
    }

    @Test
    public void testIsValidTime() {
        assertTrue(AppointmentValidators.isValidTime("09:30"));
        assertFalse(AppointmentValidators.isValidTime("24:00")); // Invalid time format
        assertFalse(AppointmentValidators.isValidTime("9:30")); // Invalid time format
    }

    @Test
    public void testIsValidStatus() {
        assertTrue(AppointmentValidators.isValidStatus("CONFIRMED"));
        assertTrue(AppointmentValidators.isValidStatus("REJECTED"));
        assertTrue(AppointmentValidators.isValidStatus("PENDING"));
        assertTrue(AppointmentValidators.isValidStatus("COMPLETED"));
        assertFalse(AppointmentValidators.isValidStatus("INVALID_STATUS"));
    }

    @Test
    public void testIsValidStartTimeBeforeEndTime() {
        assertTrue(AppointmentValidators.isValidStartTimeBeforeEndTime("09:00", "10:00"));
        assertFalse(AppointmentValidators.isValidStartTimeBeforeEndTime("10:00", "09:00"));
        assertFalse(AppointmentValidators.isValidStartTimeBeforeEndTime("09:00", "09:00"));
    }

    @Test
    public void testIsValidAppointment() {
        assertTrue(AppointmentValidators.isValidAppointment("06-04-2023", "09:00", "10:00", "CONFIRMED"));
        assertFalse(AppointmentValidators.isValidAppointment("31-04-2023", "09:00", "10:00", "CONFIRMED"));
        assertFalse(AppointmentValidators.isValidAppointment("06-04-2023", "10:00", "09:00", "CONFIRMED"));
        assertFalse(AppointmentValidators.isValidAppointment("06-04-2023", "09:00", "10:00", "INVALID_STATUS"));
    }
}
