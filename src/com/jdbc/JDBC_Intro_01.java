package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Intro_01 {

	public static void main(String[] args) {
		System.out.println("Inside Program1");
		
		Connection myCon = null;
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root"; 
		String password = "admin";
		
		
		try {
			myCon = DriverManager.getConnection(url, username, password);
			System.out.println(myCon);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(myCon != null) {
				try {
					myCon.close();
					System.out.println("Connection closed successfully!!!");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
