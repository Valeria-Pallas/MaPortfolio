package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.models.Project;
import com.example.models.User;

@Service
public interface UserService {

  /**
   * Get all users in database
   * @return list of all users
   */
  List<User> getAllUsers();

  /**
   * Get a user by user id
   * @param id user id
   * @return user with this id, null if not found
   */
  User getUserById(int id);

  /**
   * Add a new user to database if no user with the same id already exists
   * @param newUser new user
   * @return true if user was added, false otherwise
   */
  boolean addUser(User newUser);

  /**
   * Delete a user with a given id, ignore if no user with id is found
   * @param id user id
   * @return true if user is found and deleted, false otherwise
   */
  boolean deleteUser(int id);

  /**
   * Update user having a given id with new info from input
   * @param newUser new info for user
   * @return true if user is updated successfully, false otherwise
   */
  boolean updateUser(User newUser);

  /**
   * Get all projects of a user with a given id
   * @param userId user id
   * @return list of all projects
   */
  List<Project> getAllProjectsByUserId(int userId);
}
