package com.tricast.controllers;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.controllers.requests.LoginRequest;
import com.tricast.controllers.responses.AccountResponse;
import com.tricast.managers.AccountManager;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
	
	private static final Logger log = LogManager.getLogger(AccountController.class);
	
	private final AccountManager accountManager;

	@Autowired
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
    @GetMapping
	public List<AccountResponse> getAll() {
		
    	log.trace("Requested to get All");
		
    	return accountManager.getAll();
	}
    
	@GetMapping("/{id}")
	public AccountResponse getById(@PathVariable("id") long id) {
		
		log.trace("Requested to get ID = " + id);
		
		return accountManager.getById(id);
	}
	
	@PostMapping
	public AccountResponse createAccount(@RequestBody AccountRequest newAccount) {
		
		log.trace("Trying to create or update account for " + newAccount.getRealName());
		
		return accountManager.createAccount(newAccount);
	}
	
	@PutMapping
	public AccountResponse updateAccount(@RequestBody AccountRequest account) {
		
		log.trace("Trying to update account for " + account.getRealName());
		
		return accountManager.updateAccount(account);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public AccountResponse login(@RequestBody LoginRequest loginRequest) throws SQLException {
		
		log.trace("Trying to login with account for " + loginRequest.getUserName());
		
		return accountManager.login(loginRequest.getUserName(), loginRequest.getPassword());
	}
}
