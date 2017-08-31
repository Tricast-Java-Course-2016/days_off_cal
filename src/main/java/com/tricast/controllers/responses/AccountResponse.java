package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tricast.managers.beans.HolidayType;
import com.tricast.repositories.entities.Holiday;

import lombok.Data;

@Data
public class AccountResponse implements Serializable {

	private static final long serialVersionUID = 6467389830725640940L;

	private Long id;
	private String userName;
	private String password;
	private String realName;
	private long daysOffPerYear;
	private long sickLeavePerYear;
	private List<Holiday> holidays = new ArrayList<>();
	private long holidaysLeft;
	private long sickLeavesLeft;

	public long getHolidaysLeft() {
		long left = this.daysOffPerYear;
		for (Holiday holiday : this.holidays) {
			if (holiday.getType() == HolidayType.DAYOFF.getTypeId()) {
				left -= holiday.getActualDayCount();
			}
		}
		holidaysLeft = left;
		return holidaysLeft;
	}

	public long getSickLeavesLeft() {
		long left = this.sickLeavePerYear;
		for (Holiday holiday : this.holidays) {
			if (holiday.getType() == HolidayType.SICKLEAVE.getTypeId()) {
				left -= holiday.getActualDayCount();
			}
		}
		sickLeavesLeft = left;
		return sickLeavesLeft;
	}
}
