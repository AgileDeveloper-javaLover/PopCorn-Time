package com.blackpool.PopCornTime.MovieDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseSource{
	public static DatabaseSource dbsource = new DatabaseSource();
	private String url;
	private String database;
	private String name;
	private String password;
	public  Connection con;
	private DatabaseSource() {
		
	}
	
	
	public static DatabaseSource getDbsource() {
		return dbsource;
	}

	public String getUrl() {
		return url;
	}

	public String getDatabase() {
		return database;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public static void setDbsource(DatabaseSource dbsource) {
		DatabaseSource.dbsource = dbsource;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url,name,password);

		} catch (Exception e) {
			System.out.println("Somthing went wrong when initializing DataSoruceMovie");
		}

	}



	public static class DatabaseBuilder{
		
		public DatabaseBuilder setUrl(String url) {
			dbsource.setUrl(url);
			return this;
		}
		
		public DatabaseBuilder setName(String name) {
			dbsource.setName(name);
			return this;
		}
		
		public DatabaseBuilder setPassword(String pass) {
			dbsource.setPassword(pass);
			return this;
		}
		
		public DatabaseBuilder setDatabase(String db) {
			dbsource.setDatabase(db);
			return this;
		}
		
		public void setConnection() {
			dbsource.setConnection();
		}
	}
}
