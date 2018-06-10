package com.sezayir.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import com.sezayir.model.Customer;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CustomerDao.class })
public class CustomerDaoTest {

	private static final Integer SUCCESS = 1;
	private static final String USER1 = "user1";
	private static final String USER2 = "user2";

	@Before
	public void setUp() throws Exception {
		List<Customer> list = new ArrayList<>();
		Customer customer1 = new Customer();
		customer1.setUsername(USER1);
		customer1.setName("Sezayir");
		customer1.setSurname("Dagtekin");
		Customer customer2 = new Customer();
		customer2.setUsername(USER2);
		customer2.setName("Adam");
		customer2.setSurname("Smith");
		
		list.add(customer1);
		list.add(customer2);

		PowerMockito.mockStatic(CustomerDao.class);
		PowerMockito.when(CustomerDao.createCustomers()).thenReturn(SUCCESS);
		PowerMockito.when(CustomerDao.dropCustomers()).thenReturn(SUCCESS);
		PowerMockito.when(CustomerDao.getCustomers()).thenReturn(list);
	}

	@Test
	public void createCustomer_Test() throws ClassNotFoundException, SQLException {
		Integer actual = CustomerDao.createCustomers();
		assertThat(actual, is(SUCCESS));
	}

	@Test
	public void dropCustomerTable_Test() throws ClassNotFoundException, SQLException {
		Integer actual = CustomerDao.dropCustomers();
		assertThat(actual, is(SUCCESS));
	}

	@Test
	public void getAllCustomer_Test() throws ClassNotFoundException, SQLException {
		List<Customer> customerList = CustomerDao.getCustomers();
		assertNotNull(customerList);
	}
}
