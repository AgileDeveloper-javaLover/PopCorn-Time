package com.blackpool.PopCornTime.UIController;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class PreLoaderController implements Initializable{

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setRotation(circle1,true,360,5);
		setRotation(circle2,false,360,5);
		setRotation(circle3,true,360,5);
		
	}
    
    private void setRotation(Circle circle, boolean b, int i, int j) {
		RotateTransition rt=new RotateTransition(Duration.seconds(j), circle);
		
		rt.setAutoReverse(b);
		rt.setByAngle(i);
		rt.setRate(3);
		rt.setCycleCount(25);
		
		rt.play();
		
	}
}