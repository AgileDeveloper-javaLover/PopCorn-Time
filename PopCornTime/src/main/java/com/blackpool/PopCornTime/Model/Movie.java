package com.blackpool.PopCornTime.Model;

import java.util.List;

public class Movie {
	
	private int id;
	private String name;
	private String des;
	private int release_Year;
	private double rating;
	private byte[] ss1;
	private byte[] ss2;
	private byte[] ss3;
	private byte[] ss4;
	private String language;
	
	private List<Category> category ;
	
	public Movie() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRelease_Year() {
		return release_Year;
	}
	public void setRelease_Year(int release_Year) {
		this.release_Year = release_Year;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public byte[] getSs1() {
		return ss1;
	}
	public void setSs1(byte[] ss1) {
		this.ss1 = ss1;
	}
	public byte[] getSs2() {
		return ss2;
	}
	public void setSs2(byte[] ss2) {
		this.ss2 = ss2;
	}
	public byte[] getSs3() {
		return ss3;
	}
	public void setSs3(byte[] ss3) {
		this.ss3 = ss3;
	}
	public byte[] getSs4() {
		return ss4;
	}
	public void setSs4(byte[] ss4) {
		this.ss4 = ss4;
	}
	public List<Category> getCategory() {
		return category;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	
}

