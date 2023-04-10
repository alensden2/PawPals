package com.asdc.pawpals.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.asdc.pawpals.model.User;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserDtoTest {

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAuthorities() {
        String role = "ROLE_USER";
        UserDto userDto = new UserDto();
        userDto.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(role)));
        Collection<? extends GrantedAuthority> authorities = userDto.getAuthorities();
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(role)));
    }

    @Test
    public void testGetPassword() {
        String password = "password";
        UserDto userDto = new UserDto();
        userDto.setPassword(password);
        assertEquals(password, userDto.getPassword());
    }

    @Test
    public void testGetUsername() {
        String userName = "user1";
        UserDto userDto = new UserDto();
        userDto.setUserName(userName);
        assertEquals(userName, userDto.getUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        UserDto userDto = new UserDto();
        assertTrue(userDto.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        UserDto userDto = new UserDto();
        assertTrue(userDto.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        UserDto userDto = new UserDto();
        assertTrue(userDto.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        UserDto userDto = new UserDto();
        assertTrue(userDto.isEnabled());
    }

    @Test
    public void testUserDtoConstructor() {
        String userName = "user1";
        String password = "password";
        String role = "ROLE_USER";
        String email = "user1@test.com";
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        User user = new User();
        user.setUserId(userName);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);
        UserDto userDto = new UserDto(user);
        assertEquals(userName, userDto.getUsername());
        assertEquals(password, userDto.getPassword());
        assertEquals(authorities, userDto.getAuthorities());
        assertEquals(email, userDto.getEmail());
    }

}
