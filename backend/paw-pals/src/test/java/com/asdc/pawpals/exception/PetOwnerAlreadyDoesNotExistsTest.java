package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
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
