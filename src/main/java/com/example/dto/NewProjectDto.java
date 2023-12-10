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
public class NewProjectDto implements Serializable {

    private static final long serialVersionUID = 1L;

    // project id ?

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @NotBlank
    @Size(min = 3, max = 255)
    private Date start_date;

    @NotBlank
    @Size(min = 3, max = 255)
    private Date end_date;

    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    @NotBlank
    @Size(min = 3, max = 255)
    private Date deadline;


}
