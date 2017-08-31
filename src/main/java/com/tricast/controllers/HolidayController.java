package com.tricast.controllers;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.HolidayRequest;
import com.tricast.controllers.responses.HolidayResponse;
import com.tricast.managers.HolidayManager;
import com.tricast.repositories.entities.Holiday;

@RestController
@RequestMapping(path = "/holidays")
public class HolidayController {

	private static final Logger log = LogManager.getLogger(HolidayController.class);

	private final HolidayManager holidayManager;

	@Autowired
	public HolidayController(HolidayManager holidayManager) {
		this.holidayManager = holidayManager;
	}

	@GetMapping
	public List<HolidayResponse> getHolidays() {
		
		log.trace("Trying to get all holidays");
		
		return holidayManager.getAllHolidays();
	}

	@GetMapping("/{id}")
	public Holiday getById(@PathVariable("id") long id) {
		
		log.trace("Requested to get ID = " + id);
		
		return holidayManager.getById(id);
	}

	@PostMapping
	public HolidayResponse createHoliday(@RequestBody HolidayRequest holiday) throws SQLException {
		
		log.trace("Trying to create new holiday for account #" + holiday.getAccountId());
		
		return holidayManager.createHoliday(holiday);
	}

	@PutMapping
	public HolidayResponse updateHoliday(@RequestBody HolidayRequest holiday) {
		
		log.trace("Trying to update a holiday for account #" + holiday.getAccountId());
		
		return holidayManager.updateHolidayType(holiday.getId(), holiday.getType());
	}

	@DeleteMapping("/{id}")
	public Boolean deleteHoliday(@PathVariable("id") long id) {
		
		log.trace("Trying to delete holiday #" + id);
		
		holidayManager.deleteHoliday(id);
		return true;
	}
}
