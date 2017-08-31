package com.tricast.controllers.requests;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 1870713627900030216L;
	
	private String userName;
	private String password;
}
