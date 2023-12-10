package com.example.repository;

import java.util.Optional;

import com.example.models.User;

public interface AuthenUserRepository {

  /**
   * Find a user (eg. first user) with a given user name
   * @param userName user name
   * @return an Optional containing the user if found, empty Optional otherwise
   */
  Optional<User> findByUserName(String userName);

}
