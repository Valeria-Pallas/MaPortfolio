package com.example.dto;

import java.io.Serializable;
import java.util.List;

import com.example.models.Project;
import com.example.models.Task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    private List<String> roles;

    private List<Task> tasks;

    private List<Project> projects;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "business.validation.constraints.email.message")
    private String email;
}
