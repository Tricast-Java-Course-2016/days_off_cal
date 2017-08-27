package com.tricast.controllers;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.LoginRequest;
import com.tricast.managers.AccountManager;
import com.tricast.managers.beans.Account;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
	
	private static final Logger log = LogManager.getLogger(AccountController.class);
	
	private final AccountManager accountManager;

	@Autowired
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
    @RequestMapping(method = RequestMethod.GET)
	public List<Account> getAll() {
		log.trace("Requested to get All");
		return accountManager.getAll();
	}
    
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Account getById(@PathVariable("id") long id) {
		log.trace("Requested to get ID = " + id);
		return accountManager.getById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Account saveAccount(@RequestBody Account newAccount) {
		log.trace("Trying to create or update account for " + newAccount.getRealName());
		return accountManager.save(newAccount);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public Account login(@RequestBody LoginRequest loginRequest) throws SQLException {
		log.trace("Trying to login with account for " + loginRequest.getUserName());
		return accountManager.login(loginRequest.getUserName(), loginRequest.getPassword());
	}
}
