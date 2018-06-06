package com.sezayir.service;

import java.sql.SQLException;
import java.util.List;
import com.sezayir.entity.Account;
import com.sezayir.entity.Customer;

public interface BankService {
	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException;

	public List<Account> getAccounts() throws ClassNotFoundException, SQLException;

	public void createUsers() throws ClassNotFoundException, SQLException;

	public void createAccounst() throws ClassNotFoundException, SQLException;

	public Account getAccountById(String username) throws ClassNotFoundException, SQLException;

	public void updateAccount(Account account) throws ClassNotFoundException, SQLException;
}
