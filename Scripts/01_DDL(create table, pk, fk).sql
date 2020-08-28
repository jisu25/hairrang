-- 테이블 삭제

/* 고객 */
DROP TABLE hairrang.GUEST 
	CASCADE CONSTRAINTS;

/* 이벤트 */
DROP TABLE hairrang.EVENT 
	CASCADE CONSTRAINTS;

/* 헤어 */
DROP TABLE hairrang.HAIR 
	CASCADE CONSTRAINTS;

/* 주문 */
DROP TABLE hairrang.SALES 
	CASCADE CONSTRAINTS;

/* 예약 */
DROP TABLE hairrang.BOOKING 
	CASCADE CONSTRAINTS;



-- 테이블 생성

/* 고객 */
CREATE TABLE hairrang.GUEST (
	guest_no NUMBER(10) PRIMARY KEY, /* 고객번호 */
	guest_name VARCHAR2(20) NOT NULL, /* 고객명 */
	birthday DATE, /* 생년월일 */
	join_day DATE NOT NULL, /* 가입일자 */
	phone VARCHAR2(13) NOT NULL, /* 전화번호 */
	gender NUMBER(1) NOT NULL, /* 성별 */
	guest_note VARCHAR2(500) /* 고객비고 */
);

/* 헤어 */
CREATE TABLE hairrang.HAIR (
	hair_no NUMBER(10) PRIMARY KEY, /* 헤어번호 */
	hair_name VARCHAR2(20) NOT NULL, /* 헤어명 */
	price NUMBER NOT NULL /* 단가 */
);


/* 이벤트 */
CREATE TABLE hairrang.EVENT (
	event_no NUMBER(10) PRIMARY KEY, /* 이벤트번호 */
	event_name VARCHAR2(20) NOT NULL, /* 이벤트명 */
	sale NUMBER(3, 2) NOT NULL CONSTRAINT EVENT_SALE_CK CHECK (SALE BETWEEN 0 AND 1) /* 할인율 */
);


/* 주문 */
CREATE TABLE hairrang.SALES (
	sales_no NUMBER(10) PRIMARY KEY, /* 영업번호 */
	sales_day DATE NOT NULL, /* 영업일자 */
	guest_no NUMBER(10), /* 고객번호 */
	event_no NUMBER(10), /* 이벤트번호 */
	hair_no NUMBER(10) /* 헤어번호 */
);


/* 예약 */
CREATE TABLE hairrang.BOOKING (
	book_no NUMBER(10) PRIMARY KEY, /* 예약번호 */
	guest_no NUMBER(10), /* 고객번호 */
	book_date DATE NOT NULL, /* 예약 */
	hair_no NUMBER(10), /* 헤어번호 */
	book_note VARCHAR2(500) /* 예약비고 */
);



-- FK 추가

ALTER TABLE hairrang.SALES
	ADD
		CONSTRAINT FK_GUEST_TO_SALES
		FOREIGN KEY (
			guest_no
		)
		REFERENCES hairrang.GUEST (
			guest_no
		);

ALTER TABLE hairrang.SALES
	ADD
		CONSTRAINT FK_EVENT_TO_SALES
		FOREIGN KEY (
			event_no
		)
		REFERENCES hairrang.EVENT (
			event_no
		);

ALTER TABLE hairrang.SALES
	ADD
		CONSTRAINT FK_HAIR_TO_SALES
		FOREIGN KEY (
			hair_no
		)
		REFERENCES hairrang.HAIR (
			hair_no
		);

ALTER TABLE hairrang.BOOKING
	ADD
		CONSTRAINT FK_GUEST_TO_BOOKING
		FOREIGN KEY (
			guest_no
		)
		REFERENCES hairrang.GUEST (
			guest_no
		);

ALTER TABLE hairrang.BOOKING
	ADD
		CONSTRAINT FK_HAIR_TO_BOOKING
		FOREIGN KEY (
			hair_no
		)
		REFERENCES hairrang.HAIR (
			hair_no
		);
		
	
	
-- VIEW 생성
	
SELECT * FROM USER_TABLES;

CREATE OR REPLACE VIEW ALL_SALES_VIEW
AS
SELECT SALES_NO, GUEST_NO, HAIR_NO, EVENT_NO, SALES_DAY, GUEST_NAME, BIRTHDAY, JOIN_DAY, PHONE, GENDER, GUEST_NOTE, HAIR_NAME, PRICE, EVENT_NAME, SALE FROM SALES
	LEFT OUTER JOIN HAIR USING (HAIR_NO ) 
	LEFT OUTER JOIN GUEST USING(GUEST_NO) 
	LEFT OUTER JOIN EVENT USING (EVENT_NO);

SELECT * FROM ALL_SALES_VIEW;



CREATE OR REPLACE VIEW ALL_BOOKING_VIEW
AS
SELECT BOOK_NO, BOOK_DATE, GUEST_NO, GUEST_NAME, BIRTHDAY, JOIN_DAY, PHONE, GENDER, GUEST_NOTE, HAIR_NO, HAIR_NAME, PRICE, BOOK_NOTE
	FROM BOOKING
	LEFT OUTER JOIN HAIR USING (HAIR_NO) 
	LEFT OUTER JOIN GUEST USING (GUEST_NO) 
	
SELECT * FROM ALL_BOOKING_VIEW;


CREATE OR REPLACE VIEW TODAY_SALES_COUNT_VIEW
AS
SELECT count(*) AS TODAY_COUNT
FROM SALES
WHERE TO_CHAR(SALES_DAY, 'yyyy-mm-dd') = TO_CHAR(SYSDATE, 'yyyy-mm-dd');

SELECT * FROM TODAY_SALES_COUNT_VIEW;