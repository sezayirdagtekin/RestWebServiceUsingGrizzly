package com.sezayir.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import java.math.BigDecimal;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ControllerTest {

	@Mock
	BankController bankController;

	private static final String USER1 = "user1";
	private static final String USER2 = "user2";
	private static final String jsonAccounts = "{'accountId':1001,'userName':'user1','balance':800},{'accountId':1002,'userName':'user2','balance2:3000}";
	private static final String jsonCustomers = "{'name':'Sezayir','surname':'Dagtekin','username':'user1'},{'name':'Adam','surname':'Smith','username':'user2'}";

	@Before
	public void setUp() throws Exception {
		
		bankController = mock(BankController.class);
		Mockito.when(bankController.getCustomers()).thenReturn(jsonCustomers);
		Mockito.when(bankController.getAccounts()).thenReturn(jsonAccounts);
		Mockito.when(bankController.transfer(USER1, USER2, new BigDecimal(2000))).thenReturn("2000 $ is transfered from user1 to user2");
	}
	

	@Test
	public void getAllCustomersTest() throws ClassNotFoundException, SQLException {
		String json = bankController.getCustomers();
		assertThat(json, notNullValue());
		Mockito.verify(bankController).getCustomers();
	}

	@Test
	public void getAllAcountsTest() throws ClassNotFoundException, SQLException {
		String json = bankController.getAccounts();
		assertThat(json, notNullValue());
		Mockito.verify(bankController).getAccounts();
	}

	@Test
	public void transferMoneyBetweenTwoAccounstTest() throws ClassNotFoundException, SQLException {	
		String expected = "2000 $ is transfered from user1 to user2";
		String actual = bankController.transfer(USER1, USER2, new BigDecimal(2000));
		assertThat(expected, notNullValue());
		assertThat(actual, is(expected));
	}
	
	@Test
	public void transferMoneyBetweenTwoAccounstFailInSufficientBalanceTest() throws ClassNotFoundException, SQLException {
		String notExpected = "12000 $ is transfered from user1 to user2";
		String expected = "Insufficient balance!!!";
		Mockito.when(bankController.transfer(USER1, USER2, new BigDecimal(12000))).thenReturn("Insufficient balance!!!");
		String actual = bankController.transfer(USER1, USER2, new BigDecimal(12000));
		assertThat(actual, is(not(notExpected)));
		assertThat(actual, is(expected));
	}
}
