package com.example.util;

import org.mapstruct.Mapper;

import com.example.dto.NewUserDto;
import com.example.models.User;

@Mapper(componentModel = "spring")
public interface NewUserDTOMapper extends ApplicationMapper<User, NewUserDto> {

}
