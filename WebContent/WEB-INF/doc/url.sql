--���� ��α� laos ���ã�� --
--���̺��:laos
--��ȣ, ����,�ּ�,��ϳ�¥  
create table url(
    urlno int              not null auto_increment,
    title      varchar(100)  not null,
    address   varchar(100)    not null,
    rdate     DATETIME     not null,
    primary key(urlno)
    );
    
    -- ���
 insert into url(title,address,rdate) 
 values('������ ����','https://blog.naver.com/jinarara/221354393873',now()); 
 
 insert into url(title,address,rdate) 
 values('���ź�','http://blog.daum.net/dolgeobook/22',now());
 
 select urlno,title,address,rdate from url;
 
 
 
 
 -----------------------------------------

 
 create table review(
    reviewno int              not null auto_increment,
    title      varchar(100)  not null,
    content   varchar(3000)    not null,
    passwd   VARCHAR(15)    NOT NULL,
    rdate     DATETIME     not null,
    primary key(reviewno)
    );
    
 insert into review(title,content,passwd,rdate) 
 values('���','�ʹ� ���ƿ�','1234',now());
 
  insert into review(title,content,passwd,rdate) 
 values('��������','�ʹ� ���ƿ�','1234',now());
 
  select reviewno,title,content,rdate from review;
 
  DROP TABLE review;
  
  
   9)�������� ���
   
   SELECT reviewno, content, passwd, rdate
   FROM review 
   ORDER BY reviewno DESC
   LIMIT 5;
   
   ---------------------------------------------------------
   drop table activity;
drop table p_activity;
   
   ��Ƽ��Ƽ ����(�θ�)
   CREATE TABLE p_activity(
 p_activityno INT          NOT NULL AUTO_INCREMENT, 
  name      VARCHAR(100) NOT NULL, 

  PRIMARY KEY(p_activityno) 
);
 
��Ƽ��Ƽ ���� �� ���λ���(�ڽ�)
CREATE TABLE activity (
  activityno INT          NOT NULL AUTO_INCREMENT, 
  p_activityno INT      NOT NULL,
  name VARCHAR(30) NOT NULL,
  price INT      NOT NULL, 
  good   VARCHAR(20) NOT NULL,
  rdate  DATETIME     NOT NULL, 
  PRIMARY KEY(activityno),
  FOREIGN KEY(p_activityno) REFERENCES p_activity(p_activityno)
);

drop table activity;
drop table p_activity;
-- �θ����̺� ���, PK insert
INSERT INTO p_activity(name) 
VALUES('����ť��');

INSERT INTO p_activity(name) 
VALUES('¤����');

INSERT INTO p_activity(name) 
VALUES('ī��ŷ');
 
-- FK�� ����� �ڽ� ���̺� insert
INSERT INTO activity(p_activityno, name, price,good,rdate)
VALUES(1, '����ť��', 5000 , '�ڡڡ�',now());

INSERT INTO activity(p_activityno, name, price,good,rdate)
VALUES(2, '¤����', 6000 , '�ڡڡڡ�',now());

INSERT INTO activity(p_activityno, name, price,good,rdate)
VALUES(3, 'ī��ŷ', 7000 , '�ڡ�',now());

SELECT activityno,p_activityno,name, price,good, SUBSTRING(rdate, 1, 10) as rdate
FROM activity
ORDER BY activityno ASC;

 
-- �ڽ����̺��� �θ����̺� url2cate ���̺��� url2cateno �÷��� ���Ǵ� ���ڵ� ����
DELETE FROM activity
WHERE activityno = 1;
 
-- �θ����̺� ���ڵ� ����
DELETE FROM p_activity
WHERE p_activityno = 1;
 
    
    