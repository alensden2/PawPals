package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.service.implementation.JwtServiceImpl;
import com.asdc.pawpals.service.implementation.UserServiceImpl;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.AuthenticationRequest;
import com.asdc.pawpals.utils.AuthenticationResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userServiceImplMock;

    @Mock
    JwtServiceImpl jwtService;

    @Mock
    ApiResponse apiResponseMock;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerUser() throws UserAlreadyExist, InvalidUserDetails {

        UserDto userDto = new UserDto();
        userDto.setUserName("");
        userDto.setPassword("");
        when(userServiceImplMock.registerUser(any(UserDto.class))).thenReturn(userDto);
        ResponseEntity<ApiResponse> response = userController.registerUser(userDto);
        when(apiResponseMock.isSuccess()).thenReturn(true);
        when(apiResponseMock.isError()).thenReturn(false);
        ApiResponse apiResponse = response.getBody();
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());
    }


    @Test
    public void createJwtTokenWithUser() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken("Abc");
        authenticationResponse.setUser(new UserDto());
        when(jwtService.authenticate(authenticationRequest)).thenReturn(authenticationResponse);
        ResponseEntity<AuthenticationResponse> response = userController.createJwtToken(authenticationRequest);
        assertEquals("Abc", response.getBody().getToken());
        assertNotNull(response.getBody().getUser());

    }

    @Test
    public void createJwtTokenWithUserNUll() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken("");
        authenticationResponse.setUser(null);
        when(jwtService.authenticate(authenticationRequest)).thenReturn(authenticationResponse);
        ResponseEntity<AuthenticationResponse> response = userController.createJwtToken(authenticationRequest);
        assertEquals("", response.getBody().getToken());
        assertNull(response.getBody().getUser());

    }

    @Test
    public void testInitRolesAndUsers() {
        doNothing().when(userServiceImplMock).initRolesAndUsers();
        userController.initRolesAndUsers();

    }
}

