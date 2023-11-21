package com.example.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_portfolio")
public class User {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<Task> list_of_tasks;
	
	@ManyToMany(mappedBy = "list_of_users")
	private List<Project> list_of_projects;

	public User() {
	}

	/**
	 * Constructor using user inputs
	 * 
	 * @param name     : user name
	 * @param email    : email address
	 * @param password : user password
	 */
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Constructor using user inputs
	 * 
	 * @param id       : ID for user
	 * @param name     : user name
	 * @param email    : email address
	 * @param password : user password
	 */
	public User(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Constructor using user inputs
	 * 
	 * @param id       : ID for user
	 * @param name     : user name
	 * @param email    : email address
	 * @param password : user password
	 */
	public User(int id, String name, String email, String password, List<Task> task_list, List<Project> project_list) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.list_of_tasks = task_list;
		this.list_of_projects = project_list;
	}

	/**
	 * Get user id
	 * 
	 * @return user id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get user name
	 * 
	 * @return user name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get user's email
	 * 
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Get user's password
	 * @return user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Get list of tasks
	 * 
	 * @return list of tasks
	 */
	public List<Task> getListOfTasks() {
		return list_of_tasks;
	}

	/**
	 * Get list of projects
	 * 
	 * @return list of projects
	 */
	public List<Project> getListOfProjects() {
		return list_of_projects;
	}

	/**
	 * Set new id of user
	 * @param id : new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set new name for user
	 * @param name : new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set new email of user
	 * @param email : new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Set new password
	 * 
	 * @param new_pwd : new password
	 */
	public void setPassword(String new_pwd) {
		this.password = new_pwd;
	}

	/**
	 * Set list of tasks
	 * 
	 * @param task_list : list of tasks
	 */
	public void setListOfTasks(List<Task> task_list) {
		this.list_of_tasks = task_list;
	}

	/**
	 * Set list of projects
	 * 
	 * @param project_list : list of projects
	 */
	public void setListOfProjects(List<Project> project_list) {
		this.list_of_projects = project_list;
	}

	public void copyExceptIdFrom(User new_user) {
		this.name = new_user.getName();
		this.email = new_user.getEmail();
		this.password = new_user.getPassword();
		this.list_of_tasks = new_user.getListOfTasks();
		this.list_of_projects = new_user.getListOfProjects();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}