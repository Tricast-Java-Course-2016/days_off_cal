package com.tricast.managers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tricast.controllers.requests.HolidayRequest;
import com.tricast.controllers.responses.HolidayResponse;
import com.tricast.managers.beans.HolidayType;
import com.tricast.managers.helpers.HolidayHelper;
import com.tricast.managers.mappers.HolidayRequestMapper;
import com.tricast.managers.mappers.HolidayResponseMapper;
import com.tricast.repositories.AccountRepository;
import com.tricast.repositories.BlockedDaysRepository;
import com.tricast.repositories.HolidaysRepository;
import com.tricast.repositories.entities.Account;
import com.tricast.repositories.entities.BlockedDay;
import com.tricast.repositories.entities.Holiday;

@Component
public class HolidayManagerImpl implements HolidayManager {

	private final AccountRepository accountsRepository;
	private final BlockedDaysRepository blockedDaysRepository;
	private final HolidaysRepository holidayRepository;
	
	@Autowired
	public HolidayManagerImpl(AccountRepository accountsRepository, BlockedDaysRepository blockedDaysRepository,
			HolidaysRepository holidayRepository) {
		this.accountsRepository = accountsRepository;
		this.blockedDaysRepository = blockedDaysRepository;
		this.holidayRepository = holidayRepository;
	}

	@Override
	public Holiday saveHoliday(HolidayRequest request) throws SQLException {

		// create
		if (request.getId() == null) {
				
			Holiday leave = HolidayRequestMapper.mapToEntity(request, accountsRepository.findById(request.getAccountId()));
			
			List<Holiday> currentHolidays = holidayRepository.findByAccount_id(leave.getAccount().getId());

			if (!HolidayHelper.isOverlapping(currentHolidays, leave)) {

				List<BlockedDay> blockedDayEntities = blockedDaysRepository.getBlockedDays();

				List<String> blockedDays = new ArrayList<>();
				blockedDayEntities.forEach(item -> {
					blockedDays.add(item.getDay());
				});

				leave.setActualdaycount(HolidayHelper.getActualNumberOfDaysForLeave(leave, blockedDays));

				return holidayRepository.save(leave);

			} else {
				throw new SQLException("The account already has a holiday planned for this period.");
			}
		} else {
			throw new SQLException("Update holiday is not allowed!");
		}
	}

	@Override
	public Holiday updateHolidayType(Long holidayId, HolidayType type) {
		
		// load holiday by id
		Holiday holiday = holidayRepository.findById(holidayId);
		
		// set to the given type
		holiday.setType(type.getTypeId());
		
		// save it
		return holidayRepository.save(holiday);
	}
	
	@Override
	public void deleteHoliday(long leaveId) {
		holidayRepository.delete(leaveId);
	}

	@Override
	public List<HolidayResponse> getAllHolidays() {
		
		List<Account> accounts = accountsRepository.findAll();
		List<HolidayResponse> responses = new ArrayList<>();
		
		for (Account account : accounts) {
			List<Holiday> holidays = holidayRepository.findByAccount_id(account.getId()); 	
			responses.addAll(HolidayResponseMapper.mapToResponseList(holidays, account));
		}
		return responses;
	}

	@Override
	public Holiday getById(long id) {
		return holidayRepository.findById(id);
	}
}
