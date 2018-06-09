package com.sezayir.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sezayir.model.Account;
import com.sezayir.repository.DerbDbRepsository;

public class AccountDao {
	

	public  void createAccounts() throws ClassNotFoundException, SQLException {
		Connection connection = DerbDbRepsository.getConnection();
		Statement st = connection.createStatement();
		st.executeUpdate("create  table Account (accountid int,  username varchar(20), balance  decimal )");
		System.out.println("Account table is created");
		st.executeUpdate("INSERT INTO Account " + "VALUES (1001,  'user1', 1000)");
		System.out.println("Account 1001 is created and assigned to user1");
		st.executeUpdate("INSERT INTO Account " + "VALUES (1002,  'user2', 2000)");
		System.out.println("Account 1002  is created and assigned to user2");
		connection.close();
	}

	public  List<Account> getAccounts() throws ClassNotFoundException, SQLException {
		Statement st = DerbDbRepsository.getConnection().createStatement();
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
	
	public  Account getAccountByUsername(String username) throws ClassNotFoundException, SQLException {
		Statement st = DerbDbRepsository.getConnection().createStatement();
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

	public  void updateAccount(Account account) throws ClassNotFoundException, SQLException {
		Statement st = DerbDbRepsository.getConnection().createStatement();
		st.executeUpdate("update Account set balance=" + account.getBalance() + " where username='"+ account.getUserName() + "'");
		st.close();
	}
	
	public  void dropAccounts() throws SQLException, ClassNotFoundException {
		Connection connection = DerbDbRepsository.getConnection();
		Statement st = connection.createStatement();
		st.executeUpdate("DROP TABLE Account");
		System.out.println("Account Table deleted");
	}

}
