package com.asdc.pawpals.service;


public interface MailService {
    void sendMail(String to, String subject, String body);
}