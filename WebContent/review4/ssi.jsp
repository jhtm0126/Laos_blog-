<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.ArrayList" %>

<%@ page import="nation.web.tool.Tool1" %>

<%@ page import="nation.web.review4.ReviewVO" %>
<%@ page import="nation.web.review4.ReviewProc" %>
 <%@ page import="nation.web.admin4.AdminTool" %>
 
<% 
request.setCharacterEncoding("utf-8"); 
String root = request.getContextPath();

ReviewProc reviewProc = new ReviewProc();
%>
 
