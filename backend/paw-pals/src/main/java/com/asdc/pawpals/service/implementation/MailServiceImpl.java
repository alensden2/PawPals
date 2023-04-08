package com.asdc.pawpals.service.implementation;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.asdc.pawpals.service.MailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final static String ENCODING_TYPE = "utf-8";
    Logger logger = LogManager.getLogger(MailServiceImpl.class);

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
        // Create a new MimeMessage object and set its properties
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();;
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MailServiceImpl.ENCODING_TYPE);
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
        } catch (MessagingException e) {
            logger.info(Arrays.toString(e.getStackTrace()));
        }

        // Use the mailSender object to send the message
        mailSender.send(mimeMessage);
    }
}
