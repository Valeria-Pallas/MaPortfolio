package com.example.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dto.UserDTO;
import com.example.models.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "project.id", target = "projectId")

    UserDTO userToUserDTO(User user);

}
