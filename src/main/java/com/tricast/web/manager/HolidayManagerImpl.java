package com.tricast.web.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.tricast.beans.Account;
import com.tricast.beans.Holiday;
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
    public Holiday createHoliday(Holiday holiday) throws SQLException {
        List<Holiday> currentHolidays = leaveDao.getAllForAccount(holiday.getAccountId());
        if (!HolidayHelper.isOverlapping(currentHolidays, holiday)) {
            Long leaveId = leaveDao.create(holiday, blockedDaysDao.getBlockedDays());
            if (leaveId != null) {
                return leaveDao.getById(leaveId);
            } else {
                return null;
            }
        } else {
            throw new SQLException("The account already has a holiday planned for this period.");
        }
    }

    @Override
    public Holiday updateHoliday(Holiday holiday) throws SQLException {
        Long leaveId = leaveDao.update(holiday);
        if (leaveId != null) {
            return leaveDao.getById(leaveId);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteHoliday(long holidayId) throws SQLException {
        return leaveDao.deleteById(holidayId);
    }

    @Override
    public List<HolidayResponse> getAllHolidays() throws SQLException {
        List<Account> accounts = accountDao.getAll();
        List<HolidayResponse> responses = new ArrayList<HolidayResponse>();
        for (Account account : accounts) {
            List<Holiday> holidays = leaveDao.getAllForAccount(account.getId());
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
    public Holiday getById(long id) throws SQLException {
        return leaveDao.getById(id);
    }

}
