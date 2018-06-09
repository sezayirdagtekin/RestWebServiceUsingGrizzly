package com.sezayir.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.sezayir.model.Account;

@RunWith(JUnit4.class)
public class AccountServiceTest {

	@Mock
	AcccountService accountService;

	private Account account;
	
	private List<Account> accountList;
	
	private static final String USER1="user1";
	
	private static final String USER2="user2";

	@Before
	public void setUp() throws Exception {
		accountService = mock(AcccountService.class);
		List<Account>  list=new ArrayList<>();
	
		Account account1 = new Account();
		account1.setAccountId(1001);
		account1.setBalance(new BigDecimal(5000));
		account1.setUserName(USER1);
		
		Account account2 = new Account();
		account2.setAccountId(1002);
		account2.setBalance(new BigDecimal(3000));
		account2.setUserName(USER2);
		
		list.add(account1);
		list.add(account2);
	
		Mockito.when(accountService.getAccountByUsername(USER1)).thenReturn(account1);
		account = accountService.getAccountByUsername(USER1);
		
		Mockito.when(accountService.getAccounts()).thenReturn(list);
		accountList= accountService.getAccounts();
		
		Mockito.when(accountService.createAccount()).thenReturn(1);
		Account account2Update=account2;
		account2Update.setBalance(new BigDecimal(4000));
		Mockito.when(accountService.updateAccount(account2)).thenReturn(account2Update);
	
		Mockito.when(accountService.dropAccountTable()).thenReturn(1);
	}

	@Test
	public void getAccountByUsernameTest() throws ClassNotFoundException, SQLException {

		Mockito.verify(accountService).getAccountByUsername((USER1));
		assertNotNull(account);
		assertThat(account.getBalance(), is(new BigDecimal(5000)));
		assertThat(account.getUserName(), is(USER1));
		assertThat(account.getAccountId(), is(new Long(1001)));
	}

	@Test
	public void getAccountByUsernameWithWrongUsernameTest() throws ClassNotFoundException, SQLException {
		account = accountService.getAccountByUsername("userxxx");
		assertThat(account, is(nullValue()));
	}

	@Test
	public void getAccountByUsernameCheckWithInvalidDataTest() throws ClassNotFoundException, SQLException {
		account = accountService.getAccountByUsername(USER1);

		assertNotNull(account);
		assertThat(account.getBalance(), is(not(new BigDecimal(2000))));
		assertThat(account.getUserName(), is(not("userxxx")));
		assertThat(account.getAccountId(), is(not(new Long(1111111))));
	}
	
	@Test
	public void getAccounts_MethodIsCalledTest() throws ClassNotFoundException, SQLException {
		Mockito.verify(accountService).getAccounts();
		assertNotNull(accountList);
	}
	

	
	@Test
	public void getAccounts_MethodTest() throws ClassNotFoundException, SQLException {

		assertThat(accountList,notNullValue());
		assertThat(accountList.size(),is(2));
		
	}	
	
	@Test
	public void createAccount_MethodTest() throws ClassNotFoundException, SQLException {
		int result=accountService.createAccount();
		Mockito.verify(accountService).createAccount();
		assertThat(result, is(1));	
	}	
	
	@Test
	public void updateAccount_MethodTest() throws ClassNotFoundException, SQLException {
		
		Account account2 = new Account();
		account2.setAccountId(1002);
		account2.setBalance(new BigDecimal(4000));
		account2.setUserName(USER2);
		Account updatedAccount=accountService.updateAccount(account2);
		
		Mockito.verify(accountService).updateAccount(account2);
		assertThat(updatedAccount.getBalance(), is(new BigDecimal(4000)));	
		assertThat(updatedAccount.getBalance(), is(not(new BigDecimal(0))));	
	}	
	
	@Test
	public void dropAcoountTable_MethodTest() throws ClassNotFoundException, SQLException {
		int result=accountService.dropAccountTable();
		Mockito.verify(accountService).dropAccountTable();
		assertThat(result, is(1));	
	}	
}
