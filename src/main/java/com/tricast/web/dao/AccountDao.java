package com.tricast.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Account;

public interface AccountDao {

	List<Account> getAll(Workspace workspace) throws SQLException;

	Account getById(Workspace workspace, long id) throws SQLException;

	Account login(Workspace workspace, String username, String password) throws SQLException;

	Long create(Workspace workspace, Account newItem) throws SQLException;

	Long update(Workspace workspace, Account updateItem) throws SQLException;

	boolean deleteById(Workspace workspace, long Id) throws SQLException;

}
