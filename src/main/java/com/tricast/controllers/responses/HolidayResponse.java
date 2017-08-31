package com.tricast.controllers.responses;

import java.io.Serializable;

import com.tricast.managers.beans.HolidayType;

import lombok.Data;

@Data
public class HolidayResponse implements Serializable {

	private static final long serialVersionUID = 7441709506155843223L;

	private long id;
	private long accountId;
	private String accountRealName;
	private String fromDay;
	private String toDay;
	private long actualDayCount;
	private HolidayType type;
}
