package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  /**

   * Loads a user with the given username from the database and returns a UserDetails object that
   * wraps the user information. Throws UsernameNotFoundException if the user is not found in the
   * database.
   * @param username the username of the user to load
   * @return a UserDetails object that wraps the user information
   * @throws UsernameNotFoundException if the user is not found in the database
*/
  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    Optional<User> userInfo = userRepository.findById(username);
    return userInfo
      .map(UserDto::new)
      .orElseThrow(
        () -> new UsernameNotFoundException("user not found " + username)
      );
  }
}
