UPDATE 
	/*=SCHEMA*/ACCOUNTS 
SET 
	REALNAME = :realName
	, PASSWORD = :password
	, DAYSOFFPERYEAR = :daysOffPerYear
	, SICKLEAVEPERYEAR = :sickLeavePerYear
WHERE 
	ID = :id
