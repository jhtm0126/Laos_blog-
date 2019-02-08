<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ include file = "./ssi.jsp"  %>

<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();

int libraryno = Integer.parseInt(request.getParameter("libraryno"));
LibraryVO libraryVO = libraryProc.read(libraryno);
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
  <ASIDE style='float: left;'>게시판＞<%=category_title %>＞ MP4 파일 삭제</ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'>펼쳐보기</A>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>

<FORM name='frm' method='POST' action='./delete_mp4_proc.jsp'>
  <input type='hidden' name='categoryno' value='<%=categoryno %>'>
  <input type='hidden' name='libraryno' value='<%=libraryno %>'>
           
  <fieldset class='fieldset_no_line'>
    <ul>
      <li class='li_center'>
       
        <%
        if (Tool1.checkNull(libraryVO.getVideo()).trim().length() > 0) { // 파일이 존재하는 경우
        %>
          <VIDEO controls src='./storage/<%=libraryVO.getVideo() %>' style='width: 70%;'></VIDEO>      
        <%
        }
        %>
      </li>     
      <li class='li_none' style='text-align: center; font-size: 1.2em; color: #AA0000;'>
        삭제된 파일은 복구 할 수 없습니다.<br>
        삭제를 진행하시려면 삭제 진행 버튼을 클릭하세요.
      </li>
      <li class='li_center'>
        <label for='pw'>패스워드: </label>
        <input class='input_basic'  type='password' name='pw' id='pw' value='' size='5'>
      </li>

      <li class='li_center'>
        <button type="submit">MP4 파일 삭제</button>
        <button type="button" onclick="history.back();">삭제 취소</button>
      </li>    
    </ul>
  </fieldset>

</FORM>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->

</body>
</html> 
