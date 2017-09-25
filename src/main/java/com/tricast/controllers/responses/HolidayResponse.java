package com.tricast.controllers.responses;

import java.io.Serializable;

import com.tricast.managers.beans.HolidayType;

public class HolidayResponse implements Serializable {

	private static final long serialVersionUID = 7441709506155843223L;

	private long id;
	private long accountId;
	private String accountRealName;
	private String fromDay;
	private String toDay;
	private long actualDayCount;
	private HolidayType type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountRealName() {
		return accountRealName;
	}

	public void setAccountRealName(String accountRealName) {
		this.accountRealName = accountRealName;
	}

	public String getFromDay() {
		return fromDay;
	}

	public void setFromDay(String fromDay) {
		this.fromDay = fromDay;
	}

	public String getToDay() {
		return toDay;
	}

	public void setToDay(String toDay) {
		this.toDay = toDay;
	}

	public long getActualDayCount() {
		return actualDayCount;
	}

	public void setActualDayCount(long actualDayCount) {
		this.actualDayCount = actualDayCount;
	}

	public HolidayType getType() {
		return type;
	}

	public void setType(HolidayType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		result = prime * result + ((accountRealName == null) ? 0 : accountRealName.hashCode());
		result = prime * result + (int) (actualDayCount ^ (actualDayCount >>> 32));
		result = prime * result + ((fromDay == null) ? 0 : fromDay.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((toDay == null) ? 0 : toDay.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		HolidayResponse other = (HolidayResponse) obj;
		if (accountId != other.accountId)
			return false;
		if (accountRealName == null) {
			if (other.accountRealName != null)
				return false;
		} else if (!accountRealName.equals(other.accountRealName))
			return false;
		if (actualDayCount != other.actualDayCount)
			return false;
		if (fromDay == null) {
			if (other.fromDay != null)
				return false;
		} else if (!fromDay.equals(other.fromDay))
			return false;
		if (id != other.id)
			return false;
		if (toDay == null) {
			if (other.toDay != null)
				return false;
		} else if (!toDay.equals(other.toDay))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HolidayResponse [id=" + id + ", accountId=" + accountId + ", accountRealName=" + accountRealName
				+ ", fromDay=" + fromDay + ", toDay=" + toDay + ", actualDayCount=" + actualDayCount + ", type=" + type
				+ "]";
	}
}
