<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>


 <%
int reviewno = Integer.parseInt(request.getParameter("reviewno"));
String passwd = request.getParameter("passwd");
%>

 
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
    <legend>리뷰삭제</legend>
      <ul>
     <%
      int count =0;
      count = reviewProc.passwordCheck(reviewno,passwd);
      if(count == 1){
        count =reviewProc.delete(reviewno);
           if(count == 1){
           response.sendRedirect("./list2.jsp");
           }else {
       %>  
           <li class='li_none'>방명록 수정에 실패했습니다.</li>
           <li class='li_none'>다시한번 시도해주세요.</li>
      <%  
     }
   }else{//패스워드 불일치
   %>
      <li class='li_none'>패스워드가 일치하지 않습니다.</li>
      <li class='li_none'>다시한번 시도해주세요.</li>
   <%
   }
   %>
      </ul>

    <DIV class='bottom_menu'>
      <button type='button' onclick="history.back();">다시 시도</button>
      <button type='button' onclick="location.href='./list2.jsp'">목록</button>
    </DIV>
  </fieldset>
 
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>