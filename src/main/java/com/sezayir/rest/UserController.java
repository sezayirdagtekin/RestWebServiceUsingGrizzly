package com.sezayir.rest;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sezayir.entity.Account;
import com.sezayir.entity.Customer;
import com.sezayir.service.BankService;
import com.sezayir.service.BankServiceImpl;


@Path("bank")
public class UserController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("users")
	public String getCustomers() throws ClassNotFoundException, SQLException {
		
		BankService bs = new BankServiceImpl();
		List<Customer> customers = bs.getCustomers();
		String json = new Gson().toJson(customers);
		return json;

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("accounts")
	public String getAccounts() throws ClassNotFoundException, SQLException {

		BankService bs = new BankServiceImpl();
		List<Account> accounts = bs.getAccounts();
		String json = new Gson().toJson(accounts);
		return json;

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/{sourceAccountId}/transfer/{targetAccountId}/{amount}")
	public Account transfer(@PathParam("sourceAccountId") long sourceAccountId, @PathParam("targetAccountId") long targetAccountId, @PathParam("amount") BigDecimal amount)
			throws ClassNotFoundException, SQLException {

		BankService bs = new BankServiceImpl();
		Account account = bs.getAccountById("user1");
		return account;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("transferMoney")
	public String transferMoney() throws ClassNotFoundException, SQLException {
		BankService bs = new BankServiceImpl();
		Account account = bs.getAccountById("user1");
		account.setBalance(new BigDecimal(4500));
		bs.updateAccount(account);
		return account.toString();
	}

}
