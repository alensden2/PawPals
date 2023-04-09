package com.asdc.pawpals.config;

import com.asdc.pawpals.filter.JwtAuthFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SecurityConfigTest {

    @Mock
    private JwtAuthFilter authFilter;

    @Mock
    UserDetailsService userDetailsService;

    @InjectMocks
    private SecurityConfig securityConfig;

    @Mock
    private HttpSecurity http;

    @InjectMocks
    private AuthenticationConfiguration authenticationConfiguration;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testPasswordEncoderBeanCreation() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assertTrue(passwordEncoder.matches("test", passwordEncoder.encode("test")));
    }

    @Test
    public void testAuthenticationProviderBeanCreation() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        assertNotNull(authenticationProvider);

    }


}
