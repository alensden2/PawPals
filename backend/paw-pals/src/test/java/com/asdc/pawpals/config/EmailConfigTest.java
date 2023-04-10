package com.asdc.pawpals.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
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
