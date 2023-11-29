package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Task;
import com.example.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	

	
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	public Task getTaskById(Integer id) {
		return taskRepository.findById(id).orElse(null);
	}

	public void updateTask(int id, Task taskModifie) {
		Task task = taskRepository.findById(id).orElse(null);
		if (task != null) {
			task.setName(taskModifie.getName());
			task.setStatus(taskModifie.getStatus());;
			task.setUser(taskModifie.getUser());
			task.setProject(taskModifie.getProject());
			task.setDescription(taskModifie.getDescription());
			task.setDeadline(taskModifie.getDeadline());
			taskRepository.save(task);
		}
	}
		public void deleteTask(Integer id) {
			taskRepository.deleteById(id);
		}

		public List<Task> getAllTasksByUserId(Integer userId) {
			return taskRepository.findByUserId(userId);
		}

		public List<Task> getAllTasksByProjectId(Integer projectId) {
			return taskRepository.findByProjectId(projectId);
		}

		public List<Task> getTasksByUserIdAndProjectId(Integer userId, Integer projectId) {
			return taskRepository.findByUserIdAndProjectId(userId, projectId);
		}
	}
