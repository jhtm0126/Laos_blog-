<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
%>

 
<!DOCTYPE html>  
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>게시판</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>  
 
<DIV class='aside_menu'>
  <ASIDE style='float: left;'><A href='../category4/list.jsp'>게시판</A>＞<A href='./list.jsp?categoryno=<%=categoryno %>'><%=category_title %></A></ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'>펼쳐보기</A>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>
 
  <%--  
  <DIV class='title_box'><%=category_title %></DIV>
  --%>
 
<TABLE class='table_basic'>
  <colgroup>
    <col style='width: 10%;' />
    <col style='width: 10%;' />
    <col style='width: 30%;' />
    <col style='width: 10%;' />
    <col style='width: 10%;' />
    <col style='width: 20%;' />
    <col style='width: 10%;' />
  </colgroup>
  <thead>
    <TR>
      <TH class='th_basic'>번호</TH>
      <TH class='th_basic'>파일</TH>
      <TH class='th_basic'>제목</TH>
      <TH class='th_basic'>성명</TH>
      <TH class='th_basic'>조회</TH>
      <TH class='th_basic'>등록일</TH>
      <TH class='th_basic'>기타</TH>
    </TR>
  </thead>
  <tbody>
<%
  ArrayList<LibraryVO> list = libraryProc.list_category(categoryno); // 특정 카테고리 목록
 
  int cnt = list.size();
  for(int index=0; index < cnt; index++){
    LibraryVO libraryVO = list.get(index);
    int libraryno = libraryVO.getLibraryno();
    String thumb = libraryVO.getThumb();
    String file_name = Tool1.checkNull(libraryVO.getFile_name()); // null -> "" 
    // System.out.println("--> file1: " + file1);  // 파일이 없어도 null이 아님
%> 
  <TR style='height: 90px;'>
    <TD class='td_basic'><%=libraryno%></TD>
    <TD class='td_basic' style='padding-top: 5px; padding-bottom: 5px;'>
      <%
      if (file_name.length() > 0) { // 파일 존재
        if (Tool1.isImage(thumb)) { // 이미지인가?
        %>
          <IMG src='./storage/<%=libraryVO.getThumb() %>' style='width: 100%; display: block;'>
        <%  
        } else{
        %>  
          <%=file_name%>
        <%    
        }
      } else {
      %>
        <IMG src='./images/none1.jpg' style='width: 100%; display: block;'>      
      <%  
      }
      %>
    </TD>
   <TD class='td_left'>
     <A href='./read.jsp?libraryno=<%=libraryno%>&categoryno=<%=categoryno%>'><%=libraryVO.getSpot() %></A></TD>
    <TD class='td_basic'><%=libraryVO.getWriter() %></TD>
    <TD class='td_basic'><%=libraryVO.getHits() %></TD>
    <TD class='td_basic'><%=libraryVO.getRdate() %></TD>
    <TD class='td_basic'>
      <%
      if (libraryVO.getVisible().equals("Y")) {
      %>
        <A href='./show_hide.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno %>&visible=<%=libraryVO.getVisible() %>'><IMG src='./images/show.png' title='출력'></A>
      <%
      } else {
        %>
        <A href='./show_hide.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno %>&visible=<%=libraryVO.getVisible() %>'><IMG src='./images/hide.png' title='숨기기'></A>
      <%
      }
      %>
      <A href='./update_form.jsp?libraryno=<%=libraryno%>'><IMG src='./images/update.png' title='수정'></A>
      <A href='./delete_form.jsp?libraryno=<%=libraryno%>'><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
    
  </TR>
<%
  }
%>
  </tbody>
  <tfoot>
  </tfoot>
</TABLE>
 
<DIV class='bottom_menu'>
  <button type='button' 
             onclick="location.href='./create_form.jsp?categoryno=<%=categoryno %>'">등록</button>
  <button type='button' 
              onclick="location.href='../category4/list.jsp'">카테고리</button>
</DIV>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 