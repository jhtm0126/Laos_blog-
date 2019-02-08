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
  <DIV class='title_box'>즐겨찾기</DIV>
  
   <DIV class='sub_menu'>
    <button type='button' onclick="location.href='./create_form.jsp'">추가</button>
   </DIV>
    
  <TABLE class='table_basic'>
    <colgroup>
      <col style='width: 5%;'>
      <col style='width: 50%;'>
      <col style='width: 12%;'>
      <col style='width: 18%;'>
      <col style='width: 15%;'>
    </colgroup>
    <thead>
      <TR>
        <TH class='th_basic' style='border-left:none;'>NO</TH>
        <TH class='th_basic'>타이틀</TH>
        <TH class='th_basic'>주소</TH>
        <TH class='th_basic'>등록일</TH>
        <TH class='th_basic' style='border-right:none;'>기타</TH>
      </TR>
    </thead>
    <tbody>
      <%
      try {
         // memory로 클래스를 읽어옴, 객체는 생성하지 않음.
        con = new DBOpen().getConnection(); // MySQL 연결
        sql = new StringBuffer();
        
        sql.append(" select urlno,title,address,rdate");
        sql.append(" from url");
        sql.append(" order by urlno desc");
        
 
   
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        rs = pstmt.executeQuery(); // SELECT SQL 실행
   
        // 최초 호출시 첫번째 레코드로 이동하며 그후 호출부터 다음 레코드로 자동 이동
        while(rs.next()) {
        %>
          <tr>
                <td class='td_basic'><%=rs.getInt("urlno") %></td>
                <td class='td_left'><%=rs.getString("title") %></td>
                <td class='td_basic'><A href ="<%=rs.getString("address") %>">[링크 바로가기]</A></td>
                <td class='td_basic'><%=rs.getString("rdate").substring(0,16) %></td>
                <td class='td_basic'> 
                <A href ='./update_form.jsp?urlno=<%=rs.getInt("urlno")%>'>수정</A> /<A href ='./delete_form.jsp?urlno=<%=rs.getInt("urlno")%>'>삭제</A></td>
          </tr>
        <%  
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        new DBClose().close(con, pstmt, rs);
      }
      %>
    </tbody>
  </TABLE>
 
   <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='./create_form.jsp'">등록</button>
   </DIV>

  
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>
  
  