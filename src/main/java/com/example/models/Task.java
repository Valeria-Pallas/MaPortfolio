package com.example.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@Column(name = "id_task")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "task_name")
	private String name;
	@Column(name = "task_description")
	private String description;
	@Column(name = "task_status")
	private String status;

	@ManyToOne
	private User user;

	@ManyToOne
	private Project project;

	@Column(name = "deadline")
	private LocalDate deadline;

	public Task() {
	}

	/**
	 * Constructor where all attributes, except deadline, comes from user inputs
	 * 
	 * @param id_task     : task id
	 * @param name        : task name
	 * @param description : task description
	 * @param status      : task status
	 * @param user        : user associated to task
	 * @param proj        : project of task
	 */
	public Task(int id_task, String name, String description, String status, User user, Project proj) {
		this.id = id_task;
		this.name = name;
		this.description = description;
		this.status = status;
		this.user = user;
		this.project = proj;
	}

	/**
	 * Full constructor using user inputs
	 * 
	 * @param id_task     : task id
	 * @param name        : task name
	 * @param description : task description
	 * @param status      : task status
	 * @param user        : user associated to task
	 * @param proj        : project of task
	 * @param deadline    : deadline of task
	 */
	public Task(int id_task, String name, String description, String status, User user, Project proj,
			LocalDate deadline) {
		this.id = id_task;
		this.name = name;
		this.description = description;
		this.status = status;
		this.user = user;
		this.project = proj;
		this.deadline = deadline;
	}

	/**
	 * Get task id
	 * 
	 * @return task id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get task name
	 * 
	 * @return task name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get task description
	 * 
	 * @return task description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get task status
	 * 
	 * @return task status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Get user who will do task
	 * 
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Get project to which task belongs
	 * 
	 * @return project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Get deadline of task
	 * 
	 * @return deadline
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	/**
	 * Set new status for task
	 * 
	 * @param new_status
	 */
	public void updateStatus(String new_status) {
		this.status = new_status;
	}

	/**
	 * Update id of new user assigned to this task
	 * 
	 * @param new_user : new user's id
	 */
	public void updateUser(User new_user) {
		this.user = new_user;
	}

	public void copyExceptIdFrom(Task new_task) {
		this.name = new_task.getName();
		this.description = new_task.getDescription();
		this.status = new_task.getStatus();
		this.deadline = new_task.getDeadline();
		this.user = new_task.getUser();
		this.project = new_task.getProject();
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", name='" + getName() + "'" +
			", description='" + getDescription() + "'" +
			", status='" + getStatus() + "'" +
			", user ID='" + getUser().getId() + "'" +
			", project ID='" + getProject().getId() + "'" +
			", deadline='" + getDeadline() + "'" +
			"}";
	}

}
