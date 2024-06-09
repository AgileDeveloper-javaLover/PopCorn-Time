package com.blackpool.PopCornTime.UIController;

import java.io.ByteArrayInputStream;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.blackpool.PopCornTime.App;
import com.blackpool.PopCornTime.UserProfile;
import com.blackpool.PopCornTime.MovieDatabase.DatabaseSource;
import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MovieInnerController implements Initializable {

	@FXML
	private Text ratingText;

	

	@FXML
	private Text yearText;

	@FXML
	private TextArea descText;

	@FXML
	private Text cateText;

	@FXML
	private Text langText;

	@FXML
	private AnchorPane mainPane;

	@FXML
	private JFXToggleButton addToFav;

	private byte[] ss1;
	private byte[] ss2;
	private byte[] ss3;
	private byte[] ss4;

	@FXML
	private Text nameText;

	public int id;
	public int movieId;



	private String movieName;



	private int releaseYear;



	private double rating;

	public JFXToggleButton getAddToFav() {
		return addToFav;
	}

	public void setPostImg(byte[] postImg) {
		ss1 = postImg;
		ByteArrayInputStream bis = new ByteArrayInputStream(postImg);
		Image image = new Image(bis);
		ImageView im = new ImageView(image);
		im.setFitWidth(190);
		im.setFitHeight(250);
		im.setLayoutX(14);
		im.setLayoutY(14);
		mainPane.getChildren().add(im);
	}

	public void setRatingText(double ratingText) {
		rating=ratingText;
		this.ratingText.setText(String.valueOf(ratingText));
	}

	public void setYearText(int yearText) {
		releaseYear=yearText;
		this.yearText.setText(String.valueOf(yearText));
	}

	public void setDescText(String descText) {
		this.descText.setText(descText);
	}

	public void setCategoryText(String cateText) {
		this.cateText.setText(cateText);
	}

	public void setLangText(String langText) {
		this.langText.setText(langText);
	}

	public void setSs1(byte[] ss1) {
		ss2 = ss1;
		ByteArrayInputStream bis = new ByteArrayInputStream(ss1);
		Image image = new Image(bis);
		ImageView im = new ImageView(image);
		im.setFitWidth(200);
		im.setFitHeight(170);
		im.setLayoutX(40);
		im.setLayoutY(370);
		mainPane.getChildren().add(im);
	}

	public void setSs2(byte[] ss2) {
		ss3 = ss2;
		ByteArrayInputStream bis = new ByteArrayInputStream(ss2);
		Image image = new Image(bis);
		ImageView im = new ImageView(image);
		im.setFitWidth(200);
		im.setFitHeight(170);
		im.setLayoutX(280);
		im.setLayoutY(370);
		mainPane.getChildren().add(im);
	}

	public void setSs3(byte[] ss3) {
		ss4 = ss3;
		ByteArrayInputStream bis = new ByteArrayInputStream(ss3);
		Image image = new Image(bis);
		ImageView im = new ImageView(image);
		im.setFitWidth(200);
		im.setFitHeight(170);
		im.setLayoutX(520);
		im.setLayoutY(370);
		mainPane.getChildren().add(im);
	}

	public void setNameText(String nameText) {
		movieName=nameText;
		this.nameText.setText(nameText);
	}

	public static MovieInnerController movieInnerController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		movieInnerController = this;

	}

	@FXML
	void addToFavourite(ActionEvent event) throws SQLException {
		if (addToFav.isSelected()) {
			PreparedStatement stmt = DatabaseSource.dbsource.con
					.prepareStatement("select * from popcorntime_application.movie where id=?");
			stmt.setInt(1, movieId);
			ResultSet rst = null;
			try {
				rst = stmt.executeQuery();
			} catch (SQLException e) {
				System.out.println("not in movie");
				return;
			}
			if (!rst.next()) {
				PreparedStatement stmt1 = DatabaseSource.dbsource.con
						.prepareStatement("insert into popcorntime_application.movie values(?,?,?,?,?,?,?,?,?,?,?)");
				stmt1.setInt(1, movieId);
				stmt1.setString(2, descText.getText());
				stmt1.setString(3, nameText.getText());
				stmt1.setDouble(4, Double.valueOf(ratingText.getText()));
				stmt1.setInt(5, Integer.valueOf(yearText.getText()));
				stmt1.setBytes(6, ss1);
				stmt1.setBytes(7, ss2);
				stmt1.setBytes(8, ss3);
				stmt1.setBytes(9, ss4);
				stmt1.setString(10, cateText.getText());
				stmt1.setString(11, langText.getText());
				stmt1.executeUpdate();
			}

			PreparedStatement stmt2 = DatabaseSource.dbsource.con
					.prepareStatement("insert into popcorntime_application.favourite values(?,?)");
			stmt2.setString(1, UserProfile.email);
			stmt2.setInt(2, movieId);
			try {
				stmt2.executeUpdate();
			} catch (SQLException e) {
				System.out.println("not added in fav");
				return;
			}

		} else {
			PreparedStatement stmt2 = DatabaseSource.dbsource.con
					.prepareStatement("delete from popcorntime_application.favourite where userId=? and movieId=?");
			stmt2.setString(1, UserProfile.email);
			stmt2.setInt(2, movieId);
			try {
				stmt2.executeUpdate();
			} catch (SQLException e) {
				System.out.println("not deleted from fav");
				return;
			}
		}
	}

	public boolean checkFavourite() {
		try {
			PreparedStatement stmt = DatabaseSource.dbsource.con
					.prepareStatement("select * from popcorntime_application.favourite where userId=? and movieId=?");
			stmt.setString(1, UserProfile.email);
			stmt.setInt(2, movieId);
			ResultSet rst = null;
			rst = stmt.executeQuery();
			if (!rst.next())
				return false;
			return true;
		} catch (SQLException e) {
			System.out.println("error in checking in fav");
			return false;
		}
	}

	@FXML
	void close(MouseEvent event) {
		App.mainBorderPain.setBorderPane(SetXml.setxml("movies"));
	}
	
	@FXML
	void shareMovie(MouseEvent event) {
		ShareController.movieThumblin=ss1;
		ShareController.movieName=movieName;
		ShareController.rating=rating;
		ShareController.releaseYear=releaseYear;
		App.mainBorderPain.setBorderPane(SetXml.setxml("share"));
	}
}
