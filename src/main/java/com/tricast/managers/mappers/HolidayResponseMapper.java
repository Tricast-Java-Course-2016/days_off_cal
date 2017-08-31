package com.tricast.managers.mappers;

import java.util.ArrayList;
import java.util.List;

import com.tricast.controllers.responses.HolidayResponse;
import com.tricast.managers.beans.HolidayType;
import com.tricast.repositories.entities.Account;
import com.tricast.repositories.entities.Holiday;

public class HolidayResponseMapper {

	public static HolidayResponse mapToResponse(Holiday entityObject, Account account) {

		if (entityObject == null) {
			return null;
		}

		HolidayResponse responseObject = new HolidayResponse();
		responseObject.setId(entityObject.getId());
		responseObject.setAccountId(entityObject.getAccount().getId());
		responseObject.setActualDayCount(entityObject.getActualDayCount());
		responseObject.setFromDay(entityObject.getFromDay());
		responseObject.setToDay(entityObject.getToDay());
		responseObject.setType(HolidayType.getType(entityObject.getType()));
		
		if(account != null) {
			responseObject.setAccountRealName(account.getRealName());
		}
		
		
		return responseObject;
	}
	
	public static List<HolidayResponse> mapToResponseList(List<Holiday> entityObjectsList, Account account) {

		if (entityObjectsList == null) {
			return null;
		}

		List<HolidayResponse> accountsBeanList = new ArrayList<>();

		entityObjectsList.forEach(entity -> accountsBeanList.add(mapToResponse(entity, account)));

		return accountsBeanList;
	}
}
