package com.example.dropwizard.test.salssa.api;

import java.security.Principal;
import java.util.List;

/**
 * Principal requesting access to a resource
 * @author Elitza Haltakova
 *
 */
public class User implements Principal {

	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private boolean admin;
	private boolean changePassword;
	private List<String> customers;
	
	public User() {
		
	}
	
	public User(int id, String username, String firstName, String lastName, boolean admin, boolean changePassword, List<String> customers) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.admin = admin;
		this.changePassword = changePassword;
		this.customers = customers;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public boolean shouldChangePassword() {
		return changePassword;
	}
	
	public List<String> getCustomers() {
		return customers;
	}

	// Principal method
	public String getName() {
		return username;
	}
}
