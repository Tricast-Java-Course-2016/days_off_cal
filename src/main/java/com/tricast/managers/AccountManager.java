package com.tricast.managers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.tricast.managers.beans.Account;

public interface AccountManager {

	List<Account> getAll();

	Account getById(long id);

	Account save(Account newAccount);

//	Account update(Account updateAccount);

//	boolean deleteById(long Id);

	public Account login(String username, String password) throws SQLException;

}
