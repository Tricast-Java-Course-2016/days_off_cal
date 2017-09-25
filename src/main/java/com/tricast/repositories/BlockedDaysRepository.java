package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.customs.BlockedDayRepositoryCustom;
import com.tricast.repositories.entities.BlockedDay;

public interface BlockedDaysRepository extends CrudRepository<BlockedDay, Long>, BlockedDayRepositoryCustom {

	@Override
	List<BlockedDay> findAll();
}
