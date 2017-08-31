package com.tricast.managers.mappers;

import com.tricast.controllers.requests.HolidayRequest;
import com.tricast.repositories.entities.Account;
import com.tricast.repositories.entities.Holiday;

public class HolidayRequestMapper {

	public static Holiday mapToEntity(HolidayRequest requestObject, Holiday entityObject, Account account) {

		if (requestObject == null) {
			return null;
		}

		 if(entityObject == null) {
	        	throw new IllegalArgumentException("Missing Holiday entityObject for mapping");
	        }
		 
		entityObject.setId(requestObject.getId());
		entityObject.setAccount(account);
		entityObject.setFromDay(requestObject.getFromDay());
		entityObject.setToDay(requestObject.getToDay());
		entityObject.setType(requestObject.getType().getTypeId());
		
		return entityObject;
	}
}
