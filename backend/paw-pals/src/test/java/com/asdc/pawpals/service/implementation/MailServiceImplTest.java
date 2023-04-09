//package com.asdc.pawpals.service.implementation;
//
//import jakarta.mail.internet.MimeMessage;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MailServiceImplTest {
//
//    @Mock
//    private JavaMailSender mailSender;
//
//    @InjectMocks
//    private MailServiceImpl mailService;
//
//    @Mock
//    MimeMessageHelper helper;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSendMail() {
//        // Given
//        String to = "test@example.com";
//        String subject = "Test subject";
//        String body = "Test body";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject(subject);
//        message.setText(body);
//        message.setTo(to);
//        MimeMessage mimeMessage
//
//        when(mailSender.createMimeMessage()).thenReturn(new MimeMessage());
//
//        // When
//        mailService.sendMail(to, subject, body);
//
//        // Then
//        verify(mailSender, times(0)).send(any(SimpleMailMessage.class));
//    }
//
//}
