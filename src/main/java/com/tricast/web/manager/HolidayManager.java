package com.tricast.web.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Holiday;
import com.tricast.database.Workspace;
import com.tricast.web.response.HolidayResponse;

public interface HolidayManager {

	public Holiday createHoliday(Workspace workspace, Holiday leave) throws SQLException, IOException;

	public Holiday updateHoliday(Workspace workspace, Holiday leave) throws SQLException, IOException;

	public boolean deleteHoliday(Workspace workspace, long leaveId) throws SQLException, IOException;

	public List<HolidayResponse> getAllHolidays(Workspace workspace) throws SQLException, IOException;

	public Holiday getById(Workspace workspace, long id) throws SQLException, IOException;

}
