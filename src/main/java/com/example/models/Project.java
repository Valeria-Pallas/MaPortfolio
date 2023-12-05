package com.example.models;

import java.util.Calendar;
import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "begin_date")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "deadline")
	private Date deadline;

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
	 * @param deadline        : expected deadline
	 * @param status        : project status
	 */
	public Project(String name, String description, Date deadline, String status) {
		this.name = name;
		this.description = description;
		this.startDate = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, Calendar.JANUARY, 1);
		this.endDate = calendar.getTime();
		this.deadline = deadline;
		this.status = status;
	}

	/**
	 * Constructor where all attributes, except id, list of users and tasks, come from user inputs
	 * 
	 * @param name        : project name
	 * @param description : project's description
	 * @param startDate      : starting date
	 * @param endDate      : ending date
	 * @param deadline      : expected deadline
	 * @param status      : project status
	 */
	public Project(String name, String description, Date startDate, Date endDate, Date deadline,
			String status) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deadline = deadline;
		this.status = status;
	}

	/**
	 * Constructor where all attributes, except list of users and tasks, come from user inputs
	 * 
	 * @param id          : project id
	 * @param name        : project name
	 * @param description : project's description
	 * @param startDate      : starting date
	 * @param endDate      : ending date
	 * @param deadline      : expected deadline
	 * @param status      : project status
	 */
	public Project(int id, String name, String description, Date startDate, Date endDate, Date deadline,
			String status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deadline = deadline;
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
