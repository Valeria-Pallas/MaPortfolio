package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.Project;
import com.example.models.Task;
import com.example.models.User;
import com.example.repository.ProjectRepository;
import com.example.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  private ProjectRepository projRepo;

  public List<Project> getAllProjects() {
    return projRepo.findAll();
  }

  public Project getProjectById(int id) {
    return projRepo.findById(id).orElse(null);
  }

  public boolean updateProject(Project newProject) {
    if (projRepo.findById(newProject.getId()).isPresent()) {
      projRepo.saveAndFlush(newProject);
      return true;
    }
    return false;
  }

  public boolean addProject(Project newProject) {
    if (projRepo.findById(newProject.getId()).isPresent()) {
      return false;
    }
    projRepo.saveAndFlush(newProject);
    return true;
  }

  public boolean deleteProjectById(int id) {
    if (projRepo.findById(id).isPresent()) {
      projRepo.deleteById(id);
      return true;
    }
    return false;
  }

  public boolean addUserToProject(User newUser, int projectId) {
    Project project = projRepo.findById(projectId).orElse(null);
    if (project == null) {
      return false;
    }
    project.addUser(newUser);
    projRepo.saveAndFlush(project);
    return true;
  }

  public boolean addTaskToProject(Task newTask, int projectId) {
    Project project = projRepo.findById(projectId).orElse(null);
    if (project == null) {
      return false;
    }
    project.addTask(newTask);
    projRepo.saveAndFlush(project);
    return true;
  }

  public List<User> getAllUsersByProjectId(int id) {
    Project project = projRepo.findById(id).orElse(null);
    if (project != null) {
      return project.getUsers();
    }
    return new ArrayList<User>();
  }
}
