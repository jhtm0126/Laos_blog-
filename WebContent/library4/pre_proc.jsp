<%@ page contentType="text/html; charset=UTF-8" %> 
 
<%@ include file = "./ssi.jsp"  %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
 
int libraryno = Integer.parseInt(request.getParameter("libraryno"));
LibraryVO libraryVO = libraryProc.pre_list(libraryno, categoryno);// 조회
System.out.println("categoryno:----"+libraryVO.getCategoryno());
System.out.println("libraryno:----"+libraryVO.getLibraryno());
System.out.println("cnts:----"+libraryVO.getCnts());



if(libraryVO.getCnts()>=1){
response.sendRedirect("./read.jsp?categoryno=" + libraryVO.getCategoryno()+"&libraryno="+libraryVO.getLibraryno());
}else{
 response.sendRedirect("./read.jsp?categoryno=" + categoryno+"&libraryno="+libraryno);
}
%>

