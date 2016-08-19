package com.tricast.web.manager;

import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Account;

public interface AccountManager {

	List<Account> getAll() throws SQLException;

	Account getById(long id) throws SQLException;

	Account create(Account newAccount) throws SQLException;

	Account update(Account updateAccount) throws SQLException;

	boolean deleteById(long Id) throws SQLException;
	
	public Account login(String username, String password) throws SQLException;

}
