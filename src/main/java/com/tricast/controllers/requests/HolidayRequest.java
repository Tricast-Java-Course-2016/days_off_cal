package com.tricast.controllers.requests;

import java.io.Serializable;

import com.tricast.managers.beans.HolidayType;

import lombok.Data;

@Data 
public class HolidayRequest implements Serializable {

	private static final long serialVersionUID = 7315468738416533833L;

	private Long id;
	private Long accountId;
	private String fromDay;
	private String toDay;
	private HolidayType type;
}
