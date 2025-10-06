package com.myfin.notification.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private static final Logger log = LoggerFactory.getLogger(EmailSender.class);

    public void sendEmail(String toUser, String subject, String body) {
        // Simulate sending email
        log.info("Sending email to {} | Subject: {} | Body: {}", toUser, subject, body);
    }
}
