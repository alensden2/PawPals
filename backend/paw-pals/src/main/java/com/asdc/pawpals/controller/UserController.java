package com.asdc.pawpals.controller;

import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.service.JwtService;
import com.asdc.pawpals.service.UserService;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.AuthenticationRequest;
import com.asdc.pawpals.utils.AuthenticationResponse;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;

import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin
@RequestMapping("/unauth/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    ApiResponse apiResponse;

    @PostMapping({"/authenticate"})
    public ResponseEntity<AuthenticationResponse> createJwtToken(@RequestBody
    AuthenticationRequest authenticationRequest) throws Exception {
    return ResponseEntity.ok(jwtService.authenticate(authenticationRequest));
    }

    // @GetMapping({"/auth/forAdmin"})
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    // public String forAdmin() {
    // return "This URL is only accessible by admin";
    // }

    // @GetMapping({"/auth/forPetOwner"})
    // @PreAuthorize("hasAuthority('ROLE_PET_OWNER')")
    // public String forPetOwner() {
    // return "This URL is only accessible by pet owner";
    // }

    // @GetMapping({"/auth/forVet"})
    // @PreAuthorize("hasAuthority('ROLE_VET')")
    // public String forVet() {
    // return "This URL is only accessible by vet";
    // }

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUsers();
    }

    
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
