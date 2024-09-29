package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC07 {
	private static Scanner scanner = new Scanner(System.in);
	final private static String Driver = "com.mysql.cj.jdbc.Driver";
	private static Connection myConnection;
	private static Statement stmt;
	
	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String username = "root";
	private static String password = "admin";
	
	public static void main(String[] args) {
		System.out.println("This is the task to be done.");
		System.out.println("Make modifications in JDBC6 such that instead of hard-coding the values,"
				+ " you need to dynamically provide the values at run-time");
		System.out.println("Enter the no.of users you want to add: ");
		int noOfUsers = scanner.nextInt();
		int n = noOfUsers;
		while(n > 0) {
			System.out.println("Enter the user id :");
			int id = scanner.nextInt();
			
			System.out.println("Enter the name :");
			String name = scanner.next();
			
			System.out.println("Enter the email: ");
			String email = scanner.next();
			
			System.out.println("Enter the department: ");
			String department = scanner.next();
			
			System.out.println("Enter the salary: ");
			int salary = scanner.nextInt();
			
			System.out.println(id + " " + name + " " + email  + " " + department + " " + salary);
			n--;
		}
		
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
