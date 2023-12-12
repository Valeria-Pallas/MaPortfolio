package com.example.dto;

import java.io.Serializable;
import java.sql.Date;

import com.example.enumeration.TaskStatus;
import com.example.models.Project;
import com.example.models.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    private String description;

    @NotBlank
    private TaskStatus status;

    private User user;

    private Project project;

    private Date deadline;


}
