INSERT INTO 
	/*=SCHEMA*/ACCOUNTS(
		ID, 
		USERNAME, 
		REALNAME, 
		PASSWORD, 
		DAYSOFFPERYEAR, 
		SICKLEAVEPERYEAR
		) 
VALUES (
		NEXTVAL('/*=SCHEMA*/SEQ_ACCOUNTS')
		, :userName
		, :realName
		, :password
		, :daysOffPerYear
		, :sickLeavePerYear
		)
