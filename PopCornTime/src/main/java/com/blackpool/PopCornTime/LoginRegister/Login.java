package com.blackpool.PopCornTime.LoginRegister;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.blackpool.PopCornTime.App;
import com.blackpool.PopCornTime.UserProfile;
import com.blackpool.PopCornTime.MovieDatabase.DatabaseSource;
import com.blackpool.PopCornTime.UIController.SetXml;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class Login  {

	@FXML
	private JFXTextField email;

	@FXML
	private JFXPasswordField pass;

	@FXML
	private void register(MouseEvent event) {
		App.scene.setRoot((Parent) SetXml.setxml("register"));
	}

	@FXML
	private void closeWindow(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void submit(MouseEvent event) throws SQLException {
		PreparedStatement stmt=DatabaseSource.dbsource.con.prepareStatement("select * from popcorntime_application.user where email=?");
		stmt.setString(1, email.getText().toLowerCase());
		ResultSet rst=null;
		try {
			rst=stmt.executeQuery();
		} catch (SQLException e) {
			email.setText("");
			System.out.println("either email is not exist or Statement is null");
			return;
		}
		rst.next();
		if(!rst.first())
			return ;
		if (!pass.getText().equals(rst.getString(2))) {
			pass.setText("");
			return;
		}
		
		UserProfile.email=rst.getString(1);
		
		
//		for(String i:UserProfile.interest)
//			System.out.println(i);
		App.launchMovieUI();
	}

	
}
