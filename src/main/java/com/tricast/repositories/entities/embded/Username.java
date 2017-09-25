package com.tricast.repositories.entities.embded;

import javax.persistence.Embeddable;

import org.springframework.util.StringUtils;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Username other = (Username) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return username;
	}
}
