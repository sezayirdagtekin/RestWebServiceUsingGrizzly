package com.sezayir.service;

import java.sql.SQLException;
import java.util.List;
import com.sezayir.model.Customer;

public interface CustomerService {
	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException;
	public int createCustomers() throws ClassNotFoundException, SQLException;
	public int dropCustomerTable()  throws ClassNotFoundException, SQLException;;
}

