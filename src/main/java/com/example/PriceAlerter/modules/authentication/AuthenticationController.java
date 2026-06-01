package com.example.PriceAlerter.modules.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PriceAlerter.modules.authentication.dto.LoginRequest;
import com.example.PriceAlerter.modules.authentication.dto.LoginResponse;
import com.example.PriceAlerter.modules.authentication.dto.RegisterRequest;
import com.example.PriceAlerter.modules.authentication.dto.RegisterResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody @Valid RegisterRequest request) {
        return authenticationService.register(request);
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {
        // Implement login logic here
        return authenticationService.login(request);
    }
}
