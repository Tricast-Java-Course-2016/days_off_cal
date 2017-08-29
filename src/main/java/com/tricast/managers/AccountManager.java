package com.tricast.managers;

import java.sql.SQLException;
import java.util.List;

import com.tricast.controllers.responses.AccountResponse;
import com.tricast.repositories.entities.Account;

public interface AccountManager {

	List<AccountResponse> getAll();

	AccountResponse getById(long id);

	Account save(Account newAccount);

	AccountResponse login(String username, String password) throws SQLException;

}
