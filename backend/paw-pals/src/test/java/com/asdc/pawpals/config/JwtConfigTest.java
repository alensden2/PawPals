package com.asdc.pawpals.config;

import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class JwtConfigTest {

    @InjectMocks
    private JwtConfig jwtConfig;

    @Mock
    private UserDetails userDetails;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testExtractUsername() {
        String token = jwtConfig.generateToken("testUser");
        String username = jwtConfig.extractUsername(token);
        assertEquals("testUser", username);
    }

    @Test
    public void testExtractExpiration() {
        String token = jwtConfig.generateToken("testUser");
        Date expiration = jwtConfig.extractExpiration(token);
        assertNotNull(expiration);
    }

    @Test
    public void testExtractClaim() {
        String token = jwtConfig.generateToken("testUser");
        String subject = jwtConfig.extractClaim(token, Claims::getSubject);
        assertEquals("testUser", subject);
    }


    @Test
    public void testValidateToken() {
        String token = jwtConfig.generateToken("testUser");
        when(userDetails.getUsername()).thenReturn("testUser");
        assertTrue(jwtConfig.validateToken(token, userDetails));
    }

    @Test
    public void testGenerateToken() {
        String token = jwtConfig.generateToken("testUser");
        assertNotNull(token);
        String username = jwtConfig.extractUsername(token);
        assertEquals("testUser", username);
    }
}
