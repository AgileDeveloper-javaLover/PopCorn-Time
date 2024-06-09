package com.Blackpool.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	private String password;
	@Id
	private String email;


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

	
	
	
	
}
