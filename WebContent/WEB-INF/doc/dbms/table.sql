CREATE TABLE category (
categoryno  INT              NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '카테고리번호',
title            VARCHAR(30) NOT NULL COMMENT '게시판 제목',
seqno         INT              DEFAULT 0 NOT NULL COMMENT '출력 순서',
visible         CHAR(1)        DEFAULT 'Y'      NOT NULL COMMENT '출력 선택',
ids             VARCHAR(200) DEFAULT 'admin' NOT NULL COMMENT '접근 계정',
cnt             INT              DEFAULT 0        NOT NULL COMMENT '등록된 자료수',
rdate                       DATETIME     NOT NULL COMMENT '등록날짜'
) COMMENT='카테고리';
 
2) 테이블 삭제
DROP TABLE category;

INSERT INTO category(title, seqno, visible, ids, cnt,rdate)
VALUES('관광지', 1, 'Y', 'guest', 0,now());
INSERT INTO category(title, seqno, visible, ids, cnt,rdate)
VALUES('먹거리', 2, 'Y', 'guest', 0,now());
INSERT INTO category(title, seqno, visible, ids, cnt,rdate)
VALUES('숙소', 3, 'Y', 'guest', 0,now());

SELECT categoryno, title, seqno, visible, ids, cnt,rdate
FROM category
ORDER BY categoryno ASC;



drop table library;

CREATE TABLE library ( 
  libraryno      INT           NOT NULL AUTO_INCREMENT COMMENT '글 일련 번호', 
  categoryno INT           NOT NULL COMMENT '카테고리번호',
  writer      VARCHAR(20)   NOT NULL COMMENT '글쓴이',
  spot      VARCHAR(200)  NOT NULL COMMENT '장소명', 
  content    VARCHAR(4000) NOT NULL COMMENT '글 내용', 
  pw     VARCHAR(15)   NOT NULL COMMENT '글 일련 번호', 
  hits       INT           DEFAULT 0 NOT NULL COMMENT '조회수', 
  rdate      DATETIME      NOT NULL COMMENT '등록 날짜', 
  site        VARCHAR(100)      NULL COMMENT 'URL',
  file_name      VARCHAR(100)      NULL COMMENT '파일명',
  fstor1     VARCHAR(100)      NULL COMMENT '실제 저장 파일명',
  thumb      VARCHAR(100)      NULL COMMENT 'preview',
  size      BIGINT        DEFAULT 0 NOT NULL COMMENT '파일 크기 1',
  map_info        VARCHAR(1024)     NULL COMMENT '지도 정보',
  youtube    VARCHAR(512)      NULL COMMENT 'Google Youtube',
  video        VARCHAR(50)       NULL COMMENT 'MP4 영상',  
  visible    CHAR(1)       DEFAULT 'Y' NOT NULL COMMENT '숨기기',  
  PRIMARY KEY (libraryno),
  FOREIGN KEY (categoryno) REFERENCES category(categoryno)
) COMMENT='자료실'; 
---------------------------------------------------------------------------------------------------
INSERT INTO library(categoryno, writer,spot,content, pw,
                  hits, rdate,site, file_name, fstor1, thumb, size,
                  map_info, youtube,video, visible)
 VALUES(1, '아로미', '방비엥 ', '시크릿라쿤', '123',
           0, now(), 'http://art.incheon.go.kr', 'spring.jpg', 'spring01.jpg', 'spring_m.jpg', 1000,
           'daum map', 'http://', 'movie.mp4', 'Y');    
           
--------------------------------------------------------------------------------------------------------          
 select  categoryno, writer,spot,content, pw,
                  hits, rdate,site, file_name, fstor1, thumb, size,
                  map_info, youtube,video, visible
 from library;
 
 delete from library;