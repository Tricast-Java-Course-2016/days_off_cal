package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Holidays")
public class HolidayEntity implements Serializable  {

	private static final long serialVersionUID = 149087048970724608L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accountid")
    private AccountEntity account;

    @Column(name = "fromday")
    private String fromday;
    
    @Column(name = "today")
    private String today;
    
    @Column(name = "type")
    private Long type;
    
    @Column(name = "actualdaycount")
    private Integer actualdaycount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public String getFromday() {
		return fromday;
	}

	public void setFromday(String fromday) {
		this.fromday = fromday;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Integer getActualdaycount() {
		return actualdaycount;
	}

	public void setActualdaycount(Integer actualdaycount) {
		this.actualdaycount = actualdaycount;
	}

	@Override
	public String toString() {
		return "HolidayEntity [id=" + id + ", account=" + account + ", fromday=" + fromday + ", today=" + today
				+ ", type=" + type + ", actualdaycount=" + actualdaycount + "]";
	}
}
