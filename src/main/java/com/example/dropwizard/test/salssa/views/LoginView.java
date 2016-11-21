package com.example.dropwizard.test.salssa.views;

import io.dropwizard.views.View;

public class LoginView extends View{

	private String message;
	
	public LoginView(String message) {
		super("mustache/login.mustache");
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
