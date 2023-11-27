package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Project;
import com.example.models.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository user_repo;

  /**
   * Get all users in database
   * @return list of all users
   */
  public List<User> getAllUsers() {
    return user_repo.findAll();
  }

  /**
   * Get a user by user id
   * @param id user id
   * @return user with this id, null if not found
   */
  public User getUserById(int id) {
    return user_repo.findById(id).orElse(null);
  }

  /**
   * Add a new user to database if no user with the same id already exists
   * @param new_user new user
   */
  public void addUser(User new_user) {
    User user_exist = user_repo.findById(new_user.getId()).orElse(null);
    if (user_exist == null) {
      user_repo.save(new_user);
    }
  }

  /**
   * Delete a user with a given id, ignore if no user with id is found
   * @param id user id
   */
  public void deleteUser(int id) {
    user_repo.deleteById(id);
  }

  /**
   * Update user having a given id with new info from input
   * @param id user id
   * @param new_user new info for user
   */
  public void updateUserById(int id, User new_user) {
    User user = user_repo.findById(id).orElse(null);
    if (user != null) {
      user.copyExceptIdFrom(new_user);
      user_repo.save(user);
    }
  }

  /**
   * Get all projects of a user with a given id
   * @param id_user user id
   * @return list of all projects
   */
  public List<Project> getAllProjectsByUserId(int id_user) {
    User user = user_repo.findById(id_user).orElse(null);
    List<Project> projects = new ArrayList<>();
    if (user != null) {
      projects = user.getListOfProjects();
    }
    return projects;
  }
}
