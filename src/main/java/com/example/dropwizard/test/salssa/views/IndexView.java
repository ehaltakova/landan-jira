package com.example.dropwizard.test.salssa.views;

import io.dropwizard.views.View;

public class IndexView extends View{

	private String message;
	
	public IndexView(String message) {
		super("mustache/index.mustache");
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
