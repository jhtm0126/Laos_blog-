<%@ page contentType="text/html; charset=UTF-8" %>
 
 <%@ include file ='./ssi.jsp' %>

 
<%
int loginno = Integer.parseInt(request.getParameter("loginno"));
LoginVO loginVO=loginProc.read(loginno);
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
 
  <form name='frm' action='./list_visit_del.jsp' method='POST'>
    <input type='hidden' name='loginno' id='urlno' value='<%=loginno%>'>
    
    <fieldset style='width: 70%; margin: 10px auto;'>
      <legend>방문내역 삭제</legend>
      <ul>
        <li class='li_none'>
            Email: <%= loginVO.getName() %>
        </li>
        <li class='li_none'>
            IP: <%=loginVO.getIp() %>
        </li>
        <li class='li_none' style="margin: 20px auto; text-align: center;">
          <span style='color: #FF0000; font-weight: bold;'>삭제를 진행하면 복구 할 수 없습니다.</span><br>
          삭제하시겠습니까?
        </li>
      </ul>

      <DIV class='bottom_menu'>
        <button type='submit'>삭제</button>
        <button type='button' onclick="location.href='./list_visit.jsp'">취소</button>
      </DIV>  
    </fieldset>
 
  </form>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>