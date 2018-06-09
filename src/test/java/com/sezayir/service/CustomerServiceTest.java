package com.sezayir.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.sezayir.model.Customer;

@RunWith(JUnit4.class)
public class CustomerServiceTest {

	@Mock
	CustomerService customerService;

	private List<Customer> customerList;

	@Before
	public void setUp() throws Exception {
		customerService = mock(CustomerService.class);
		List<Customer> list = new ArrayList<>();

		Customer customer1 = new Customer();
		customer1.setUsername("user1");
		customer1.setName("Sezayir");
		customer1.setSurname("Dagtekin");

		Customer customer2 = new Customer();
		customer2.setUsername("user2");
		customer2.setName("Adam");
		customer2.setSurname("Smith");

		list.add(customer1);
		list.add(customer2);

		Mockito.when(customerService.createCustomers()).thenReturn(1);

		Mockito.when(customerService.getCustomers()).thenReturn(list);
		customerList = customerService.getCustomers();

		Mockito.when(customerService.dropCustomerTable()).thenReturn(1);
	}

	@Test
	public void createCustomers_MethodTest() throws ClassNotFoundException, SQLException {
		int result = customerService.createCustomers();
		Mockito.verify(customerService).createCustomers();
		assertThat(result, is(1));
	}

	@Test
	public void getCustomers_MethodIsCalledTest() throws ClassNotFoundException, SQLException {
		Mockito.verify(customerService).getCustomers();
		assertNotNull(customerList);
	}

	@Test
	public void getCustomers_MethodTest() throws ClassNotFoundException, SQLException {
		assertThat(customerList, notNullValue());
		assertThat(customerList.size(), is(2));

	}

	@Test
	public void dropCustomerTable_MethodTest() throws ClassNotFoundException, SQLException {
		int result = customerService.dropCustomerTable();
		Mockito.verify(customerService).dropCustomerTable();
		assertThat(result, is(1));
	}
}
