package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tricast.repositories.entities.embded.Username;

@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = -3370075957769037307L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private Username userName;

	@Column(name = "password")
	private String password;

	@Column(name = "realname")
	private String realName;

	@Column(name = "daysoffperyear")
	private Integer daysOffPerYear;

	@Column(name = "sickleaveperyear")
	private Integer sickLeavePerYear;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Username getUserName() {
		return userName;
	}

	public void setUserName(Username userName) {
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
		Account other = (Account) obj;
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
		return "Account [id=" + id + ", userName=" + userName + ", password=" + password + ", realName=" + realName
				+ ", daysOffPerYear=" + daysOffPerYear + ", sickLeavePerYear=" + sickLeavePerYear + "]";
	}
}
