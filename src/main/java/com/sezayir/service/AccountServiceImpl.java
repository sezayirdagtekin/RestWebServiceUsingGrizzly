package com.sezayir.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.sezayir.dao.AccountDao;
import com.sezayir.model.Account;
import com.sezayir.repository.DerbDbRepsository;

public class AccountServiceImpl implements AcccountService {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		return DerbDbRepsository.getConnection();
	}

	@Override
	public int createAccount() throws ClassNotFoundException, SQLException {
		return AccountDao.createAccounts();
	}

	public List<Account> getAccounts() throws ClassNotFoundException, SQLException {
		Statement st = getConnection().createStatement();
		List<Account> accounts = new ArrayList<>();
		ResultSet rec = st.executeQuery("select accountid,username,balance from Account");
		while (rec.next()) {
			Account account = new Account();
			account.setAccountId(rec.getLong(1));
			account.setUserName(rec.getString(2));
			account.setBalance(rec.getBigDecimal(3));
			accounts.add(account);
		}
		st.close();
		return accounts;

	}

	@Override
	public Account getAccountByUsername(String username) throws ClassNotFoundException, SQLException {
		Statement st = getConnection().createStatement();
		Account account = null;
		ResultSet rec = st.executeQuery("select accountid,username,balance from Account where username='" + username + "'");
		while (rec.next()) {
			account = new Account();
			account.setAccountId(rec.getLong(1));
			account.setUserName(rec.getString(2));
			account.setBalance(rec.getBigDecimal(3));
		}
		st.close();
		return account;
	}

	@Override
	public Account updateAccount(Account account) throws ClassNotFoundException, SQLException {
		Statement st = getConnection().createStatement();
		st.executeUpdate("update Account set balance=" + account.getBalance() + " where username='"
				+ account.getUserName() + "'");
		st.close();
		return account;
	}

	@Override
	public int dropAccountTable() throws ClassNotFoundException, SQLException {
		return AccountDao.dropAcoountTable();
	}

}
