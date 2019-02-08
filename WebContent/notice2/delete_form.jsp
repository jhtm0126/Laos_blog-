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
int urlno = Integer.parseInt(request.getParameter("urlno"));
String title = "";
String address = "";
 
try {
   // memory로 클래스를 읽어옴, 객체는 생성하지 않음.
  con = new DBOpen().getConnection(); // MySQL 연결
  sql = new StringBuffer();
  
  sql.append(" select urlno, title, address, rdate");
  sql.append(" from url");
  sql.append(" where urlno =?");
  
  pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
  pstmt.setInt(1, urlno);
  
  
  rs = pstmt.executeQuery();
 
 
 
  if (rs.next()) {
    title = rs.getString("title");
    address = rs.getString("address");
  }
 
} catch (Exception e) {
  e.printStackTrace();
} finally {
  new DBClose().close(con, pstmt, rs);
}
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>즐겨찾기</title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 
  <form name='frm' action='./delete_proc.jsp' method='POST'>
    <input type='hidden' name='urlno' id='urlno' value='<%=urlno%>'>
    
    <fieldset style='width: 70%; margin: 10px auto;'>
      <legend>즐겨찾기 삭제</legend>
      <ul>
        <li class='li_none'>
            타이틀: <%=title %>
        </li>
        <li class='li_none'>
            주소: <%=address %>
        </li>
        <li class='li_none' style="margin: 20px auto; text-align: center;">
          <span style='color: #FF0000; font-weight: bold;'>삭제를 진행하면 복구 할 수 없습니다.</span><br>
          삭제하시겠습니까?
        </li>
      </ul>

      <DIV class='bottom_menu'>
        <button type='submit'>삭제</button>
        <button type='button' onclick="location.href='./list.jsp'">취소</button>
      </DIV>  
    </fieldset>
 
  </form>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>