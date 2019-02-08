--개인 블로그 laos 즐겨찾기 --
--테이블명:laos
--번호, 제목,주소,등록날짜  
create table url(
    urlno int              not null auto_increment,
    title      varchar(100)  not null,
    address   varchar(100)    not null,
    rdate     DATETIME     not null,
    primary key(urlno)
    );
    
    -- 등록
 insert into url(title,address,rdate) 
 values('지나랑 라라랑','https://blog.naver.com/jinarara/221354393873',now()); 
 
 insert into url(title,address,rdate) 
 values('돌거북','http://blog.daum.net/dolgeobook/22',now());
 
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
 values('방비엥','너무 좋아요','1234',now());
 
  insert into review(title,content,passwd,rdate) 
 values('루앙프라방','너무 좋아요','1234',now());
 
  select reviewno,title,content,rdate from review;
 
  DROP TABLE review;
  
  
   9)공지사항 등록
   
   SELECT reviewno, content, passwd, rdate
   FROM review 
   ORDER BY reviewno DESC
   LIMIT 5;
   
   ---------------------------------------------------------
   drop table activity;
drop table p_activity;
   
   액티비티 종류(부모)
   CREATE TABLE p_activity(
 p_activityno INT          NOT NULL AUTO_INCREMENT, 
  name      VARCHAR(100) NOT NULL, 

  PRIMARY KEY(p_activityno) 
);
 
액티비티 가격 및 세부사항(자식)
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
-- 부모테이블에 등록, PK insert
INSERT INTO p_activity(name) 
VALUES('동굴큐빙');

INSERT INTO p_activity(name) 
VALUES('짚라인');

INSERT INTO p_activity(name) 
VALUES('카약킹');
 
-- FK가 선언된 자식 테이블에 insert
INSERT INTO activity(p_activityno, name, price,good,rdate)
VALUES(1, '동굴큐빙', 5000 , '★★★',now());

INSERT INTO activity(p_activityno, name, price,good,rdate)
VALUES(2, '짚라인', 6000 , '★★★★',now());

INSERT INTO activity(p_activityno, name, price,good,rdate)
VALUES(3, '카약킹', 7000 , '★★',now());

SELECT activityno,p_activityno,name, price,good, SUBSTRING(rdate, 1, 10) as rdate
FROM activity
ORDER BY activityno ASC;

 
-- 자식테이블에서 부모테이블 url2cate 테이블의 url2cateno 컬럼이 사용되는 레코드 삭제
DELETE FROM activity
WHERE activityno = 1;
 
-- 부모테이블 레코드 삭제
DELETE FROM p_activity
WHERE p_activityno = 1;
 
    
    