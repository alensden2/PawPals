package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
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
