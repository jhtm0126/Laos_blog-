<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>

<%

    String upDir = application.getRealPath("/library4/storage");
    
    int categoryno = Integer.parseInt(request.getParameter("categoryno"));
 
    CategoryVO categoryVO = categoryProc.read(categoryno);
    String category_title = categoryVO.getTitle();
    int count = 0;
    String[] schecks = request.getParameterValues("scheck");
    for (int i=0; i < schecks.length; i++) {
      
      int libraryno =Integer.parseInt(schecks[i]);
      LibraryVO libraryVO = libraryProc.read(libraryno);
      
      Tool1.deleteFile(upDir, libraryVO.getFile_name());
      Tool1.deleteFile(upDir, libraryVO.getThumb());
      
      count = libraryProc.delete(libraryno); // DBMS 레코드 삭제 처리
   
      if (count == 1) {
        categoryProc.decreaseCnt(categoryno);  // 글수 감소
        
        if (libraryProc.count(categoryno, col, word) % recordPerPage== 0){ 
          nowPage = nowPage - 1;
          if (nowPage < 1){
            nowPage = 1;
          }
        }
      }
    }
    response.sendRedirect("./list_category_table2.jsp?categoryno=" + categoryno+"&nowPage="+nowPage);
%>






     
