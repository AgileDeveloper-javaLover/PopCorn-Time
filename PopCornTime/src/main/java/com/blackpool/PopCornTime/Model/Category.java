package com.blackpool.PopCornTime.Model;

import java.util.List;

public class Category {
	
	private int id;
	
	private String category_name;
	List<Movie> movie;
	
	
	
	public Category() {
	}

	public Category(String s) {
		category_name=s;
	}

	public Category(String category_name, List<Movie> movie) {
		super();
		this.category_name = category_name;
		this.movie = movie;
	}
	
	

	public Category(int id) {
		super();
		this.id = id;
	}



	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public List<Movie> getMovie() {
		return movie;
	}

	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}
}
