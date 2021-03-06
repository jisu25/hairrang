/*
CREATE SEQUENCE GUEST_SEQ
    START WITH 1
    INCREMENT BY 1
   	nocache;

DROP SEQUENCE GUEST_SEQ;
*/

--1 여자, 2남자

DELETE GUEST;
DELETE FROM guest WHERE guest_name ='고연정';

SELECT* FROM guest;
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '고연정', TO_DATE('1990-02-20', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 1, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '김연지', TO_DATE('1992-09-09', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 1, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '이나라', TO_DATE('1987-06-01', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 2, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '최순호', TO_DATE('1990-10-11', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 2, NULL);
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, '고연정', TO_DATE('1990-02-20', 'YYYY-MM-DD'), SYSDATE, '010-1234-5678', 1, NULL);


DELETE sales;
SELECT SALES_NO, SALES_DAY, GUEST_NO, EVENT_NO, HAIR_NO FROM SALES;

SELECT * FROM SALES;
INSERT INTO SALES VALUES (1,TO_DATE('2020-08-25','yyyy-mm-dd'),1,1,1);
INSERT INTO SALES VALUES (2,TO_DATE('2020-08-25','yyyy-mm-dd'),1,2,2);
INSERT INTO SALES VALUES (3,TO_DATE('2020-08-25','yyyy-mm-dd'),2,1,1);
INSERT INTO SALES VALUES (4,TO_DATE('2020-08-25','yyyy-mm-dd'),2,2,2);
INSERT INTO SALES VALUES (5,TO_DATE('2020-08-25','yyyy-mm-dd'),3,1,1);
INSERT INTO SALES VALUES (6,TO_DATE('2020-08-25','yyyy-mm-dd'),3,2,2);
INSERT INTO SALES VALUES (7,TO_DATE('2020-08-25','yyyy-mm-dd'),4,1,1);
INSERT INTO SALES VALUES (8,TO_DATE('2020-08-25','yyyy-mm-dd'),4,2,2);
INSERT INTO SALES VALUES (9,TO_DATE('2020-08-25','yyyy-mm-dd'),5,1,1);
INSERT INTO SALES VALUES (10,TO_DATE('2020-08-25','yyyy-mm-dd'),5,2,2);



SELECT HAIR_NO ,HAIR_NAME ,PRICE FROM HAIR;

INSERT INTO HAIR VALUES (1,'커트',3000);
INSERT INTO HAIR VALUES (2,'염색',5000);


SELECT EVENT_NO, EVENT_NAME FROM EVENT;

INSERT INTO EVENT VALUES (1,'생일쿠폰', 0.15);
INSERT INTO EVENT VALUES (2,'1주년', 0.3);



--selectByAll
SELECT GUEST_NO, GUEST_NAME, BIRTHDAY, JOIN_DAY, PHONE, GENDER, GUEST_NOTE FROM GUEST ORDER BY GUEST_NO ;

--selectByName
SELECT  GUEST_NO, GUEST_NAME, BIRTHDAY, JOIN_DAY, PHONE, GENDER, GUEST_NOTE FROM GUEST WHERE GUEST_NAME = '고연정';

--selectByNo
SELECT GUEST_NO, GUEST_NAME, BIRTHDAY, JOIN_DAY, PHONE, GENDER, GUEST_NOTE FROM GUEST WHERE GUEST_NO = 6;

--insert 
INSERT INTO GUEST VALUES(GUEST_SEQ.NEXTVAL, ?,  TO_DATE(?, 'YYYY-MM-DD'), SYSDATE, ?, ?, ?);

--UPDATE 
UPDATE guest SET guest_name = '고연정', birthday ='1991-12-19' , phone = '010-0000-0000', gender = '2', guest_note = '변경' WHERE guest_no = 5;

--searchByName
SELECT GUEST_NO, GUEST_NAME, BIRTHDAY, JOIN_DAY, PHONE, GENDER, GUEST_NOTE FROM GUEST WHERE GUEST_NAME LIKE '%고연%';

--마지막시퀀스조회/다음번호
SELECT GUEST_SEQ.currval FROM DUAL;

SELECT * from USER_SEQUENCES
WHERE SEQUENCE_NAME = 'GUEST_SEQ';

SELECT * FROM booking;

SELECT * FROM SALES s LEFT OUTER JOIN HAIR h ON (s.HAIR_NO = h.HAIR_NO )LEFT OUTER JOIN GUEST g ON (g.GUEST_NO = s.GUEST_NO) LEFT OUTER JOIN EVENT e ON (s.EVENT_NO = e.EVENT_NO) WHERE s.GUEST_NO = 1;

