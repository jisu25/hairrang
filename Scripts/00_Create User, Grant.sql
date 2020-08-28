-- Hairrang 계정 생성
CREATE USER hairrang IDENTIFIED BY rootroot;

-- Hairrang 권한 부여
GRANT CONNECT, DBA, RESOURCE TO HAIRRANG;

-- 현재 접속 계정 조회
SELECT USER
FROM dual;

SELECT *
 FROM DBA_USERS;


-- 필요시 쓰세요. 헤어랑 계정 삭제 및 모든 정보 삭제.
DROP USER HAIRRANG CASCADE;