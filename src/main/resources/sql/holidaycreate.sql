INSERT INTO 
	/*=SCHEMA*/HOLIDAYS(
		ID
		, ACCOUNTID
		, FROMDAY
		, TODAY
		, TYPE
		, ACTUALDAYCOUNT
	) 
VALUES 
	(
		NEXTVAL('/*=SCHEMA*/SEQ_HOLIDAYS')
		, :accountId
		, :fromDay
		, :toDay
		, :type
		, :actualDayCount
	)