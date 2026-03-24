package com.kabir.productApp.service;

import com.kabir.productApp.dto.AuthResponse;
import com.kabir.productApp.dto.LoginRequest;
import com.kabir.productApp.dto.SignupRequest;
import com.kabir.productApp.dto.UserDto;
import com.kabir.productApp.entity.UserEntity;
import com.kabir.productApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setStatus("ACTIVE");

        UserEntity savedUser = userRepository.save(user);
        String token = jwtService.generateToken(toUserDetails(savedUser));

        return buildResponse(token, savedUser);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(toUserDetails(user));

        return buildResponse(token, user);
    }

    public void logout(String token) {
        tokenBlacklistService.blacklist(token);
    }

    private UserDetails toUserDetails(UserEntity user) {
        return new User(user.getEmail(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }

    private AuthResponse buildResponse(String token, UserEntity user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return AuthResponse.builder().token(token).user(dto).build();
    }
}
