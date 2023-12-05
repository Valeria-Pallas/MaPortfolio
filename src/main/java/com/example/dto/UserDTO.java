package com.example.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Project;
import com.example.models.Task;

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

    public UserDTO(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.list_of_tasks = new ArrayList<Task>();
        this.list_of_projects = new ArrayList<Project>();

    }

    public UserDTO(int id, String name, String email, List<Task> list_of_tasks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.list_of_tasks = list_of_tasks;
        this.list_of_projects = new ArrayList<Project>();

    }

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return List<Task> return the list_of_tasks
     */
    public List<Task> getList_of_tasks() {
        return list_of_tasks;
    }

    /**
     * @param list_of_tasks the list_of_tasks to set
     */
    public void setList_of_tasks(List<Task> list_of_tasks) {
        this.list_of_tasks = list_of_tasks;
    }

    /**
     * @return List<Project> return the list_of_projects
     */
    public List<Project> getList_of_projects() {
        return list_of_projects;
    }

    /**
     * @param list_of_projects the list_of_projects to set
     */
    public void setList_of_projects(List<Project> list_of_projects) {
        this.list_of_projects = list_of_projects;
    }

}
