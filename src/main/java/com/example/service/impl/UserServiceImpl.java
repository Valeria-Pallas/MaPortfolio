package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.Project;
import com.example.models.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
@Transactional
public class UserServiceImpl  implements UserService{
  @Autowired
  private UserRepository userRepo;

  @Autowired
  private MessageSource messageSource;

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public User getUserById(int id) {
    return userRepo.findById(id).orElse(null);
  }

  public boolean addUser(User newUser) {
    if (userRepo.findById(newUser.getId()).isPresent()) {
      return false;
    }
    userRepo.saveAndFlush(newUser);
    return true;
  }

  public boolean deleteUser(int id) {
    if (userRepo.findById(id).isPresent()) {
      userRepo.deleteById(id);
      return true;
    }
    return false;
    // TODO - return messageSource.getMessage("business.error.100", new Object[]{id}, Locale.getDefault());
  }

  public boolean updateUser(User newUser) {
    if (userRepo.findById(newUser.getId()).isPresent()) {
      userRepo.saveAndFlush(newUser);
      return true;
    }
    return false;
  }

  public List<Project> getAllProjectsByUserId(int userId) {
    User user = userRepo.findById(userId).orElse(null);
    if (user != null) {
      return user.getProjects();
    }
    return new ArrayList<>();
  }
}
