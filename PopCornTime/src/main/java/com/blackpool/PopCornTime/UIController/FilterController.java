package com.blackpool.PopCornTime.UIController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.blackpool.PopCornTime.App;
import com.blackpool.PopCornTime.Model.Category;
import com.blackpool.PopCornTime.Model.Movie;
import com.blackpool.PopCornTime.Model.MovieFilter;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class FilterController implements Initializable {

	@FXML
	private JFXSlider startY;

	@FXML
	private JFXCheckBox bollywood;

	@FXML
	private JFXCheckBox tollywood;

	@FXML
	private JFXCheckBox hollywood;

	@FXML
	private JFXCheckBox dhollywood;

	@FXML
	private JFXSlider startR;

	@FXML
	private JFXSlider endY;

	@FXML
	private JFXSlider endR;

	@FXML
	private JFXToggleButton tgbtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		tgbtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (tgbtn.isSelected())
					disableAll();
				else
					enableAll();
			}

			private void enableAll() {
				startY.setDisable(true);
				startR.setDisable(true);

				endY.setDisable(true);
				endR.setDisable(true);

				tollywood.setDisable(true);
				hollywood.setDisable(true);
				bollywood.setDisable(true);
				dhollywood.setDisable(true);
			}

			private void disableAll() {
				startY.setDisable(false);
				startR.setDisable(false);

				endY.setDisable(false);
				endR.setDisable(false);

				tollywood.setDisable(false);
				hollywood.setDisable(false);
				bollywood.setDisable(false);
				dhollywood.setDisable(false);
			}

		});
	}

	@FXML
	void close(MouseEvent event) {
		App.mainBorderPain.setBorderPane(SetXml.setxml("movies"));
	}

	@FXML
	void search(MouseEvent event) {
		MoviesController.filtermovie.clear();
		if (!tgbtn.isSelected()) {
			MoviesController.filtermovie.addAll(MoviesController.movies);

		} else {
			MovieFilter.startYear = (int) startY.getValue();
			MovieFilter.endYear = (int) endY.getValue();
			if (MovieFilter.startYear >= MovieFilter.endYear)
				MovieFilter.endYear = MovieFilter.startYear;

			MovieFilter.startRating = (int) startR.getValue();
			MovieFilter.endRating = (int) endR.getValue();

			if (MovieFilter.startRating >= MovieFilter.endRating)
				MovieFilter.endRating = MovieFilter.startRating;

			List<String> l = new ArrayList<>();

			if (bollywood.isSelected())
				l.add("Bollywood");

			if (tollywood.isSelected())
				l.add("Tollywood");

			if (dhollywood.isSelected())
				l.add("Dhollywood");
			
			if (hollywood.isSelected())
				l.add("Hollywood");

			MovieFilter.languageSpecific = l;

			List<Movie> filtered = new ArrayList<>();
			MoviesController.movies.forEach(e -> addMovies(e, filtered));

			MoviesController.filtermovie.addAll(filtered);
		}
//		System.out.println(MovieFilter.startRating);
//		System.out.println(MovieFilter.endRating);
//		System.out.println();
//		
//		System.out.println(MovieFilter.startYear);
//		System.out.println(MovieFilter.endYear);
//		
//		System.out.println("language------>");
//		for(String i:MovieFilter.languageSpecific)
//			System.out.println(i);
//		
//		System.out.println("category------>");
//		for(String i:MovieFilter.category)
//			System.out.println(i);
		

		MoviesController.mvController.cleanMainPane();
		MoviesController.mvController.setMovies();
		close(event);
	}

	private static void addMovies(Movie m, List<Movie> l) {

		if (!isCategoryMatch(m))
			return;
		if (!isLanguageMatch(m))
			return;
		if (!isRatingMatch(m))
			return;
		if (!isYearMatch(m))
			return;

		l.add(m);

	}

	private static boolean isLanguageMatch(Movie m) {
		boolean flag = false;
		for (String i : MovieFilter.languageSpecific) {
			if (m.getLanguage().toLowerCase().equals(i.toLowerCase())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private static boolean isYearMatch(Movie m) {
		if (m.getRelease_Year() > MovieFilter.endYear)
			return false;

		if (m.getRelease_Year() < MovieFilter.startYear)
			return false;

		return true;
	}

	private static boolean isRatingMatch(Movie m) {
		if (m.getRating() > MovieFilter.endRating)
			return false;

		if (m.getRating() < MovieFilter.startRating)
			return false;

		return true;
	}

	private static boolean isCategoryMatch(Movie m) {
		boolean match = false;
		for (Category ca : m.getCategory()) {
			for (String i : MovieFilter.category) {
				if (ca.getCategory_name().toLowerCase().equals(i.toLowerCase())) {
					match = true;
					break;
				}
			}
			if(match)
				break;
		}
		return match;
	}

}
