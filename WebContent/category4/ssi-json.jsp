<%@ page contentType="text/plain; charset=UTF-8" %> 
 
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
 
<%@ page import="org.json.simple.*" %>
 
<%@ page import="nation.web.tool.*" %>
<%@ page import="nation.web.category4.*" %>
 
<% 
request.setCharacterEncoding("UTF-8"); 
String root = request.getContextPath();
%>
 
<%
CategoryProc categoryProc = new CategoryProc();
%>