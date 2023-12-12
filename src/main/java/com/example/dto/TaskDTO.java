package com.example.dto;

import java.sql.Date;

import com.example.enumeration.TaskStatus;
import com.example.models.Project;
import com.example.models.User;

public record TaskDTO(
    int id,
    String name,
    String description,
    TaskStatus status,
    User user,
    Project project,
    Date deadline) {
}
