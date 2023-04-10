package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserAlreadyExistTest {

    @Test
    public void testDefaultConstructor() {
        UserAlreadyExist exception = new UserAlreadyExist();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testMessageConstructor() {
        String message = "User already exists";
        UserAlreadyExist exception = new UserAlreadyExist(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }


    @Test
    public void testMessageCauseConstructor() {
        String message = "User already exists";
        Throwable cause = new Throwable("Some cause");
        UserAlreadyExist exception = new UserAlreadyExist(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testSuppressedConstructor() {
        String message = "User already exists";
        Throwable cause = new Throwable("Some cause");
        boolean enableSuppression = true;
        boolean writableStackTrace = true;
        UserAlreadyExist exception = new UserAlreadyExist(message, cause, enableSuppression, writableStackTrace);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
