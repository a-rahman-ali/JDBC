package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC16 {
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

			call = myConnection.prepareCall("{ call get_employees(?) }");

			System.out.println("Enter the salary amount: ");
			// call.setInt(1, scanner.nextInt());
			int inputSalary = scanner.nextInt();
			call.setInt(1, inputSalary);

			call.execute();
			ResultSet resultSet = call.getResultSet();
			System.out.println("Employees whose salary is less than " + inputSalary + " :");
			System.out.println("+--------+-----------------+----------------------+-----------------+----------+");
			System.out.printf("|  %-5s | %-15s | %-20s | %-15s | %-8s |\n", "id", "name", "email", "department",
					"salary");
			System.out.println("+--------+-----------------+----------------------+-----------------+----------+");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String department = resultSet.getString("department");
				int salary = resultSet.getInt("salary");

				System.out.printf("|  %-5d | %-15s | %-20s | %-15s | %-8d |\n", id, name, email, department, salary);
			}
			System.out.println("+--------+-----------------+----------------------+-----------------+----------+");
			System.out.println();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
