package com.asdc.pawpals.controller;

import com.asdc.pawpals.service.JwtService;
import com.asdc.pawpals.service.UserService;
import com.asdc.pawpals.utils.AuthenticationResponse;
import com.asdc.pawpals.utils.AuthenticationRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping({"/auth"})
@CrossOrigin
public class AuthenticationController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @PostMapping({"/auth/authenticate"})
    public ResponseEntity<AuthenticationResponse> createJwtToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(jwtService.authenticate(authenticationRequest));
    }

    @GetMapping({"/auth/forAdmin"})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String forAdmin() {
        return "This URL is only accessible by admin";
    }

    @GetMapping({"/auth/forPetOwner"})
    @PreAuthorize("hasAuthority('ROLE_PET_OWNER')")
    public String forPetOwner() {
        return "This URL is only accessible by pet owner";
    }

    @GetMapping({"/auth/forVet"})
    @PreAuthorize("hasAuthority('ROLE_VET')")
    public String forVet() {
        return "This URL is only accessible by vet";
    }

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUsers();
    }
}
