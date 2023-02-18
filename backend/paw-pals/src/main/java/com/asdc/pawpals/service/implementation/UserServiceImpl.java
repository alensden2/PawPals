package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRolesAndUsers(){
        User user1=new User();
        user1.setUserId("admin1");
        user1.setPassword(passwordEncoder.encode("admin123"));
        user1.setEmail("abcAdmin@gmail.com");
        user1.setRole("ROLE_ADMIN");

        User user2=new User();
        user2.setUserId("pet_owner1");
        user2.setPassword(passwordEncoder.encode("pet123"));
        user2.setEmail("abcPet@gmail.com");
        user2.setRole("ROLE_PET_OWNER");

        User user3=new User();
        user3.setUserId("vet1");
        user3.setPassword(passwordEncoder.encode("vet123"));
        user3.setEmail("abcVet@gmail.com");
        user3.setRole("ROLE_VET");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
