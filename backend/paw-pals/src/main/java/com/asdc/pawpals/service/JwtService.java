package com.asdc.pawpals.service;

import com.asdc.pawpals.utils.AuthenticationRequest;
import com.asdc.pawpals.utils.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    public AuthenticationResponse authenticate(AuthenticationRequest jwtRequest) throws Exception;

}
