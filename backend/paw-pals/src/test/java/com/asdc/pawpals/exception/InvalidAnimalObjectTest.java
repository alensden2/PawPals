package com.asdc.pawpals.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class InvalidAnimalObjectTest {

    @Test
    public void testInvalidAnimalObject() {
        InvalidAnimalObject invalidAnimalObject = new InvalidAnimalObject("Invalid animal object");
        assertEquals("Invalid animal object", invalidAnimalObject.getMessage());
    }
}
