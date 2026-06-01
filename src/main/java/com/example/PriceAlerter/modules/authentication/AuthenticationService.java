package com.example.PriceAlerter.modules.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.PriceAlerter.common.jwt.JwtService;
import com.example.PriceAlerter.modules.authentication.dto.LoginRequest;
import com.example.PriceAlerter.modules.authentication.dto.LoginResponse;
import com.example.PriceAlerter.modules.authentication.dto.RegisterRequest;
import com.example.PriceAlerter.modules.authentication.dto.RegisterResponse;
import com.example.PriceAlerter.modules.users.User;
import com.example.PriceAlerter.modules.users.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request) {
        // This single line: loads user by email, checks password, throws if wrong
        // It uses the authenticationProvider you wired in SecurityConfig
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        // If we get here, credentials are valid — load user and generate token
        User user = usersRepository.findByEmail(request.email()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new LoginResponse(token, user.getEmail(), user.getName());
    }

    public RegisterResponse register(RegisterRequest request) {
        if (usersRepository.existsByEmailIgnoreCase(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        User user = new User();
        user.setName(request.name().trim());
        user.setEmail(request.email().trim().toLowerCase());
        user.setPhoneNumber(request.phoneNumber().trim());
        user.setPassword(passwordEncoder.encode(request.password()));

        User savedUser = usersRepository.save(user);
        return new RegisterResponse(savedUser.getEmail(), savedUser.getName());
    }

}
