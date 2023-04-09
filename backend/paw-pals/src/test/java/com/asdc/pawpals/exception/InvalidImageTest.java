package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class InvalidImageTest {

    @Test
    public void testInvalidImageConstructorWithNullMessage() {
        InvalidImage exception = new InvalidImage();
        assertNull(exception.getMessage());
    }

    @Test
    public void testInvalidImageConstructorWithMessage() {
        String message = "Invalid image format";
        InvalidImage exception = new InvalidImage(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testInvalidImageConstructorWithMessageAndCause() {
        String message = "Invalid image format";
        Exception cause = new Exception("An exception occurred while processing the image");
        InvalidImage exception = new InvalidImage(message, cause);
        assertEquals(message, exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testInvalidImageConstructorWithCause() {
        Exception cause = new Exception("An exception occurred while processing the image");
        InvalidImage exception = new InvalidImage(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertNotNull(exception.getCause());
        assertEquals(cause, exception.getCause());
    }
}