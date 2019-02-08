<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<%
String upDir = application.getRealPath("/library4/storage");
 
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
int count = 0;
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 
  <DIV class='aside_menu'>
    <ASIDE style='float: left;'>게시판＞<%=category_title %>＞자료 삭제</ASIDE> 
    <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&nowPage=<%=nowPage%>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>&nowPage=<%=nowPage%>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp?nowPage=<%=nowPage%>'>펼쳐보기</A>
    </ASIDE> 
    <DIV class='menu_line' style='clear: both;'></DIV>
  </DIV>
 
  <DIV class='message'>
    <%
    ArrayList<LibraryVO> list = libraryProc.list_category(categoryno);
    
    for (int index=0; index < list.size(); index++) {
      LibraryVO libraryVO = list.get(index); // 레코드 1건 추출
 
      // 등록된 파일 삭제
      Tool1.deleteFile(upDir, libraryVO.getFile_name());
      Tool1.deleteFile(upDir, libraryVO.getThumb());
      Tool1.deleteFile(upDir, libraryVO.getVideo());
      
      count = libraryProc.delete(libraryVO.getLibraryno()); // DBMS 파일 삭제 처리
      if (count == 1){
        categoryProc.decreaseCnt(categoryno); // 글수 삭제
      %> 
        <SPAN class='span_info'>"<%=libraryVO.getSpot() %>" 자료를 삭제 했습니다.</SPAN><br>
      <%  
      } else {
      %>
        <SPAN class='span_warning'>에러가 발생하여 자료 삭제에 실패했습니다.</SPAN><br>
      <%  
      }
    }
    %>
  </DIV>  
 
  <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='../category4/delete_form.jsp?categoryno=<%=categoryno %>'">카테고리 삭제 계속하기</button>
    <button type='button' onclick="location.href='../category4/list.jsp'">카테고리 삭제를 취소하고 목록으로 가기</button>
  </DIV>
  
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>