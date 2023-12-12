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

	/**
	 * Add a new task to database if no task with the same id already exists
	 * @param task new task
	 * @return true if task was added, false otherwise
	 */
	boolean addTask(Task task);

	/**
	 * Get all tasks in database
	 * @return list of all users
	 */
	List<Task> getAllTasks();

	/**
	 * Get a task by task id
	 * @param id task id
	 * @return task with this id, null if not found
	 */
	Task getTaskById(int id);

	/**
	 * 
	 * @param taskModifie
	 * @return
	 */
	boolean updateTask(Task taskModifie);

	/**
	 * Delete a task with a given id, ignore if no task with id is found
	 * @param id task id
	 * @return true if task is found and deleted, false otherwise
	 */
	boolean deleteTask(int id);

	/**
	 * Get all tasks of a user with a given id
	 * @param userId user id
	 * @return list of all tasks
	 */
	List<Task> getAllTasksByUserId(int userId);

	/**
	 * Get all tasks of a project with a given id
	 * @param projectId project id
	 * @return list of all tasks
	 */
	List<Task> getAllTasksByProjectId(int projectId);

	/**
	 * Get all tasks of a user with a given id belonging to a project with a given id
	 * @param userId user id
	 * @param projectId project id
	 * @return list of all tasks
	 */
	List<Task> getTasksByUserIdAndProjectId(int userId, int projectId);

}
