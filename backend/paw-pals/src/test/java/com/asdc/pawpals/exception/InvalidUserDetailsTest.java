package com.asdc.pawpals.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class InvalidUserDetailsTest {

    @Test
    public void testConstructorWithNoArguments() {
        InvalidUserDetails e = new InvalidUserDetails();
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Invalid user details";
        InvalidUserDetails e = new InvalidUserDetails(message);
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }


    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Invalid user details";
        Throwable cause = new RuntimeException("Root cause");
        InvalidUserDetails e = new InvalidUserDetails(message, cause);
        assertEquals(message, e.getMessage());
        assertEquals(cause, e.getCause());
    }

    @Test
    public void testConstructorWithAllArguments() {
        String message = "Invalid user details";
        Throwable cause = new RuntimeException("Root cause");
        boolean enableSuppression = true;
        boolean writableStackTrace = true;
        InvalidUserDetails e = new InvalidUserDetails(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, e.getMessage());
        assertEquals(cause, e.getCause());
        assertTrue(e.getSuppressed().length == 0);
    }
}
