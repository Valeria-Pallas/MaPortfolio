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

@Entity
@Table(name = "project")
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
	private LocalDate start_date;
	@Column(name = "end_date")
	private LocalDate end_date;
	@Column(name = "deadline")
	private LocalDate deadline;

	@Column(name = "project_status")
	private String status;
	
	@ManyToMany
	@JoinTable(name = "user_project", 
	           joinColumns = @JoinColumn(name = "id_project"), 
	           inverseJoinColumns = @JoinColumn(name = "id_user"))
	private List<User> list_of_users;

	@OneToMany(mappedBy = "project", cascade = CascadeType.PERSIST)
	private List<Task> list_of_tasks;

	public Project() {
	}

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
		this.start_date = LocalDate.now();
		this.end_date = LocalDate.of(2000, 1, 1);
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
		this.start_date = s_date;
		this.end_date = e_date;
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
		this.start_date = s_date;
		this.end_date = e_date;
		this.deadline = d_date;
		this.status = status;
	}

	/**
	 * Full constructor using user inputs
	 * 
	 * @param id            : project id
	 * @param name          : project name
	 * @param description   : project's description
	 * @param s_date        : starting date
	 * @param e_date        : ending date
	 * @param d_date        : expected deadline
	 * @param status        : project status
	 * @param list_of_users : list of users
	 * @param list_of_tasks : list of tasks
	 */
	public Project(int id, String name, String description, LocalDate s_date, LocalDate e_date, LocalDate d_date,
			String status, List<User> user_list, List<Task> task_list) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = s_date;
		this.end_date = e_date;
		this.deadline = d_date;
		this.status = status;
		this.list_of_users = user_list;
		this.list_of_tasks = task_list;
	}

	/**
	 * Get project id
	 * 
	 * @return project id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get project name
	 * 
	 * @return project name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get project description
	 * 
	 * @return project description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get starting date of project
	 * 
	 * @return starting date
	 */
	public LocalDate getStartDate() {
		return start_date;
	}

	/**
	 * Get ending date of project
	 * 
	 * @return ending date
	 */
	public LocalDate getEndDate() {
		return end_date;
	}

	/**
	 * Get expected deadline of project
	 * 
	 * @return expected deadline
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * Get status of project
	 * 
	 * @return status of project
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Get list of users
	 * 
	 * @return list of users
	 */
	public List<User> getListOfUsers() {
		return list_of_users;
	}

	/**
	 * Get list of tasks of project
	 * 
	 * @return list of tasks
	 */
	public List<Task> getListOfTasks() {
		return list_of_tasks;
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

	public void setStartDate(LocalDate start_date) {
		this.start_date = start_date;
	}

	public void setEndDate(LocalDate end_date) {
		this.end_date = end_date;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	 
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Set list of users
	 * 
	 * @param user_list : list of users
	 */
	public void setListOfUsers(List<User> user_list) {
		this.list_of_users = user_list;
	}

	/**
	 * Set list of tasks of project
	 * 
	 * @param task_list : list of tasks
	 */
	public void setListOfTasks(List<Task> task_list) {
		this.list_of_tasks = task_list;
	}

	/**
	 * Update project status
	 * 
	 * @param new_status new status of project
	 */
	public void updateStatus(String new_status) {
		this.status = new_status;
	}

	public void copyExceptIdFrom(Project new_project) {
		this.name = new_project.getName();
		this.description = new_project.getDescription();
		this.start_date = new_project.getStartDate();
		this.end_date = new_project.getEndDate();
		this.deadline = new_project.getDeadline();
		this.status = new_project.getStatus();
		this.list_of_users = new_project.getListOfUsers();
		this.list_of_tasks = new_project.getListOfTasks();
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
