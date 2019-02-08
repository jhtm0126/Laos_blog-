<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>


<% 

    int select_list= Integer.parseInt(request.getParameter("select_list"));

    int categoryno = Integer.parseInt(request.getParameter("categoryno"));

    CategoryVO categoryVO = categoryProc.read(categoryno);


   
    String[] schecks = request.getParameterValues("scheck");
    for (int i=0; i < schecks.length; i++) {
      
      int libraryno =Integer.parseInt(schecks[i]);
      
      LibraryVO libraryVO = libraryProc.read(libraryno);
      
      libraryVO.setCategoryno(select_list);
      libraryVO.setFile_name(libraryVO.getFile_name());
      
       
      
     int count = libraryProc.create(libraryVO); // DBMS
     if(count==1){
       categoryProc.increaseCnt(select_list);
       System.out.println("삭제 성공");
      
       
     }else{
       System.out.println("삭제 실패");
     }
    
    }
  
    response.sendRedirect("./list_category_table2.jsp?categoryno=" + select_list);
%>





     
