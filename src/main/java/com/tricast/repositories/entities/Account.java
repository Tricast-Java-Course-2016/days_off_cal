package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = -3370075957769037307L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

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
	public String toString() {
		return "AccountEntity [id=" + id + ", userName=" + userName + ", password=" + password + ", realname="
				+ realName + ", daysoffperyear=" + daysOffPerYear + ", sickleaveperyear=" + sickLeavePerYear + "]";
	}
}
