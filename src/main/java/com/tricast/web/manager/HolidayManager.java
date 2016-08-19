package com.tricast.web.manager;

import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Holiday;
import com.tricast.web.response.HolidayResponse;

public interface HolidayManager {

    public Holiday createHoliday(Holiday leave) throws SQLException;

    public Holiday updateHoliday(Holiday leave) throws SQLException;

    public boolean deleteHoliday(long leaveId) throws SQLException;

    public List<HolidayResponse> getAllHolidays() throws SQLException;

    public Holiday getById(long id) throws SQLException;

}
