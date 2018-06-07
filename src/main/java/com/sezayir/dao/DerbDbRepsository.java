package com.sezayir.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbDbRepsository {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String dbURL1 = "jdbc:derby:codejava/webdb1;create=true";
		Connection connection = DriverManager.getConnection(dbURL1);
		if (connection != null) {
			System.out.println("Connected to database #1");
		}
		return connection;
	}

	public static void createUsers() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		Statement st = connection.createStatement();

		st.executeUpdate("create  table Customer (name varchar(50) , surname varchar(50), username varchar(20) )");
		System.out.println("Customer table isCreated");
		st.executeUpdate("INSERT INTO Customer " + "VALUES ('Sezayir',  'Dagtekin', 'user1')");
		System.out.println("user1 is  inserted");
		st.executeUpdate("INSERT INTO Customer " + "VALUES ('Adam',  'Smith', 'user2')");
		System.out.println("user2 is inserted");
		connection.close();
	}

	public static void createAccounts() throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		Statement st = connection.createStatement();
		st.executeUpdate("create  table Account (accountid int,  username varchar(20), balance  decimal )");
		System.out.println("Account table is created");
		st.executeUpdate("INSERT INTO Account " + "VALUES (1001,  'user1', 1000)");
		System.out.println("Account 1001 is created and assigned to user1");
		st.executeUpdate("INSERT INTO Account " + "VALUES (1002,  'user2', 2000)");
		System.out.println("Account 1002  is created and assigned to user2");
		connection.close();
	}

	public static void cleanDB() throws SQLException, ClassNotFoundException {
		Connection connection = getConnection();
		Statement st = connection.createStatement();
		st.executeUpdate("DROP TABLE Account");
		st.executeUpdate("DROP TABLE Customer");
		System.out.println("Account & Customer Tables deleted");
	}
}
