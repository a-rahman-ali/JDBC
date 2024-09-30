package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class JDBC15 {
	private static Scanner scanner = new Scanner(System.in);
	
	final private static String Driver = "com.mysql.cj.jdbc.Driver";
	private static Connection myConnection;
	private static CallableStatement call;
	
	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String username = "root";
	private static String password = "admin";
	
	public static void main(String[] args) {
				
	
		try {
			Class.forName(Driver);
			
			myConnection = DriverManager.getConnection(url, username, password);
			
			JDBC02.displayData(null, null, myConnection);
			
			call =  myConnection.prepareCall("{ call salary_count(?) }");
			
			System.out.println("Enter the salary amount: ");
			call.setInt(1, scanner.nextInt());
			call.registerOutParameter(1, Types.INTEGER);
			
			call.execute();
			
			System.out.println("No. of employees whose salary is greater than the provided salary is: ");
			int count = call.getInt(1);
			System.out.println(count);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
