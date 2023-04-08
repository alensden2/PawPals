/**

Configuration class for Spring Security.
*/
package com.asdc.pawpals.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.asdc.pawpals.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
private JwtAuthFilter authFilter;

/**
 * Creates the security filter chain for the HTTP requests.
 * 
 * @param http HttpSecurity object used to configure the security filter chain
 * @return a SecurityFilterChain object representing the security filter chain
 * @throws Exception if an error occurs while configuring the security filter chain
 */
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/unauth/**").permitAll()
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/auth/**")
            .authenticated().and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}

/**
 * Configures the authentication provider for the application.
 * 
 * @return an AuthenticationProvider object used for authentication
 */
@Bean
public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
}

/**
 * Configures the authentication manager for the application.
 * 
 * @param config an AuthenticationConfiguration object used for configuration
 * @return an AuthenticationManager object used for authentication
 * @throws Exception if an error occurs while configuring the authentication manager
 */
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
}

/**
 * Configures the password encoder for the application.
 * 
 * @return a PasswordEncoder object used for encoding passwords
 */
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Autowired
UserDetailsService userDetailsService;
// @Bean
// //authentication
// public UserDetailsService userDetailsService() {
// return new UserDetailServiceImpl();
// }

}
