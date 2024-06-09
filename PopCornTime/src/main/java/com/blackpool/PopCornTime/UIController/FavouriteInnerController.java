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

public class FavouriteInnerController implements Initializable {

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


	@FXML
	private Text nameText;

	public int id;
	public int movieId;

	public static FavouriteInnerController favController;

	public JFXToggleButton getAddToFav() {
		return addToFav;
	}

	public void setPostImg(byte[] postImg) {
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
		this.ratingText.setText(String.valueOf(ratingText));
	}

	public void setYearText(int yearText) {
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
		this.nameText.setText(nameText);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		favController = this;

	}

	@FXML
	void removeFromFav(ActionEvent event) throws SQLException {

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
		
		App.mainBorderPain.favBtnlickedButton();
	}

	

	@FXML
	void close(MouseEvent event) throws SQLException {
		App.mainBorderPain.setBorderPane(SetXml.setxml("favourite"));
	}

}
