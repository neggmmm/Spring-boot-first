package com.example.PriceAlerter.modules.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
    @NotBlank(message = "Name is required") String name,
    @NotBlank @Email(message = "Valid email is required") String email,
    @NotBlank(message = "Password is required") String password,
    @NotBlank(message = "Phone number is required") String phoneNumber
) {}