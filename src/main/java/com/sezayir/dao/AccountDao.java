package com.sezayir.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.sezayir.model.Account;
import com.sezayir.repository.DerbDbRepsository;

public class AccountDao {
	final static Logger log= Logger.getLogger(AccountDao.class);

	public  static int createAccounts() throws ClassNotFoundException, SQLException {
		int    result=0;
		Connection connection = DerbDbRepsository.getConnection();
		Statement st = connection.createStatement();
		st.executeUpdate("create  table Account (accountid int,  username varchar(20), balance  decimal )");
		log.info("Account table is created");
		result=st.executeUpdate("INSERT INTO Account " + "VALUES (1001,  'user1', 1000)");
		log.info("Account 1001 is created and assigned to user1");
		result=st.executeUpdate("INSERT INTO Account " + "VALUES (1002,  'user2', 2000)");
		log.info("Account 1002  is created and assigned to user2");
		connection.close();
		return result;
	}

	public  static List<Account> getAccounts() throws ClassNotFoundException, SQLException {
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
	

	public static int dropAcoountTable() throws ClassNotFoundException, SQLException {
		int result=0;
		Connection connection = DerbDbRepsository.getConnection();
		Statement st = connection.createStatement();
		result=st.executeUpdate("DROP TABLE Account");
		log.info("Account Table deleted");
		return result;
	}


}
