package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;


public class JDBC14 {
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
			
			call =  myConnection.prepareCall("{ call department_count(?,?) }");
			
			System.out.println("Enter the name of Department: ");
			String dept = scanner.next();
			call.setString(1, dept);
			call.registerOutParameter(2, Types.INTEGER);
			
			call.execute();
			
			int count = call.getInt(2);
//			System.out.println(count);
			System.out.println("No. of employees working in " + dept + " are : " +  count);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
