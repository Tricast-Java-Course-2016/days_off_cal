package com.tricast.web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tricast.beans.Holiday;
import com.tricast.beans.HolidayType;
import com.tricast.web.annotations.JdbcTransaction;
import com.tricast.web.helpers.HolidayHelper;

public class HolidayDaoImpl implements HolidayDao {

	private static final Logger log = LogManager.getLogger(HolidayDaoImpl.class);

	@Override
	@JdbcTransaction
	public List<Holiday> getAllForAccount(Workspace workspace, long accountId) throws SQLException {

		List<Holiday> result = new ArrayList<Holiday>();

		try (PreparedStatement ps = workspace.getPreparedStatement(
				"SELECT ID, ACCOUNTID, FROMDAY, TODAY, TYPE, ACTUALDAYCOUNT FROM CALENDAR.HOLIDAYS WHERE ACCOUNTID="
						+ accountId + " AND FROMDAY::int > date_part('year', current_timestamp) * 10000");
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				result.add(buildHoliday(rs));
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public Holiday getById(Workspace workspace, long holidayId) throws SQLException {

		Holiday result = null;

		try (PreparedStatement ps = workspace.getPreparedStatement(
				"SELECT ID, ACCOUNTID, FROMDAY, TODAY, TYPE, ACTUALDAYCOUNT FROM CALENDAR.HOLIDAYS WHERE ID="
						+ holidayId);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				result = buildHoliday(rs);
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public Long create(Workspace workspace, Holiday holiday, List<String> blockedDays) throws SQLException {
		holiday.setActualDayCount(HolidayHelper.getActualNumberOfDaysForLeave(holiday, blockedDays));

		Long result = null;
		PreparedStatement ps = null;

		try {

			ps = workspace.getPreparedStatement(
					"INSERT INTO CALENDAR.HOLIDAYS (ID, ACCOUNTID, FROMDAY, TODAY, TYPE, ACTUALDAYCOUNT) VALUES (NEXTVAL('CALENDAR.SEQ_HOLIDAYS'), ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			ps.setLong(i++, holiday.getAccountId());
			ps.setString(i++, holiday.getFromDay());
			ps.setString(i++, holiday.getToDay());
			ps.setLong(i++, holiday.getType().getTypeId());
			ps.setLong(i++, holiday.getActualDayCount());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					result = rs.getLong(1);
				}
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;

	}

	@Override
	@JdbcTransaction
	public Long update(Workspace workspace, Holiday holiday) throws SQLException {
		Long result = null;
		PreparedStatement ps = null;

		try {

			ps = workspace.getPreparedStatement("UPDATE CALENDAR.HOLIDAYS SET TYPE = ? WHERE ID = ?",
					Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			ps.setLong(i++, holiday.getType().getTypeId());
			ps.setLong(i++, holiday.getId());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					result = rs.getLong(1);
				}
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	@Override
	@JdbcTransaction
	public boolean deleteById(Workspace workspace, long holidayId) throws SQLException {
		boolean result = false;
		PreparedStatement ps = null;

		try {

			ps = workspace.getPreparedStatement("DELETE FROM CALENDAR.HOLIDAYS WHERE ID = ?");
			int i = 1;
			ps.setLong(i++, holidayId);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				result = true;
			}

		} catch (SQLException ex) {
			log.error(ex, ex);
			throw ex;
		}
		return result;
	}

	private Holiday buildHoliday(ResultSet rs) throws SQLException {
		Holiday leave = new Holiday();
		int i = 1;
		leave.setId(rs.getLong(i++));
		leave.setAccountId(rs.getLong(i++));
		leave.setFromDay(rs.getString(i++));
		leave.setToDay(rs.getString(i++));
		leave.setType(HolidayType.getType(rs.getLong(i++)));
		leave.setActualDayCount(rs.getLong(i++));
		return leave;
	}

}
