package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.config.JwtConfig;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.service.JwtService;
import com.asdc.pawpals.utils.AuthenticationRequest;
import com.asdc.pawpals.utils.AuthenticationResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtServiceImpl implements JwtService {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  JwtConfig jwtConfig;

  /**
   * Authenticates a user by validating their credentials and generating a JWT token.
   *
   * @param jwtRequest the authentication request object containing the user's credentials
   * @return an authentication response object containing the JWT token and user details
   * @throws Exception if the user is disabled, the credentials are invalid, or no username is found
   */
  public AuthenticationResponse authenticate(AuthenticationRequest jwtRequest)
    throws Exception {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          jwtRequest.getUsername(),
          jwtRequest.getPassword()
        )
      );
    } catch (DisabledException exception) {
      throw new Exception("User is disabled");
    } catch (BadCredentialsException exception) {
      throw new Exception("Bad credentials from user");
    }
    UserDto userDto = userRepository
      .findById(jwtRequest.getUsername())
      .map(UserDto::new)
      .orElseThrow(
        () -> new AuthenticationServiceException("No username found ")
      );
    String jwtToken = jwtConfig.generateToken(userDto.getUsername());

    return AuthenticationResponse
      .builder()
      .token(jwtToken)
      .user(userDto)
      .build();
  }
}
