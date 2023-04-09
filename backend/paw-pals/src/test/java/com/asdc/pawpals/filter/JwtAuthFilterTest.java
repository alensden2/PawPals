package com.asdc.pawpals.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.asdc.pawpals.config.JwtConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class JwtAuthFilterTest {

    @Mock
    private JwtConfig jwtConfig;

    @Mock
    private UserDetailsService userDetailService;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testDoFilterInternal_InvalidToken_AuthenticationUnsuccessful() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        String username = "testUser";
        UserDetails userDetails = mock(UserDetails.class);

        SecurityContextHolder.getContext().setAuthentication(null);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());

    }

    @Test
    public void testDoFilterInternal_NoToken_AuthenticationUnsuccessful() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        SecurityContextHolder.getContext().setAuthentication(null);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());

    }

}
