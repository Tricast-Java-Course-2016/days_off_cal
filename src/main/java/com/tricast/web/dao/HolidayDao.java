package com.tricast.web.dao;

import java.sql.SQLException;
import java.util.List;

import com.tricast.beans.Holiday;

/**
 * User: Renato
 */
public interface HolidayDao {

	List<Holiday> getAllForAccount(long accountId) throws SQLException;

	Holiday getById(long holidayId) throws SQLException;

	Long create(Holiday holiday, List<String> blockedDays) throws SQLException;

	boolean deleteById(long holidayId) throws SQLException;

	Long update(Holiday holiday) throws SQLException;

}
