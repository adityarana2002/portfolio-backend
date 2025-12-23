package com.aditya.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank(message = "Name is required")
    private  String name;
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Message is required")
    private String message;

}
