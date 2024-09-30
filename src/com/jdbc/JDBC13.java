package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class JDBC13 {
	private static Scanner scanner = new Scanner(System.in);
	
	final private static String Driver = "com.mysql.cj.jdbc.Driver";
	private static Connection myConnection;
	private static PreparedStatement pstmt;
	
	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String username = "root";
	private static String password = "admin";
	
	public static void main(String[] args) {
				
	
		try {
			Class.forName(Driver);
			
			myConnection = DriverManager.getConnection(url, username, password);
			
			JDBC02.displayData(null, pstmt, myConnection);

			transaction();
			
			JDBC02.displayData(null, pstmt, myConnection);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void transaction() throws SQLException {
		myConnection.setAutoCommit(false);
		
		System.out.println("Enter sender's name: ");
		String sender = scanner.next();
		System.out.println("Enter reciever's name: ");
		String reciever = scanner.next();
		System.out.println("Enter amount to be transferred: ");
		int amount = scanner.nextInt();
		
		int i = updateAmount(sender, -amount);
		int j = updateAmount(reciever, amount);
		
		if(isConfirm(i, j)) {
			myConnection.commit();
			System.out.println("Transaction Successful....!");
		} else {
			myConnection.rollback();
			System.out.println("Transaction Failed!!!");
		}
	}
	
	private static boolean isConfirm(int i, int j) {
		System.out.println("Do you want to confirm the transaction ? (yes/no)");
		String choice = scanner.next();
				
		return (choice.equalsIgnoreCase("yes") && i == 1 && j == 1);
	} 
	private static int updateAmount(String user, int amount) throws SQLException {
//		To be implemented
//		String selectQuery = "SELECT `salary` FROM `employees` WHERE `username` = ?";
		
		String updateAmt = "UPDATE `employees` SET `salary` = `salary` + ? WHERE `name` = ?";
		pstmt = myConnection.prepareStatement(updateAmt);
		pstmt.setInt(1, amount);
		pstmt.setString(2, user);
		
		return pstmt.executeUpdate();
	}

}
