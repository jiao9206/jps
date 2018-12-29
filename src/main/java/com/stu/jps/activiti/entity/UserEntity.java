package com.stu.jps.activiti.entity;
import org.activiti.engine.identity.User;
public class UserEntity implements User{

	private static final long serialVersionUID = -4336553750238316420L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPictureSet() {
		// TODO Auto-generated method stub
		return false;
	}

}
