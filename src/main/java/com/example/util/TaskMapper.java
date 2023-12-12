package com.example.util;

import org.mapstruct.Mapper;

import com.example.dto.TaskDTO;
import com.example.models.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper extends ApplicationMapper<Task, TaskDTO> {
}
