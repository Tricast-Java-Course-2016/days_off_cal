package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Holiday;

public interface HolidaysRepository  extends CrudRepository<Holiday, Long>  {

	@Override
	List<Holiday> findAll();
	
	List<Holiday> findByAccount_id(Long id);

	Holiday findById(Long id);
}
