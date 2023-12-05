package com.example.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dto.UserDTO;
import com.example.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "list_of_tasks", source = "task_list")
    @Mapping(target = "list_of_projects", source = "project_list")
    UserDTO userToUserDTO(User user);
}
