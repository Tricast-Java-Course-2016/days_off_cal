package com.tricast.repositories.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tricast.repositories.entities.Account;

@Transactional
@Repository
public class AccountRepositoryHibernateImpl implements AccountRepositoryHibernate {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveAccount(Account account) {
		entityManager.persist(account);
	}

	@Override
	public Account login(String username, String password) {
		String hql = "FROM Account as acc WHERE acc.userName = ? and acc.password = ?";
		List<Account> account = entityManager.createQuery(hql).setParameter(1, username)
        .setParameter(2, password).getResultList();
		
		if(account != null && account.size() == 1) {
			return (Account) account.get(0);
		} else {
			return null;
		}
	}
}
