package com.example.dto;

import java.sql.Date; // Cambiado a java.sql.Date

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO {
    private int id;
    private String name;
    private String description;
    private Date start_date; // Cambiado a java.sql.Date
    private Date end_date; // Cambiado a java.sql.Date
    private Date deadline; // Cambiado a java.sql.Date
    private String status;

    // Constructor, getters y setters
    public ProjectDTO() {
    }

    /**
     * Constructor where all attributes, except deadline, comes from user inputs
     *
     * @param id          : project id
     * @param name        : project name
     * @param description : project description
     * @param start_date  : project starting date
     * @param end_date    : project ending date
     * @param status      : project status
     */

    public ProjectDTO(int id, String name, String description, Date start_date, Date end_date, Date deadline,
            String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.deadline = deadline;
        this.status = status;

    }
}
