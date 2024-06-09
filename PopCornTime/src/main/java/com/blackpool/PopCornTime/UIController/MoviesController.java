package com.blackpool.PopCornTime.UIController;

import java.io.BufferedReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONException;

import com.blackpool.PopCornTime.App;
import com.blackpool.PopCornTime.Model.Category;
import com.blackpool.PopCornTime.Model.Movie;
import com.blackpool.PopCornTime.Services.MovieServiceFromDatabase;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MoviesController implements Initializable {
	public static int pageNo = 0;

	private MovieServiceFromDatabase movieService;
	@FXML
	private GridPane gridpane;

	@FXML
	private TextField search;

	@FXML
	private FlowPane mainPane;

	public static MoviesController mvController = null;

	public boolean nexStop = false;

	public static List<Movie> movies;
	public static List<Movie> filtermovie = new ArrayList<Movie>();

	private int moviePerPage = 8;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		System.out.println("initializing movie ");
		mvController = this;

		getMoviesFromService();

	}

	public void getMoviesFromService() {
		Service<List<Movie>> service = new Service<List<Movie>>() {

			@Override
			protected void succeeded() {
				cleanMainPane();
				setMovies();
			}

			@Override
			protected Task<List<Movie>> createTask() {
				return new Task<List<Movie>>() {

					@Override
					protected List<Movie> call() throws Exception {
						List<Movie> json = null;
						try {
							json = readJsonFromUrl("http://localhost:8080/getAll");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						movies = json;
						filtermovie.addAll(movies);
						return json;
					}

				};
			}

		};

		service.start();

	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
//	    rd.read();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public void cleanMainPane() {
		mainPane.getChildren().clear();
		pageNo = 0;
		if (moviePerPage < filtermovie.size() - 1)
			nexStop = false;
		else
			nexStop = true;
	}

	public static List<Movie> readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			final ObjectMapper objectMapper = new ObjectMapper();
			Movie[] json = objectMapper.readValue(jsonText, Movie[].class);
			return List.of(json);
		} finally {
			is.close();
		}
	}

	
	//fucntion to get slice of array
	public static byte[] getSliceOfArray(byte[] arr, int start, int end) {


		byte[] slice = new byte[end - start];

		for (int i = 0; i < slice.length; i++) {
			slice[i] = arr[start + i];
		}

		return slice;
	}

	public void setMovies() {

		if (filtermovie.size() == 0)
			return;
		for (int i = pageNo * moviePerPage; i < moviePerPage + moviePerPage * pageNo; i++) {

			ByteArrayInputStream bis = new ByteArrayInputStream(getSliceOfArray(filtermovie.get(i).getSs1(), 0, filtermovie.get(i).getSs1().length/2));
			Image image = new Image(bis);
			ImageView im = new ImageView(image);
			im.setFitWidth(155);
			im.setFitHeight(165);

			final int index = i;
			final String name = filtermovie.get(i).getName();
			final String desc = filtermovie.get(i).getDes();
			String cate = "";
			int len = filtermovie.get(i).getCategory().size();
			for (int j = 0; j < len; j++) {
				cate += filtermovie.get(i).getCategory().get(j).getCategory_name();
				if (j != len - 1)
					cate += ",";
			}
			final String categ = cate;
			final String lang = filtermovie.get(i).getLanguage();
			final int year = filtermovie.get(i).getRelease_Year();
			final int movieId = filtermovie.get(i).getId();
			final double rating = filtermovie.get(i).getRating();
			final byte[] poster = filtermovie.get(i).getSs1();
			final byte[] ss1 = filtermovie.get(i).getSs2();
			final byte[] ss2 = filtermovie.get(i).getSs3();
			final byte[] ss3 = filtermovie.get(i).getSs4();

			Text tx = new Text(name);
			tx.setStyle(
					"-fx-stroke : #1f2120;-fx-stroke-width: 1;-fx-font-weight:bold;-fx-cursor: hand;-fx-background-radius:5.5");
			tx.setFont(Font.font(20));
			tx.setFill(Color.WHITE);
			tx.setWrappingWidth(154);

			EventHandler<MouseEvent> e = new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					MovieInnerController.movieInnerController.id = index;
					MovieInnerController.movieInnerController.movieId = movieId;
					MovieInnerController.movieInnerController.setNameText(name);
					MovieInnerController.movieInnerController.setDescText(desc);
					MovieInnerController.movieInnerController.setRatingText(rating);
					MovieInnerController.movieInnerController.setYearText(year);
					MovieInnerController.movieInnerController.setPostImg(poster);
					MovieInnerController.movieInnerController.setSs1(ss1);
					MovieInnerController.movieInnerController.setSs2(ss2);
					MovieInnerController.movieInnerController.setSs3(ss3);
					MovieInnerController.movieInnerController.setCategoryText(categ);
					MovieInnerController.movieInnerController.setLangText(lang);

					if (MovieInnerController.movieInnerController.checkFavourite())
						MovieInnerController.movieInnerController.getAddToFav().setSelected(true);
					else
						MovieInnerController.movieInnerController.getAddToFav().setSelected(false);

					App.mainBorderPain.setBorderPane(SetXml.setxml("movieInner"));

				}

			};

			tx.setOnMouseClicked(e);
			VBox v = new VBox(im, tx);
			v.prefWidth(154);
			v.prefHeight(210);
			v.setStyle("-fx-background-color:black;-fx-background-radius:5.5");

			mainPane.getChildren().add(v);
			if (i == filtermovie.size() - 1) {
				nexStop = true;
				break;
			}

		}
	}

	@FXML
	void next(MouseEvent event) {
		if (nexStop)
			return;
		mainPane.getChildren().clear();
		pageNo++;
		setMovies();
	}

	@FXML
	void previous(MouseEvent event) {
		if (pageNo == 0)
			return;
		mainPane.getChildren().clear();
		pageNo--;
		nexStop = false;
		setMovies();
	}

	@FXML
	void searchMovieBYName(MouseEvent event) {
		filtermovie.clear();
		if (search.getText().trim().equals("")) {
			filtermovie.addAll(movies);
		} else {
			List<Movie> filtered = new ArrayList<>();
			movies.forEach(e -> {
				if (e.getName().trim().toLowerCase().equals(search.getText().trim().toLowerCase()))
					filtered.add(e);
			});
			filtermovie.addAll(filtered);
		}

		cleanMainPane();
		if (filtermovie.size() != 0)
			setMovies();
	}

	@FXML
	void filter(MouseEvent event) {
		App.mainBorderPain.setBorderPane(SetXml.setxml("filter"));
	}

}
