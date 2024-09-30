package com.jdbc;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC18 {
	private static Scanner scanner = new Scanner(System.in);

	final private static String Driver = "com.mysql.cj.jdbc.Driver";
	private static Connection myConnection;
	private static PreparedStatement pstmt;

	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String username = "root";
	private static String password = "admin";

	static FileReader fileReader;

	public static void main(String[] args) {

		String updateQuery = "UPDATE `employees` SET `intro` = ? WHERE `id` = ?";

		try {
			fileReader = new FileReader("C:\\Users\\LENOVO\\Desktop\\JDBC\\textfiles\\sachinIntro.txt");
			Class.forName(Driver);

			myConnection = DriverManager.getConnection(url, username, password);

			JDBC02.displayData(null, null, myConnection);
			pstmt = myConnection.prepareStatement(updateQuery);

			System.out.println("Enter the id of the employee: ");
			pstmt.setInt(2, scanner.nextInt());

			pstmt.setCharacterStream(1, fileReader);
			int count = pstmt.executeUpdate();
			System.out.println("No. of rows updated: " + count);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
