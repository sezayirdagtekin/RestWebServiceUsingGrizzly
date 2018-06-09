package com.sezayir.repository;

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

	public static int  createUsers() throws ClassNotFoundException, SQLException {
		int result=0;
		Connection connection = getConnection();
		Statement st = connection.createStatement();

		st.executeUpdate("create  table Customer (name varchar(50) , surname varchar(50), username varchar(20) )");
		System.out.println("Customer table isCreated");
		result=st.executeUpdate("INSERT INTO Customer " + "VALUES ('Sezayir',  'Dagtekin', 'user1')");
		System.out.println("user1 is  inserted");
		 result=st.executeUpdate("INSERT INTO Customer " + "VALUES ('Adam',  'Smith', 'user2')");
		System.out.println("user2 is inserted");
		connection.close();
		return result;
	}

	public static int createAccounts() throws ClassNotFoundException, SQLException {
		int    result=0;
		Connection connection = getConnection();
		Statement st = connection.createStatement();
		st.executeUpdate("create  table Account (accountid int,  username varchar(20), balance  decimal )");
		System.out.println("Account table is created");
	    result=st.executeUpdate("INSERT INTO Account " + "VALUES (1001,  'user1', 1000)");
		System.out.println("Account 1001 is created and assigned to user1");
		result=st.executeUpdate("INSERT INTO Account " + "VALUES (1002,  'user2', 2000)");
		System.out.println("Account 1002  is created and assigned to user2");
		connection.close();
		return result;
	}

	public static int dropUserTable() throws SQLException, ClassNotFoundException {
		int    result=0;
		Connection connection = getConnection();
		Statement st = connection.createStatement();
		result=st.executeUpdate("DROP TABLE Customer");
		System.out.println(" Customer Table deleted");
		return result;
	}

	public static int   dropAcoountTable() throws SQLException, ClassNotFoundException {
		int    result=0;
		Connection connection = getConnection();
		Statement st = connection.createStatement();
		result= st.executeUpdate("DROP TABLE Account");
		System.out.println("Account  Table deleted");
		return result;
	}
}
