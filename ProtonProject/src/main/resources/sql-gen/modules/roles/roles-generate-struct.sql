CREATE TABLE APP_USER (
	  USER_ID BIGINT not null GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 100 MAXVALUE 9999999999999 CACHE 1 ),
	  USER_NAME VARCHAR(36) not null,
	  ENCRYTED_PASSWORD VARCHAR(128) not null,
	  ENABLED Int not null, 
	  first_name varchar(120), 
	  surname varchar(120), 
	  is_first_time int 
) ;  
ALTER TABLE APP_USER
add constraint APP_USER_PK primary key (USER_ID);   
ALTER TABLE APP_USER
add constraint APP_USER_UK unique (USER_NAME); 

CREATE TABLE APP_ROLE (   
	ROLE_ID   BIGINT not null GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 100 MAXVALUE 9999999999999 CACHE 1 ),
	ROLE_NAME VARCHAR(30) not null 
) ;  
ALTER TABLE APP_ROLE
add constraint APP_ROLE_PK primary key (ROLE_ID);   ALTER TABLE APP_ROLE
add constraint APP_ROLE_UK unique (ROLE_NAME);
					
CREATE TABLE USER_ROLE (
	id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 100 MAXVALUE 999999999 CACHE 1 ),
	USER_ID BIGINT not null,   
	ROLE_ID BIGINT not null 
); 
ALTER TABLE USER_ROLE   
add constraint USER_ROLE_PK primary key (ID);  
ALTER TABLE USER_ROLE   
add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);
ALTER TABLE USER_ROLE   
add constraint USER_ROLE_FK1 foreign key (USER_ID)
references APP_USER (USER_ID);   
ALTER TABLE USER_ROLE
add constraint USER_ROLE_FK2 foreign key (ROLE_ID)
references APP_ROLE (ROLE_ID);        
					-- Used by Spring Remember Me API.   
					
CREATE TABLE Persistent_Logins (  
	username varchar(64) not null,     
	series varchar(64) not null,
	token varchar(64) not null,     
	last_used timestamp not null,
	PRIMARY KEY (series)       
);   