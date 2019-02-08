<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./ssi.jsp" %>
 
<%
String email = request.getParameter("email");
String passwd = request.getParameter("passwd");
%>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>로그인</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head> 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 
  <fieldset class='fieldset_no_line'>
    <ul>
      <li class='li_center'>
        <% 
        int count = adminProc.login(email, passwd);
        // System.out.println("--> count: " + count);
         String act = adminProc.read(email).getAct(); // 권한 
         if (count == 1){

           session.setAttribute("email", email); // session에 로그인 정보 저장
           session.setAttribute("passwd", passwd);
           session.setAttribute("act", act);

             

           response.sendRedirect(root +"/index.jsp");
         }else{

           if ("MY".indexOf(act)>=0) {
           %>

             로그인 아이디와 패스워드가 일치하지 않습니다.<br>
             확인후 다시 로그인해주세요.<br>

           <%  

           } else if ("N".equals(act)) {
           %>

             현재 계정이 사용 불가합니다.<br>
             관리자에게 문의해주세요.<br>

           <%  

           } else if ("H".equals(act)) {

           %>

             현재 계정이 확인중에있습니다.<br>
             관리자에게 문의해주세요.<br>

           <%  

           } else if ("C".equals(act)) {

           %>

             탈퇴 회원으로 사용이 불가합니다.<br>
             관리자에게 문의해주세요.<br>

           <%  

           }

         }

         %> 
      </li>
      <li class='li_center'>
      <%
      if (count == 0){
      %>
        <button type='button' onclick="history.back();">로그인</button>
        <button type='button' onclick="location.href='../index.jsp';">홈페이지</button>
      <%
      }
      %>
      </li>
    </ul>
  </fieldset>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 
 