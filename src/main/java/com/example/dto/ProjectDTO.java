package com.example.dto;

import java.util.Date;
import java.util.List;

import com.example.enumeration.ProjectStatus;
import com.example.models.Task;
import com.example.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date deadline;
    private ProjectStatus status;
    private List<User> users;
    private List<Task> tasks;

    // Getters y Setters
}
