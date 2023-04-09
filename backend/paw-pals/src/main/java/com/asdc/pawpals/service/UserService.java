package com.asdc.pawpals.service;

import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void initRolesAndUsers();

    public UserDto registerUser(UserDto user) throws UserAlreadyExist, InvalidUserDetails;
}
