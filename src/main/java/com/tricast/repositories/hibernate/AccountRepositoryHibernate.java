package com.tricast.repositories.hibernate;

import com.tricast.repositories.entities.Account;

public interface AccountRepositoryHibernate {

	void saveAccount(Account account);
	
	Account login(String username, String password);
}
