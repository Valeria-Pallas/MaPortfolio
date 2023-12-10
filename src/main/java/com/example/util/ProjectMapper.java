package com.example.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dto.ProjectDTO;
import com.example.models.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

  @Mapping(source = "project.id", target = "projectId")
  @Mapping(source = "user.id", target = "userId")
    ProjectDTO projectToProjectDTO(Project project);
}
