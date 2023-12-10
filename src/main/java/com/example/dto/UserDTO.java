package com.example.dto;

import java.util.List;

import com.example.models.Project;
import com.example.models.Task;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private List<Task> list_of_tasks;
    private List<Project> list_of_projects;


    // Constructor, getters y setters

    public UserDTO() {
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
     * @param list_of_users : list of users associated to project
     * @param list_of_tasks : list of tasks of project
     */

    public UserDTO(int id, String name, String email, List<Task> list_of_tasks, List<Project> list_of_projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.list_of_tasks = list_of_tasks;
        this.list_of_projects = list_of_projects;

    }


  }
