package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.BlockedDayEntity;

public interface BlockedDaysRepository extends CrudRepository<BlockedDayEntity, Long>/*, BlockedDayRepositoryCustom*/ {

	@Override
	List<BlockedDayEntity> findAll();
}