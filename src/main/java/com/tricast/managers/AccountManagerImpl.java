package com.tricast.managers;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.controllers.responses.AccountResponse;
import com.tricast.managers.mappers.AccountRequestMapper;
import com.tricast.managers.mappers.AccountResponseMapper;
import com.tricast.repositories.AccountRepository;
import com.tricast.repositories.HolidaysRepository;
import com.tricast.repositories.entities.Account;
import com.tricast.repositories.entities.embded.Username;

@Component
@Transactional
public class AccountManagerImpl implements AccountManager {

	private final AccountRepository accountsRepository;
	private final HolidaysRepository holidayRepository;

	private final PasswordEncoder encoder;

	@Autowired
	public AccountManagerImpl(AccountRepository accountsRepository, HolidaysRepository holidayRepository,
			PasswordEncoder encoder) {
		this.accountsRepository = accountsRepository;
		this.holidayRepository = holidayRepository;
		this.encoder = encoder;
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
	public AccountResponse createAccount(AccountRequest accountRequest) {

		Account account = AccountRequestMapper.mapToEntity(accountRequest, new Account());
		account.setPassword(encoder.encode(accountRequest.getPassword()));

		return AccountResponseMapper.mapToResponse(accountsRepository.save(account));
	}

	@Override
	public AccountResponse updateAccount(AccountRequest accountRequest) {

		Account accountToUpdate = accountsRepository.findById(accountRequest.getId());
		AccountRequestMapper.mapToEntity(accountRequest, accountToUpdate);

		// update the password if necessary
		if (!StringUtils.isEmpty(accountRequest.getPassword())
				&& !encoder.matches(accountRequest.getPassword(), accountToUpdate.getPassword())) {
			accountToUpdate.setPassword(encoder.encode(accountRequest.getPassword()));
		}

		return AccountResponseMapper.mapToResponse(accountToUpdate);
	}

	@Override
	public AccountResponse login(String username, String password) throws SQLException {

		Username usernameObject = new Username(username);

		Account foundAccountEntity = accountsRepository.findByUserName(usernameObject);

		if (foundAccountEntity == null || !encoder.matches(password, foundAccountEntity.getPassword())) {
			throw new SQLException("Wrong username or password: " + username);
		}

		AccountResponse account = AccountResponseMapper.mapToResponse(foundAccountEntity);
		account.setHolidays(holidayRepository.findByAccount_id(account.getId()));

		return account;
	}
}
