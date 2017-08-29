package com.tricast.managers;

import java.sql.SQLException;
import java.util.List;

import com.tricast.controllers.requests.HolidayRequest;
import com.tricast.controllers.responses.HolidayResponse;
import com.tricast.managers.beans.HolidayType;
import com.tricast.repositories.entities.Holiday;

public interface HolidayManager {

	Holiday saveHoliday(HolidayRequest leave) throws SQLException;

	Holiday updateHolidayType(Long holidayId, HolidayType type);
	
	void deleteHoliday(long leaveId);

	List<HolidayResponse> getAllHolidays();

	Holiday getById(long id);
}
