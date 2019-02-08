<%@ page contentType="text/html; charset=UTF-8" %> 
 
<%@ include file = "./ssi.jsp"  %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
 
int libraryno = Integer.parseInt(request.getParameter("libraryno"));
LibraryVO libraryVO = libraryProc.read(libraryno); // 조회
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
  <ASIDE style='float: left;'>게시판＞<%=category_title %>＞지도(Map) 수정</ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'>펼쳐보기</A>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>
 
<FORM name='frm' method='POST' action='./replace_map_proc.jsp'>
  <input type='hidden' name='categoryno' value='<%=categoryno %>'>
  <input type='hidden' name='libraryno' value='<%=libraryno %>'>
           
  <fieldset class='fieldset_no_line'>
    <ul>
      <li class='li_center'>
        <!-- 가운데 정렬 설정 -->
        <DIV style='width:520px; margin: 0px auto;'>
          <%=libraryVO.getMap_info() %>
        </DIV>
      </li>
      <li class='li_none'>
        <label for='content'>지도: </label><br>
        <textarea name='map_info' id='map_info' rows='5' style='width: 100%;'><%=libraryVO.getMap_info() %></textarea>
      </li>                
      <li class='li_none'>
        <label for='pw'>패스워드: </label>
        <input class='input_basic'  type='password' name='pw' id='pw' value='' size='5'>
      </li>
 
      <li class='li_center'>
        <button type="submit">변경 진행</button>
        <button type="button" onclick="history.back();">변경 취소</button>
      </li>    
    </ul>
  </fieldset>
 
</FORM>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 