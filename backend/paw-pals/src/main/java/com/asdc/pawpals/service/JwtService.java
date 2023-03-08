package com.asdc.pawpals.service;

import com.asdc.pawpals.utils.AuthenticationResponse;
import com.asdc.pawpals.utils.AuthenticationRequest;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public interface JwtService {

    public AuthenticationResponse authenticate(AuthenticationRequest jwtRequest) throws Exception;

}
