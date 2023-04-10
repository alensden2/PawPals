package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class NoPetRegisterUnderPetOwnerTest {

    @Test
    public void testConstructorWithNoArguments() {
        NoPetRegisterUnderPetOwner ex = new NoPetRegisterUnderPetOwner();
        assertNull(ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "No pet registered under this pet owner";
        NoPetRegisterUnderPetOwner ex = new NoPetRegisterUnderPetOwner(message);
        assertEquals(message, ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Some runtime exception");
        NoPetRegisterUnderPetOwner ex = new NoPetRegisterUnderPetOwner(cause);
        assertEquals(cause, ex.getCause());
        assertNotNull(ex.getMessage());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "No pet registered under this pet owner";
        Throwable cause = new RuntimeException("Some runtime exception");
        NoPetRegisterUnderPetOwner ex = new NoPetRegisterUnderPetOwner(message, cause);
        assertEquals(message, ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
