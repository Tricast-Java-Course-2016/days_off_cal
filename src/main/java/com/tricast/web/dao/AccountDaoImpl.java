package com.tricast.web.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tricast.beans.Account;
import com.tricast.database.SqlManager;
import com.tricast.database.Workspace;
import com.tricast.web.annotations.JdbcTransaction;

public class AccountDaoImpl implements AccountDao {

	private static final Logger log = LogManager.getLogger(AccountDaoImpl.class);
	private static final SqlManager sqlManager = SqlManager.getInstance();

	@Override
	@JdbcTransaction
	public List<Account> getAll(Workspace workspace) throws SQLException, IOException {

		List<Account> result = new ArrayList<Account>();

		String sql = sqlManager.get("accountgetall.sql");

		try (PreparedStatement ps = workspace.getPreparedStatement(sql); ResultSet rs = ps.executeQuery()) {

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
	public Account getById(Workspace workspace, long id) throws SQLException, IOException {

		Account result = null;
		ResultSet rs = null;

		String sql = sqlManager.get("accountgetbyid.sql");

		try (PreparedStatement ps = workspace.getPreparedStatement(sql)) {
			ps.setLong(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				result = buildAccount(rs, true);
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		} finally {
			rs.close();
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public Account login(Workspace workspace, String username, String password) throws SQLException, IOException {

		Account result = null;
		ResultSet rs = null;

		String sql = sqlManager.get("accountlogin.sql");

		try (PreparedStatement ps = workspace.getPreparedStatement(sql)) {

			int i = 1;
			ps.setString(i++, username);
			ps.setString(i++, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				result = buildAccount(rs, false);
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		} finally {
			rs.close();
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public Long create(Workspace workspace, Account newItem) throws SQLException, IOException {

		Long result = null;
		ResultSet rs = null;

		String sql = sqlManager.get("accountcreate.sql");

		try (PreparedStatement ps = workspace.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			int i = 1;
			ps.setString(i++, newItem.getUserName());
			ps.setString(i++, newItem.getRealName());
			ps.setString(i++, newItem.getPassword());
			ps.setLong(i++, newItem.getDaysOffPerYear());
			ps.setLong(i++, newItem.getSickLeavePerYear());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					result = rs.getLong(1);
				}
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		} finally {
			rs.close();
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public Long update(Workspace workspace, Account updateItem) throws SQLException, IOException {

		Long result = null;
		ResultSet rs = null;

		String sql = sqlManager.get("accountupdate.sql");

		try (PreparedStatement ps = workspace.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			ps.setString(i++, updateItem.getRealName());
			ps.setString(i++, updateItem.getPassword());
			ps.setLong(i++, updateItem.getDaysOffPerYear());
			ps.setLong(i++, updateItem.getSickLeavePerYear());
			ps.setLong(i++, updateItem.getId());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					result = rs.getLong(1);
				}
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		} finally {
			rs.close();
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public boolean deleteById(Workspace workspace, long Id) throws SQLException, IOException {

		boolean result = false;

		String sql = sqlManager.get("accountdeletebyid.sql");

		try (PreparedStatement ps = workspace.getPreparedStatement(sql)) {

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
