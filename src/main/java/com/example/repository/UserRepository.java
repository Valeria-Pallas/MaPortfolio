package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
//  List<User> FindByName(String name);
//  List<User> findByNameContaining(String name);
	@Query("SELECT u FROM User u JOIN u.list_of_projects p WHERE p.id=:id_proj")
	List<User> findByProjectId(int id_proj);
}
