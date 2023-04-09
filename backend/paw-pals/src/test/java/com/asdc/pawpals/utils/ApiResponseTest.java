package com.asdc.pawpals.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ApiResponseTest {

    @Test
    public void testConstructorAndGetters() {
        String message = "test message";
        boolean error = true;
        Object body = new Object();
        boolean success = false;

        ApiResponse response = new ApiResponse(message, error, body, success);

        Assertions.assertEquals(message, response.getMessage());
        Assertions.assertEquals(error, response.isError());
        Assertions.assertEquals(body, response.getBody());
        Assertions.assertEquals(success, response.isSuccess());
    }

    @Test
    public void testSetters() {
        ApiResponse response = new ApiResponse();

        String message = "test message";
        boolean error = true;
        Object body = new Object();
        boolean success = false;

        response.setMessage(message);
        response.setError(error);
        response.setBody(body);
        response.setSuccess(success);

        Assertions.assertEquals(message, response.getMessage());
        Assertions.assertEquals(error, response.isError());
        Assertions.assertEquals(body, response.getBody());
        Assertions.assertEquals(success, response.isSuccess());
    }


}
