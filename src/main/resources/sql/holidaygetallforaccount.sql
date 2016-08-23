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
	AND FROMDAY::int > date_part('year', current_timestamp) * 10000