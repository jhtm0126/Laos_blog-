<%@ page contentType="text/html; charset=UTF-8" %>

 

<%@ page import="java.util.Enumeration" %>

<%@ page import="java.net.URLEncoder" %>

<%@ page import="nation.web.admin4.AdminTool" %>

 

<%

 

// !: false일 경우 참으로 전환하여 실행

 if (!(AdminTool.isMaster(request))){

  response.sendRedirect(request.getContextPath() + "/admin4/login_ck_form.jsp");

 

  return; // 이부분에서 처리 종료

} 

%>