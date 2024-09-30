package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC10 {
	private static Scanner scanner = new Scanner(System.in);
	
	final private static String Driver = "com.mysql.cj.jdbc.Driver";
	private static Connection myConnection;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	
	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String username = "root";
	private static String password = "admin";
	
	public static void main(String[] args) {
		
		String insertQuery = "INSERT INTO `employees` (`id`,`name`,`email`,`department`,`salary`) values(?,?,?,?,?)";
	
		try {
			Class.forName(Driver);
			
			myConnection = DriverManager.getConnection(url, username, password);

			JDBC02.displayData(null, pstmt, myConnection);
			
			pstmt = myConnection.prepareStatement(insertQuery);
			
			System.out.println("Enter the id of employee: ");
			pstmt.setInt(1, scanner.nextInt());
			System.out.println("Enter the name: ");
			pstmt.setString(2, scanner.next());
			System.out.println("Enter the email: ");
			pstmt.setString(3, scanner.next());
			System.out.println("Enter the department: ");
			pstmt.setString(4, scanner.next());
			System.out.println("Enter the salary: ");
			pstmt.setInt(5, scanner.nextInt());
			
			int count = pstmt.executeUpdate();
			System.out.println("No. of rows affected: " + count + "\n");
			
			JDBC02.displayData(null, stmt, myConnection);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
