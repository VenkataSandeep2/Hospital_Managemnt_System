package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
	private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";
	 public static Connection getConnection() {

	        Connection connection = null;

	        try {

	            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	            System.out.println("Database Connected Successfully.");

	        } catch (SQLException e) {

	            System.out.println("Database Connection Failed.");
	            //e.printStackTrace();
	            System.out.println(e);
	        }

	return connection;
	 }
}
