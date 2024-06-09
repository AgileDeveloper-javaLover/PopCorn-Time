package com.blackpool.PopCornTime.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blackpool.PopCornTime.UserProfile;
import com.blackpool.PopCornTime.MovieDatabase.DatabaseSource;

public class MovieFilter {
	public static int startYear;
	public static int endYear;
	public static int startRating;
	public static int endRating;
	public static List<String> category=new ArrayList<>();
	public static List<String> languageSpecific;
	static {

		
		try {PreparedStatement stmt;
			stmt = DatabaseSource.dbsource.con.prepareStatement("select interest from popcorntime_application.user where email=?");
			stmt.setString(1,UserProfile.email);
			
			ResultSet rs=stmt.executeQuery();
			rs.next();
			category.addAll(List.of(rs.getString(1).split(",")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
