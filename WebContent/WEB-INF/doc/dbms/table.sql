CREATE TABLE category (
categoryno  INT              NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ī�װ���ȣ',
title            VARCHAR(30) NOT NULL COMMENT '�Խ��� ����',
seqno         INT              DEFAULT 0 NOT NULL COMMENT '��� ����',
visible         CHAR(1)        DEFAULT 'Y'      NOT NULL COMMENT '��� ����',
ids             VARCHAR(200) DEFAULT 'admin' NOT NULL COMMENT '���� ����',
cnt             INT              DEFAULT 0        NOT NULL COMMENT '��ϵ� �ڷ��',
rdate                       DATETIME     NOT NULL COMMENT '��ϳ�¥'
) COMMENT='ī�װ�';
 
2) ���̺� ����
DROP TABLE category;

INSERT INTO category(title, seqno, visible, ids, cnt,rdate)
VALUES('������', 1, 'Y', 'guest', 0,now());
INSERT INTO category(title, seqno, visible, ids, cnt,rdate)
VALUES('�԰Ÿ�', 2, 'Y', 'guest', 0,now());
INSERT INTO category(title, seqno, visible, ids, cnt,rdate)
VALUES('����', 3, 'Y', 'guest', 0,now());

SELECT categoryno, title, seqno, visible, ids, cnt,rdate
FROM category
ORDER BY categoryno ASC;



drop table library;

CREATE TABLE library ( 
  libraryno      INT           NOT NULL AUTO_INCREMENT COMMENT '�� �Ϸ� ��ȣ', 
  categoryno INT           NOT NULL COMMENT 'ī�װ���ȣ',
  writer      VARCHAR(20)   NOT NULL COMMENT '�۾���',
  spot      VARCHAR(200)  NOT NULL COMMENT '��Ҹ�', 
  content    VARCHAR(4000) NOT NULL COMMENT '�� ����', 
  pw     VARCHAR(15)   NOT NULL COMMENT '�� �Ϸ� ��ȣ', 
  hits       INT           DEFAULT 0 NOT NULL COMMENT '��ȸ��', 
  rdate      DATETIME      NOT NULL COMMENT '��� ��¥', 
  site        VARCHAR(100)      NULL COMMENT 'URL',
  file_name      VARCHAR(100)      NULL COMMENT '���ϸ�',
  fstor1     VARCHAR(100)      NULL COMMENT '���� ���� ���ϸ�',
  thumb      VARCHAR(100)      NULL COMMENT 'preview',
  size      BIGINT        DEFAULT 0 NOT NULL COMMENT '���� ũ�� 1',
  map_info        VARCHAR(1024)     NULL COMMENT '���� ����',
  youtube    VARCHAR(512)      NULL COMMENT 'Google Youtube',
  video        VARCHAR(50)       NULL COMMENT 'MP4 ����',  
  visible    CHAR(1)       DEFAULT 'Y' NOT NULL COMMENT '�����',  
  PRIMARY KEY (libraryno),
  FOREIGN KEY (categoryno) REFERENCES category(categoryno)
) COMMENT='�ڷ��'; 
---------------------------------------------------------------------------------------------------
INSERT INTO library(categoryno, writer,spot,content, pw,
                  hits, rdate,site, file_name, fstor1, thumb, size,
                  map_info, youtube,video, visible)
 VALUES(1, '�Ʒι�', '��� ', '��ũ������', '123',
           0, now(), 'http://art.incheon.go.kr', 'spring.jpg', 'spring01.jpg', 'spring_m.jpg', 1000,
           'daum map', 'http://', 'movie.mp4', 'Y');    
           
--------------------------------------------------------------------------------------------------------          
 select  categoryno, writer,spot,content, pw,
                  hits, rdate,site, file_name, fstor1, thumb, size,
                  map_info, youtube,video, visible
 from library;
 
 delete from library;