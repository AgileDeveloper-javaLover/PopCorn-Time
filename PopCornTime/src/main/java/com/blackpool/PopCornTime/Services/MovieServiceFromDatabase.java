package com.blackpool.PopCornTime.Services;

import com.blackpool.PopCornTime.Model.Movie;
import com.blackpool.PopCornTime.MovieDatabase.MovieDatabase;

public class MovieServiceFromDatabase {
	
	private MovieDatabase movieDatabase=MovieDatabase.database;

	public Movie getAllMovieById(int id) {
		
		return movieDatabase.getById(id);

	}
}
