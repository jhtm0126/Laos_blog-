*****************************/

/* Table Name: �α��� ���� */

/**********************************/
drop table login;
CREATE TABLE login(

  loginno                      INT NOT NULL AUTO_INCREMENT, 
  admin4no                       int not null,
  name                       VARCHAR(50) ,
  ip                           VARCHAR(100) ,
  logindate                      DATETIME NOT NULL,
  PRIMARY KEY (loginno),
  FOREIGN KEY (admin4no) REFERENCES admin4(admin4no)
);
delete from login;
 select * from login;

select admin4no from admin4 where email
 

COMMENT ON TABLE login is '�α��� ����';

COMMENT ON COLUMN login.loginno is '�α��� ��ȣ';

COMMENT ON COLUMN login.mno is 'ȸ�� ��ȣ';

COMMENT ON COLUMN login.ip is '���� IP';

COMMENT ON COLUMN login.logindate is '�α��� ��¥';
