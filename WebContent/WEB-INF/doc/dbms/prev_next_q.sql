-- 이전글(◀) 다음글(▶)이 등장하는 조회 페이지는 3번의 SQL 실행이 필요합니다. 
DROP TABLE prenext;
 


CREATE TABLE prenext(
  prenextno INT NOT NULL AUTO_INCREMENT,
  grp          INT NOT NULL,
  title          VARCHAR(100) NOT NULL,
  PRIMARY KEY(prenextno)
);


 
INSERT INTO prenext(grp, title)
VALUES(1, '내일 눈 많이 옵니다.');
 
INSERT INTO prenext(grp, title)
VALUES(1, '크리스 마스 트리 제작');
 
INSERT INTO prenext(grp, title)
VALUES(1, '내일 초강력 강추위 옵니다.');
 
INSERT INTO prenext(grp, title)
VALUES(2, '드디어 따뜻한 봄입니다.');
 
INSERT INTO prenext(grp, title)
VALUES(2, '봄날 벗꽃 여행 갑니다.');
 
INSERT INTO prenext(grp, title)
VALUES(2, '봄 캠핑갑니다.');
 
SELECT prenextno, grp, title 
FROM prenext 
ORDER BY prenextno ASC;


------------------------------------------
select * from library;
 sql = "select seq, title from album where seq=(select max(seq) from album where seq < ?)";
 다음글
	select min(libraryno) as libraryno,categoryno from library where categoryno =1 and libraryno > 10;
	select min(libraryno) as libraryno,count(libraryno)as cnts,categoryno from library where categoryno =1 and libraryno > 14;
	
	select min(libraryno) as libraryno from library where categoryno =? and libraryno >?;

	 
 이전글
 	select max(libraryno) as libraryno,categoryno from library where categoryno =1 and libraryno < 15;
	select max(libraryno) as libraryno from library where categoryno =? and libraryno < ?;
 
 PRENEXTNO GRP TITLE
 --------- --- ---------------
         1   1 내일 눈 많이 옵니다.
         2   1 크리스 마스 트리 제작
         3   1 내일 초강력 강추위 옵니다.
         4   2 드디어 따뜻한 봄입니다.
         5   2 봄날 벗꽃 여행 갑니다.
         6   2 봄 캠핑갑니다.
 
-- 현재글
SELECT prenextno, grp, title
FROM prenext
WHERE prenextno=2;
 
 prenextno grp title
 --------- --- ------------
         2   1 크리스 마스 트리 제작
         
select * from library;
         
          

        
 
-- 이전글
-- 2개의 값이 필요함: grp = 1, prenextno = 2;
-- 현재 PK 컬럼인 prenextno가 2번인 경우 이전글은 1번이 나와야함.
SELECT MAX(prenextno) as prenextno
FROM prenext
WHERE grp = 1 AND prenextno < 2;
 
 PRENEXTNO
 --------------
         1  <-- 현재 2번임으로 정상 출력됨.
 
-- 다음글, 현재 PK 컬럼의 값보다 큰값중에 가장 작은 레코드
SELECT MIN(prenextno) as prenextno
FROM prenext
WHERE grp= 1 AND prenextno > 2;
 
 PRENEXTNO
 ---------
         3
 
-- 현재글: 5번
SELECT prenextno, grp, title
FROM prenext
WHERE prenextno=5;
 
 prenextno grp title
 --------- --- -------------
         5   2 봄날 벗꽃 여행 갑니다.
 
 
-- 이전글
-- 2개의 값이 필요함: grp = 1, prenextno = 2;
SELECT MAX(prenextno) as prenextno
FROM prenext
WHERE grp = 2 AND prenextno < 5;
 
 prenextno
 ---------
         4
 
-- 다음글
SELECT MIN(prenextno) as prenextno
FROM prenext
WHERE grp = 2 AND prenextno > 5;
 
 prenextno
 ---------
         6
         