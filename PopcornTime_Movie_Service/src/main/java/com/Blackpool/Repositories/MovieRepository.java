package com.Blackpool.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Blackpool.Models.Movie;

@Repository
public interface MovieRepository  extends JpaRepository<Movie, Integer>{
		
//		@Query("SELECT m FROM Movie m WHERE m.release_Year >= :start and m.release_Year <= :end")
//		public List<Movie> findByYears(@Param("start") int start,@Param("end") int end);
//		
//		
//		@Query("SELECT m FROM Movie m WHERE m.rating >= ?1 and m.rating <= ?2")
//		public List<Movie> findByRating(int start,int end);
	
}
