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
public class AccountEntity implements Serializable {

	private static final long serialVersionUID = -3370075957769037307L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "realname")
    private String realname;

    @Column(name = "daysoffperyear")
    private Integer daysoffperyear;

    @Column(name = "sickleaveperyear")
    private Integer sickleaveperyear;
    
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getDaysoffperyear() {
		return daysoffperyear;
	}

	public void setDaysoffperyear(Integer daysoffperyear) {
		this.daysoffperyear = daysoffperyear;
	}

	public Integer getSickleaveperyear() {
		return sickleaveperyear;
	}

	public void setSickleaveperyear(Integer sickleaveperyear) {
		this.sickleaveperyear = sickleaveperyear;
	}

	@Override
	public String toString() {
		return "AccountEntity [id=" + id + ", userName=" + userName + ", password=" + password + ", realname="
				+ realname + ", daysoffperyear=" + daysoffperyear + ", sickleaveperyear=" + sickleaveperyear + "]";
	}
}
