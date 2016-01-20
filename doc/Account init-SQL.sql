CREATE SEQUENCE Account_seq;

CREATE TABLE Account(
	ID BIGINT DEFAULT NEXTVAL('Account_seq') PRIMARY KEY,
	USER_ACC CHARACTER VARYING(20),
	USER_PWD CHARACTER VARYING(20)
);

INSERT INTO Account(USER_ACC, 
	USER_PWD)
VALUES
('Billy','123'),
('Ebi','123' ),
('Sophia','123' ),
('Mdoing','123' ),
('Khalil','123' ),
('Rhys','123' );

SELECT * FROM Account;

DROP TABLE Account;
DROP SEQUENCE Account_seq;
