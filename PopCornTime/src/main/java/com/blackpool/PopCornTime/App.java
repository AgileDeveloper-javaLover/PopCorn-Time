package com.blackpool.PopCornTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.json.JSONException;

import com.blackpool.PopCornTime.Model.Category;
import com.blackpool.PopCornTime.Model.Movie;
import com.blackpool.PopCornTime.MovieDatabase.DatabaseSource;
import com.blackpool.PopCornTime.UIController.HomeController;
import com.blackpool.PopCornTime.UIController.SetXml;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

	public static Scene scene;
	public static HomeController mainBorderPain;
	public static Stage mainstage;

	@Override
	public void start(Stage stage) throws IOException {
		mainstage = stage;
		stage.setTitle("PopCorn Time");
		stage.initStyle(StageStyle.TRANSPARENT);
		scene = new Scene((Parent) SetXml.setxml("login"), 375, 570);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();

		DatabaseSource.DatabaseBuilder builder = new DatabaseSource.DatabaseBuilder();
		new Thread(() -> {
			builder.setName("root").setPassword("Nishant@1234").setUrl("jdbc:mysql://localhost:3306").setConnection();
		}).start();

	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		DatabaseSource.dbsource.con.close();
	}
	
	

	public static void main(String[] args) throws IOException {
		
		
    	launch();
		
	}

	public static void launchMovieUI() {

		mainstage.close();
		mainstage.setScene(new Scene((Parent) SetXml.setxml("home"), 1000, 600));
		mainstage.show();
	}

}