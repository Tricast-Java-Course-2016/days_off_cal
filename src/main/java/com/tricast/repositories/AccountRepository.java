package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Account;
import com.tricast.repositories.entities.embded.Username;
				  
public interface AccountRepository extends CrudRepository<Account, Long>{

	@Override
	List<Account> findAll();
	
	Account findById(Long id);
	
	// login
	Account findByUserName(Username username);
}
