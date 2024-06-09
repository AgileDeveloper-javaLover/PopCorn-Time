package com.blackpool.PopCornTime.UIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.blackpool.PopCornTime.App;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	private double x;
	private double y;

	@FXML
	private Button mvbtn;

	@FXML
	private Button favbtn;

	@FXML
	private Button probtn;

	@FXML
	private BorderPane borderPane;

	private Bloom bloom = new Bloom();

	private Button prebtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		App.mainBorderPain = this;
		
		new Thread(()->{

			mvbtn.setOnMouseClicked(e -> {
				mvBtnClickedButton();
			});

			favbtn.setOnMouseClicked(e -> {
				favBtnlickedButton();
			});


			probtn.setOnMouseClicked(e -> {
				proBtnlickedButton();
			});

			
		}).start();
		mvBtnClickedButton();

	}

	

	private void proBtnlickedButton() {
		setStyle(probtn);
		setBorderPane(SetXml.setxml("profile"));

	}

	public void favBtnlickedButton() {
		setStyle(favbtn);
		setBorderPane(SetXml.setxml("favourite"));
		try {
			FavouriteController.favController.setMovies();
		} catch (SQLException e) {
			System.out.println("couldn't set movie in fav through home controller");
			e.printStackTrace();
		}

	}

	private boolean completed = false;

	private void mvBtnClickedButton() {
		setStyle(mvbtn);
//		if (!completed)
//			setBorderPane(SetXml.setxml("preloader"));
//		Service<Void> service = new Service<Void>() {
//
//			@Override
//			protected Task<Void> createTask() {
//				return new Task<Void>() {
//
//					@Override
//					protected Void call() {
//						System.out.println("in begining");
//						try {
//							Thread.sleep(10000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						;
//
//						System.out.println("in end");
//						return null;
//					}
//
//					@Override
//					protected void succeeded() {
//						setBorderPane(SetXml.setxml("movies"));
//						completed = true;
//					}
//				};
//			}
//		};
//		System.out.println(service.getState());
//		if (!service.isRunning()) {
//			service.start();
//		}
		
		setBorderPane(SetXml.setxml("movies"));
	}

	private void setStyle(Button btn) {
		remove();

		btn.setFont(Font.font(30));
		btn.setEffect(bloom);
	}

	private void remove() {
		mvbtn.setFont(Font.font(25));
		mvbtn.setEffect(null);

		probtn.setFont(Font.font(25));
		probtn.setEffect(null);

		
		favbtn.setFont(Font.font(25));
		favbtn.setEffect(null);

	}

	@FXML
	void close(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY)
			System.exit(0);
	}

	@FXML
	void mini(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	void move(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		System.out.println("screenX "+event.getScreenX()+"  screenY"+event.getScreenY());
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}

	@FXML
	void pressed(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		System.out.println("screenX "+event.getScreenX()+"  screenY"+event.getScreenY());
//		System.out.println("X "+event.getSceneX()+" Y"+event.getSceneY());
		x = event.getSceneX();
		y = event.getSceneY();
	}

	public void setBorderPane(Node bp) {
		borderPane.setCenter(bp);
	}

}
