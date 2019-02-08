<%@ page contentType="text/html; charset=UTF-8" %>
 
 <%@ include file ='./ssi.jsp' %>
 
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
 
  <fieldset style='width: 70%; margin: 10px auto;'>
    <legend>즐겨찾기 삭제</legend>
      <ul>
      <%
      int urlno = Integer.parseInt(request.getParameter("urlno"));
      int count = urlDAO.delete(urlno);
 
        if (count == 1) {
        %>  
          <li class='li_none'>삭제 처리에 성공했습니다.</li>
        <%  
        } else {
        %>  
          <li class='li_none'>공지사항 삭제에 실패했습니다.</li>
          <li class='li_none'>다시한번 시도해주세요.</li>
        <%  
        }
        
  
      %>
      </ul>

    <DIV class='bottom_menu'>
      <button type='button' onclick="location.href='./list.jsp'">목록</button>
    </DIV>
  </fieldset>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>