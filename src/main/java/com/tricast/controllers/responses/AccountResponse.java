package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tricast.managers.beans.HolidayType;
import com.tricast.repositories.entities.Holiday;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public long getDaysOffPerYear() {
		return daysOffPerYear;
	}

	public void setDaysOffPerYear(long daysOffPerYear) {
		this.daysOffPerYear = daysOffPerYear;
	}

	public long getSickLeavePerYear() {
		return sickLeavePerYear;
	}

	public void setSickLeavePerYear(long sickLeavePerYear) {
		this.sickLeavePerYear = sickLeavePerYear;
	}

	public List<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

	public void setHolidaysLeft(long holidaysLeft) {
		this.holidaysLeft = holidaysLeft;
	}

	public void setSickLeavesLeft(long sickLeavesLeft) {
		this.sickLeavesLeft = sickLeavesLeft;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (daysOffPerYear ^ (daysOffPerYear >>> 32));
		result = prime * result + ((holidays == null) ? 0 : holidays.hashCode());
		result = prime * result + (int) (holidaysLeft ^ (holidaysLeft >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + (int) (sickLeavePerYear ^ (sickLeavePerYear >>> 32));
		result = prime * result + (int) (sickLeavesLeft ^ (sickLeavesLeft >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountResponse other = (AccountResponse) obj;
		if (daysOffPerYear != other.daysOffPerYear)
			return false;
		if (holidays == null) {
			if (other.holidays != null)
				return false;
		} else if (!holidays.equals(other.holidays))
			return false;
		if (holidaysLeft != other.holidaysLeft)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (realName == null) {
			if (other.realName != null)
				return false;
		} else if (!realName.equals(other.realName))
			return false;
		if (sickLeavePerYear != other.sickLeavePerYear)
			return false;
		if (sickLeavesLeft != other.sickLeavesLeft)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountResponse [id=" + id + ", userName=" + userName + ", password=" + password + ", realName="
				+ realName + ", daysOffPerYear=" + daysOffPerYear + ", sickLeavePerYear=" + sickLeavePerYear
				+ ", holidays=" + holidays + ", holidaysLeft=" + holidaysLeft + ", sickLeavesLeft=" + sickLeavesLeft
				+ "]";
	}
}
