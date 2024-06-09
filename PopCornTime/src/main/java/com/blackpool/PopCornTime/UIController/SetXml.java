package com.blackpool.PopCornTime.UIController;

import java.io.IOException;

import com.blackpool.PopCornTime.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class SetXml {
	private static Node homeN=null;
	private static Node registerN=null;
	private static Node moviesN=null;
	private static Node loginN=null;
	private static Node proN=null;
	private static Node shareN=null;
	private static Node favN=null;
	private static Node preN=null;
	private static Node filterN=null;
	private static Node movieInnerN=null;
	private static Node favInnerN=null;
	static {
		try {
			loginN = new FXMLLoader(App.class.getResource("login.fxml")).load();
			registerN = new FXMLLoader(App.class.getResource("Register.fxml")).load();
			
		} catch (IOException e) {
			System.out.println("didn't load fxml files");
		}

	}

	public static Node setxml(String xml){
		try {
			if (xml.equals("login")) {
				return loginN;
			} else if (xml.equals("register")) {
				return registerN;
			}
			else if(xml.equals("home")) {
				if(homeN==null)
					homeN=new FXMLLoader(App.class.getResource("Home.fxml")).load();
				return homeN;
			}
			else if(xml.equals("profile")) {
				if(proN==null)
					proN=new FXMLLoader(App.class.getResource("profile.fxml")).load();
				return proN;
			}
			else if(xml.equals("share")) {
				if(shareN==null)
					shareN=new FXMLLoader(App.class.getResource("Share.fxml")).load();
				return shareN;
			}
			else if(xml.equals("favourite")) {
				if(favN==null) {
					favN=new FXMLLoader(App.class.getResource("favourite.fxml")).load();
					favInnerN=new FXMLLoader(App.class.getResource("favInner.fxml")).load();
				}
				return favN;
			}
			else if(xml.equals("movies")) {
				if(moviesN==null) {
					movieInnerN=new FXMLLoader(App.class.getResource("movieInner.fxml")).load();
					moviesN=new FXMLLoader(App.class.getResource("movies.fxml")).load();
				}
				return moviesN;
			}
			else if(xml.equals("preloader")) {
				if(preN==null)
					preN=new FXMLLoader(App.class.getResource("preloader.fxml")).load();
				return preN;
			}
			else if(xml.equals("filter")) {
				if(filterN==null)
					filterN=new FXMLLoader(App.class.getResource("filter.fxml")).load();
				return filterN;
			}
			else if(xml.equals("movieInner")) {
				return movieInnerN;
			}
			
			else if(xml.equals("favInner")) {
				return favInnerN;
			}
			
			else
				return registerN;
		}
		catch(Exception ex) {
			System.out.println("couldn't load xml file :"+ex.getStackTrace());
			ex.printStackTrace();
			return null;
		}

	}
}