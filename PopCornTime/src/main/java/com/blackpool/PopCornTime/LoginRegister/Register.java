package com.blackpool.PopCornTime.LoginRegister;

import java.net.URL;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.blackpool.PopCornTime.App;
import com.blackpool.PopCornTime.UserProfile;
import com.blackpool.PopCornTime.MovieDatabase.DatabaseSource;
import com.blackpool.PopCornTime.UIController.SetXml;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

import com.blackpool.PopCornTime.UIController.ProfileController;

public class Register implements Initializable {

	@FXML
	private CheckBox chk1;

	@FXML
	private CheckBox chk2;

	@FXML
	private CheckBox chk3;

	@FXML
	private CheckBox chk5;

	@FXML
	private CheckBox chk6;

	@FXML
	private CheckBox chk7;

	@FXML
	private CheckBox chk4;

	@FXML
	private CheckBox chk8;

	@FXML
	private JFXTextField email;
	
	@FXML
    private JFXTextField uname;

	@FXML
	private JFXPasswordField pass;

	private List<String> category = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ProfileController.setCheckboxListener(chk1,category);
		ProfileController.setCheckboxListener(chk2,category);
		ProfileController.setCheckboxListener(chk3,category);
		ProfileController.setCheckboxListener(chk4,category);
		ProfileController.setCheckboxListener(chk5,category);
		ProfileController.setCheckboxListener(chk6,category);
		ProfileController.setCheckboxListener(chk7,category);
		ProfileController.setCheckboxListener(chk8,category);
	}

	@FXML
	private void login(MouseEvent event) {
		App.scene.setRoot((Parent) SetXml.setxml("login"));
	}

	@FXML
	private void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void submit(MouseEvent event) throws SQLException {
		
		try {
			DatabaseSource.dbsource.con.setAutoCommit(false);
			String mailId=email.getText().toLowerCase();
			String passw=pass.getText();
			String username=uname.getText();
			
			if(mailId=="" || passw=="")
				return ;
			String result = "";
			for (String s : category) {
				result = s + "," + result;
			}
			
			PreparedStatement stmt = DatabaseSource.dbsource.con.prepareStatement("insert into popcorntime_application.user values(?,?,?,?)");
			stmt.setString(1,mailId );
			stmt.setString(2,passw);
			stmt.setString(3, result);
			stmt.setString(4,username);
			try {
				stmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("may be,email already exist");
				return;
			}
			
			
			DatabaseSource.dbsource.con.commit();
			App.launchMovieUI();
			UserProfile.email=mailId;
		} catch (Exception e) {
			System.out.println("rolled back");
			DatabaseSource.dbsource.con.rollback();
		}
		
	}
	
	private void createDatabase(String name) throws SQLException {
		
		Statement stmt = DatabaseSource
				.dbsource
				.con
				.createStatement();
		
		
		stmt.execute(" CREATE DATABASE "+name);
		
		stmt=DatabaseSource.dbsource.con.createStatement();
		String sql = "CREATE TABLE "+name+".favourite "+
					"(userId varchar(40) NOT NULL PRIMARY KEY,"+
					" movieId varchar(30));";
                 
		stmt.executeUpdate(sql);
		stmt=DatabaseSource.dbsource.con.createStatement();
		
		sql = "CREATE TABLE "+name+".share "+
				"(userId varchar(40) NOT NULL PRIMARY KEY,"+
				" movieId varchar(30));";
		stmt.execute(sql);
		
		
	}

}
