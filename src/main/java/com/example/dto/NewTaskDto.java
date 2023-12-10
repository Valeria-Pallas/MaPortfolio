package com.example.dto;

import java.io.Serializable;
import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskDto implements Serializable {
    private static final long serialVersionUID = 1L;

    // task id ?

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    @NotBlank
    @Size(min = 3, max = 255)
    private int userId;

    @NotBlank
    @Size(min = 3, max = 255)
    private int projectId;

    @NotBlank
    @Size(min = 3, max = 255)
    private Date deadline;


}
