package com.example.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.dto.TaskDTO;
import com.example.models.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "project.id", target = "projectId")
    TaskDTO taskToTaskDTO(Task task);
}
