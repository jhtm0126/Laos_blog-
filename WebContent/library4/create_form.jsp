<%@ page contentType="text/html; charset=UTF-8" %> 
 
<%@ include file = "./ssi.jsp"  %>
<%@ include file = "./master.jsp"  %>

 
<% 

 int categoryno = Integer.parseInt(request.getParameter("categoryno")); // PK

CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();




%>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script>
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>  
 
<DIV class='aside_menu'>
  <ASIDE style='float: left;'><A href='../category4/list.jsp'>게시판</A>＞<A href='./list.jsp?categoryno=<%=categoryno %>'><%=category_title %></A> > 등록</ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'>펼쳐보기</A>
  
  </ASIDE> 
  <DIV class='menu_line'></DIV>
</DIV>
 
<FORM name='frm' method='POST' action='./create_proc.jsp' 
           enctype='multipart/form-data'><!--/request.getParameter 안먹음/.proc에서 upload로가져와야함  -->
  <input type='hidden' name='categoryno' value='<%=categoryno%>'>
           
  <fieldset class='fieldset_no_line' style ='width:80%'>
    <ul>
      <li class='li_none'>
        <label for='spot'>타이틀: </label>
        <input class='input_basic' type='text' name='spot' id='spot' size='50' value=''>
      </li>
      <li class='li_none'>
        <TEXTAREA name='content' id='content' rows='10' cols='70'></TEXTAREA>
      </li>  
      <li class='li_none'>
        <label for='site'>WEB URL: </label>
        <input class='input_basic'  type="text" name='site' id='site' size='50' value='http://www.daum.net'>
      </li>
      
      <li class='li_none'>
        <label for='file_name'>참고 파일: </label>
        <input class='input_basic'  type="file" name='file_name' id='file_name' size='50'> (10 MB 이하만 전송 가능)
      </li>        
      
      <li class='li_none'>
         <label for='content'>Youtube(<A href='https://www.youtube.com' target='_blank'>https://www.youtube.com</A>):
         </label><br>
         <textarea name='youtube' id='youtube' rows='3' style='width: 100%;'></textarea>
     </li>
     
      <li class='li_none'>
        <DIV style='display: table; height: 100px;'>
          <DIV style='display: table-cell;'>
            <IMG src='./images/movie.png' style='vertical-align: middle;'>
            <input class='input_basic' style='vertical-align: middle;' type="file" name='video' id='video' size='50'> (영상 파일(MP4), 1000 MB 이하만 전송 가능)
          </DIV>
        </DIV>
      </li>
              
      <li class='li_none'><label for='map_info'>지도: </label><br>
         <textarea name='map_info' id='map_info' rows='5' style='width: 100%;'></textarea>
     </li>
      
      
      <li class='li_none'>
        <label for='writer'>작성자: </label>
        <input class='input_basic'  type='text' name='writer' id='writer' value='왕눈이' size='10'>
 
 
        <label for='pw'>패스워드: </label>
        <input class='input_basic'  type='password' name='pw' id='pw' value='123' size='5'>
      </li>
 
      <li class='li_center'>
        <button type="submit">등록</button>
        <button type="button" onclick="history.back();">목록</button>
        <button type="button" onclick="history.back();">취소</button>
      </li>    
    </ul>
  </fieldset>
 
</FORM>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 