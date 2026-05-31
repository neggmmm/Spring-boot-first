package com.example.PriceAlerter.modules.authentication.dto;

import java.util.UUID;

import com.example.PriceAlerter.modules.users.User;

public class RegisterResponse {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;

    public RegisterResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
