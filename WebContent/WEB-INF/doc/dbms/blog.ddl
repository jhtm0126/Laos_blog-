DROP TABLE IF EXISTS category;

/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE category(
		categoryno                INT(10)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT COMMENT 'ī�װ���ȣ',
		title                        CHARACTER(30)		 NOT NULL COMMENT '�Խ�������',
		seqno                      INT(10)		 DEFAULT 0		 NOT NULL COMMENT '��¼���',
		visible                     CHAR(1)		 DEFAULT 'Y'		 NULL  COMMENT '��¼���',
		ids                         VARCHAR(200)		 NOT NULL COMMENT '���ٰ���',
		cnt                         INT(10)		 DEFAULT 0		 NOT NULL COMMENT '��ϵ� �ڷ��',
		rdate                     	DATETIME		 NOT NULL COMMENT '��ϳ�¥'
) COMMENT='ī�װ�';

