package com.example.repository.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.models.User;
import com.example.repository.AuthenUserRepository;
import com.example.repository.UserRepository;

@Repository
public class AuthenUserRepositoryImpl implements AuthenUserRepository {

  @Autowired
  private UserRepository userRepo;

  @Override
  public Optional<User> findByUserName(String userName) {
    return userRepo.findByName(userName).stream().findFirst();
  }
  
}
