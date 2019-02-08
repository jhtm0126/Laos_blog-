<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();



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
listFile = "list.jsp";
String paging = libraryProc.paging5(listFile, 
                                                   recordCount, 
                                                   nowPage, recordPerPage, col, word, categoryno);
// -----------------------------------------------------------------------------
 
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
  <ASIDE style='float: left;'><A href='../category4/list.jsp'>게시판</A>＞<A href='./list.jsp?categoryno=<%=categoryno %>'><%=category_title %></A></ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>앨범형</A> <span class='menu_divide'> |</span>
        <%
    if (list.size() > 0) {
    %>
      <A href='./list_read.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=1&recordPerPage=1'>펼쳐보기</A> <span class='menu_divide'> |</span>
    <%
    } 
    %>
    <%
     if (AdminTool.isMaster(request)) {
       
     %>
     
    <A href='./create_form.jsp?categoryno=<%=categoryno %>'>등록</A>

    
    <% }
    %>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both; margin-top:10px;'></DIV>
</DIV>
 
 <%
  // ArrayList<Pds4VO> list = pds4Proc.list_category(categoryno);
  // ArrayList<Pds4VO> list = pds4Proc.list_category(categoryno, col, word);
  %>
 
  <%
  int count = list.size();
  for(int index=0; index < count; index++){
    LibraryVO libraryVO = list.get(index);
    String thumb = libraryVO.getThumb();
    
    int libraryno = libraryVO.getLibraryno();
    String spot = libraryVO.getSpot();
    String rdate = libraryVO.getRdate();
    String writer = libraryVO.getWriter();
    int hits =libraryVO.getHits();
    String fstor1 = libraryVO.getFstor1();
    String file_name = libraryVO.getFile_name();
    
    if (index != 0 && index % 4 == 0) { // 0, 1, 2, 3, 4 <HR>, 5, 6, 7, 8<HR>
    %>
      <HR class='hr_dotted'>
    <%  
    }
  %>
  <!-- 하나의 이미지 출력 -->

  <DIV class='div_grid_img'>
    <%
    if (libraryVO.getSize() > 0) {
    %> 
       <A href='./read.jsp?libraryno=<%=libraryno%>&categoryno=<%=categoryno%>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>
      <IMG src='./storage/<%=thumb %>' style='width: 100%; height: 150px;'>
      </A>
      <br>
    <%
    } else {
    %>
    <IMG src='./images/none1.jpg' style='width: 100%; height:150px; display: block;'>      
    <!--  <DIV class='div_grid_text' style='height: 150px; width: 98%'>
        <DIV class='div_grid_text_content'>
          등록된 파일 없음 
        </DIV>
      </DIV> --> 
    <%
    } // END if (pds4VO.getSize1() > 0) {
    %>
    <A href='./read.jsp?libraryno=<%=libraryno%>&categoryno=<%=categoryno%>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'><%=Tool1.textLength(spot, 15) %> [<%=hits%>]</A>  
    <br>
    <span style='font-size: 0.8em;'><%=rdate %> (<%=writer %>)</span>
  </DIV>  
  <%
  } // for END
  %>
 
  <HR class='hr_dotted'>
                      
<DIV class='bottom_menu'>
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