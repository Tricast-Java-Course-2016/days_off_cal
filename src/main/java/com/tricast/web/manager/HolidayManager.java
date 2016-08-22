package com.tricast.web.manager;

import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Holiday;
import com.tricast.web.dao.Workspace;
import com.tricast.web.response.HolidayResponse;

public interface HolidayManager {

	public Holiday createHoliday(Workspace workspace, Holiday leave) throws SQLException;

	public Holiday updateHoliday(Workspace workspace, Holiday leave) throws SQLException;

	public boolean deleteHoliday(Workspace workspace, long leaveId) throws SQLException;

	public List<HolidayResponse> getAllHolidays(Workspace workspace) throws SQLException;

	public Holiday getById(Workspace workspace, long id) throws SQLException;

}
