SELECT * FROM  GUEST;

SELECT * FROM  EVENT;

SELECT * FROM HAIR;

SELECT * FROM booking;

SELECT * FROM SALES;

SELECT * FROM SALES_DETAIL;

SELECT SALES_NO, SALES_DAY , SALES_TIME , s.GUEST_NO , s.EVENT_NO
	FROM SALES s JOIN GUEST g ON (g.GUEST_NO = s.GUEST_NO ) 
	JOIN EVENT e ON (s.EVENT_NO = e.EVENT_NO );
	

USING (guest_no)
SELECT * FROM SALES WHERE SALES_NO = 2;

UPDATE SALES 
	SET SALES_DAY = TO_DATE('2001-01-01','yyyy-mm-dd'),
		SALES_TIME  = TO_DATE('11:11','HH24:Mi'),
		GUEST_NO = 1,
		EVENT_NO = 1
	WHERE SALES_NO =3;

DELETE SALES WHERE SALES_NO =4;

<<<<<<< HEAD
SELECT DETAIL_NO , s.*,h.*,g.*,e.*
	FROM SALES_DETAIL sd 
		JOIN SALES s ON (sd.SALES_NO = s.SALES_NO ) 
			JOIN HAIR h ON (sd.HAIR_NO = h.HAIR_NO )
				JOIN GUEST g ON (g.GUEST_NO = s.GUEST_NO )
					JOIN EVENT e ON (s.EVENT_NO = e.EVENT_NO );
	
				
-------------------- 수정!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
SELECT * FROM SALES;
				
SELECT * FROM SALES s 
	LEFT OUTER JOIN HAIR h USING (HAIR_NO)
	LEFT OUTER JOIN GUEST g USING (GUEST_NO) 
	LEFT OUTER JOIN EVENT e USING (EVENT_NO);

SELECT * FROM SALES s JOIN HAIR h ON (s.HAIR_NO = h.HAIR_NO ) JOIN GUEST g ON (g.GUEST_NO = s.GUEST_NO) JOIN EVENT e ON (s.EVENT_NO = e.EVENT_NO);


	WHERE GUEST_NO = 2;
			
			


=======
INSERT INTO guest
VALUES(1, '이지수', TO_DATE('1994-03-15', 'YYYY-MM-DD'), SYSDATE, '010-7423-3200', 2, NULL);
>>>>>>> branch 'master' of https://github.com/DaeguIT-MinSuKim/yi_java4st_1team.git

INSERT INTO HAIR
VALUES(1, '컷트', 10000);

INSERT INTO BOOKING
values(BOOKING_SEQ.NEXTVAL, 1, SYSDATE, 1, '10분 늦을 수도 있다고 하심');


UPDATE BOOKING SET GUEST_NO = 1, BOOK_DAY = SYSDATE, HAIR_NO = 1, BOOK_NOTE = '30분 전후' WHERE BOOK_NO = 7; 


SELECT GUEST_NO, GUEST_NAME, BIRTHDAY, JOIN_DAY, PHONE, GENDER, GUEST_NOTE FROM GUEST WHERE GUEST_NO != 0 ORDER BY GUEST_NO ;

SELECT * FROM BOOKING WHERE TO_CHAR(BOOK_DAY, 'yyyy-mm-dd') = TO_CHAR(SYSDATE, 'yyyy-mm-dd') ORDER BY BOOK_DAY;