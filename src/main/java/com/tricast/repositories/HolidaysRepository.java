package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.AccountEntity;
import com.tricast.repositories.entities.HolidayEntity;

public interface HolidaysRepository  extends CrudRepository<HolidayEntity, Long>  {

	List<HolidayEntity> findAll();
	
	List<HolidayEntity> findByAccount_id(Long id);

	HolidayEntity findById(Long id);
}
