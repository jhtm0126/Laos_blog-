<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
<%@ include file="./master.jsp" %>
 
<%
 int reviewno = Integer.parseInt(request.getParameter("reviewno"));
 ReviewVO reviewVO = reviewProc.read(reviewno);
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>리뷰 삭제</title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 
  <form name='frm' action='./delete_proc.jsp' method='POST'>
    <input type='hidden' name='reviewno' value='<%=reviewno %>'>
    
    <fieldset class='fieldset_basic'>
      <legend class='legend_basic'>리뷰 삭제(*: 필수)</legend>
      <ul>
        <li class='li_none'>
            관광지: <%=reviewVO.getTitle() %>
        </li>
        <li class='li_none'>
            리뷰: <div style='padding-left:16px; font-size:0.9em;'>
      <%=Tool1.convertChar(reviewVO.getContent()) %>
      </div>
        </li>
        <li class='li_none' style="margin: 20px auto; text-align: center;">
          <div style ='line-height:35px;'>
          <span style='color: #FF0000; font-weight: bold;'>삭제를 진행하면 복구 할 수 없습니다.</span><br>
          삭제하시겠습니까?<br>
          패스워드:
          <input type='password' name='passwd' id='passwd' value='' style='width: 10%;' placeholder="패스워드">*
          <br>
          </div>
        </li>
      </ul>

      <DIV class='bottom_menu'>
        <button type='submit'>삭제</button>
        <button type='button' onclick="location.href='./list2.jsp'">취소</button>
      </DIV>  
    </fieldset>
 
  </form>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>