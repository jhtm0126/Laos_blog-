*****************************/

/* Table Name: 로그인 내역 */

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
 

COMMENT ON TABLE login is '로그인 내역';

COMMENT ON COLUMN login.loginno is '로그인 번호';

COMMENT ON COLUMN login.mno is '회원 번호';

COMMENT ON COLUMN login.ip is '접속 IP';

COMMENT ON COLUMN login.logindate is '로그인 날짜';
