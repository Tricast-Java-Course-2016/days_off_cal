package com.tricast.controllers.requests;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountRequest implements Serializable {

	private static final long serialVersionUID = 4417951629931970086L;
	
	private Long id;
    private String userName;
    private String password;
    private String realName;
    private Integer daysOffPerYear;
    private Integer sickLeavePerYear;
}
