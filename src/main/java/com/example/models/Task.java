package com.example.models;

import java.util.Date;

import com.example.enumeration.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

	@Id
	@Column(name = "id_task")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "task_name")
	private String name;
	@Column(name = "task_description")
	private String description;
	@Enumerated(EnumType.STRING)
	@Column(name = "task_status")
	private TaskStatus status;

	@ManyToOne
	private User user;

	@ManyToOne
	private Project project;

	@Temporal(TemporalType.DATE)
	@Column(name = "deadline")
	private Date deadline;

	@OneToMany(mappedBy = "task")
	private Tracking tracking;

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
	public Task(int id_task, String name, String description, TaskStatus status, User user, Project proj) {
		this.id = id_task;
		this.name = name;
		this.description = description;
		this.status = status;
		this.user = user;
		this.project = proj;
	}

	/**
	 * Set new status for task
	 * 
	 * @param newStatus
	 */
	public void updateStatus(TaskStatus newStatus) {
		this.status = newStatus;
	}

	/**
	 * Update id of new user assigned to this task
	 * 
	 * @param new_user : new user's id
	 */
	public void updateUser(User new_user) {
		this.user = new_user;
	}

	/**
	 * Copy new info, except task's id, to this task
	 * @param new_task new info of task
	 */

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
