package com.tricast.beans;

public enum HolidayType {
	
	DAYOFF(1), 
	SICKLEAVE(2);
	
	private long typeId;
	
	private HolidayType(long typeId)
	{
		this.typeId = typeId;
	}

	public long getTypeId() {
		return typeId;
	}

	public static HolidayType getType(long id)
	{
		return id == 1 ? DAYOFF : SICKLEAVE;
	}
	
	
	
}
