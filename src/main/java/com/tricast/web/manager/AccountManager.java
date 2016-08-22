package com.tricast.web.manager;

import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Account;
import com.tricast.web.dao.Workspace;

public interface AccountManager {

	List<Account> getAll(Workspace workspace) throws SQLException;

	Account getById(Workspace workspace, long id) throws SQLException;

	Account create(Workspace workspace, Account newAccount) throws SQLException;

	Account update(Workspace workspace, Account updateAccount) throws SQLException;

	boolean deleteById(Workspace workspace, long Id) throws SQLException;

	public Account login(Workspace workspace, String username, String password) throws SQLException;

}
