package com.tricast.managers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tricast.controllers.responses.AccountResponse;
import com.tricast.managers.mappers.AccountResponseMapper;
import com.tricast.repositories.AccountRepository;
import com.tricast.repositories.HolidaysRepository;
import com.tricast.repositories.entities.Account;
import com.tricast.repositories.hibernate.AccountRepositoryHibernate;

@Component
public class AccountManagerImpl implements AccountManager {

	private final AccountRepository accountsRepository;
	private final HolidaysRepository holidayRepository;

	private final AccountRepositoryHibernate accountRepositoryHibernate;

	@Autowired
	public AccountManagerImpl(AccountRepository accountsRepository, HolidaysRepository holidayRepository,
			AccountRepositoryHibernate accountRepositoryHibernate) {
		this.accountsRepository = accountsRepository;
		this.holidayRepository = holidayRepository;
		this.accountRepositoryHibernate = accountRepositoryHibernate;
	}

	@Override
	public List<AccountResponse> getAll() {
		return AccountResponseMapper.mapToBeanList(accountsRepository.findAll());
	}

	@Override
	public AccountResponse getById(long id) {

		Account account = accountsRepository.findById(id);

		AccountResponse accountBean = AccountResponseMapper.mapToResponse(account);

		if (accountBean != null) {
			accountBean.setHolidays(holidayRepository.findByAccount_id(account.getId()));
		}

		return accountBean;
	}

	@Override
	public Account save(Account newAccount) {
		return accountsRepository.save(newAccount);
	}

	// @Override
	// public boolean deleteById(long Id) throws SQLException, IOException {
	// return accountDao.deleteById(workspace, Id);
	// }

	@Override
	public AccountResponse login(String username, String password) throws SQLException {

		// try out Hibernate stuff
		// AccountEntity foundAccountEntity =
		// accountsRepository.findByUserNameAndPassword(username, password);

		Account foundAccountEntity = accountRepositoryHibernate.login(username, password);

		if (foundAccountEntity == null) {
			throw new SQLException("No account exists with the specified username:" + username);
		}

		AccountResponse account = AccountResponseMapper.mapToResponse(foundAccountEntity);
		account.setHolidays(holidayRepository.findByAccount_id(account.getId()));

		return account;
	}

}
