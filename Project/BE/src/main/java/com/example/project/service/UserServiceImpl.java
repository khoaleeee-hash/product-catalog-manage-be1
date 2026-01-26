package com.example.project.service;


import com.example.project.dto.request.LoginRequest;
import com.example.project.dto.request.RegisterRequest;
import com.example.project.dto.response.LoginResponse;
import com.example.project.dto.response.RegisterResponse;
import com.example.project.entity.User;
import com.example.project.mapper.UserMapper;
import com.example.project.repository.UserRepository;
import com.example.project.security.JwtTokenProvider;
import com.example.project.service.implement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmailAndActiveTrue(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new RuntimeException("Email already exists");
        }

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);
        return userMapper.toRegisterResponse(userRepository.save(user));
    }

}
