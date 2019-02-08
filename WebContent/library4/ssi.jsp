<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.ArrayList" %>

<%@ page import="org.apache.commons.fileupload.FileItem" %>  


<%@ page import="nation.web.tool.Tool1" %>
<%@ page import="nation.web.tool.Upload" %>

<%@ page import="nation.web.admin4.AdminTool" %>

<%@ page import="nation.web.category4.CategoryVO" %>
<%@ page import="nation.web.category4.CategoryProc" %>


<%@ page import="nation.web.library4.LibraryVO" %>
<%@ page import="nation.web.library4.LibraryProc" %>



 
<% 
request.setCharacterEncoding("utf-8"); 
String root = request.getContextPath();

LibraryProc libraryProc = new LibraryProc();
CategoryProc categoryProc = new CategoryProc();



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


 
