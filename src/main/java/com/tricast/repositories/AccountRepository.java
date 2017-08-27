package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.AccountEntity;
				  
public interface AccountRepository extends CrudRepository<AccountEntity, Long>{

	@Override
	List<AccountEntity> findAll();
	
	AccountEntity findById(Long id);
	
	// login
	AccountEntity findByUserNameAndPassword(String username, String password);
}
