package com.example.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dto.ProjectDTO;
import com.example.models.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    // Mapea otros atributos según sea necesario
    ProjectDTO projectToProjectDTO(Project project);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    // Mapea otros atributos según sea necesario
    Project projectDTOtoProject(ProjectDTO projectDTO);
}
