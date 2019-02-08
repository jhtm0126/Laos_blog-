<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<%
String upDir = application.getRealPath("/library4/storage");
 
int categoryno = Integer.parseInt(request.getParameter("categoryno_del"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
int count = 0;



    int libraryno = Integer.parseInt(request.getParameter("libraryno_del"));

    
  
 
      LibraryVO libraryVO = libraryProc.read(libraryno);// 삭제할 파일 정보 읽기
      
     // Tool1.deleteFile(upDir, pds4VO.getFstor1());       // 기존 파일 삭제
      Tool1.deleteFile(upDir, libraryVO.getThumb());      // 기존 파일 삭제
      
      count = libraryProc.delete_file1(libraryno); // DBMS 파일 정보 삭제 처리
      
  %>   