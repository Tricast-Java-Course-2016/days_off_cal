package com.tricast.managers;

import java.sql.SQLException;
import java.util.List;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.controllers.responses.AccountResponse;

public interface AccountManager {

	List<AccountResponse> getAll();

	AccountResponse getById(long id);

	AccountResponse createAccount(AccountRequest account);
	
	AccountResponse updateAccount(AccountRequest account);

	AccountResponse login(String username, String password) throws SQLException;

}
