package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class NoAppointmentExistTest {

    @Test
    public void testNoAppointmentExist() {
        NoAppointmentExist e = new NoAppointmentExist();
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    public void testNoAppointmentExistWithMessage() {
        String message = "No appointment exists";
        NoAppointmentExist e = new NoAppointmentExist(message);
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    public void testNoAppointmentExistWithCause() {
        String causeMessage = "Some cause";
        Throwable cause = new RuntimeException(causeMessage);
        NoAppointmentExist e = new NoAppointmentExist(cause);
        assertEquals(cause, e.getCause());
        assertEquals(causeMessage, e.getCause().getMessage());
    }

    @Test
    public void testNoAppointmentExistWithMessageAndCause() {
        String message = "No appointment exists";
        String causeMessage = "Some cause";
        Throwable cause = new RuntimeException(causeMessage);
        NoAppointmentExist e = new NoAppointmentExist(message, cause);
        assertEquals(message, e.getMessage());
        assertEquals(cause, e.getCause());
        assertEquals(causeMessage, e.getCause().getMessage());
    }

}
