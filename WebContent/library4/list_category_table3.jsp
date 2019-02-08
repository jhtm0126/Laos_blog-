<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
LibraryVO libraryVO = null;






%>

<%
// -----------------------------------------------------------------------------
// 페이징 관련 코드 시작
// -----------------------------------------------------------------------------
int recordCount = libraryProc.count(categoryno, col, word);
 
// listFile 목록 파일명
// recordCount 전체 레코드수 
// nowPage 현재 페이지
// recordPerPage 페이지당 레코드 수
// col 검색 컬럼 
// word 검색어
// categoryno 카테고리 그룹
listFile = "list_category_table2.jsp";
String paging = libraryProc.paging4(listFile, 
                                                   recordCount, 
                                                   nowPage, recordPerPage, col, word, categoryno);
// -----------------------------------------------------------------------------
 //검색(select)+페이징
ArrayList<LibraryVO> list = libraryProc.list_category(categoryno, col, word, nowPage, recordPerPage);
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

      <ASIDE style='float: right;'>
      <FORM name='frm' method='GET' action='./list_category_table2.jsp'>
        <input type='hidden' name='categoryno' value='<%=categoryno %>'>
        
 
        <SELECT name='col' onchange="total_list(this.form);">
          <OPTION value='writer' <% if (col.equals("writer")) { out.print("selected = 'selected'"); } %>>성명</OPTION>
          <OPTION value='spot' <% if (col.equals("spot")) { out.print("selected = 'selected'"); } %>>제목</OPTION>
          <OPTION value='content' <% if (col.equals("content")) { out.print("selected = 'selected'"); } %>>내용</OPTION>
          <OPTION value='title_content' <% if (col.equals("title_content")) { out.print("selected = 'selected'"); } %>>제목+내용</OPTION>
        </SELECT>
        <INPUT type='text' name='word' value='<%=word %>' placeholder="검색어">
        <BUTTON type='submit'>검색</BUTTON>
        <%if(word.length()>0) {%>
        <button type = 'button' onclick="location.href='./list_category_table2.jsp?categoryno=<%=categoryno %>'">전체글</button>
         <%} %>
      </FORM> 
    </ASIDE>

  <ASIDE style='float: left;'><A href='../category4/list.jsp'>게시판</A>＞<A href='./list.jsp?categoryno=<%=categoryno %>'><%=category_title %></A></ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=1&recordPerPage=1'>펼쳐보기</A><span class='menu_divide'> |</span>
    <A href='./create_form.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>등록</A>
    
   

  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>


 
 
  <%--  
  <DIV class='title_box'><%=category_title %></DIV>
  --%>
    
    
 <form name='frm2' id='frm2' action='' method='POST'>
      <input type='hidden' name='categoryno' id='categoryno' value='<%=categoryno %>'>
      <input type='hidden' name='nowPage' id='nowPage' value='<%=nowPage %>'>
<TABLE class='table_basic'>
  <colgroup>
 <%
if (AdminTool.isMaster(request)) {
%>
    <col style='width: 5%;' />
    
 <%
}
 %>   
    <col style='width: 5%;' />
    <col style='width: 15%;' />
    <col style='width: 20%;' />
    <col style='width: 10%;' />
    <col style='width: 10%;' />
    <col style='width: 25%;' />
    <col style='width: 10%;' />
  </colgroup>
  <thead>
    <TR>
<%
if (AdminTool.isMaster(request)) {
%>
      <TH class='th_basic'>선택</TH>
 <%
}
 %>
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
 
  int cnt = list.size();
  for(int index=0; index < cnt; index++){
    libraryVO = list.get(index);
    int libraryno = libraryVO.getLibraryno();
    String thumb = libraryVO.getThumb();
    String file_name = Tool1.checkNull(libraryVO.getFile_name()); // null -> "" 
    // System.out.println("--> file1: " + file1);  // 파일이 없어도 null이 아님
%> 

  <TR style='height: 90px;'>
<%
if (AdminTool.isMaster(request)) {
%>
    <TD class='td_basic'><input type="checkbox" name='scheck' id='scheck' value='<%=libraryno %>'></TD>
 <%
}
 %>
    <TD class='td_basic'><%=libraryno%></TD>
    <TD class='td_basic' style='padding-top: 4px; padding-bottom: 4px;'>
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
     <A href='./read.jsp?libraryno=<%=libraryno%>&categoryno=<%=categoryno%>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'><%=libraryVO.getSpot() %></A></TD>
    <TD class='td_basic'><%=libraryVO.getWriter() %></TD>
    <TD class='td_basic'><%=libraryVO.getHits() %></TD>
    <TD class='td_basic'><%=libraryVO.getRdate().substring(0,16) %></TD>
    <TD class='td_basic'>
    
         <%
      if (AdminTool.isMaster(request)||AdminTool.isAdmin(request)) { // Master@회원
      %>
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
      
 
      <A href='./update_form.jsp?libraryno=<%=libraryno%>&categoryno=<%=categoryno%>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'><IMG src='./images/update.png' title='수정'></A>
      <A href='./delete_form.jsp?libraryno=<%=libraryno%>&categoryno=<%=categoryno%>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'><IMG src='./images/delete.png' title='삭제'></A>
     <%
      }else{
      %>
      [비회원]
      <%} %>
    </TD>
    
  </TR>
  <%
    }
  %>
  </tbody>
  
  <tfoot>
  
  </tfoot>
</TABLE>
<%
if (AdminTool.isMaster(request)||AdminTool.isAdmin(request)) {
%>

<input type='submit' value='선택 삭제' onclick ="javascript: frm2.action='./check_delete_proc.jsp';"/> 

 
<input type='submit' value='복사(개발중)' onclick ="javascript: frm2.action='./check_delete_proc1.jsp';"/> 
<%
}
%>
</form>
 
<DIV class='bottom_menu' >

   
   <%=paging %>
 <!-- 
 <button type='button' 
             onclick="location.href='./create_form.jsp?categoryno=<%=categoryno %>'">등록</button>
  <button type='button' 
              onclick="location.href='../category4/list.jsp'">카테고리</button>
  --> 
</DIV>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 