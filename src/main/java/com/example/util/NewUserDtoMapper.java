package com.example.util;

import org.mapstruct.Mapper;

import com.example.dto.NewUserDto;
import com.example.models.User;

@Mapper(componentModel = "spring")
public interface NewUserDtoMapper extends ApplicationMapper<User, NewUserDto> {

}
