package com.example.util;

import org.mapstruct.Mapper;

import com.example.dto.UserDTO;
import com.example.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends ApplicationMapper<User, UserDTO>{
}
