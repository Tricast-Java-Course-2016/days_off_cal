DO $$
BEGIN
    IF EXISTS
    (SELECT 
      schema_name
    FROM 
      information_schema.schemata 
    WHERE 
      schema_name = 'calendar')
    THEN
      DROP SCHEMA calendar CASCADE; 
    END IF; 

    CREATE SCHEMA IF NOT EXISTS CALENDAR;

	CREATE TABLE calendar.accounts
	  ( 
	  id SERIAL NOT NULL, 
	  username VARCHAR(30) NOT NULL, 
	  password VARCHAR(60) NOT NULL, 
	  realname VARCHAR(120) NOT NULL, 
	  daysoffperyear INTEGER NOT NULL, 
	  sickleaveperyear INTEGER NOT NULL,
	  CONSTRAINT ACCOUNTS_PK PRIMARY KEY (id)
	  );
  
	CREATE TABLE CALENDAR.BLOCKEDDAYS 
	   (	
	    id SERIAL NOT NULL, 
	    day VARCHAR(8)
	   );
	   
	CREATE TABLE CALENDAR.HOLIDAYS 
	   (	
	    id SERIAL NOT NULL, 
	    accountid INTEGER NOT NULL, 
	    fromday VARCHAR(8) NOT NULL, 
	    today VARCHAR(8) NOT NULL, 
	    type INTEGER NOT NULL, 
	  	actualdaycount INTEGER,
	    CONSTRAINT HOLIDAY_PK PRIMARY KEY (id),
		  CONSTRAINT HOLIDAYS_ACCOUNTS_FK1 FOREIGN KEY (accountid)
		  REFERENCES CALENDAR.accounts(id)
	   ); 

END$$;
   