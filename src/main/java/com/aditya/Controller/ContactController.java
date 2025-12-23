package com.aditya.Controller;

import com.aditya.Service.EmailService;
import com.aditya.dto.ContactRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

        @Autowired
        private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendMessage(
            @Valid @RequestBody ContactRequest request) {
        emailService.sendContactMail(request);
        return ResponseEntity.ok("Message sent successfully!");
    }
    }

