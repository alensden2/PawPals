package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidAnimalIdTest {

    @Test
    public void testConstructorWithMessage() {
        String message = "Invalid Animal Id entered";
        InvalidAnimalId exception = new InvalidAnimalId(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Invalid Animal Id entered";
        Exception cause = new Exception("Some cause");
        InvalidAnimalId exception = new InvalidAnimalId(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Exception cause = new Exception("Some cause");
        InvalidAnimalId exception = new InvalidAnimalId(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
