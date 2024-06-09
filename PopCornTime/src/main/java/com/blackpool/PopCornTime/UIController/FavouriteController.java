package com.blackpool.PopCornTime.UIController;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.blackpool.PopCornTime.App;
import com.blackpool.PopCornTime.UserProfile;
import com.blackpool.PopCornTime.Model.Category;
import com.blackpool.PopCornTime.Model.Movie;
import com.blackpool.PopCornTime.MovieDatabase.DatabaseSource;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FavouriteController implements Initializable {

	@FXML
	private FlowPane mainPane;
	private boolean nexStop;
	public static int pageNo=0;
	public static int moviePerPage=8;

	public static List<Movie> favList = new ArrayList<>();
	public static FavouriteController favController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		favController = this;
	}

	public void setMovies() throws SQLException {
		favList.clear();
		List<Integer> ids = getmovieIds();
		if (ids != null) {
			List<Movie> movies = getMovies(ids);
			if (movies != null)
				favList.addAll(movies);
		}
		
		setTemplet();

	}

	private void setTemplet() {
		mainPane.getChildren().clear();
		if (favList.size() == 0)
			return;
		for (int i = pageNo * moviePerPage; i < moviePerPage + moviePerPage * pageNo; i++) {
			if(favList.size()<i-1)return;
			ByteArrayInputStream bis = new ByteArrayInputStream(favList.get(i).getSs1());
			Image image = new Image(bis);
			ImageView im = new ImageView(image);
			im.setFitWidth(155);
			im.setFitHeight(165);

			final int index = i;
			final String name = favList.get(i).getName();
			final String desc = favList.get(i).getDes();
			String cate = "";
			int len = favList.get(i).getCategory().size();
			for (int j = 0; j < len; j++) {
				cate += favList.get(i).getCategory().get(j).getCategory_name();
				if (j != len - 1)
					cate += ",";
			}
			final String categ = cate;
			final String lang = favList.get(i).getLanguage();
			final int year = favList.get(i).getRelease_Year();
			final int movieId = favList.get(i).getId();
			final double rating = favList.get(i).getRating();
			final byte[] poster = favList.get(i).getSs1();
			final byte[] ss1 = favList.get(i).getSs2();
			final byte[] ss2 = favList.get(i).getSs3();
			final byte[] ss3 = favList.get(i).getSs4();

			Text tx = new Text(name);
			tx.setStyle(
					"-fx-stroke : #1f2120;-fx-stroke-width: 1;-fx-font-weight:bold;-fx-cursor: hand;-fx-background-radius:5.5");
			tx.setFont(Font.font(20));
			tx.setFill(Color.WHITE);
			tx.setWrappingWidth(154);

			EventHandler<MouseEvent> e = new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					FavouriteInnerController.favController.id = index;
					FavouriteInnerController.favController.movieId = movieId;
					FavouriteInnerController.favController.setNameText(name);
					FavouriteInnerController.favController.setDescText(desc);
					FavouriteInnerController.favController.setRatingText(rating);
					FavouriteInnerController.favController.setYearText(year);
					FavouriteInnerController.favController.setPostImg(poster);
					FavouriteInnerController.favController.setSs1(ss1);
					FavouriteInnerController.favController.setSs2(ss2);
					FavouriteInnerController.favController.setSs3(ss3);
					FavouriteInnerController.favController.setCategoryText(categ);
					FavouriteInnerController.favController.setLangText(lang);

					FavouriteInnerController.favController.getAddToFav().setSelected(true);

					App.mainBorderPain.setBorderPane(SetXml.setxml("favInner"));

				}

			};

			tx.setOnMouseClicked(e);
			VBox v = new VBox(im, tx);
			v.prefWidth(154);
			v.prefHeight(210);
			v.setStyle("-fx-background-color:black;-fx-background-radius:5.5");

			mainPane.getChildren().add(v);
			if (i == favList.size() - 1) {
				nexStop = true;
				break;
			}

		}

	}

	private List<Movie> getMovies(List<Integer> ids) throws SQLException {
		List<Movie> l = new ArrayList<>();
		for (Integer i : ids) {
			PreparedStatement stmt = DatabaseSource.dbsource.con
					.prepareStatement("select * from popcorntime_application.movie where id=?");
			stmt.setInt(1, i);
			ResultSet rst = stmt.executeQuery();
			if (!rst.next())
				return null;
			do {
				l.add(parseToMovie(rst, l));
			} while (rst.next());
		}
		return l;
	}

	private Movie parseToMovie(ResultSet rst, List<Movie> l) throws SQLException {
		Movie m = new Movie();
		m.setId(rst.getInt(1));
		m.setDes(rst.getString(2));
		m.setName(rst.getString(3));
		m.setRating(rst.getDouble(4));
		m.setRelease_Year(rst.getInt(5));
		m.setSs1(rst.getBytes(6));
		m.setSs2(rst.getBytes(7));
		m.setSs3(rst.getBytes(8));
		m.setSs4(rst.getBytes(9));

		List<String> c = List.of(rst.getString(10).split(","));
		List<Category> cl = new ArrayList<>();
		for (String i : c) {
			cl.add(new Category(i));
		}
		m.setCategory(cl);

		m.setLanguage(rst.getString(11));

		return m;
	}

	private List<Integer> getmovieIds() throws SQLException {
		List<Integer> l = new ArrayList<>();
		PreparedStatement stmt = DatabaseSource.dbsource.con
				.prepareStatement("select * from popcorntime_application.favourite where userId=?");
		stmt.setString(1, UserProfile.email);
		ResultSet rst = stmt.executeQuery();
		if (!rst.next())
			return null;
		do {
			l.add(rst.getInt(2));
		} while (rst.next());

		return l;
	}

	@FXML
	void next(MouseEvent event) {
		if (nexStop)
			return;
		mainPane.getChildren().clear();
		pageNo++;

		setTemplet();
	}

	@FXML
	void previous(MouseEvent event) {
		if (pageNo == 0)
			return;
		mainPane.getChildren().clear();
		pageNo--;
		nexStop = false;
		setTemplet();

	}

}
