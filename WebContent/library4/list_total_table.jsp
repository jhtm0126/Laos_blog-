<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
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
 
<DIV class='title_box'>게시판(전체 목록)</DIV>
 
<TABLE class='table_basic'>
  <colgroup>
    <col style='width: 10%;' />
    <col style='width: 40%;' />
    <col style='width: 15%;' />
    <col style='width: 15%;' />
    <col style='width: 20%;' />
    
  </colgroup>
  <thead>
    <TR>
      <TH class='th_basic' style='border-left:none;'>번호</TH>
      <TH class='th_basic'>제목</TH>
      <TH class='th_basic'>성명</TH>
      <TH class='th_basic'>조회</TH>
      <TH class='th_basic'>등록일</TH>
      
    </TR>
  </thead>
  <tbody>
    <% 
    ArrayList<LibraryVO> list = libraryProc.list();
    
    int cnt = list.size();
    for (int index=0; index < cnt; index++) {
      LibraryVO libraryVO = list.get(index);
      int libraryno = libraryVO.getLibraryno();
    %>
      <tr>
        <td class='td_basic' ><%=libraryno %></td>
        <td class='td_left'><%=libraryVO.getSpot() %></td>
        <td class='td_basic'><%=libraryVO.getWriter() %></td>
        <td class='td_basic'><%=libraryVO.getHits() %></td>
        <td class='td_basic'><%=libraryVO.getRdate().substring(0,16)%></td>
      
      </tr>
    <%  
    }
    %>
  </tbody>
  <tfoot>
  </tfoot>
</TABLE>
 
<!-- <DIV class='bottom_menu'>
  <button type='button' 
             onclick="location.href='./create_form.jsp?categoryno=1'">등록</button>
</DIV> -->
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 