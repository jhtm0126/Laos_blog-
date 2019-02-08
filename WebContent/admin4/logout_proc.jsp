<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

 

<%

session.invalidate(); // 모든 세션 변수 삭제
 
response.sendRedirect(root + "/index.jsp");

%>
  