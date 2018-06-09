package com.sezayir.service;

import java.sql.SQLException;
import java.util.List;

import com.sezayir.dao.DerbDbRepsository;
import com.sezayir.entity.Account;

public interface AcccountService {
	
	public int createAccount() throws ClassNotFoundException, SQLException;

	public List<Account> getAccounts() throws ClassNotFoundException, SQLException;

	public Account getAccountByUsername(String username) throws ClassNotFoundException, SQLException;

	public Account updateAccount(Account account) throws ClassNotFoundException, SQLException;

	public int dropAccountTable() throws ClassNotFoundException, SQLException;	

}
