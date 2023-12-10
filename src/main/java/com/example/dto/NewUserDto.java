package com.example.dto;

import java.io.Serializable;
import java.util.List;

import com.example.models.Project;
import com.example.models.Task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank
    @Size(min = 3, max = 255)
    private List<Task> list_of_tasks;

    @NotBlank
    @Size(min = 3, max = 255)
    private List<Project> list_of_projects;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "Email should be valid")
    private String email;
}
