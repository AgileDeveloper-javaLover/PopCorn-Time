package com.Blackpool.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blackpool.Models.Movie;
import com.Blackpool.Repositories.MovieRepository;

@Service
public class MovieService {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private MovieRepository movierepo;
	
	public List<Movie> getAllMovie() {
		return movierepo.findAll();
	}
	
	public List<Movie> getByName(String name){
		return movierepo.findAll().stream()
				.filter(m->m.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}
	
	public List<Movie> getByRelaseYears(int a,int b){
		return movierepo.findAll().stream()
				.filter(m-> m.getRelease_Year() >=a && m.getRelease_Year()<=b)
				.collect(Collectors.toList());
	}
	
	public List<Movie> getByRatings(double a,double b){
		return movierepo.findAll().stream()
				.filter(m->(m.getRating()>=a && m.getRating()<=b))
				.collect(Collectors.toList());
	}
	
	
	public List<Movie> getByCatagory(List<String> cat) {
		return movierepo.findAll().stream()
				.filter(m->{
					for(String c:cat) {
						if(m.getCategory().contains(c.toLowerCase()))
							return true;
					}
					return false;
				})
				.collect(Collectors.toList());
    }

	public Optional<Movie> getById(int id) {
		
		return movierepo.findById(id);
	}
	
}
