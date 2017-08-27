package com.tricast.managers;

import java.sql.SQLException;
import java.util.List;

import com.tricast.managers.beans.Account;

public interface AccountManager {

	List<Account> getAll();

	Account getById(long id);

	Account save(Account newAccount);

	public Account login(String username, String password) throws SQLException;

}
