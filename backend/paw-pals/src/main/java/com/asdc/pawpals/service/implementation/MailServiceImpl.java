package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.service.MailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    // Inject the JavaMailSender via constructor injection
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Send an email to the specified recipient with the given subject and body
     * @param to the recipient's email address
     * @param subject the subject of the email
     * @param body the body of the email
     */
    @Override
    public void sendMail(String to, String subject, String body) {
        // Create a new SimpleMailMessage object and set its properties
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        // Use the mailSender object to send the message
        mailSender.send(message);
    }
}
