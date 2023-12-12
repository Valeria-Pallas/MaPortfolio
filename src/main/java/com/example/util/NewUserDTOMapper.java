package com.example.util;

import org.mapstruct.Mapper;

import com.example.dto.NewUserDTO;
import com.example.models.User;

@Mapper(componentModel = "spring")
public interface NewUserDTOMapper extends ApplicationMapper<User, NewUserDTO> {

}
