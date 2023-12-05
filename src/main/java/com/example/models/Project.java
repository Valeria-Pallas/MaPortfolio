package com.example.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

	@Id
	@Column(name = "id_project")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "project_name")
	private String name;
	@Column(name = "project_description")
	private String description;


	@Column(name = "begin_date")
	private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name = "deadline")
	private LocalDate deadline;

	@Column(name = "project_status")
	private String status;
	
	@ManyToMany
	@JoinTable(name = "user_project", 
	           joinColumns = @JoinColumn(name = "id_project"), 
	           inverseJoinColumns = @JoinColumn(name = "id_user"))
	private List<User> users;

	@OneToMany(mappedBy = "project", cascade = CascadeType.PERSIST)
	private List<Task> tasks;

	/**
	 * Constructor using user inputs
	 * Version minimal to create essential project's info
	 * 
	 * @param name          : project name
	 * @param description   : project's description
	 * @param d_date        : expected deadline
	 * @param status        : project status
	 */
	public Project(String name, String description, LocalDate d_date, String status) {
		this.name = name;
		this.description = description;
		this.startDate = LocalDate.now();
		this.endDate = LocalDate.of(2000, 1, 1);
		this.deadline = d_date;
		this.status = status;
	}

	/**
	 * Constructor where all attributes, except id, list of users and tasks, come from user inputs
	 * 
	 * @param name        : project name
	 * @param description : project's description
	 * @param s_date      : starting date
	 * @param e_date      : ending date
	 * @param d_date      : expected deadline
	 * @param status      : project status
	 */
	public Project(String name, String description, LocalDate s_date, LocalDate e_date, LocalDate d_date,
			String status) {
		this.name = name;
		this.description = description;
		this.startDate = s_date;
		this.endDate = e_date;
		this.deadline = d_date;
		this.status = status;
	}

	/**
	 * Constructor where all attributes, except list of users and tasks, come from user inputs
	 * 
	 * @param id          : project id
	 * @param name        : project name
	 * @param description : project's description
	 * @param s_date      : starting date
	 * @param e_date      : ending date
	 * @param d_date      : expected deadline
	 * @param status      : project status
	 */
	public Project(int id, String name, String description, LocalDate s_date, LocalDate e_date, LocalDate d_date,
			String status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = s_date;
		this.endDate = e_date;
		this.deadline = d_date;
		this.status = status;
	}

	/**
	 * Update project status
	 * 
	 * @param new_status new status of project
	 */
	public void updateStatus(String new_status) {
		this.status = new_status;
	}

	/**
	 * Copy new info, except project's id, to this project
	 * @param new_project new info of project
	 */
	public void copyExceptIdFrom(Project new_project) {
		this.name = new_project.getName();
		this.description = new_project.getDescription();
		this.startDate = new_project.getStartDate();
		this.endDate = new_project.getEndDate();
		this.deadline = new_project.getDeadline();
		this.status = new_project.getStatus();
		this.users = new_project.getUsers();
		this.tasks = new_project.getTasks();
	}

	/**
	 * Add a new user to list of users if no user exists in the list with the same id
	 * Do nothing otherwise
	 * @param new_user new user
	 */
	public void addUser(User new_user) {
		if (!this.users.stream().anyMatch(user -> user.getId() == new_user.getId())){
			this.users.add(new_user);
		}
	}

	/**
	 * Update a user with a given id in the list of users with new user info 
	 * @param id user id
	 * @param new_user new info of user
	 */
	public void updateUserById(int id, User new_user) {
		for (int i=0; i<this.users.size(); i++) {
			if (this.users.get(i).getId() == id) {
				this.users.get(i).copyExceptIdFrom(new_user);
				break;
			}
		}
	}

	/**
	 * Delete, from the list of users, any user with a given id
	 * @param id id of user to be deleted
	 */
	public void deleteUserById(int id) {
		this.users.removeIf(user -> user.getId() == id);
	}

	/**
	 * Add a new task to list of tasks if no task exists in the list with the same id
	 * Do nothing otherwise
	 * @param new_task new task
	 */
	public void addTask(Task new_task) {
		if (!this.tasks.stream().anyMatch(task -> task.getId() == new_task.getId())) {
			this.tasks.add(new_task);
		}
	}

	/**
	 * Update a task with a given id from the list of tasks with new info
	 * @param id task id
	 * @param new_task new info of task
	 */
	public void updateTaskById(int id, Task new_task) {
		for (int i=0; i<tasks.size(); i++) {
			if (tasks.get(i).getId() == id) {
				tasks.get(i).copyExceptIdFrom(new_task);
				break;
			}
		}
	}

	/**
	 * Delete, from the list of tasks, any task with a given id 
	 * @param id id of task to be deleted
	 */
	public void deleteTaskById(int id) {
		this.tasks.removeIf(task -> task.getId() == id);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", name='" + getName() + "'" +
			", description='" + getDescription() + "'" +
			", start_date='" + getStartDate() + "'" +
			", end_date='" + getEndDate() + "'" +
			", deadline='" + getDeadline() + "'" +
			", status='" + getStatus() + "'" +
			"}";
	}

}
