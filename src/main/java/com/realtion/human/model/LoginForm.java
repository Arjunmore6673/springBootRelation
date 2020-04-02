package com.realtion.human.model;

import javax.validation.constraints.NotNull;

public class LoginForm {

	@NotNull
	private String username; // stores the username

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	private String password;
}
