<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.ArrayList" %>

<%@ page import="nation.web.tool.Tool1" %>

<%@ page import="nation.web.url3.UrlVO" %>
<%@ page import="nation.web.url3.UrlDAO" %>
<%@ page import="nation.web.admin4.AdminTool" %>
 
<% 
request.setCharacterEncoding("utf-8"); 
String root = request.getContextPath();

UrlDAO urlDAO = new UrlDAO();
%>
 
