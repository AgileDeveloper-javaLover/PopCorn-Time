package com.Blackpool.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Blackpool.Models.Movie;
import com.Blackpool.Services.MovieService;

@RestController
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/getAll")
	public List<Movie> getAll() {
		return movieService.getAllMovie();
	}
	
	@GetMapping("/getById/{id}")
	public Optional<Movie> getById(@PathVariable("id") int id) {
		return movieService.getById(id);
	}
	
	@GetMapping("/getByName/{name}")
	public List<Movie> getByName(@PathVariable("name") String name){
		return movieService.getByName(name);
	}
	
	@GetMapping("/getByRating/{a}/{b}")
	public List<Movie> getByRating(@PathVariable("a") double a, @PathVariable("b")double b){
		return movieService.getByRatings(a, b);
	}
	
	
	@GetMapping("/getByYear/{a}/{b}")
	public List<Movie> getByYear(@PathVariable("a") int a, @PathVariable("b")int b){
		return movieService.getByRelaseYears(a, b);
	}
	
	@PostMapping("/getByCatagory")
	public List<Movie> getByCatagory(@RequestBody List<String> cat){
		return movieService.getByCatagory(cat);
		
	}
	
	
}
