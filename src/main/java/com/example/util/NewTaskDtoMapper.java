package com.example.util;

import org.mapstruct.Mapper;

import com.example.dto.NewTaskDto;
import com.example.models.Task;

@Mapper(componentModel = "spring")
public interface NewTaskDtoMapper extends ApplicationMapper<Task, NewTaskDto> {


}
