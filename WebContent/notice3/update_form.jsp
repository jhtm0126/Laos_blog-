<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file ='./ssi.jsp' %>
<%@ include file="./master.jsp" %> 

 
<%
// 수정할 레코드의 pk 컬럼의 값
int urlno = Integer.parseInt(request.getParameter("urlno"));
UrlVO urlVO=urlDAO.read(urlno);
 
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
          <input type='text' name='title' id='title' value='<%=urlVO.getTitle() %>' 
                    style='width: 80%'>
        </li>
        <li class='li_none'>
          <label for='rname'>주소: </label>
          <input type='text' name='address' id='address' value='<%=urlVO.getAddress() %>' 
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