package com.example.project.service.implement;

import com.example.project.dto.request.LoginRequestDTO;
import com.example.project.dto.response.LoginResponseDTO;


package com.example.project.service.User;

import com.example.project.dto.request.LoginRequestDTO;
import com.example.project.dto.request.RegisterRequestDTO;
import com.example.project.dto.response.LoginResponseDTO;
import com.example.project.dto.response.RegisterResponseDTO;
import com.example.project.dto.response.UserResponseDTO;


public interface UserService {
    LoginResponseDTO login(LoginRequestDTO request);
    RegisterResponseDTO register(RegisterRequestDTO request);


}
