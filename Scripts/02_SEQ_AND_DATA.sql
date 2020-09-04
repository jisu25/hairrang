SELECT * FROM guest;

--INSERT문

--GUEST
DELETE GUEST;
DROP SEQUENCE GUEST_SEQ;

CREATE SEQUENCE GUEST_SEQ
    START WITH 1
    INCREMENT BY 1
   	nocache;

INSERT INTO GUEST VALUES(0, '비회원', SYSDATE, SYSDATE, '비회원', 2, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '고연정', TO_DATE('1990-02-20', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 1, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '김연지', TO_DATE('1992-09-09', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 1, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '이나라', TO_DATE('1987-06-01', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 2, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '최순호', TO_DATE('1990-10-11', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 2, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '고연정', TO_DATE('1990-02-20', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 1, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '이지수', TO_DATE('1990-02-20', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 1, NULL);

SELECT * FROM GUEST;

--HAIR




DELETE HAIR;
DROP SEQUENCE HAIR_SEQ;

CREATE SEQUENCE HAIR_SEQ
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

INSERT INTO HAIR VALUES (0, '(선택)', 0);
INSERT INTO HAIR VALUES (HAIR_SEQ.NEXTVAL,'커트', 12000);
INSERT INTO HAIR VALUES (HAIR_SEQ.NEXTVAL,'염색', 60000);
INSERT INTO HAIR VALUES (HAIR_SEQ.NEXTVAL,'펌', 80000);

SELECT * FROM HAIR;


--EVENT
DELETE EVENT;
DROP SEQUENCE EVENT_SEQ;

CREATE SEQUENCE EVENT_SEQ
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

INSERT INTO EVENT VALUES (0, '(선택)', 0);
INSERT INTO EVENT VALUES (EVENT_SEQ.NEXTVAL, '가입 쿠폰', 0.2);
INSERT INTO EVENT VALUES (EVENT_SEQ.NEXTVAL, '생일 쿠폰', 0.2);
INSERT INTO EVENT VALUES (EVENT_SEQ.NEXTVAL, '1주년', 0.1);
INSERT INTO EVENT VALUES (EVENT_SEQ.NEXTVAL, '없음', 0);

SELECT * FROM EVENT;


--SALES
DELETE SALES;
DROP SEQUENCE SALES_SEQ;

CREATE SEQUENCE SALES_SEQ
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

INSERT INTO SALES VALUES (SALES_SEQ.NEXTVAL, TO_DATE('2000-08-22','yyyy-mm-dd'),1,1,1, null);
INSERT INTO SALES VALUES (SALES_SEQ.NEXTVAL, TO_DATE('2000-08-26','yyyy-mm-dd'),2,2,2, null);
INSERT INTO SALES VALUES (SALES_SEQ.NEXTVAL, SYSDATE - 4/24, 1, 2, 1, null); -- 현재시각 4시간 전
INSERT INTO SALES VALUES (SALES_SEQ.NEXTVAL, SYSDATE, 2, 2, 1, null); -- 현재시각


SELECT * FROM SALES;



--BOOKING
DELETE BOOKING;
DROP SEQUENCE BOOK_SEQ;

CREATE SEQUENCE BOOK_SEQ
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

INSERT INTO BOOKING VALUES (BOOK_SEQ.NEXTVAL, 1, '고연정', '010-1234-5678', SYSDATE + 1/24, 1, null);
INSERT INTO BOOKING VALUES (BOOK_SEQ.NEXTVAL, 3, '이나라', '010-1234-5678', SYSDATE + 4/24, 2, '30분 늦으실 수도 있음');
INSERT INTO BOOKING VALUES (BOOK_SEQ.NEXTVAL, 2, '김연지', '010-1234-5678', SYSDATE + 30/24/60, 3, '쿠폰 쓰실 듯');

SELECT * FROM BOOKING;
SELECT BOOK_NO, GUEST_NO, BOOK_DAY, HAIR_NO, BOOK_NOTE FROM BOOKING;

-- SALES 기준 조인 (주문 내역 조회)
SELECT * FROM ALL_SALES_VIEWW;


-- 다시 통일해달라~~~!
ALTER TABLE BOOKING RENAME COLUMN BOOK_DATE TO BOOK_DAY;
