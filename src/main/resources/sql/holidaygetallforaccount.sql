SELECT 
	ID
	, ACCOUNTID
	, FROMDAY
	, TODAY
	, TYPE
	, ACTUALDAYCOUNT 
FROM 
	/*=SCHEMA*/HOLIDAYS 
WHERE 
	ACCOUNTID=:accountId 
	AND CAST(FROMDAY AS integer) > date_part('year', current_timestamp) * 10000