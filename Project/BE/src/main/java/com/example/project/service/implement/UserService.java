package com.example.project.service.implement;

import com.example.project.dto.request.LoginRequest;
import com.example.project.dto.response.LoginResponse;
import com.example.project.dto.request.RegisterRequest;
import com.example.project.dto.response.RegisterResponse;


public interface UserService {
    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);


}
