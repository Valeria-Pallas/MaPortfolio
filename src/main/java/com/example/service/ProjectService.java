package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Project;
import com.example.models.Task;
import com.example.models.User;
import com.example.repository.ProjectRepository;

@Service
public class ProjectService {
  @Autowired
  private ProjectRepository proj_repo;

  /**
   * Get all projects in database
   * @return list of projects
   */
  public List<Project> getAllProjects() {
    return proj_repo.findAll();
  }

  /**
   * Get a project by project id
   * @param id project id
   * @return project with this id, null if not found
   */
  public Project getProjectById(int id) {
    return proj_repo.findById(id).orElse(null);
  }

  /**
   * Update project having a given id with info from user-defined input
   * Do nothing if no project with id is found
   * @param id project id
   * @param new_project info of project to be update
   */
  public void updateProjectById(int id, Project new_project) {
    Project project = proj_repo.findById(id).orElse(null);
    if (project != null) {
      project.copyExceptIdFrom(new_project);
      proj_repo.save(project);
    }
  }

  /**
   * Add a new project to database if no project with the same id exists in database
   * @param new_project new project
   */
  public void addProject(Project new_project) {
    Project project_exist = proj_repo.findById(new_project.getId()).orElse(null);
    if (project_exist == null) {
      proj_repo.save(new_project);
    }
  }

  /**
   * Delete a project with a given id
   * @param id project id
   */
  public void deleteProjectById(int id) {
    proj_repo.deleteById(id);
  }

  /**
   * Add a new user to list of users of a project with a given id
   * @param new_user new user
   * @param id_project project id
   */
  public void addUserToProject(User new_user, int id_project) {
    Project project = proj_repo.findById(id_project).orElse(null);
    if (project != null) {
      project.addUser(new_user);
      proj_repo.save(project);
    }
  }

  /**
   * Add a task to list of tasks of a project with a given id
   * @param new_task new task
   * @param id_project project id
   */
  public void addTaskToProject(Task new_task, int id_project) {
    Project project = proj_repo.findById(id_project).orElse(null);
    if (project != null) {
      project.addTask(new_task);
      proj_repo.save(project);
    }
  }

  /**
   * Get all users of a project with a given id
   * @param id project id
   * @return list of all users
   */
  public List<User> getAllUsersByProjectId(int id) {
    Project project = proj_repo.findById(id).orElse(null);
    List<User> users = new ArrayList<User>();
    if (project != null) {
      users = project.getListOfUsers();
    }
    return users;
  }
}
