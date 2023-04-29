package com.anghack.backfullcourse.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text) throws MessagingException;

    void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException;
}
