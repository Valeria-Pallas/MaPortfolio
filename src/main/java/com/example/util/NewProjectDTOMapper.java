package com.example.util;

import org.mapstruct.Mapper;

import com.example.dto.NewProjectDto;
import com.example.models.Project;

@Mapper(componentModel = "spring")
public interface NewProjectDTOMapper extends ApplicationMapper<Project, NewProjectDto> {

}
