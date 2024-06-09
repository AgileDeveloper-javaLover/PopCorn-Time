package com.Blackpool.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Blackpool.Models.Admin;
import com.Blackpool.Models.Category;
import com.Blackpool.Models.Movie;
import com.Blackpool.Repositories.AdminRepository;
import com.Blackpool.Repositories.MovieRepository;

@Controller
@ComponentScan(basePackages = "com.blackpool.Filters")
public class ViewController {
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private MovieRepository movierepo;

	@GetMapping("/")
	public String root(HttpServletRequest req, HttpServletResponse res) {
		return "index.html";
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession ses = req.getSession();
		ses.removeAttribute("ucredentials");
		resp.sendRedirect("/");
	}

	@PostMapping("/saveDetails")
	public void saveDetails(HttpServletRequest req, HttpServletResponse res, Admin a) throws IOException {
		if (adminRepo.existsById(a.getEmail())) {
			res.sendRedirect("/");
		} else {
			adminRepo.save(a);
			req.getSession().setAttribute("ucredentials", a);
			res.sendRedirect("/home.html");
		}
	}

	@PostMapping("/admin")
	public void admin(HttpServletRequest req, HttpServletResponse res, Admin a) throws IOException, ServletException {

		if (adminRepo.existsById(a.getEmail())) {

			if (a.getPassword().equals(adminRepo.findById(a.getEmail()).get().getPassword())) {

				req.getSession().setAttribute("ucredentials", a);
				res.sendRedirect("/home.html");
			}
		} else {
			res.sendRedirect("/register.html");
		}
	}
	
	
	
	@PostMapping("/addMovie")
	public void addMovie(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("ss1") MultipartFile ss1,@RequestParam("ss2") MultipartFile ss2,
			@RequestParam("ss3") MultipartFile ss3,@RequestParam("ss4") MultipartFile ss4,@RequestParam("language") String[] lan) throws Exception {
		Movie m=new Movie();
		
//		for(String i:category){
//			m.getCategory().ad
//		}
		m.setCategory(setCategory(req));
		
		m.setName(req.getParameter("name"));
		m.setDes(req.getParameter("desc"));
		m.setRating(Double.valueOf(req.getParameter("rating")));
		m.setRelease_Year(Integer.valueOf(req.getParameter("release_Year")));
		System.out.println(lan[0]);
		m.setLanguage(lan[0]);
		
		if(ss1.getSize()!=0)
			m.setSs1(ss1.getBytes());
		if(ss2.getSize()!=0)
			m.setSs2(ss2.getBytes());
		if(ss3.getSize()!=0)
			m.setSs3(ss3.getBytes());
		if(ss4.getSize()!=0)
			m.setSs4(ss4.getBytes());
		movierepo.save(m);
		res.sendRedirect("/home.html");
		
	}
	
	@GetMapping("/allMovie")
	public String allMovie(Model m) {
		List<Movie> l=movierepo.findAll();
		m.addAttribute("movies", l);
		return "/movies.jsp";
	}
	
	@GetMapping("/movieGetById/{id}")
	public String movieById(@PathVariable("id") int id,Model m) {
		Optional<Movie> movie=movierepo.findById(id);
		m.addAttribute("movie", movie.get());
//		System.out.println("name=============="+movie.get().getSs1());
		return "/editmovie.jsp";
	}
	
	
	List<Category> setCategory(HttpServletRequest req){
		List<Category> c=new ArrayList<>();
		if(req.getParameter("action")!=null)
			c.add(new Category(1));
		if(req.getParameter("comedy")!=null)
			c.add(new Category(2));
		if(req.getParameter("drama")!=null)
			c.add(new Category(4));
		if(req.getParameter("thriller")!=null)
			c.add(new Category(8));
		if(req.getParameter("mystery")!=null)
			c.add(new Category(6));
		if(req.getParameter("romance")!=null)
			c.add(new Category(7));
		if(req.getParameter("fantasy")!=null)
			c.add(new Category(5));
		if(req.getParameter("horror")!=null)
			c.add(new Category(3));
		
		return c;
		
	}

}