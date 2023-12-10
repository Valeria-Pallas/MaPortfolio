package com.example.dto;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TaskDTO {
    private int id;
    private String name;
    private String description;
    private String status;
    private int userId;
    private int projectId;
    private Date deadline;

    // Constructor, getters y setters
    public TaskDTO() {
    }

    public TaskDTO(int id, String name, String description, String status, int userId, int projectId, Date deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.projectId = projectId;
        this.deadline = deadline;
    }

    // @GetMapping(value = "/{id}")
    // public TaskDTO findById(@PathVariable Long id) {
    //   TaskDTO taskDto = taskMapper.mapToD(taskService.findById(id));
    //   return taskDto;
    // }
    // Getters y Setters
}
