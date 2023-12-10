package com.example.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.models.Task;

/**
 * @author Valeria Pallas
 * Modified by Tan Nguyen
 */
@Service
public interface TaskService {
	
	boolean createTask(Task task);

	List<Task> getAllTasks();
	
	Task getTaskById(int id);

	boolean updateTask(Task taskModifie);
	
	boolean deleteTask(int id);

	List<Task> getAllTasksByUserId(int userId);

	List<Task> getAllTasksByProjectId(int projectId);

	List<Task> getTasksByUserIdAndProjectId(int userId, int projectId);

}
