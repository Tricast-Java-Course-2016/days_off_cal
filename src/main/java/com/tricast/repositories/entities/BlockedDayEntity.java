package com.tricast.repositories.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "blockeddays")
public class BlockedDayEntity implements Serializable  {

	private static final long serialVersionUID = -6632079768279394060L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day")
    private String day;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "BlockedDayEntity [id=" + id + ", day=" + day + "]";
	}
}
