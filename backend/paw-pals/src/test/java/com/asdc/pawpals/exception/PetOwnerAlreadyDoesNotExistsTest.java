package com.asdc.pawpals.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class PetOwnerAlreadyDoesNotExistsTest {

    @Test
    public void testConstructorWithNoArgs() {
        PetOwnerAlreadyDoesNotExists ex = new PetOwnerAlreadyDoesNotExists();
        assertNull(ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Pet owner already does not exist";
        PetOwnerAlreadyDoesNotExists ex = new PetOwnerAlreadyDoesNotExists(message);
        assertEquals(message, ex.getMessage());
        assertNull(ex.getCause());
    }


    @Test
    public void testConstructorWithMessageAndCause() {
        String message = "Pet owner already does not exist";
        Throwable cause = new RuntimeException("Some runtime exception");
        PetOwnerAlreadyDoesNotExists ex = new PetOwnerAlreadyDoesNotExists(message, cause);
        assertEquals(message, ex.getMessage());
        assertEquals(cause, ex.getCause());
    }

    @Test
    public void testConstructorWithMessageCauseEnableSuppressionAndWritableStackTrace() {
        String message = "Pet owner already does not exist";
        Throwable cause = new RuntimeException("Some runtime exception");
        boolean enableSuppression = true;
        boolean writableStackTrace = true;
        PetOwnerAlreadyDoesNotExists ex = new PetOwnerAlreadyDoesNotExists(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, ex.getMessage());
        assertEquals(cause, ex.getCause());
        assertTrue(ex.getSuppressed().length == 0);
        assertNotNull(ex.getStackTrace());
    }
}
