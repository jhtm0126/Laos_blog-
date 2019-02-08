<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<%
// int categoryno = 1;
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);//카테고리 정보를 읽어옴
String category_title = categoryVO.getTitle();//게시판 제목만 선택
%>
 
<!DOCTYPE html>  
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title><%=category_title %></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>  
 
<DIV class='title_box'><%=category_title %></DIV>
 
<TABLE class='table_basic'>
  <colgroup>
    <col style='width: 10%;' />
    <col style='width: 40%;' />
    <col style='width: 10%;' />
    <col style='width: 10%;' />
    <col style='width: 20%;' />
    <col style='width: 10%;' />
  </colgroup>
  <thead>
    <TR>
      <TH class='th_basic'>번호</TH>
      <TH class='th_basic'>제목</TH>
      <TH class='th_basic'>성명</TH>
      <TH class='th_basic'>조회</TH>
      <TH class='th_basic'>등록일</TH>
      <TH class='th_basic'>기타</TH>
    </TR>
  </thead>
  <tbody>
  <% 

    ArrayList<LibraryVO> list = libraryProc.list_category(categoryno);//특정 카테고리 목록
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
        <td class='td_basic'><%=libraryVO.getRdate() %></td>
        <td class='td_basic'>
          <A href='./update_form.jsp?libraryno=<%=libraryno %>'><IMG src='./images/update.png' style='width: 16px;' title='수정'></A>
          <A href='./delete_form.jsp?libraryno=<%=libraryno %>'><IMG src='./images/delete.png' style='width: 16px;' title='삭제'></A>
        </td>
      </tr>
    <%  
    }
    %>
  </tbody>
</TABLE>
 
<DIV class='bottom_menu'>
  <button type='button' 
              onclick="location.href='./create_form.jsp?categoryno=<%=categoryno%>'">등록</button>
  <button type='button' 
              onclick="location.href='../category4/list.jsp'">카테고리</button>
</DIV>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 