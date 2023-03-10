package com.asdc.pawpals.service;

import org.springframework.stereotype.Service;

import com.asdc.pawpals.utils.AuthenticationRequest;
import com.asdc.pawpals.utils.AuthenticationResponse;

@Service
public interface JwtService {

    public AuthenticationResponse authenticate(AuthenticationRequest jwtRequest) throws Exception;

}
