package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.exception.InvalidUserDetails;
import com.asdc.pawpals.exception.UserAlreadyExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.service.MailService;
import com.asdc.pawpals.utils.Transformations;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepositoryMock;


    @Mock
    PetOwnerRepository petOwnerRepositoryMock;

    @Mock
    MailService mailServiceMock;

    @InjectMocks
    UserServiceImpl userServiceImpl;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void objectCreated() {
        assertNotNull(userRepositoryMock);
    }


    @Test
    public void testRegisterUserForUserAlreadyExist() {
        // Arrange
        UserDto userDto = new UserDto();

        userDto.setPassword("testpassword");
        userDto.setEmail("test@example.com");
        userDto.setRole("ROLE_USER");
        userDto.setUserName("Username1");
        when(userRepositoryMock.existsById(anyString())).thenReturn(true);

        //Act
        UserAlreadyExist exception = assertThrows(UserAlreadyExist.class, () -> userServiceImpl.registerUser(userDto));

        //Assert
        Assertions.assertEquals("user exist in the system", exception.getMessage());

    }

    @Test
    public void testRegisterUser() throws UserAlreadyExist, InvalidUserDetails {
        // Arrange
        UserDto userDto = new UserDto();

        userDto.setPassword("testpassword");
        userDto.setEmail("test@example.com");
        userDto.setRole("ROLE_USER");
        userDto.setUserName("Username1");

        User user = Transformations.DTO_TO_MODEL_CONVERTER.user(userDto);
        when(userRepositoryMock.existsById(anyString())).thenReturn(false);
        when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        doNothing().when(mailServiceMock).sendMail(anyString(), anyString(), anyString());


        // Act
        UserDto returnedDto = userServiceImpl.registerUser(userDto);

        //Assert
        assertNotNull(returnedDto);
        assertNotNull(returnedDto.getPassword());
        assertNotNull(returnedDto.getUsername());
        assertNotNull(returnedDto.getRole());
        assertNotNull(returnedDto.getEmail());
        assertEquals(userDto.getEmail(), returnedDto.getEmail());
        assertEquals(userDto.getRole(), returnedDto.getRole());
    }

    @Test
    public void testRegisterForInvalidUserDetails() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUserName("Username1");
        userDto.setRole("ROLE_USER");


        //Act
        InvalidUserDetails exception = assertThrows(InvalidUserDetails.class, () -> userServiceImpl.registerUser(userDto));

        //Assert
        Assertions.assertEquals("incorrect user data " + userDto, exception.getMessage());

    }

    @Test
    public void testInitRolesAndUsers_PetOwnerUserSaved() throws UserNameNotFound, UserNameNotFound {
        User user2 = new User();
        user2.setUserId("pet_owner1");
        user2.setPassword("pet123");
        user2.setEmail("abcPet@gmail.com");
        user2.setRole("PET_OWNER");

        PetOwner petOwner = new PetOwner();
        petOwner.setUser(user2);
        petOwner.setAddress("16 Chris Hampton Rd");
        petOwner.setFirstName("James");
        petOwner.setLastName("Collin");
        petOwner.setPhoneNo("+1-723-453-2343");


        when(userRepositoryMock.save(any(User.class))).thenReturn(user2);
        when(petOwnerRepositoryMock.findByUser_UserId("pet_owner1")).thenReturn(Optional.empty());
        when(petOwnerRepositoryMock.save(any(PetOwner.class))).thenReturn(petOwner);

        userServiceImpl.initRolesAndUsers();

    }
}

