package com.asdc.pawpals.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class InvalidAnimalObjectTest {

    @Test
    public void testInvalidAnimalObject() {
        InvalidAnimalObject invalidAnimalObject = new InvalidAnimalObject("Invalid animal object");
        assertEquals("Invalid animal object", invalidAnimalObject.getMessage());
    }
}
