package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC06 {
	final private static String Driver = "com.mysql.cj.jdbc.Driver";
	private static Connection myConnection;
	private static Statement stmt;
	
	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String username = "root";
	private static String password = "admin";
	
	public static void main(String[] args) {
		
		String insertQuery1 = "INSERT into `employees` (`id`, `name`, `email`, `department`, `salary`) values(9, 'rajesh', 'rajesh@gmail.com', 'IT', 35000)";
		String insertQuery2 = "INSERT into `employees` (`id`, `name`, `email`, `department`, `salary`) values(10, 'ramesh', 'ramesh@gmail.com', 'Finance', 25000)";
		String insertQuery3 = "INSERT into `employees` (`id`, `name`, `email`, `department`, `salary`) values(11, 'pooja', 'pooja@gmail.com', 'HR', 15000)";
		String insertQuery4 = "INSERT into `employees` (`id`, `name`, `email`, `department`, `salary`) values(12, 'iyer', 'iyer@gmail.com', 'Misc', 18000)";
		String insertQuery5 = "INSERT into `employees` (`id`, `name`, `email`, `department`, `salary`) values(13, 'balayya', 'balyya@gmail.com', 'Finance', 90000)";
	
		try {
			Class.forName(Driver);
			
			myConnection = DriverManager.getConnection(url, username, password);
			JDBC02.displayData(null, stmt, myConnection);
			
			stmt = myConnection.createStatement();
			
			stmt.addBatch(insertQuery1);
			stmt.addBatch(insertQuery2);
			stmt.addBatch(insertQuery3);
			stmt.addBatch(insertQuery4);
			stmt.addBatch(insertQuery5);
			
			int[] arr = stmt.executeBatch();
			for(int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			} System.out.println();
			
			JDBC02.displayData(null, stmt, myConnection);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
