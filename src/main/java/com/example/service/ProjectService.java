package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.models.Project;
import com.example.models.Task;
import com.example.models.User;

@Service
public interface ProjectService {

  /**
   * Get all projects in database
   * @return list of projects
   */
 List<Project> getAllProjects();

  /**
   * Get a project by project id
   * @param id project id
   * @return project with this id, null if not found
   */
  Project getProjectById(int id);

  /**
   * Update project having a given id with info from user-defined input
   * Do nothing if no project with id is found
   * @param newProject info of project to be update
   * @return true if project is updated successfully, false otherwise
   */
  boolean updateProject(Project newProject);

  /**
   * Add a new project to database if no project with the same id exists in database
   * @param newProject new project
   * @return true if project is added successfully, false otherwise
   */
  boolean addProject(Project newProject);

  /**
   * Delete a project with a given id
   * @param id project id
   * @return true if project is deleted successfully, false otherwise
   */
  boolean deleteProjectById(int id);

  /**
   * Add a new user to list of users of a project with a given id
   * @param newUser new user
   * @param projectId project id
   * @return true if user is added to project successfully, false otherwise
   */
  boolean addUserToProject(User newUser, int projectId);

  /**
   * Add a task to list of tasks of a project with a given id
   * @param newTask new task
   * @param projectId project id
   * @return true if task is added to project successfully, false otherwise
   */
  boolean addTaskToProject(Task newTask, int projectId);

  /**
   * Get all users of a project with a given id
   * @param id project id
   * @return list of all users
   */
  List<User> getAllUsersByProjectId(int id);
}
