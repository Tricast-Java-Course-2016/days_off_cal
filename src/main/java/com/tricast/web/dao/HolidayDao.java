package com.tricast.web.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Holiday;
import com.tricast.database.Workspace;

public interface HolidayDao {

	List<Holiday> getAllForAccount(Workspace workspace, long accountId) throws SQLException, IOException;

	Holiday getById(Workspace workspace, long holidayId) throws SQLException, IOException;

	Long create(Workspace workspace, Holiday holiday, List<String> blockedDays) throws SQLException, IOException;

	boolean deleteById(Workspace workspace, long holidayId) throws SQLException, IOException;

	Long update(Workspace workspace, Holiday holiday) throws SQLException, IOException;

}
