package com.example.PriceAlerter.modules.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PriceAlerter.modules.authentication.dto.LoginRequest;
import com.example.PriceAlerter.modules.authentication.dto.LoginResponse;
import com.example.PriceAlerter.modules.authentication.dto.RegisterRequest;
import com.example.PriceAlerter.modules.authentication.dto.RegisterResponse;
import com.example.PriceAlerter.modules.authentication.dto.UserDetailsResponse;
import com.example.PriceAlerter.modules.users.User;

import jakarta.servlet.http.HttpServletResponse;
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
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletResponse response) {

        LoginResponse result = authenticationService.login(request);
        response.setHeader("Authorization", "Bearer " + result.token());

        // Also expose it so frontend JS can read it (blocked by default)
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return result;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailsResponse getCurrentUser(@AuthenticationPrincipal User currentUser) {
        // Implement logic to get current user details here
        return new UserDetailsResponse(currentUser.getId(), currentUser.getEmail(), currentUser.getName());
    }
}
