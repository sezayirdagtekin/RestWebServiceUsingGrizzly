package com.sezayir.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbDbRepsository {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String dbURL1 = "jdbc:derby:codejava/webdb1;create=true";
		Connection connection = DriverManager.getConnection(dbURL1);
		return connection;
	}

}
