package com.tricast.managers.mappers;

import java.util.ArrayList;
import java.util.List;

import com.tricast.managers.beans.Holiday;
import com.tricast.managers.beans.HolidayType;
import com.tricast.repositories.entities.AccountEntity;
import com.tricast.repositories.entities.HolidayEntity;

public class HolidayMapper {

	public static Holiday mapToBean(HolidayEntity entityObject) {

		if (entityObject == null) {
			return null;
		}

		Holiday beanObject = new Holiday();
		beanObject.setId(entityObject.getId());
		beanObject.setAccountId(entityObject.getAccount().getId());
		beanObject.setActualDayCount(entityObject.getActualdaycount());
		beanObject.setFromDay(entityObject.getFromday());
		beanObject.setToDay(entityObject.getToday());
		beanObject.setType(HolidayType.getType(entityObject.getType()));

		return beanObject;
	}

	public static HolidayEntity mapToEntity(Holiday beanObject, AccountEntity account) {

		if (beanObject == null) {
			return null;
		}

		HolidayEntity entityObject = new HolidayEntity();
		entityObject.setId(beanObject.getId());
		entityObject.setAccount(account);
		entityObject.setActualdaycount(beanObject.getActualDayCount());
		entityObject.setFromday(beanObject.getFromDay());
		entityObject.setToday(beanObject.getToDay());
		entityObject.setType(beanObject.getType().getTypeId());

		return entityObject;
	}
	
	public static List<Holiday> mapToBeanList(List<HolidayEntity> entityObjectsList) {

		if (entityObjectsList == null) {
			return null;
		}

		List<Holiday> accountsBeanList = new ArrayList<>();

		entityObjectsList.forEach(entity -> accountsBeanList.add(mapToBean(entity)));

		return accountsBeanList;
	}
}
