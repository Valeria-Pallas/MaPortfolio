package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Task;

/**
 * @author Valeria Pallas
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByStatus(String status);
    List<Task> findByNameContainingIgnoreCase(String keyword);

    List<Task> findByUserId(int userId);

    List<Task> findByProjectId(int projectId);

    List<Task> findByUserIdAndProjectId(int userId, int projectId);

}
