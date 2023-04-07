package com.asdc.pawpals.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationRequestTest {

    @Test
    public void testConstructorAndGetters() {
        AuthenticationRequest authRequest = new AuthenticationRequest("testUser", "testPassword");
        Assertions.assertEquals("testUser", authRequest.getUsername());
        Assertions.assertEquals("testPassword", authRequest.getPassword());
    }

    @Test
    public void testSetters() {
        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setUsername("testUser");
        authRequest.setPassword("testPassword");
        Assertions.assertEquals("testUser", authRequest.getUsername());
        Assertions.assertEquals("testPassword", authRequest.getPassword());
    }


}
