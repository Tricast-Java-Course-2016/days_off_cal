package com.tricast.web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tricast.beans.Account;
import com.tricast.web.annotations.JdbcTransaction;

public class AccountDaoImpl implements AccountDao {

	private static final Logger log = LogManager.getLogger(AccountDaoImpl.class);

	@Override
	@JdbcTransaction
	public List<Account> getAll(Workspace workspace) throws SQLException {

		List<Account> result = new ArrayList<Account>();

		try (PreparedStatement ps = workspace.getPreparedStatement(
				"SELECT ID, USERNAME, REALNAME, DAYSOFFPERYEAR, SICKLEAVEPERYEAR FROM CALENDAR.ACCOUNTS");
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				result.add(buildAccount(rs, false));
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public Account getById(Workspace workspace, long id) throws SQLException {

		Account result = null;

		try (PreparedStatement ps = workspace.getPreparedStatement(
				"SELECT ID, USERNAME, REALNAME, PASSWORD, DAYSOFFPERYEAR, SICKLEAVEPERYEAR FROM CALENDAR.ACCOUNTS WHERE ID = ?")) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = buildAccount(rs, true);
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public Account login(Workspace workspace, String username, String password) throws SQLException {

		Account result = null;

		try (PreparedStatement ps = workspace.getPreparedStatement(
				"SELECT ID, USERNAME, REALNAME, DAYSOFFPERYEAR, SICKLEAVEPERYEAR FROM calendar.accounts WHERE USERNAME = ? AND PASSWORD = ?")) {

			int i = 1;
			ps.setString(i++, username);
			ps.setString(i++, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = buildAccount(rs, false);
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public Long create(Workspace workspace, Account newItem) throws SQLException {

		Long result = null;
		PreparedStatement ps = null;

		try {

			ps = workspace.getPreparedStatement(
					"INSERT INTO calendar.accounts(ID, USERNAME, REALNAME, PASSWORD, DAYSOFFPERYEAR, SICKLEAVEPERYEAR) VALUES (NEXTVAL('calendar.seq_accounts'), ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			ps.setString(i++, newItem.getUserName());
			ps.setString(i++, newItem.getRealName());
			ps.setString(i++, newItem.getPassword());
			ps.setLong(i++, newItem.getDaysOffPerYear());
			ps.setLong(i++, newItem.getSickLeavePerYear());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					result = rs.getLong(1);
				}
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public Long update(Workspace workspace, Account updateItem) throws SQLException {

		Long result = null;
		PreparedStatement ps = null;

		try {

			ps = workspace.getPreparedStatement(
					"UPDATE CALENDAR.ACCOUNTS SET REALNAME = ?, PASSWORD = ?, DAYSOFFPERYEAR = ?, SICKLEAVEPERYEAR = ? WHERE ID = ?",
					Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			ps.setString(i++, updateItem.getRealName());
			ps.setString(i++, updateItem.getPassword());
			ps.setLong(i++, updateItem.getDaysOffPerYear());
			ps.setLong(i++, updateItem.getSickLeavePerYear());
			ps.setLong(i++, updateItem.getId());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					result = rs.getLong(1);
				}
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public boolean deleteById(Workspace workspace, long Id) throws SQLException {

		boolean result = false;
		PreparedStatement ps = null;

		try {

			ps = workspace.getPreparedStatement("DELETE FROM CALENDAR.ACCOUNTS WHERE ID = ?");
			int i = 1;
			ps.setLong(i++, Id);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				result = true;
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	private Account buildAccount(ResultSet rs, boolean isLogin) throws SQLException {
		Account account = new Account();
		int i = 1;
		account.setId(rs.getLong(i++));
		account.setUserName(rs.getString(i++));
		account.setRealName(rs.getString(i++));
		if (isLogin) {
			account.setPassword(rs.getString(i++));
		}
		account.setDaysOffPerYear(rs.getLong(i++));
		account.setSickLeavePerYear(rs.getLong(i++));
		return account;
	}

}
