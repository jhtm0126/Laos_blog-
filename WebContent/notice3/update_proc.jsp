<%@ page contentType="text/html; charset=UTF-8" %>
 

 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>즐겨찾기</title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
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
    <legend class='legend_basic'>즐겨찾기 수정</legend>
      <ul>
      <%
      int urlno = Integer.parseInt(request.getParameter("urlno"));
      String title = request.getParameter("title");
      String address = request.getParameter("address");
      
      UrlVO urlVO = new UrlVO();
      urlVO.setUrlno(urlno);
      urlVO.setTitle(title);
      urlVO.setAddress(address);
      
      int count = urlDAO.update(urlVO);
      
        if (count == 1) {
        %>  
          <li class='li_none'>수정 처리에 성공했습니다.</li>
        <%  
        } else {
        %>  
          <li class='li_none'>공지사항 수정에 실패했습니다.</li>
          <li class='li_none'>다시한번 시도해주세요.</li>
        <%  
        }
        
   
      %>
      </ul>
  </fieldset>
  
  <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='./update_form.jsp?urlno=<%=urlVO.getUrlno() %>'">다시 수정</button>
    <button type='button' onclick="location.href='./list.jsp'">목록</button>
  </DIV>
  
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>