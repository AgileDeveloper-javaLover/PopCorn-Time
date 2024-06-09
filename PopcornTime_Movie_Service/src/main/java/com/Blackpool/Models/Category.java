package com.Blackpool.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String category_name;
	
	@ManyToMany(mappedBy = "category")
	@JsonBackReference
	List<Movie> movie;
	
	
	
	public Category() {
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
