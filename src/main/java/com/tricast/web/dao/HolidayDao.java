package com.tricast.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Holiday;

/**
 * User: Renato
 */
public interface HolidayDao {

	List<Holiday> getAllForAccount(Workspace workspace, long accountId) throws SQLException;

	Holiday getById(Workspace workspace, long holidayId) throws SQLException;

	Long create(Workspace workspace, Holiday holiday, List<String> blockedDays) throws SQLException;

	boolean deleteById(Workspace workspace, long holidayId) throws SQLException;

	Long update(Workspace workspace, Holiday holiday) throws SQLException;

}
