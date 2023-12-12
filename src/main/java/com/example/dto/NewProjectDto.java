package com.example.dto;

import java.io.Serializable;
import java.sql.Date;

import com.example.enumeration.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewProjectDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

	@NotNull
    private ProjectStatus status;

    private Date deadline;


}
