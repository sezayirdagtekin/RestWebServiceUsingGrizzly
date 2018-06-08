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
import com.sezayir.entity.Account;
import com.sezayir.entity.Customer;
import com.sezayir.service.BankService;
import com.sezayir.service.BankServiceImpl;


@Path("bank")
public class BankController {

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
		
		BankService service = new BankServiceImpl();
		List<Account> accounts = service.getAccounts();
		String json = new Gson().toJson(accounts);
		return json;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{sourceUserName}/transfer/{targetUserName}/{amount}")
	public String transfer(@PathParam("sourceUserName") String sourceUserName, @PathParam("targetUserName") String targetUserName, @PathParam("amount") BigDecimal amount)
			throws ClassNotFoundException, SQLException {

		BankService service = new BankServiceImpl();
		Account source = service.getAccountById(sourceUserName);
		Account target = service.getAccountById(targetUserName);
		source.setBalance(source.getBalance().subtract(amount));
		target.setBalance(target.getBalance().add(amount));
		service.updateAccount(source);
		service.updateAccount(target);
		
		return amount+" $ is transfered from "+sourceUserName+ " to "+ targetUserName;
	}


}
