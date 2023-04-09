package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.Enums.ProfileStatus;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.MailService;
import com.asdc.pawpals.service.UserService;
import com.asdc.pawpals.utils.MailTemplates;
import com.asdc.pawpals.utils.Transformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    VetRepository vetRepository;

    @Autowired
    PetOwnerRepository petOwnerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    MailService mailService;

    @Value("${spring.profiles.active}")
    String activeProfile;

    /**
     * Initializes the roles and users for development environment.
     * Creates an admin user,
     * a pet owner user and a vet user with pre-defined user IDs,
     * passwords, emails and roles. Also creates a vet and a pet owner
     * object and links them to the respective user object.
     * Saves the user and pet owner/vet objects to their respective repositories.
     */
    public void initRolesAndUsers() {
        if (activeProfile != null && activeProfile.equals("dev")) {
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

            try {
                if (vetRepository.findByUser_UserId(user3.getUserId()).isEmpty()) {
                    Vet vet = new Vet();
                    vet.setUser(user3);
                    vet.setFirstName("John");
                    vet.setLastName("Doe");
                    vet.setClinicAddress("19 Inglis St");
                    vet.setExperience(3);
                    vet.setPhoneNo("+1-723-443-2343");
                    vet.setProfileStatus(ProfileStatus.APPROVED.getLabel());
                    vet.setQualification("MBBS, MD, DO");
                    vetRepository.save(vet);
                }

                if (petOwnerRepository.findByUser_UserId(user2.getUserId()).isEmpty()) {
                    PetOwner petOwner = new PetOwner();
                    petOwner.setUser(user2);
                    petOwner.setAddress("16 Chris Hampton Rd");
                    petOwner.setFirstName("James");
                    petOwner.setLastName("Collin");
                    petOwner.setPhoneNo("+1-723-453-2343");
                    petOwnerRepository.save(petOwner);
                }
            } catch (UserNameNotFound e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Registers a user by creating a new user in the system and sending a registration confirmation email.
     *
     * @param userDto A UserDto object containing the details of the user to be registered.
     * @return A UserDto object containing the details of the registered user.
     * @throws UserAlreadyExist   If a user with the same user ID already exists in the system.
     * @throws InvalidUserDetails If the provided userDto is null or has null fields for email, username, password, or role.
     */
    @Override
    public UserDto registerUser(UserDto userDto)
            throws UserAlreadyExist, InvalidUserDetails {
        UserDto returnedDto = null;

        boolean isEmailValid = userDto != null && userDto.getEmail() != null;
    boolean isUsernameValid = userDto != null && userDto.getUsername() != null;
    boolean isPasswordValid = userDto != null && userDto.getPassword() != null;
    boolean isRoleValid = userDto != null && userDto.getRole() != null;

    /**
 * Old code - 
 * if (
      userDto != null &&
      userDto.getEmail() != null &&
      userDto.getUsername() != null &&
      userDto.getPassword() != null &&
      userDto.getRole() != null
    )
 */

    if (isEmailValid && isUsernameValid && isPasswordValid && isRoleValid) {
      User user = Transformations.DTO_TO_MODEL_CONVERTER.user(userDto);
            if (userRepository.existsById(user.getUserId())) {
                throw new UserAlreadyExist("user exist in the system");
            }
            user = userRepository.save(user);
            returnedDto = Transformations.MODEL_TO_DTO_CONVERTER.user(user);
        } else {
            throw new InvalidUserDetails("incorrect user data " + userDto);
        }

        String subject = "Registration Successful @ PawPals";
        String body = MailTemplates.getRegistrationSuccessfulString();
        String to = userDto.getEmail();
        mailService.sendMail(to, subject, body);

        return returnedDto;
    }
}
