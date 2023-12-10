package com.example.dto;

import java.util.List;

import com.example.models.Project;
import com.example.models.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private List<Task> list_of_tasks;
    private List<Project> list_of_projects;


  }
