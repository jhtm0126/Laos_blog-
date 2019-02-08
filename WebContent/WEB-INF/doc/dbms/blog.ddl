DROP TABLE IF EXISTS category;

/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE category(
		categoryno                INT(10)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT '카테고리번호',
		title                        CHARACTER(30)		 NOT NULL COMMENT '게시판제목',
		seqno                      INT(10)		 DEFAULT 0		 NOT NULL COMMENT '출력순서',
		visible                     CHAR(1)		 DEFAULT 'Y'		 NULL  COMMENT '출력선택',
		ids                         VARCHAR(200)		 NOT NULL COMMENT '접근계정',
		cnt                         INT(10)		 DEFAULT 0		 NOT NULL COMMENT '등록된 자료수',
		rdate                     	DATETIME		 NOT NULL COMMENT '등록날짜'
) COMMENT='카테고리';

