package com.example.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_portfolio")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Column(name = "user_name")
	private String name;
	@Column(name = "email")
	private String email;
	@NotEmpty
	@Column(name = "password")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	// @Builder.Default
	private List<String> roles = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<Task> tasks;
	
	@ManyToMany(mappedBy = "users")
	private List<Project> projects;

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
	 * Copy new info, except user's id, to this user 
	 * @param new_user new info of user
	 */
	public void copyExceptIdFrom(User new_user) {
		this.name = new_user.getName();
		this.email = new_user.getEmail();
		this.password = new_user.getPassword();
		this.tasks = new_user.getTasks();
		this.projects = new_user.getProjects();
	}

	/**
	 * Add a new task to list of tasks if no task exists in the list with the same id
	 * Do nothing otherwise
	 * @param new_task new task
	 */
	public void addTask(Task new_task) {
		if (!tasks.stream().anyMatch(task -> task.getId() == new_task.getId())) {
			tasks.add(new_task);
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
		tasks.removeIf(task -> task.getId() == id);
	}

	/**
	 * Add a new project to list of projects if no project exists in the list with the same id
	 * Do nothing otherwise
	 * @param new_project new project
	 */
	public void addProject(Project new_project) {
		if (!projects.stream().anyMatch(user -> user.getId() == new_project.getId())){
			projects.add(new_project);
		}
	}

	/**
	 * Update a project with a given id in the list of projects with new project info 
	 * @param id project id
	 * @param new_project new info of project
	 */
	public void updateProjectById(int id, Project new_project) {
		for (int i=0; i<projects.size(); i++) {
			if (projects.get(i).getId() == id) {
				projects.get(i).copyExceptIdFrom(new_project);
				break;
			}
		}
	}

	/**
	 * Delete, from the list of projects, any project with a given id
	 * @param id id of project to be deleted
	 */
	public void deleteProjectById(int id) {
		projects.removeIf(project -> project.getId() == id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}