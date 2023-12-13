package com.example.dto;

import java.util.List;

// import com.example.models.Project;
// import com.example.models.Task;

public record UserDTO(
  int id,
  String name,
  String email,
  List<String> roles
  // List<Task> tasks,
  // List<Project> projects
  ) {
}
