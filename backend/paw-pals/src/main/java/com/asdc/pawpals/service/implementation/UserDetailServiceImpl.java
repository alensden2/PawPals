package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepository.findById(username);
        return userInfo.map(UserDto::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
