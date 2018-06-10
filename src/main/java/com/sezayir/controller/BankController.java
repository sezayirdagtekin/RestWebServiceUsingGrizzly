package com.sezayir.controller;

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
import com.sezayir.exception.MoneyTransferException;
import com.sezayir.model.Account;
import com.sezayir.model.Customer;
import com.sezayir.service.AcccountService;
import com.sezayir.service.AccountServiceImpl;
import com.sezayir.service.CustomerService;
import com.sezayir.service.CustomerServiceImpl;


@Path("bank")
public class BankController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("customers")
	public String getCustomers() throws ClassNotFoundException, SQLException {
		
		CustomerService bs = new CustomerServiceImpl();
		List<Customer> customers = bs.getCustomers();
		String json = new Gson().toJson(customers);
		return json;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("accounts")
	public String getAccounts() throws ClassNotFoundException, SQLException {
		
		AcccountService service = new AccountServiceImpl();
		List<Account> accounts = service.getAccounts();
		String json = new Gson().toJson(accounts);
		return json;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{sourceUserName}/transfer/{targetUserName}/{amount}")
	public String transfer(@PathParam("sourceUserName") String sourceUserName, @PathParam("targetUserName") String targetUserName, @PathParam("amount") BigDecimal amount)
			throws ClassNotFoundException, SQLException{

		AcccountService service = new AccountServiceImpl();
		Account source = service.getAccountByUsername(sourceUserName);
		Account target = service.getAccountByUsername(targetUserName);

		if (source == null || target == null) {
			return  new MoneyTransferException("Invalid  account!!!").getMessage();
		}
		BigDecimal subtract = source.getBalance().subtract(amount);
		if (subtract.compareTo(BigDecimal.ZERO) < 0) {
			return  new MoneyTransferException("Insufficient balance!!!").getMessage();
		}
		source.setBalance(subtract);
		target.setBalance(target.getBalance().add(amount));
		service.updateAccount(source);
		service.updateAccount(target);
		return amount + " $ is transfered from " + sourceUserName + " to " + targetUserName;
	}


}
