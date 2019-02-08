<%@ page contentType="text/html; charset=UTF-8" %> 
 
<%@ include file = "./ssi.jsp"  %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
 
recordPerPage = 1; // 페이지당 레코드 수, 펼쳐 보기만 지정
listFile = "list_read.jsp";
 
// -----------------------------------------------------------------
// 페이징 관련 코드 시작
// -----------------------------------------------------------------
int recordCount = libraryProc.count(categoryno, col, word);
 
// listFile 목록 파일명
// recordCount 전체 레코드수 
// nowPage 현재 페이지
// recordPerPage 페이지당 레코드 수
// col 검색 컬럼 
// word 검색어  
String paging = libraryProc.paging5(listFile, recordCount, nowPage, recordPerPage, col, word, categoryno);
// -----------------------------------------------------------------
 
ArrayList<LibraryVO> list = libraryProc.list_category(categoryno, col, word, nowPage, recordPerPage);
 
LibraryVO libraryVO = list.get(0); // 펼쳐보기는 하나의 레코드만 대상으로함으로 0 번 index 사용 
int libraryno = libraryVO.getLibraryno();
%>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>  
 
<DIV class='aside_menu'>
  <ASIDE style='float: left;'>게시판＞<%=category_title %>＞조회</ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>'>앨범형</A> <span class='menu_divide'> |</span>
      <%
      if (list.size() > 0) {
      %>
      <A href='./list_read.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=1&recordPerPage=1'>펼쳐보기</A> <span class='menu_divide'> |</span>
      <%
      }
      %>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>
 
<FORM name='frm' method='POST' action='./create_proc.jsp' 
           enctype='multipart/form-data'>
  <input type='hidden' name='categoryno' value='<%=categoryno %>'>
           
  <fieldset class='fieldset_no_line'>
    <ul>
      <li class='li_none'>
        <span style="font-weight: bold;">
          <%=libraryVO.getSpot() %> (<%=libraryVO.getRdate() %>)
          조회수: <%=libraryVO.getHits() %>
          <%
          if (libraryVO.getSize() > 0) {
          %>
            <%=libraryVO.getFile_name() %> (<%=Tool1.unit(libraryVO.getSize()) %>)
            <A href='<%=root%>/download?dir=/library4/storage&filename=<%=libraryVO.getFile_name() %>&downname=<%=libraryVO.getFile_name() %>'><IMG src='./images/download.png'></A>
            
          <% 
          }
          %>
        </span>
      </li>
      
      <li class='li_none'>
      <%
      if(Tool1.isImage(libraryVO.getFile_name())){
      %>
      <div style='float:left;margin:10px 15px 0px 10px; width:40%;'>
      <a href='./storage/<%=libraryVO.getFile_name()%>' target='_blank'><img src="./storage/<%=libraryVO.getFile_name()%>" style='width:100%;'></a>
      </div>
     <% 
      }
      %>
      
       <DIV>
       <%=libraryVO.getContent()%>
       </DIV> 
      </li>
 
      
      <%
      String map_info = Tool1.checkNull(libraryVO.getMap_info());
      if (map_info.trim().length() > 0) { // 지도가 있는 경우만 출력
      %>
      <li class='li_none'>
        <!-- 가운데 정렬 설정 -->
        <DIV style='width:520px; margin: 0px auto;'>
          <%=libraryVO.getMap_info() %>
        </DIV>
      </li>
      <%
      }
      %>
      
      <li class='li_none' style='font-size: 0.8em;'>
        <%=libraryVO.getWriter() %><%=libraryVO.getSite() %>
      </li>
      
      <%
      String video = Tool1.checkNull(libraryVO.getVideo());
      if (video.trim().length() > 0) { // MP3 파일이 있는 경우만 출력
      %>
        <li class='li_none' style='font-size: 0.8em; '>
          <DIV style="text-align: center;">
            <VIDEO controls src='./storage/<%=video%>' style='width: 80%;'></VIDEO>
          </DIV>
        </li>
      <%
      }
      %>  
      
      <%
      String youtube = Tool1.checkNull(libraryVO.getYoutube());
      if (youtube.trim().length() > 0) { // 지도가 있는 경우만 출력
      %>
      <li class='li_none'>
        <!-- 가운데 정렬 설정 -->
        <DIV style='width: 854px; margin: 0px auto;'>
          <%=libraryVO.getYoutube() %>
        </DIV>
      </li>  
      <%
      }
      %>
            
    </ul>
  </fieldset>
 
</FORM>
 
<HR class='hr_dotted'>
                      
<DIV class='bottom_menu'>
  <%=paging %>
 
</DIV>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 