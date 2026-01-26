package com.example.project.mapper;

import com.example.project.dto.request.RegisterRequest;
import com.example.project.dto.response.RegisterResponse;
import com.example.project.entity.Role;
import com.example.project.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "role", expression = "java(Role.CUSTOMER)")
    User toEntity(RegisterRequest request);

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    @Mapping(target = "isActive", source = "isActive")
    RegisterResponse toRegisterResponse(User user);
}
