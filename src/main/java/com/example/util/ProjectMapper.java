package com.example.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dto.ProjectDTO;
import com.example.models.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "listOfUsers", target = "list_of_users") // Si los nombres de los campos difieren
    @Mapping(source = "listOfTasks", target = "list_of_tasks") // Si los nombres de los campos difieren
    ProjectDTO projectToProjectDTO(Project project);
}
