<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>

<%@ page import="nation.web.tool.DBOpen" %>
<%@ page import="nation.web.tool.DBClose" %>
 
<%
Connection con = null;              // DBMS 연결   
PreparedStatement pstmt = null; // SQL 실행
ResultSet rs = null;                   // SELECT 결과를 저장
StringBuffer sql = null;              // SQL 저장
int count = 0;                          // 처리된 레코드 갯수
 

%>
 
<%
// 수정할 레코드의 pk 컬럼의 값

int urlno = Integer.parseInt(request.getParameter("urlno"));
String title = "";
String address = "";

try {
 
  con =new DBOpen().getConnection();
  sql = new StringBuffer();
  
  sql.append("  select urlno,title,address,rdate;");
  sql.append(" from url");
  sql.append(" order by urlno desc");
  sql.append(" limit ?");
  
  pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
  pstmt.setInt(1, count);
  
  
  rs = pstmt.executeQuery();

  if (rs.next()) {
    title = rs.getString("title");
    address = rs.getString("address");
  }
  
} catch (Exception e) {
  e.printStackTrace();
} finally {
  new DBClose().close(con, pstmt,rs);
}


%>