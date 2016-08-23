package com.tricast.web.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.tricast.beans.Account;
import com.tricast.beans.Holiday;
import com.tricast.database.Workspace;
import com.tricast.web.annotations.JdbcTransaction;
import com.tricast.web.dao.AccountDao;
import com.tricast.web.dao.BlockedDaysDao;
import com.tricast.web.dao.HolidayDao;
import com.tricast.web.helpers.HolidayHelper;
import com.tricast.web.response.HolidayResponse;

public class HolidayManagerImpl implements HolidayManager {

	private final HolidayDao leaveDao;
	private final BlockedDaysDao blockedDaysDao;
	private final AccountDao accountDao;

	@Inject
	public HolidayManagerImpl(HolidayDao leaveDao, BlockedDaysDao blockedDaysDao, AccountDao accountDao) {
		this.leaveDao = leaveDao;
		this.blockedDaysDao = blockedDaysDao;
		this.accountDao = accountDao;
	}

	@Override
	@JdbcTransaction
	public Holiday createHoliday(Workspace workspace, Holiday holiday) throws SQLException, IOException {
		List<Holiday> currentHolidays = leaveDao.getAllForAccount(workspace, holiday.getAccountId());
		if (!HolidayHelper.isOverlapping(currentHolidays, holiday)) {
			Long leaveId = leaveDao.create(workspace, holiday, blockedDaysDao.getBlockedDays(workspace));
			if (leaveId != null) {
				return leaveDao.getById(workspace, leaveId);
			} else {
				return null;
			}
		} else {
			throw new SQLException("The account already has a holiday planned for this period.");
		}
	}

	@Override
	@JdbcTransaction
	public Holiday updateHoliday(Workspace workspace, Holiday holiday) throws SQLException, IOException {
		Long leaveId = leaveDao.update(workspace, holiday);
		if (leaveId != null) {
			return leaveDao.getById(workspace, leaveId);
		} else {
			return null;
		}
	}

	@Override
	@JdbcTransaction
	public boolean deleteHoliday(Workspace workspace, long holidayId) throws SQLException, IOException {
		return leaveDao.deleteById(workspace, holidayId);
	}

	@Override
	@JdbcTransaction
	public List<HolidayResponse> getAllHolidays(Workspace workspace) throws SQLException, IOException {
		List<Account> accounts = accountDao.getAll(workspace);
		List<HolidayResponse> responses = new ArrayList<HolidayResponse>();
		for (Account account : accounts) {
			List<Holiday> holidays = leaveDao.getAllForAccount(workspace, account.getId());
			for (Holiday holiday : holidays) {
				HolidayResponse newResponse = new HolidayResponse();
				newResponse.setAccountId(account.getId());
				newResponse.setAccountRealName(account.getRealName());
				newResponse.setFromDay(holiday.getFromDay());
				newResponse.setToDay(holiday.getToDay());
				newResponse.setActualDayCount(holiday.getActualDayCount());
				newResponse.setType(holiday.getType());
				newResponse.setId(holiday.getId());
				responses.add(newResponse);
			}
		}

		return responses;
	}

	@Override
	@JdbcTransaction
	public Holiday getById(Workspace workspace, long id) throws SQLException, IOException {
		return leaveDao.getById(workspace, id);
	}

}
