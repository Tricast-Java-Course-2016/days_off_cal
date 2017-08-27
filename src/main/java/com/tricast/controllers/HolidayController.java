package com.tricast.controllers;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.responses.HolidayResponse;
import com.tricast.managers.HolidayManager;
import com.tricast.managers.beans.Holiday;

@RestController
@RequestMapping(path = "/holidays")
public class HolidayController {

	private static final Logger log = LogManager.getLogger(HolidayController.class);

	private final HolidayManager holidayManager;

	@Autowired
	public HolidayController(HolidayManager holidayManager) {
		this.holidayManager = holidayManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<HolidayResponse> getHolidays() {
		log.trace("Trying to get all holidays");
		return holidayManager.getAllHolidays();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Holiday getById(@PathVariable("id") long id) {
		log.trace("Requested to get ID = " + id);
		return holidayManager.getById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Holiday createHoliday(@RequestBody Holiday holiday) throws SQLException {
		log.trace("Trying to create new holiday for account #" + holiday.getAccountId());
		return holidayManager.saveHoliday(holiday);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Holiday updateHoliday(@RequestBody Holiday holiday) {
		log.trace("Trying to update a holiday for account #" + holiday.getAccountId());
		return holidayManager.updateHolidayType(holiday.getId(), holiday.getType());
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public void deleteHoliday(@PathVariable("id") long id) {
		log.trace("Trying to delete holiday #" + id);
		holidayManager.deleteHoliday(id);
	}
}
