package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserNameNotFoundTest {

    @Test
    public void testConstructorWithNoMessage() {
        UserNameNotFound ex = new UserNameNotFound();
        assertEquals(null, ex.getMessage());
    }

    @Test
    public void testConstructorWithMessage() {
        UserNameNotFound ex = new UserNameNotFound("User name not found");
        assertEquals("User name not found", ex.getMessage());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new Throwable("Cause of user name not found");
        UserNameNotFound ex = new UserNameNotFound(cause);
        assertEquals("Cause of user name not found", ex.getCause().getMessage());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        Throwable cause = new Throwable("Cause of user name not found");
        UserNameNotFound ex = new UserNameNotFound("User name not found", cause);
        assertEquals("User name not found", ex.getMessage());
        assertEquals("Cause of user name not found", ex.getCause().getMessage());
    }
}
