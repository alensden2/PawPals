package com.asdc.pawpals.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class EmailConfigTest {

    @Mock
    private JavaMailSenderImpl javaMailSenderMock;

    @InjectMocks
    private EmailConfig emailConfig;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void javaMailSenderBeanCreated() {
        assertNotNull(emailConfig.javaMailSender());
    }

}
