package com.sezayir.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sezayir.model.Customer;
import com.sezayir.repository.DerbDbRepsository;

public class CustomerDao {
	
	public static void createCustomers() throws ClassNotFoundException, SQLException {
		Connection connection = DerbDbRepsository.getConnection();
		Statement st = connection.createStatement();
		st.executeUpdate("create  table Customer (name varchar(50) , surname varchar(50), username varchar(20) )");
		System.out.println("Customer table isCreated");
		st.executeUpdate("INSERT INTO Customer " + "VALUES ('Sezayir',  'Dagtekin', 'user1')");
		System.out.println("user1 is  inserted");
		st.executeUpdate("INSERT INTO Customer " + "VALUES ('Adam',  'Smith', 'user2')");
		System.out.println("user2 is inserted");
		connection.close();
	}	
	
	public static List<Customer> getCustomers() throws ClassNotFoundException, SQLException {
		Statement st = DerbDbRepsository.getConnection().createStatement();
		List<Customer> customerList = new ArrayList<>();
		ResultSet rec = st.executeQuery("select name,surname,username from Customer");
		while (rec.next()) {
			Customer customer = new Customer();
			customer.setName(rec.getString(1));
			customer.setSurname(rec.getString(2));
			customer.setUsername(rec.getString(3));
			customerList.add(customer);
		}
		st.close();

		return customerList;
	}
	public static void dropCustomers() throws SQLException, ClassNotFoundException {
		Connection connection = DerbDbRepsository.getConnection();
		Statement st = connection.createStatement();
		st.executeUpdate("DROP TABLE Customer");
		System.out.println(" Customer Table deleted");
	}
}
