package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
  List<User> findByName(String name);
  List<User> findByNameContaining(String name);
  @Query("SELECT u FROM User u JOIN u.projects p WHERE p.id=:projectId")
  List<User> findByProjectId(int projectId);
}
