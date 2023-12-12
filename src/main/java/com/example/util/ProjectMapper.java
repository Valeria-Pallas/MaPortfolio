package com.example.util;

import org.mapstruct.Mapper;

import com.example.dto.ProjectDTO;
import com.example.models.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends ApplicationMapper<Project, ProjectDTO> {
}
