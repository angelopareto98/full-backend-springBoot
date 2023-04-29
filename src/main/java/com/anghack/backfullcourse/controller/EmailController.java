package com.anghack.backfullcourse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anghack.backfullcourse.payload.ApiResponse;
import com.anghack.backfullcourse.payload.EmailRequest;
import com.anghack.backfullcourse.service.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping(path = "/send")
    public ResponseEntity<ApiResponse> sendMailSimple(@RequestBody EmailRequest emailRequest) {

        try {
            this.emailService.sendSimpleMessage(emailRequest.getTo(), emailRequest.getSubject(),
                    emailRequest.getText());

            return ResponseEntity.ok(new ApiResponse("Email sending successfuly", true));
        } catch (MessagingException e) {
            return new ResponseEntity<>(new ApiResponse("Email not sending", false),
                    HttpStatus.OK);
        }

    }

    @PostMapping(path = "/sendHtml")
    public ResponseEntity<ApiResponse> sendMailWithHtml(@RequestBody EmailRequest emailRequest) {

        try {
            this.emailService.sendHtmlMessage(emailRequest.getTo(), emailRequest.getSubject(),
                    emailRequest.getText());

            return ResponseEntity.ok(new ApiResponse("Email sending successfuly", true));
        } catch (MessagingException e) {
            return new ResponseEntity<>(new ApiResponse("Email not sending", false),
                    HttpStatus.EXPECTATION_FAILED);
        }

    }
}
