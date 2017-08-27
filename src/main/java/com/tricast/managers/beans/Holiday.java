package com.tricast.managers.beans;

import java.io.Serializable;

public class Holiday implements Serializable {

	private static final long serialVersionUID = 7441709506155843223L;

	private Long id;
	private Long accountId;
	private String fromDay;
	private String toDay;
	private Integer actualDayCount;
	private HolidayType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getToDay() {
		return toDay;
	}

	public void setToDay(String toDay) {
		this.toDay = toDay;
	}

	public String getFromDay() {
		return fromDay;
	}

	public void setFromDay(String fromDay) {
		this.fromDay = fromDay;
	}

	public Integer getActualDayCount() {
		return actualDayCount;
	}

	public void setActualDayCount(Integer actualDayCount) {
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
		result = prime * result
				+ (int) (actualDayCount ^ (actualDayCount >>> 32));
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
		Holiday other = (Holiday) obj;
		if (accountId != other.accountId)
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
		return "Leave [id=" + id + ", accountId=" + accountId + ", toDay="
				+ toDay + ", fromDay=" + fromDay + ", actualDayCount="
				+ actualDayCount + ", type=" + type + "]";
	}

}
