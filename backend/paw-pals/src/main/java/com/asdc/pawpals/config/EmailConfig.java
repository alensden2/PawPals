package com.asdc.pawpals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

@Configuration
public class EmailConfig {
    // Define constants for the email host, port, and username
    private final String host = "smtp.gmail.com";
    private final int port = 587;
    private final String username = "pawpals.noreply@gmail.com";

    // Inject the email password from an external configuration source
    @Value("${spring.mail.password}")
    private String password;

    // Create a bean for the JavaMailSender
    @Bean
    public JavaMailSender javaMailSender() {
        // Create a new instance of the JavaMailSender implementation
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Set the host, port, username, and password properties on the mailSender
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        // Set additional properties on the mailSender using a Properties object
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        // Return the configured mailSender as a bean
        return mailSender;
    }
}

