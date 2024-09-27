package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC02 {
	// 1. load the driver
	// 2. get connection
	// 3. create stmt
	// 4. execute
	// 5. process result set

	public static void main(String[] args) {
		Connection myCon = null;
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String password = "admin";

		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			myCon = DriverManager.getConnection(url, username, password);

			displayData(resultSet, stmt, myCon);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (myCon != null) {
					myCon.close();
					System.out.println("Connection Closed");
				}
				if (stmt != null) {
					stmt.close();
					System.out.println("Statement Closed");
				}
				if (resultSet != null) {
					resultSet.close();
					System.out.println("ResultSet Closed");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	protected static void displayData(ResultSet resultSet, Statement stmt, Connection myCon) throws SQLException {
		String readQuery = "SELECT * FROM employees";
		stmt = myCon.createStatement();

		resultSet = stmt.executeQuery(readQuery);
		System.out.println("+--------+-----------------+----------------------+-----------------+----------+");
		System.out.printf("|  %-5s | %-15s | %-20s | %-15s | %-8s |\n", "id", "name", "email", "department", "salary");
		System.out.println("+--------+-----------------+----------------------+-----------------+----------+");
		while (resultSet.next()) {
			// System.out.print(resultSet.getInt("id") + " ");
			// System.out.print(resultSet.getString("name") + " ");
			// System.out.print(resultSet.getString("email") + " ");
			// System.out.print(resultSet.getString("department") + " ");
			// System.out.print(resultSet.getString("salary") + " ");
			// System.out.println();
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String department = resultSet.getString("department");
			int salary = resultSet.getInt("salary");

			System.out.printf("|  %-5d | %-15s | %-20s | %-15s | %-8d |\n", id, name, email, department, salary);
		}
		System.out.println("+--------+-----------------+----------------------+-----------------+----------+");
		System.out.println();
	}

}
