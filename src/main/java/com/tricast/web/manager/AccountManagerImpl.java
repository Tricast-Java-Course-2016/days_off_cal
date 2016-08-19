package com.tricast.web.manager;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.tricast.beans.Account;
import com.tricast.web.dao.AccountDao;
import com.tricast.web.dao.HolidayDao;

public class AccountManagerImpl implements AccountManager {

    private final AccountDao accountDao;
    private final HolidayDao leaveDao;

    @Inject
    public AccountManagerImpl(AccountDao accountDao, HolidayDao leaveDao) {
        this.accountDao = accountDao;
        this.leaveDao = leaveDao;
    }

    @Override
    public List<Account> getAll() throws SQLException {
        return accountDao.getAll();
    }

    @Override
    public Account getById(long id) throws SQLException {
        Account account = accountDao.getById(id);
        account.setHolidays(leaveDao.getAllForAccount(account.getId()));
        return account;
    }

    @Override
    public Account create(Account newAccount) throws SQLException {
        Long id = accountDao.create(newAccount);
        if (id != null) {
            return accountDao.getById(id);
        } else {
            return null;
        }
    }

    @Override
    public Account update(Account updateAccount) throws SQLException {
        Long id = accountDao.update(updateAccount);
        if (id != null) {
            return accountDao.getById(id);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteById(long Id) throws SQLException {
        return accountDao.deleteById(Id);
    }

    @Override
    public Account login(String username, String password) throws SQLException {
        Account account = accountDao.login(username, password);
        if (account == null) {
            throw new SQLException("No account exists with the specified username:" + username);
        }
        account.setHolidays(leaveDao.getAllForAccount(account.getId()));
        return account;
    }

}
