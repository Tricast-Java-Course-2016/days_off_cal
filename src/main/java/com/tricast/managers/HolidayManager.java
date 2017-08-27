package com.tricast.managers;

import java.sql.SQLException;
import java.util.List;

import com.tricast.controllers.responses.HolidayResponse;
import com.tricast.managers.beans.Holiday;
import com.tricast.managers.beans.HolidayType;

public interface HolidayManager {

	Holiday saveHoliday(Holiday leave) throws SQLException;

	Holiday updateHolidayType(Long holidayId, HolidayType type);
	
	void deleteHoliday(long leaveId);

	List<HolidayResponse> getAllHolidays();

	Holiday getById(long id);
}
