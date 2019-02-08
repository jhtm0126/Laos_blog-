<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
int libraryno = Integer.parseInt(request.getParameter("libraryno"));
String visible = request.getParameter("visible");
 
libraryProc.show_hide(libraryno, visible);
 
response.sendRedirect("./list_category_table2.jsp?categoryno=" + categoryno);
%>
 
 