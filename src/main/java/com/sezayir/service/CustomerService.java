package com.sezayir.service;

import java.sql.SQLException;
import java.util.List;
import com.sezayir.entity.Account;
import com.sezayir.entity.Customer;

public interface CustomerService {
	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException;
	public int createUsers() throws ClassNotFoundException, SQLException;
	public int dropUserTable()  throws ClassNotFoundException, SQLException;;
}

