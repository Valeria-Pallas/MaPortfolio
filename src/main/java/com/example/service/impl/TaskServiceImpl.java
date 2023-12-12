package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;

/**
 * @author Valeria Pallas
 * Modified by Tan Nguyen
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public boolean createTask(Task task) {
    if (taskRepository.findById(task.getId()).isPresent()) {
      return false;
    }
    taskRepository.saveAndFlush(task);
    return true;
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	public Task getTaskById(int id) {
		return taskRepository.findById(id).orElse(null);
	}

	public boolean updateTask(Task taskModifie) {
    if (taskRepository.findById(taskModifie.getId()).isPresent()) {
      taskRepository.saveAndFlush(taskModifie);
      return true;
    }
    return false;
	}

	public boolean deleteTask(int id) {
    if (taskRepository.findById(id).isPresent()) {
      taskRepository.deleteById(id);
      return true;
    }
    return false;
	}

	public List<Task> getAllTasksByUserId(int userId) {
		return taskRepository.findByUserId(userId);
	}

	public List<Task> getAllTasksByProjectId(int projectId) {
		return taskRepository.findByProjectId(projectId);
	}

	public List<Task> getTasksByUserIdAndProjectId(int userId, int projectId) {
		return taskRepository.findByUserIdAndProjectId(userId, projectId);
	}
  
}
