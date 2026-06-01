package com.example.PriceAlerter.modules.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank @Email(message ="email is required")String email, 
    @NotBlank (message = "password is required")String password
) {}
