package com.example.util;

import org.mapstruct.Mapper;

import com.example.dto.NewTaskDto;
import com.example.models.Task;

@Mapper(componentModel = "spring")
public interface NewTaskDTOMapper extends ApplicationMapper<Task, NewTaskDto> {

  @Override
  default NewTaskDto mapToDto(Task entity) {
    NewTaskDto dto = new NewTaskDto();
    dto.setProjectId(entity.getProject().getId());
    dto.setUserId(entity.getUser().getId());
    // Add other mappings if needed
    return dto;
  }

  @Override
  default Task mapToEntity(NewTaskDto dto) {
    Task entity = new Task();
    entity.setId(dto.getId());
    // Add other mappings if needed
    return entity;
  }
}
