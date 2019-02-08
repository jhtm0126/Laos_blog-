<%@ page contentType="text/html; charset=UTF-8" %> 
 
<%@ include file = "./ssi.jsp"  %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
 
int libraryno = Integer.parseInt(request.getParameter("libraryno"));
LibraryVO libraryVO = libraryProc.read(libraryno); // 조회

libraryProc.increaseCnt(libraryno);
String file_name = libraryVO.getFile_name();

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
  <ASIDE style='float: left;'><A href='../category4/list.jsp'>게시판</A>＞<A href='./list.jsp?categoryno=<%=categoryno %>'><%=category_title %></A></ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=1&recordPerPage=1'>펼쳐보기</A><span class='menu_divide'> |</span>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>
<div style='float:right;'> 
<A href="./pre_proc.jsp?categoryno=<%=categoryno%>&libraryno=<%=libraryno%>"><IMG src='./images/prebu.jpg' style='width: 40px;' title='이전글'></A>
<A href="./next_proc.jsp?categoryno=<%=categoryno%>&libraryno=<%=libraryno%>"><IMG src='./images/next_bu.jpg' style='width: 40px;' title='다음글'></A>
 </div>
<FORM name='frm'>
  <fieldset class='fieldset_no_line'>
    <ul>
      <li class='li_none'>
        <span style="font-weight: bold;">
          <%=Tool1.convertChar(libraryVO.getSpot()) %> 
        </span>
      </li>
  
      <li class='li_none'>
      <%
      if(Tool1.isImage(file_name)){
      %>
      <div style='float:left;margin:10px 15px 0px 10px; width:40%;'>
      <a href='./storage/<%=file_name%>' target='_blank'><img src="./storage/<%=file_name%>" style='width:100%;'></a>
      </div>
     <% 
      }
      %>
      
       <DIV>
       <%=libraryVO.getContent()%>
       </DIV> 
      </li>
      
        <%
      // Youtube 출력 시작 ---------->
      String youtube = Tool1.checkNull(libraryVO.getYoutube());
      if (youtube.trim().length() > 0) { // 영상이 있는 경우만 출력
      %>
      <li class='li_none'>
        <!-- 가운데 정렬 설정 -->
        <DIV style='width: 854px; margin: 0px auto;'>
          <%=libraryVO.getYoutube() %>
        </DIV>
      </li>  
      <%
      }
      // ----------> Youtube 출력 종료
      %>
      
             <%
      String video= Tool1.checkNull(libraryVO.getVideo());
      if (video.trim().length() > 0) { // MP3 파일이 있는 경우만 출력
      %>
        <li class='li_none' style='font-size: 0.8em;'>
          <DIV style="text-align: center;">
            <VIDEO controls src='./storage/<%=video %>' style='width: 70%; background-color: #EEEEEE;'></VIDEO>
          </DIV>
        </li>
      <%
      }
      %>  
      
      <%
      // 지도 출력 시작 ---------->
      String map =Tool1.checkNull(libraryVO.getMap_info()); 

      if (map.trim().length() > 0) {
      %>
        <li class='li_none'>
          <DIV style='width: 520px; margin: 0px auto;'>
            <%=libraryVO.getMap_info() %>
          </DIV>
        </li>
      
      <%  
      }
      // ----------> 지도 출력 종료
      %>
 
      <li class='li_none' style='font-size: 0.8em;'>
        <%=libraryVO.getWriter() %> <%=libraryVO.getSite() %>
        (<%=libraryVO.getRdate().substring(0,16)%>)
        조회수: <%=libraryVO.getHits() %> 
        
        <%if(libraryVO.getSize()>0){
        %>
        <A href='<%=root%>/download?dir=/library4/storage&filename=<%=libraryVO.getFile_name() %>'
        Style='font-size:0.8em;'><%=libraryVO.getFile_name() %></A> (<%=Tool1.unit(libraryVO.getSize()) %>) 
        
        <% }
        %>
      </li> 
 
 
 
    </ul>
  </fieldset>
 
  <DIV class='bottom_menu' style='width: 90%; font-size: 0.8em;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=1&recordPerPage=1'>펼쳐보기</A><span class='menu_divide'> |</span>
    
    <%
      if (AdminTool.isMaster(request)||AdminTool.isAdmin(request)) { // Master@회원
      %> 
     <A href='./update_form.jsp?libraryno=<%=libraryno%>&categoryno=<%=categoryno%>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>수정</A> <span class='menu_divide'> |</span>
    <A href='./delete_form.jsp?libraryno=<%=libraryno%>&categoryno=<%=categoryno%>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>삭제</A> <span class='menu_divide'> |</span>
    <A href='./create_form.jsp?categoryno=<%=categoryno%>'>등록</A>
      <%
      }
      %>

    

  </DIV>
</FORM>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 
 