package com.clinic.service;

import com.clinic.dto.auth.LoginRequest;
import com.clinic.dto.auth.LoginResponse;
import com.clinic.dto.auth.RegisterRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    void register(RegisterRequest request);
}
