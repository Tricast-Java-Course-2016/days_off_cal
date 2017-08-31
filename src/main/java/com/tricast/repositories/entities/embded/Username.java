package com.tricast.repositories.entities.embded;

import javax.persistence.Embeddable;

import org.springframework.util.StringUtils;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class Username {

	private final String username;
	
	public Username() {
		this.username = null;
	}
	
	public Username(String username) {

		if (!StringUtils.hasText(username) || StringUtils.containsWhitespace(username)) {
			throw new IllegalArgumentException("Invalid username!");
		}

		this.username = username;
	}
	
	@Override
	public String toString() {
		return username;
	}
}
