package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC05 {
	final private static String Driver = "com.mysql.cj.jdbc.Driver";
	private static Connection myConnection;
	private static Statement stmt;
	
	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String username = "root";
	private static String password = "admin";
	
	public static void main(String[] args) {
		
		String insertQuery = "INSERT into `employees` (`id`, `name`, `email`, `department`, `salary`) values(8, 'jill', 'jill@gmail.com', 'Support', 35000)";
	
		try {
			Class.forName(Driver);
			
			myConnection = DriverManager.getConnection(url, username, password);
			JDBC02.displayData(null, stmt, myConnection);
			
			stmt = myConnection.createStatement();
			int count = stmt.executeUpdate(insertQuery);
			System.out.println("Rows Updated : " + count +"\n");
			
			JDBC02.displayData(null, stmt, myConnection);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
