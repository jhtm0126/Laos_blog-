<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>

<jsp:useBean id="reviewVO" class="nation.web.review4.ReviewVO" /> 
<jsp:setProperty name="reviewVO" property="*" />
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 
  <fieldset style='width: 70%; margin: 10px auto;'>
    <legend class='legend_basic'>리뷰 수정</legend>
      <ul>
      <%
     
        int count =0;
        count = reviewProc.passwordCheck(reviewVO.getReviewno(), reviewVO.getPasswd());
        if(count == 1){
         count =reviewProc.update(reviewVO);
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
  </fieldset>
  
  <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='./update_form.jsp?reviewno=<%=reviewVO.getReviewno() %>'">다시 수정</button>
    <button type='button' onclick="location.href='./list.jsp'">목록</button>
  </DIV>
  
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>
    
    
 