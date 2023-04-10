package com.asdc.pawpals.exception;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class InvalidPetOwnerObjectTest {

    @Test
    public void testConstructorWithNoArguments() {
        InvalidPetOwnerObject exception = new InvalidPetOwnerObject();
        assertNull(exception.getMessage());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "This is a test message";
        InvalidPetOwnerObject exception = new InvalidPetOwnerObject(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "This is a test message";
        Throwable cause = new Throwable("This is a test cause");
        InvalidPetOwnerObject exception = new InvalidPetOwnerObject(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new Throwable("This is a test cause");
        InvalidPetOwnerObject exception = new InvalidPetOwnerObject(cause);
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
