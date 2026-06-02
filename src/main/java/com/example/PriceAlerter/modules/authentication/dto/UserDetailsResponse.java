package com.example.PriceAlerter.modules.authentication.dto;

import java.util.UUID;

public record UserDetailsResponse(UUID id, String email, String name) {}
