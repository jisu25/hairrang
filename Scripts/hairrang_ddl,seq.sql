CREATE TABLE Guest(
GUEST_NO	NUMBER(10) PRIMARY KEY,
GUEST_NAME	VARCHAR2(20) NOT NULL,
BIRTHDAY	DATE,
JOIN_DAY	DATE,
PHONE		VARCHAR2(13),
GENDER		NUMBER(1),
GUEST_NOTE	VARCHAR2(500)
);

CREATE TABLE SALES(
	SALES_NO	NUMBER(10) PRIMARY KEY,
	SALES_DAY	DATE,
	SALES_TIME	DATE,
	GUEST_NO	NUMBER(10),
	EVENT_NO	NUMBER(10)
);


SELECT *
FROM USER_CONSTRAINTS;

CREATE TABLE SALES_DETAIL(
	 DETAIL_NO	NUMBER(10) PRIMARY KEY,
	 SALES_NO	NUMBER(10)	NOT NULL,
	 HAIR_NO	NUMBER(10)
);


CREATE TABLE HAIR(
	HAIR_NO	NUMBER(10) PRIMARY KEY,
	HAIR_NAME	VARCHAR2(20) NOT NULL,
	PRICE	NUMBER	NOT NULL
);


CREATE TABLE EVENT(
	EVENT_NO	NUMBER(10) PRIMARY KEY,
	EVENT_NAME	VARCHAR2(20) NOT NULL,
	SALE	NUMBER(3, 2) NOT NULL CONSTRAINT EVENT_SALE_CK CHECK (SALE BETWEEN 0 AND 1)
);

DROP TABLE EVENT;


CREATE TABLE BOOKING(
	GUEST_NO	NUMBER(10),
	BOOK_DAY	DATE,
	BOOK_TIME	DATE,
	HAIR_NO		NUMBER(10),
	BOOK_NOTE	VARCHAR2(500)
);

ALTER TABLE SALES
ADD CONSTRAINT SALES_GUESTNO_FK FOREIGN KEY (GUEST_NO) REFERENCES GUEST(GUEST_NO);

ALTER TABLE SALES
ADD CONSTRAINT SALES_EVENTNO_FK FOREIGN KEY (EVENT_NO) REFERENCES EVENT(EVENT_NO);

ALTER TABLE SALES_DETAIL
ADD CONSTRAINT SALDE_EVENTNO_FK FOREIGN KEY (SALES_NO) REFERENCES SALES(SALES_NO);

ALTER TABLE SALES_DETAIL
ADD CONSTRAINT SALDE_HAIRNO_FK FOREIGN KEY (HAIR_NO) REFERENCES HAIR(HAIR_NO);


ALTER TABLE BOOKING
ADD CONSTRAINT BOOK_GUESTNO_FK FOREIGN KEY (GUEST_NO) REFERENCES GUEST(GUEST_NO);

ALTER TABLE BOOKING
ADD CONSTRAINT BOOK_HAIRNO_FK FOREIGN KEY (HAIR_NO) REFERENCES HAIR(HAIR_NO);



---------------------------------------------------------------------------------------------------------------------------------------------------------


CREATE SEQUENCE GUEST_SEQ
    START WITH 1
    INCREMENT BY 1;

DROP SEQUENCE GUEST_SEQ;

INSERT INTO GUEST
VALUES(GUEST_SEQ.NEXTVAL, '김혜진', TO_DATE('1900-01-02', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 2, NULL);
	
DELETE FROM guest WHERE guest_name = '김혜진';
SELECT *
FROM GUEST;

DELETE GUEST;

INSERT INTO GUEST
VALUES(GUEST_SEQ.NEXTVAL, '김대훈', TO_DATE('1900-12-01', 'YYYY-MM-DD'), SYSDATE, '010-4321-8765', 1, NULL);

--GUEST완

CREATE SEQUENCE SALDETAIL_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE SEQUENCE sales_seq
	START WITH 1
	INCREMENT BY 1;


--INSERT INTO SALES 
--value(sales_seq.nextval, to_date('2020-08-01', 'YYYY-MM-DD'), to_date('2020');



-- sales detail, sales 시퀀스 완





CREATE SEQUENCE EVENT_SEQ
	START WITH 1
	INCREMENT BY 1;
	
CREATE SEQUENCE BOOKING_SEQ
	START WITH 1
	INCREMENT BY 1;