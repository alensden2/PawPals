package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.service.implementation.JwtServiceImpl;
import com.asdc.pawpals.service.implementation.UserServiceImpl;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.AuthenticationRequest;
import com.asdc.pawpals.utils.AuthenticationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    UserServiceImpl userServiceImplMock;


    JwtServiceImpl jwtService;

    @BeforeEach
    public void setup() {
        userServiceImplMock = mock(UserServiceImpl.class);
        userController.userService = userServiceImplMock;
        jwtService=mock(JwtServiceImpl.class);
        userController.jwtService=jwtService;
    }


    @Test
   public void registerUser() throws UserAlreadyExist, InvalidUserDetails {

        UserDto userDto = new UserDto();
        userDto.setUserName("");
        userDto.setPassword("");
        Object requestBody = userDto;
        when(userServiceImplMock.registerUser(userDto)).thenReturn(userDto);
        ResponseEntity<ApiResponse> response = userController.registerUser(requestBody);
        ApiResponse apiResponse = response.getBody();
        assertTrue(apiResponse.isSuccess());
        assertFalse(apiResponse.isError());
    }


    @Test
    public void createJwtTokenWithUser() throws Exception {
        AuthenticationRequest authenticationRequest=new AuthenticationRequest();
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setToken("Abc");
        authenticationResponse.setUser(new UserDto());
        when(jwtService.authenticate(authenticationRequest)).thenReturn(authenticationResponse);
        ResponseEntity<AuthenticationResponse> response = userController.createJwtToken(authenticationRequest);
        assertEquals("Abc",response.getBody().getToken());
        assertNotNull(response.getBody().getUser());

    }

    @Test
    public void createJwtTokenWithUserNUll() throws Exception {
        AuthenticationRequest authenticationRequest=new AuthenticationRequest();
        AuthenticationResponse authenticationResponse=new AuthenticationResponse();
        authenticationResponse.setToken("");
        authenticationResponse.setUser(null);
        when(jwtService.authenticate(authenticationRequest)).thenReturn(authenticationResponse);
        ResponseEntity<AuthenticationResponse> response = userController.createJwtToken(authenticationRequest);
        assertEquals("",response.getBody().getToken());
        assertNull(response.getBody().getUser());

    }
}

