<%@ page contentType="text/plain; charset=UTF-8" %> 
 
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
 
<%@ page import="org.json.simple.*" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>  
 
<%@ page import="nation.web.tool.*" %>
<%@ page import="nation.web.category4.*" %>
<%@ page import="nation.web.library4.*" %>
 
<% 
request.setCharacterEncoding("UTF-8"); 
String root = request.getContextPath();

CategoryProc categoryProc = new CategoryProc();
LibraryProc libraryProc = new LibraryProc();

String col=Tool1.checkNull(request.getParameter("col"));
String word=Tool1.checkNull(request.getParameter("word"));


//페이지당 출력할 레코드 갯수
int recordPerPage =  0;
if (request.getParameter("recordPerPage") == null) {
recordPerPage = 4;
} else {
recordPerPage = Integer.parseInt(request.getParameter("recordPerPage"));
}

//현재 페이지
//현재 페이지
int nowPage =  0;
if (request.getParameter("nowPage") == null) {
nowPage = 1; // 현재 페이지 1부터 시작
} else {
nowPage = Integer.parseInt(request.getParameter("nowPage"));
}

//목록 파일
String listFile = "";
if (request.getParameter("listFile") == null) {
listFile = "list.jsp";
} else {
listFile = request.getParameter("listFile");
};
%>