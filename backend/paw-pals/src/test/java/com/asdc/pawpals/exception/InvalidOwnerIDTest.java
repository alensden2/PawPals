package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class InvalidOwnerIDTest {

    @Test
    public void testDefaultConstructor() {
        InvalidOwnerID exception = new InvalidOwnerID();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Invalid owner ID";
        InvalidOwnerID exception = new InvalidOwnerID(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Exception cause = new Exception("Cause");
        InvalidOwnerID exception = new InvalidOwnerID(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Invalid owner ID";
        Exception cause = new Exception("Cause");
        InvalidOwnerID exception = new InvalidOwnerID(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
