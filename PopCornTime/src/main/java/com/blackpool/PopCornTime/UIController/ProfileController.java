package com.blackpool.PopCornTime.UIController;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.blackpool.PopCornTime.UserProfile;
import com.blackpool.PopCornTime.Model.MovieFilter;
import com.blackpool.PopCornTime.MovieDatabase.DatabaseSource;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ProfileController implements Initializable {

	@FXML
	private TextField utext;

	@FXML
	private TextField mailtext;

	@FXML
	private PasswordField passtext;

	@FXML
	private TextField pass_hidden;

	@FXML
	private CheckBox romance;

	@FXML
	private CheckBox horror;

	@FXML
	private CheckBox fantasy;

	@FXML
	private CheckBox thriller;

	@FXML
	private CheckBox drama;

	@FXML
	private CheckBox comedy;

	@FXML
	private CheckBox mystery;

	@FXML
	private CheckBox action;

	@FXML
	private Button reset;

	@FXML
	private Button save;

	private ArrayList<String> editInterest = new ArrayList<>();

	@FXML
	void resetChanges(MouseEvent event) {
		uncheckCheckbox();
		try {
			prepareProfile();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void uncheckCheckbox() {
		
		horror.setSelected(false);
		fantasy.setSelected(false);
		thriller.setSelected(false);
		drama.setSelected(false);
		comedy.setSelected(false);
		action.setSelected(false);
		romance.setSelected(false);
		mystery.setSelected(false);
		
	}

	@FXML
	void saveUpdates(MouseEvent event) throws SQLException {
		

		DatabaseSource.dbsource.con.setAutoCommit(false);
		String passw = passtext.getText();
		String username = utext.getText();

		String result = "";
		for (String s : editInterest) {
			result = s + "," + result;
		}
		MovieFilter.category.clear();
		MovieFilter.category.addAll(editInterest);
		PreparedStatement stmt = DatabaseSource.dbsource.con
				.prepareStatement("update popcorntime_application.user set pass=?,interest=?,uname=? where email=?");

		stmt.setString(1, passw);
		stmt.setString(2, result);
		stmt.setString(3, username);
		stmt.setString(4, UserProfile.email.toLowerCase());

		stmt.executeUpdate();

		DatabaseSource.dbsource.con.commit();


	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new Thread(()->{

			setCheckboxListener(fantasy, editInterest);
			setCheckboxListener(horror, editInterest);
			setCheckboxListener(romance, editInterest);
			setCheckboxListener(action, editInterest);
			setCheckboxListener(thriller, editInterest);
			setCheckboxListener(drama, editInterest);
			setCheckboxListener(comedy, editInterest);
			setCheckboxListener(mystery, editInterest);
			
			
			try {
				prepareProfile();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


		}).start();
	}

	private void prepareProfile() throws SQLException {
		editInterest.clear();
		mailtext.setText(UserProfile.email);
		
		PreparedStatement stmt = DatabaseSource.dbsource.con.prepareStatement("select * from popcorntime_application.user where email=?");
		stmt.setString(1,UserProfile.email);
		
		ResultSet rs=stmt.executeQuery();
		rs.next();
		passtext.setText(rs.getString(2));
		List<String> temp=List.of(rs.getString(3).split(","));
		utext.setText(rs.getString(4));

//		editInterest.addAll(UserProfile.interest);
		for (String i : temp) {
			if (i.equals("Horror")) {
				horror.setSelected(true);
				continue;
			}if (i.equals("Drama")) {
				drama.setSelected(true);
				continue;
			}if (i.equals("Mystery")) {
				mystery.setSelected(true);
				continue;
			} if (i.equals("Comedy")) {
				comedy.setSelected(true);
				continue;
			} if (i.equals("Action")) {
				action.setSelected(true);
				continue;
			}if (i.equals("Romance")) {
				romance.setSelected(true);
				continue;
			} if (i.equals("Thriller")) {
				thriller.setSelected(true);
				continue;
			} if(i.equals("Fantasy"))
				fantasy.setSelected(true);
		}
		
	}

	public static void setCheckboxListener(CheckBox chk, List<String> l) {

		chk.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				if (newValue)
					l.add(chk.getText());
				else
					l.remove(chk.getText());
			};
		});
	}

	@FXML
	void showPassword(MouseEvent event) {
		pass_hidden.setText(passtext.getText());
		pass_hidden.setVisible(true);
	}

	@FXML
	void hidePassword(MouseEvent event) {
		pass_hidden.setVisible(false);
	}
	

}
