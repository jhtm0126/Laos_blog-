<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<jsp:useBean id="reviewVO" class="nation.web.review4.ReviewVO" /> 
<jsp:setProperty name="reviewVO" property="*" />
 
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>리뷰</title>
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
      
       int count = reviewProc.create(reviewVO);
        
        if (count == 1) {

           response.sendRedirect("./list2.jsp");
        
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
    <button type='button' onclick="location.href='./list2.jsp'">목록</button>
  </DIV>
  
  </fieldset>
  

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>