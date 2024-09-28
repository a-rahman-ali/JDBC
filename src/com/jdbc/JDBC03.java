package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC03 {
	private static Connection myConnection;
	private static Statement stmt;
	
	static String url = "jdbc:mysql://localhost:3306/jdbc";
	static String username =  "root";
	static String password = "admin";

	public static void main(String[] args) {
		String updateQuery = "UPDATE `employees` SET `salary` = `salary` + 5000 WHERE `department` = 'HR'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			JDBC03.myConnection = DriverManager.getConnection(JDBC03.url, JDBC03.username, JDBC03.password);
			
			stmt = myConnection.createStatement();
			
			int count = stmt.executeUpdate(updateQuery);
			System.out.println("Rows Affected : " + count);
			
			JDBC02.displayData(null, stmt, myConnection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
