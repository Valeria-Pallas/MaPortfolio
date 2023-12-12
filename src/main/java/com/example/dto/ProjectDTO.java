package com.example.dto;

import java.util.Date;
import java.util.List;

import com.example.enumeration.ProjectStatus;
import com.example.models.Task;
import com.example.models.User;

public record ProjectDTO (
    int id,
    String name,
    String description,
    Date startDate,
    Date endDate,
    Date deadline,
    ProjectStatus status,
    List<User> users,
    List<Task> tasks){
}
