package com.asdc.pawpals.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class InvalidVetIDTest {

    @Test
    public void testConstructorWithNoArguments() {
        InvalidVetID ex = new InvalidVetID();
        assertNull(ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Invalid Vet ID";
        InvalidVetID ex = new InvalidVetID(message);
        assertEquals(message, ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Something went wrong");
        InvalidVetID ex = new InvalidVetID(cause);
        assertEquals(cause, ex.getCause());
        assertNotNull(ex.getMessage());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Invalid Vet ID";
        Throwable cause = new RuntimeException("Something went wrong");
        InvalidVetID ex = new InvalidVetID(message, cause);
        assertEquals(message, ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
