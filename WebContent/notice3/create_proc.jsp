<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>


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
    <legend class='legend_basic'>즐겨찾기 추가</legend>
      <ul>
      <%
      String title = request.getParameter("title");
      String address = request.getParameter("address");
      
      int count = 0;                          // 처리된 레코드 갯수
      
      UrlVO urlVO = new UrlVO();
      urlVO.setTitle(title);
      urlVO.setAddress(address);
       
       count=urlDAO.create(urlVO);
  
 
      

 
        
        if (count == 1) {
        %>  
          <li class='li_none'>등록 처리에 성공했습니다.</li>
        <%  
        } else {
        %>  
          <li class='li_none'>공지사항 등록에 실패했습니다.</li>
          <li class='li_none'>다시한번 시도해주세요.</li>
        <%  
        }
        
      %>
      </ul>
      
        <DIV style='margin: 30px auto; text-align: center; background-color: #FFFFFF'>
    <button type='button' onclick="location.href='./create_form.jsp'">계속 추가</button>
    <button type='button' onclick="location.href='./list.jsp'">목록</button>
  </DIV>
  
  </fieldset>
  

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>