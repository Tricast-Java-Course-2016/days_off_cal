package com.tricast.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tricast.repositories.entities.embded.Username;

import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = -3370075957769037307L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
}
