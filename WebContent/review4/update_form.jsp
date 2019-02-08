<%@ page contentType="text/html; charset=UTF-8" %>

 <%@ include file="./ssi.jsp" %>
 <%@ include file="./master.jsp" %>

<% 
    int reviewno = Integer.parseInt(request.getParameter("reviewno"));
    
    ReviewVO reviewVO=reviewProc.read(reviewno);
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
 
  <form name='frm' action='./update_proc.jsp' method='POST'><!-- hidden 태그를 숨김, 값은 다음 파일로 전송됨 -->
    <input type='hidden' name='reviewno' id='reviewno' value='<%=reviewno%>'>
    
   <fieldset class='fieldset_basic'>
      <legend class='legend_basic'>리뷰 수정(*: 필수)</legend>
      <ul>
        <li class='li_none'>
          <label for='title'>관광지: </label>
          <input type='text' name='title' id='title' value='<%=reviewVO.getTitle()%>'
                    style='width: 80%'><!-- name->jsp에서사용 id->javascripte에서 사용 -->
        </li>
        
        <li class='li_none'>
        <div style ='float:left; width:98%'>
              <textarea name='content' id='content' rows='8' style='width: 99%;'><%=reviewVO.getContent() %></textarea>
        </div>
        
        <div style ='float:left; width:2%'>
            *
        </div>
        <div style='clear:both'></div>
          
        </li>
        
        <li class='li_none'>
          <label for='title'>패스워드: </label>
          <input type='password' name='passwd' id='passwd' value='' 
                    style='width: 10%' placeholder="패스워드">*<!-- name->jsp에서사용 id->javascripte에서 사용 -->
        </li>
        
        
      </ul>
    </fieldset>
    
    <DIV class='bottom_menu'>
      <button type='submit'>저장</button>
      <button type='button' onclick="location.href='./list2.jsp'">취소</button>
    </DIV>    
  </form>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>