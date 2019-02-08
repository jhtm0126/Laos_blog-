-- ������(��) ������(��)�� �����ϴ� ��ȸ �������� 3���� SQL ������ �ʿ��մϴ�. 
DROP TABLE prenext;
 


CREATE TABLE prenext(
  prenextno INT NOT NULL AUTO_INCREMENT,
  grp          INT NOT NULL,
  title          VARCHAR(100) NOT NULL,
  PRIMARY KEY(prenextno)
);


 
INSERT INTO prenext(grp, title)
VALUES(1, '���� �� ���� �ɴϴ�.');
 
INSERT INTO prenext(grp, title)
VALUES(1, 'ũ���� ���� Ʈ�� ����');
 
INSERT INTO prenext(grp, title)
VALUES(1, '���� �ʰ��� ������ �ɴϴ�.');
 
INSERT INTO prenext(grp, title)
VALUES(2, '���� ������ ���Դϴ�.');
 
INSERT INTO prenext(grp, title)
VALUES(2, '���� ���� ���� ���ϴ�.');
 
INSERT INTO prenext(grp, title)
VALUES(2, '�� ķ�ΰ��ϴ�.');
 
SELECT prenextno, grp, title 
FROM prenext 
ORDER BY prenextno ASC;


------------------------------------------
select * from library;
 sql = "select seq, title from album where seq=(select max(seq) from album where seq < ?)";
 ������
	select min(libraryno) as libraryno,categoryno from library where categoryno =1 and libraryno > 10;
	select min(libraryno) as libraryno,count(libraryno)as cnts,categoryno from library where categoryno =1 and libraryno > 14;
	
	select min(libraryno) as libraryno from library where categoryno =? and libraryno >?;

	 
 ������
 	select max(libraryno) as libraryno,categoryno from library where categoryno =1 and libraryno < 15;
	select max(libraryno) as libraryno from library where categoryno =? and libraryno < ?;
 
 PRENEXTNO GRP TITLE
 --------- --- ---------------
         1   1 ���� �� ���� �ɴϴ�.
         2   1 ũ���� ���� Ʈ�� ����
         3   1 ���� �ʰ��� ������ �ɴϴ�.
         4   2 ���� ������ ���Դϴ�.
         5   2 ���� ���� ���� ���ϴ�.
         6   2 �� ķ�ΰ��ϴ�.
 
-- �����
SELECT prenextno, grp, title
FROM prenext
WHERE prenextno=2;
 
 prenextno grp title
 --------- --- ------------
         2   1 ũ���� ���� Ʈ�� ����
         
select * from library;
         
          

        
 
-- ������
-- 2���� ���� �ʿ���: grp = 1, prenextno = 2;
-- ���� PK �÷��� prenextno�� 2���� ��� �������� 1���� ���;���.
SELECT MAX(prenextno) as prenextno
FROM prenext
WHERE grp = 1 AND prenextno < 2;
 
 PRENEXTNO
 --------------
         1  <-- ���� 2�������� ���� ��µ�.
 
-- ������, ���� PK �÷��� ������ ū���߿� ���� ���� ���ڵ�
SELECT MIN(prenextno) as prenextno
FROM prenext
WHERE grp= 1 AND prenextno > 2;
 
 PRENEXTNO
 ---------
         3
 
-- �����: 5��
SELECT prenextno, grp, title
FROM prenext
WHERE prenextno=5;
 
 prenextno grp title
 --------- --- -------------
         5   2 ���� ���� ���� ���ϴ�.
 
 
-- ������
-- 2���� ���� �ʿ���: grp = 1, prenextno = 2;
SELECT MAX(prenextno) as prenextno
FROM prenext
WHERE grp = 2 AND prenextno < 5;
 
 prenextno
 ---------
         4
 
-- ������
SELECT MIN(prenextno) as prenextno
FROM prenext
WHERE grp = 2 AND prenextno > 5;
 
 prenextno
 ---------
         6
         