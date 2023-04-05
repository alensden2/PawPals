package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.service.UserService;
import com.asdc.pawpals.utils.Transformations;
import com.asdc.pawpals.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    MailService mailService;

    public void initRolesAndUsers() {
        User user1 = new User();
        user1.setUserId("admin1");
        user1.setPassword(passwordEncoder.encode("admin123"));
        user1.setEmail("abcAdmin@gmail.com");
        user1.setRole("ADMIN");

        User user2 = new User();
        user2.setUserId("pet_owner1");
        user2.setPassword(passwordEncoder.encode("pet123"));
        user2.setEmail("abcPet@gmail.com");
        user2.setRole("PET_OWNER");

        User user3 = new User();
        user3.setUserId("vet1");
        user3.setPassword(passwordEncoder.encode("vet123"));
        user3.setEmail("abcVet@gmail.com");
        user3.setRole("VET");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Override
    public UserDto registerUser(UserDto userDto) throws UserAlreadyExist, InvalidUserDetails {
        UserDto returnedDto = null;

        if (userDto != null && userDto.getEmail() !=null && userDto.getUsername()!=null && userDto.getPassword()!=null && userDto.getRole()!=null) {
            User user = Transformations.DTO_TO_MODEL_CONVERTER.user(userDto);
            if(userRepository.existsById(user.getUserId()))
            {
                throw new UserAlreadyExist("user exist in the system");
            }
            user = userRepository.save(user);
            returnedDto = Transformations.MODEL_TO_DTO_CONVERTER.user(user);
        }
        else
        {
            throw new InvalidUserDetails("incorrect user data "+ userDto);
        }

        String subject = "Register Success";
        String body = "Welcome to Pawpals";
        String to = userDto.getEmail();
        mailService.sendMail(to, subject, body);

        return returnedDto;

    }
}
