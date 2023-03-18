package com.asdc.pawpals.dto;

import com.asdc.pawpals.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@Getter
@Setter

public class UserDto implements UserDetails {
  private String userName;
  private String password;
  private String firstName;
  private String lastName;
  private String role;

  private String email;
  private List<GrantedAuthority> authorities;

  public UserDto(User userInfo) {
    userName = userInfo.getUserId();
    password = userInfo.getPassword();
    email = userInfo.getEmail();
    authorities =
      Arrays
        .stream(userInfo.getRole().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  @JsonProperty(value = "userName")
  public String getUsername() {
    return userName;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }
}
