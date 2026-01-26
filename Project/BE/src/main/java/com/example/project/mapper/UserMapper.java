package com.example.project.mapper;

import com.example.project.dto.request.RegisterRequestDTO;
import com.example.project.dto.response.RegisterResponseDTO;
import com.example.project.entity.Role;
import com.example.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "role", expression = "java(Role.CUSTOMER)")
    User toEntity(RegisterRequestDTO request);

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    RegisterResponseDTO toRegisterResponse(User user);
}
