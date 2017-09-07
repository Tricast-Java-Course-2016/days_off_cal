package com.tricast.controllers.requests;

import java.io.Serializable;

import com.tricast.managers.beans.HolidayType;

public class HolidayRequest implements Serializable {

	private static final long serialVersionUID = 7315468738416533833L;

	private Long id;
	private Long accountId;
	private String fromDay;
	private String toDay;
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
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((fromDay == null) ? 0 : fromDay.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		HolidayRequest other = (HolidayRequest) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (fromDay == null) {
			if (other.fromDay != null)
				return false;
		} else if (!fromDay.equals(other.fromDay))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "HolidayRequest [id=" + id + ", accountId=" + accountId + ", fromDay=" + fromDay + ", toDay=" + toDay
				+ ", type=" + type + "]";
	}
}
