package com.clinic.service.impl;

import com.clinic.dto.auth.LoginRequest;
import com.clinic.dto.auth.LoginResponse;
import com.clinic.dto.auth.RegisterRequest;
import com.clinic.entity.Role;
import com.clinic.entity.User;
import com.clinic.exception.BusinessException;
import com.clinic.repository.RoleRepository;
import com.clinic.repository.UserRepository;
import com.clinic.security.JwtService;
import com.clinic.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        log.debug("Authenticating user: {}", request.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtService.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        Set<String> roles = user.getRoles().stream()
                .map(r -> r.getName().name())
                .collect(Collectors.toSet());

        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .username(user.getUsername())
                .roles(roles)
                .build();
    }

    @Override
    public void register(RegisterRequest request) {
        log.debug("Registering user: {}", request.getUsername());
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException("Username already exists");
        }

        Set<Role> roles = new HashSet<>();
        for (String roleName : request.getRoles()) {
            Role.RoleName name = Role.RoleName.valueOf(roleName.toUpperCase());
            Role role = roleRepository.findByName(name)
                    .orElseGet(() -> roleRepository.save(Role.builder().name(name).build()));
            roles.add(role);
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .enabled(true)
                .roles(roles)
                .build();

        userRepository.save(user);
    }
}
