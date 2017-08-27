package com.tricast.managers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tricast.controllers.responses.HolidayResponse;
import com.tricast.managers.beans.Holiday;
import com.tricast.managers.beans.HolidayType;
import com.tricast.managers.helpers.HolidayHelper;
import com.tricast.managers.mappers.HolidayMapper;
import com.tricast.repositories.AccountRepository;
import com.tricast.repositories.BlockedDaysRepository;
import com.tricast.repositories.HolidaysRepository;
import com.tricast.repositories.customs.BlockedDayRepositoryCustom;
import com.tricast.repositories.entities.AccountEntity;
import com.tricast.repositories.entities.BlockedDayEntity;
import com.tricast.repositories.entities.HolidayEntity;

@Component
public class HolidayManagerImpl implements HolidayManager {

	private final AccountRepository accountsRepository;
	private final BlockedDaysRepository blockedDaysRepository;
	private final HolidaysRepository holidayRepository;
	private final BlockedDayRepositoryCustom blockedDayRepositoryCustom;
	
	@Autowired
	public HolidayManagerImpl(AccountRepository accountsRepository, BlockedDaysRepository blockedDaysRepository,
			HolidaysRepository holidayRepository, BlockedDayRepositoryCustom blockedDayRepositoryCustom) {
		this.accountsRepository = accountsRepository;
		this.blockedDaysRepository = blockedDaysRepository;
		this.holidayRepository = holidayRepository;
		this.blockedDayRepositoryCustom = blockedDayRepositoryCustom;
	}

	@Override
	public Holiday saveHoliday(Holiday leave) throws SQLException {

		// create
		if (leave.getId() == null) {
			List<Holiday> currentHolidays = HolidayMapper
					.mapToBeanList(holidayRepository.findByAccount_id(leave.getAccountId()));

			if (!HolidayHelper.isOverlapping(currentHolidays, leave)) {

				List<BlockedDayEntity> blockedDayEntities = blockedDaysRepository.findAll();

				List<String> blockedDays = new ArrayList<>();
				blockedDayEntities.forEach(item -> {
					blockedDays.add(item.getDay());
				});

				leave.setActualDayCount(HolidayHelper.getActualNumberOfDaysForLeave(leave, blockedDays));

				AccountEntity account = accountsRepository.findById(leave.getAccountId());
				return HolidayMapper.mapToBean(holidayRepository.save(HolidayMapper.mapToEntity(leave, account)));

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
		HolidayEntity holiday = holidayRepository.findById(holidayId);
		
		// set to the given type
		holiday.setType(type.getTypeId());
		
		// save it
		return HolidayMapper.mapToBean(holidayRepository.save(holiday));
	}
	
	@Override
	public void deleteHoliday(long leaveId) {
		holidayRepository.delete(leaveId);
	}

	@Override
	public List<HolidayResponse> getAllHolidays() {
		
		List<AccountEntity> accounts = accountsRepository.findAll();
		List<HolidayResponse> responses = new ArrayList<>();
		
		for (AccountEntity account : accounts) {
			List<HolidayEntity> holidays = holidayRepository.findByAccount_id(account.getId());
			
			for (HolidayEntity holiday : holidays) {
				HolidayResponse newResponse = new HolidayResponse();
				newResponse.setAccountId(account.getId());
				newResponse.setAccountRealName(account.getRealname());
				newResponse.setFromDay(holiday.getFromday());
				newResponse.setToDay(holiday.getToday());
				newResponse.setActualDayCount(holiday.getActualdaycount());
				newResponse.setType(HolidayType.getType(holiday.getType()));
				newResponse.setId(holiday.getId());
				responses.add(newResponse);
			}
		}
		return responses;
	}

	@Override
	public Holiday getById(long id) {
		return HolidayMapper.mapToBean(holidayRepository.findById(id));
	}
}
