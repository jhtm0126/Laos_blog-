�� /WEB-INF/doc/dbms/uriLog_q.sql
-------------------------------------------------------------------------------------
DROP TABLE uriLog;

CREATE TABLE uriLog(
   urilogno INT NOT NULL AUTO_INCREMENT, -- �Ϸ� ��ȣ
   id          VARCHAR(50) DEFAULT 'guest' NOT NULL, -- ���̵�
   ip          VARCHAR(15)                 NOT NULL, -- 123.456.789.012
   uri         VARCHAR(100)                 NOT NULL, -- URI �ּ�
   uridate    VARCHAR(19)                 NOT NULL, -- ���� ��¥
   PRIMARY KEY(urilogno)
);

INSERT INTO uriLog(id, ip, uri, uridate)
VALUES('user1', '10.0.0.1','/bbs/create_form.jsp', now());

INSERT INTO uriLog(id, ip, uri, uridate)
VALUES('user1', '10.0.0.1','/bbs/create_form.jsp', now());

INSERT INTO uriLog(id, ip, uri, uridate)
VALUES('user1', '10.0.0.1','/bbs/create_form.jsp', now());


SELECT urilogno, id, ip, uri, uridate 
FROM uriLog
ORDER BY urilogno DESC;

 urilogno id    ip       uri                  uridate
 -------- ----- -------- -------------------- -------------------
        3 user1 10.0.0.1 /bbs/create_form.jsp 2017-03-09 16:14:16
        2 user1 10.0.0.1 /bbs/create_form.jsp 2017-03-09 16:14:15
        1 user1 10.0.0.1 /bbs/create_form.jsp 2017-03-09 16:14:14
 
DELETE FROM uriLog;

SELECT urilogno, id, ip, uri, uridate 
FROM uriLog
ORDER BY urilogno DESC;

-------------------------------------------------------------------------------------








