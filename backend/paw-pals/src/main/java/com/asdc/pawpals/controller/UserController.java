package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.service.JwtService;
import com.asdc.pawpals.service.UserService;
import com.asdc.pawpals.utils.*;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller class is responsible for handling requests related to user management operations that do not require authentication.
 * <p>
 * It is annotated with @RestController and @RequestMapping("/unauth/user") to map incoming requests to this controller.
 * <p>
 * The class contains methods to handle user authentication and registration.
 */
@RestController
@RequestMapping("/unauth/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    ApiResponse apiResponse;


    /**
     * This method is used to create a JWT token for the provided user credentials by calling the authenticate() method of JwtService.
     * It takes an AuthenticationRequest object as the request body which contains the user's credentials.
     * The method returns a ResponseEntity with an AuthenticationResponse object which contains the JWT token if the authentication is successful.
     * If authentication fails, an exception is thrown.
     *
     * @param authenticationRequest An AuthenticationRequest object containing the user's credentials.
     * @return A ResponseEntity with an AuthenticationResponse object which contains the JWT token if authentication is successful.
     * @throws Exception If authentication fails.
     */

    @PostMapping({"/authenticate"})
    public ResponseEntity<AuthenticationResponse> createJwtToken(@RequestBody
                                                                 AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(jwtService.authenticate(authenticationRequest));
    }

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUsers();
    }

    /**
     * Endpoint for registering a user.
     *
     * @param requestBody the request body containing the user object details
     * @return a ResponseEntity object with ApiResponse as the response body, indicating the success or failure of the request
     * @throws UserAlreadyExist   if the user already exists in the system
     * @throws InvalidUserDetails if the user details are invalid or incomplete
     */

    @PostMapping({"/register"})
    public ResponseEntity<ApiResponse> registerUser(@RequestBody Object requestBody) throws UserAlreadyExist, InvalidUserDetails {
        logger.info("Received request as :", requestBody.toString());
        UserDto user = null;
        if (CommonUtils.isStrictTypeOf(requestBody, UserDto.class)) {
            user = ObjectMapperWrapper.getInstance().convertValue(requestBody, UserDto.class);
            apiResponse.setBody(userService.registerUser(user));
            apiResponse.setMessage("successfully inserted object");
            apiResponse.setSuccess(true);
            apiResponse.setError(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

}
