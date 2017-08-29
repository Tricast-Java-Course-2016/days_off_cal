package com.tricast.managers.mappers;

import com.tricast.controllers.requests.HolidayRequest;
import com.tricast.repositories.entities.Account;
import com.tricast.repositories.entities.Holiday;

public class HolidayRequestMapper {

	public static Holiday mapToEntity(HolidayRequest requestObject, Account account) {

		if (requestObject == null) {
			return null;
		}

		Holiday entityObject = new Holiday();
		entityObject.setId(requestObject.getId());
		entityObject.setAccount(account);
		entityObject.setFromday(requestObject.getFromDay());
		entityObject.setToday(requestObject.getToDay());
		entityObject.setType(requestObject.getType().getTypeId());
		
		return entityObject;
	}
}
