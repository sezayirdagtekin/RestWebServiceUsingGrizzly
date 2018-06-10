package com.sezayir.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import com.sezayir.model.Account;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ AccountDao.class })
public class AccountDaoTest {

	private static final Integer SUCCESS = 1;

	private static final String USER1 = "user1";

	private static final String USER2 = "user2";

	@Before
	public void setUp() throws Exception {
		List<Account> list = new ArrayList<>();

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

		PowerMockito.mockStatic(AccountDao.class);
		PowerMockito.when(AccountDao.createAccounts()).thenReturn(SUCCESS);
		PowerMockito.when(AccountDao.dropAcoountTable()).thenReturn(SUCCESS);
		PowerMockito.when(AccountDao.getAccounts()).thenReturn(list);
	}

	@Test
	public void createAccount_Test() throws ClassNotFoundException, SQLException {
		Integer actual = AccountDao.createAccounts();
		assertThat(actual, is(SUCCESS));
	}

	@Test
	public void dropCustomerTable_Test() throws ClassNotFoundException, SQLException {
		Integer actual = AccountDao.dropAcoountTable();
		assertThat(actual, is(SUCCESS));
	}

	@Test
	public void getAllAccount_Test() throws ClassNotFoundException, SQLException {
		List<Account> acountList = AccountDao.getAccounts();
		assertNotNull(acountList);
	}
}
