package com.example.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.dto.UserDTO;
import com.example.models.User;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(source = "tasks", target = "list_of_tasks")
  @Mapping(source = "projects", target = "list_of_projects")
  UserDTO userToUserDTO(User user);

  @Mapping(source = "list_of_tasks", target = "tasks")
  @Mapping(source = "list_of_projects", target = "projects")
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "roles", ignore = true)
  User userDTOToUser(UserDTO userDTO) throws Exception;

}
