package com.tricast.controllers.requests;

import java.io.Serializable;

public class AccountRequest implements Serializable {

	private static final long serialVersionUID = 4417951629931970086L;

	private Long id;
	private String userName;
	private String password;
	private String realName;
	private Integer daysOffPerYear;
	private Integer sickLeavePerYear;

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

	public Integer getDaysOffPerYear() {
		return daysOffPerYear;
	}

	public void setDaysOffPerYear(Integer daysOffPerYear) {
		this.daysOffPerYear = daysOffPerYear;
	}

	public Integer getSickLeavePerYear() {
		return sickLeavePerYear;
	}

	public void setSickLeavePerYear(Integer sickLeavePerYear) {
		this.sickLeavePerYear = sickLeavePerYear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((daysOffPerYear == null) ? 0 : daysOffPerYear.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((realName == null) ? 0 : realName.hashCode());
		result = prime * result + ((sickLeavePerYear == null) ? 0 : sickLeavePerYear.hashCode());
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
		AccountRequest other = (AccountRequest) obj;
		if (daysOffPerYear == null) {
			if (other.daysOffPerYear != null)
				return false;
		} else if (!daysOffPerYear.equals(other.daysOffPerYear))
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
		if (sickLeavePerYear == null) {
			if (other.sickLeavePerYear != null)
				return false;
		} else if (!sickLeavePerYear.equals(other.sickLeavePerYear))
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
		return "AccountRequest [id=" + id + ", userName=" + userName + ", password=" + password + ", realName="
				+ realName + ", daysOffPerYear=" + daysOffPerYear + ", sickLeavePerYear=" + sickLeavePerYear + "]";
	}
}
