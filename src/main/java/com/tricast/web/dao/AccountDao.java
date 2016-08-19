package com.tricast.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Account;

public interface AccountDao {

	List<Account> getAll() throws SQLException;

	Account getById(long id) throws SQLException;

	Account login(String username, String password) throws SQLException;

	Long create(Account newItem) throws SQLException;

	Long update(Account updateItem) throws SQLException;

	boolean deleteById(long Id) throws SQLException;

}
