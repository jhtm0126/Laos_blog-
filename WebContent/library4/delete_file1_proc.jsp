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
  <ASIDE style='float: left;'><A href='../category4/list.jsp'>게시판</A>＞<A href='./list.jsp?categoryno=<%=categoryno %>'><%=category_title %></A>＞수정 > 파일 삭제</ASIDE> 
    <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'>펼쳐보기</A>
    </ASIDE> 
    <DIV class='menu_line' style='clear: both;'></DIV>
  </DIV>
 
  <DIV class='message'>
    <%
    int libraryno = Integer.parseInt(request.getParameter("libraryno"));
    String pw  = request.getParameter("pw");
    
    if (libraryProc.passwordCheck(libraryno, pw) == 1)  { // 패스워드 일치여부 검사
 
      LibraryVO libraryVO = libraryProc.read(libraryno);// 삭제할 파일 정보 읽기
      
     // Tool1.deleteFile(upDir, pds4VO.getFstor1());       // 기존 파일 삭제
      Tool1.deleteFile(upDir, libraryVO.getThumb());      // 기존 파일 삭제
      
      count = libraryProc.delete_file1(libraryno); // DBMS 파일 정보 삭제 처리
      if (count == 1) {
      %>
        <SPAN class='span_info'>파일을 삭제 했습니다.</SPAN>
      <%  
      } else {
      %>
        <SPAN class='span_warning'>에러가 발생하여 파일 삭제에 실패했습니다.</SPAN>
      <%  
      }
    } else {
      %>
      <SPAN class='span_warning'>패스워드가 일치하지 않습니다.</SPAN>
      <%  
    }
    %>
  </DIV>  
 
  <DIV class='bottom_menu' style='text-align: center;'>
    <%
    if (count == 0) {
    %>
      <button type='button' onclick="history.back()">다시 시도</button>  
    <%  
    } else {
    %>
      <button type='button' onclick="location.href='./read.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'">확인</button>
    <%  
    }
    %>   
    
    <button type='button' onclick="location.href='./list.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'">목록</button>
  </DIV>
  
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>