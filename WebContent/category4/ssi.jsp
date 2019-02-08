<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="nation.web.tool.Tool1" %>

<%@ page import="nation.web.category4.CategoryProc" %>
<%@ page import="nation.web.category4.CategoryVO" %>

<%@ page import="nation.web.library4.LibraryProc" %>

<% 
String root = request.getContextPath();
request.setCharacterEncoding("utf-8"); 

DecimalFormat df = new DecimalFormat((char)65510 + " #,###,### " + (char)50896);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String date = sdf.format(new Date());
%>

<%
CategoryProc categoryProc = new CategoryProc();
LibraryProc libraryProc = new LibraryProc();
%>
 
 