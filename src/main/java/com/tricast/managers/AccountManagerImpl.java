package com.tricast.managers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tricast.managers.beans.Account;
import com.tricast.managers.mappers.AccountMapper;
import com.tricast.managers.mappers.HolidayMapper;
import com.tricast.repositories.AccountRepository;
import com.tricast.repositories.HolidaysRepository;
import com.tricast.repositories.entities.AccountEntity;

@Component
public class AccountManagerImpl implements AccountManager {

	private final AccountRepository accountsRepository;
	private final HolidaysRepository holidayRepository;

	@Autowired
	public AccountManagerImpl(AccountRepository accountsRepository, HolidaysRepository holidayRepository) {
		this.accountsRepository = accountsRepository;
		this.holidayRepository = holidayRepository;
	}

	@Override
	public List<Account> getAll() {
		return AccountMapper.mapToBeanList(accountsRepository.findAll());
	}

	@Override
	public Account getById(long id) {

		AccountEntity account = accountsRepository.findById(id);

		Account accountBean = AccountMapper.mapToBean(account);
		
		if(accountBean != null) {
			accountBean.setHolidays(HolidayMapper.mapToBeanList(holidayRepository.findByAccount_id(account.getId())));
		}
		
		return accountBean;
	}

	@Override
	public Account save(Account newAccount) {
		
		AccountEntity createdAccountEntity = accountsRepository.save(AccountMapper.mapToEntity(newAccount));
		return AccountMapper.mapToBean(createdAccountEntity);
	}

//	@Override
//	public Account update(AccountEntity updateAccount) throws SQLException, IOException {
//		Long id = accountDao.update(workspace, updateAccount);
//		if (id != null) {
//			return accountDao.getById(workspace, id);
//		} else {
//			return null;
//		}
//	}

//	@Override
//	public boolean deleteById(long Id) throws SQLException, IOException {
//		return accountDao.deleteById(workspace, Id);
//	}

	@Override
	public Account login(String username, String password) throws SQLException {
			
		AccountEntity foundAccountEntity = accountsRepository.findByUserNameAndPassword(username, password);
		
		if (foundAccountEntity == null) {
			throw new SQLException("No account exists with the specified username:" + username);
		}
		
		Account account = AccountMapper.mapToBean(foundAccountEntity);	
		account.setHolidays(HolidayMapper.mapToBeanList(holidayRepository.findByAccount_id(account.getId())));
		
		return account;
	}

}
