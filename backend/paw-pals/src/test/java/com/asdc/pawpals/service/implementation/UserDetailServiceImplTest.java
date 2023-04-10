package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserDetailServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername() {
        // Given
        String username = "testUser";
        User user = new User();
        user.setUserId(username);
        user.setRole("admin,vet,pet");
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById(anyString())).thenReturn(optionalUser);

        // When
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        // Then
        assertNotNull(userDetails);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameWithInvalidUsername() {
        // Given
        String username = "testUser";
        Optional<User> optionalUser = Optional.empty();
        when(userRepository.findById(anyString())).thenReturn(optionalUser);

        // When
        userDetailService.loadUserByUsername(username);

        // Then expect UsernameNotFoundException to be thrown
    }
}
