package com.example.dropwizard.test.salssa.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User context class.
 * @author Elitza Haltakova
 *
 */
public class UserContext {
	
	@JsonProperty
	private String sessionToken;
	@JsonProperty
	private int id;
	@JsonProperty
	private String username;
	@JsonProperty("firstname")
	private String firstName;
	@JsonProperty("lastname")
	private String lastName;
	@JsonProperty("isAdmin")
	private int admin;
	@JsonProperty("shouldChangePassword")
	private int changePassword;
	@JsonProperty
	private List<String> customers;
	
	public UserContext() {
		
	}
	
	public UserContext(String sessionToken, int id, String username, String firstName, String lastName, int admin, int changePassword, List<String> customers) {
		this.sessionToken = sessionToken;
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.admin = admin;
		this.changePassword = changePassword;
		this.customers = customers;
	}
	
	public String getSessionToken() {
		return sessionToken;
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
	
	public int isAdmin() {
		return admin;
	}
	
	public int shouldChangePassword() {
		return changePassword;
	}
	
	public List<String> getCustomers() {
		return customers;
	}
}
