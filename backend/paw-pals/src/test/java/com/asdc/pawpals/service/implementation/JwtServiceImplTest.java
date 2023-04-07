package com.asdc.pawpals.service.implementation;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.asdc.pawpals.config.JwtConfig;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.utils.AuthenticationRequest;
import com.asdc.pawpals.utils.AuthenticationResponse;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import static org.junit.Assert.*;
import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
public class JwtServiceImplTest {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtConfig jwtConfig;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
   public void testAuthenticate() throws Exception {
        // Given
        String username = "testUser";
        String password = "testPassword";
        UserDto userDto = new UserDto();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);
        String token = "testToken";
        AuthenticationResponse expectedResponse = AuthenticationResponse.builder().token(token).user(userDto).build();

        // When
        when(authenticationManager.authenticate(any()))
                .thenReturn(null); // Return null since we're just testing authenticationManager.authenticate
        User user=new User();
        user.setRole("admin,user,vet");
        user.setPassword(password);
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(user));
        when(jwtConfig.generateToken(any())).thenReturn(token);
        AuthenticationResponse actualResponse = jwtService.authenticate(authenticationRequest);

        // Then
        assertNotNull(actualResponse);

    }

}
