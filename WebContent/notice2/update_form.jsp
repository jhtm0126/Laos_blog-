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
  
  sql.append(" select urlno,title,address,rdate");
  sql.append(" from url");
  sql.append(" where urlno=?");
  
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
  new DBClose().close(con, pstmt,rs);
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
 
  <form name='frm' action='./update_proc.jsp' method='POST'><!-- hidden 태그를 숨김, 값은 다음 파일로 전송됨 -->
    <input type='hidden' name='urlno' id='urlno' value='<%=urlno%>'>
    
    <fieldset style='width: 70%; margin: 10px auto;'>
      <legend class='legend_basic'>즐겨찾기 수정</legend>
      <ul>
        <li class='li_none'>
          <label for='title'>타이틀: </label>
          <input type='text' name='title' id='title' value='<%=title %>' 
                    style='width: 80%'>
        </li>
        <li class='li_none'>
          <label for='rname'>주소: </label>
          <input type='text' name='address' id='address' value='<%=address %>' 
                    style='width: 80%'>
        </li>
      </ul>
    </fieldset>
    
    <DIV class='bottom_menu'>
      <button type='submit'>저장</button>
      <button type='button' onclick="location.href='./list.jsp'">취소</button>
    </DIV>    
  </form>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>