package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.models.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByTaskStatus(String status);
    List<Task> findByTaskNameContainingIgnoreCase(String keyword);

    @Query("SELECT t FROM Task t JOIN t.user u WHERE u.id = :id_user")
    List<Task> findByUserId(int id_user);

    List<Task> findByProjectId(Integer projectId);
    
    List<Task> findByUserIdAndProjectId(Integer userId, Integer projectId);
}
