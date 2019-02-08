<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>리뷰</title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
  <DIV class='title_box'>리뷰</DIV>
  
     <% 
   if (AdminTool.isMaster(request)||AdminTool.isAdmin(request)) {
     %>
   <DIV class='sub_menu'>
    <button type='button' onclick="location.href='./create_form.jsp'">추가</button>
   </DIV>
  <% 
   }
  %>
    
  <TABLE class='table_basic'>
    <colgroup>
      <col style='width: 5%;'>
      <col style='width: 12%;'>
      <col style='width: 58%;'>
      <col style='width: 15%;'>
      <col style='width: 10%;'>
    </colgroup>
    <thead>
      <TR>
        <TH class='th_basic' style='border-left:none;'>NO</TH>
        <TH class='th_basic'>제목</TH>
        <TH class='th_basic'>리뷰</TH>
        <TH class='th_basic'>등록일</TH>
        <TH class='th_basic' style='border-right:none;'>기타</TH>
      </TR>
    </thead>
    <tbody>
    <%
    ArrayList<ReviewVO> list =reviewProc.list();
     
    int count = list.size();// 객체의 갯수 산출
    for (int index=0; index < count; index++) {
      ReviewVO reviewVO = list.get(index);
    %>
      <tr>
      <td class='td_basic'><%=reviewVO.getReviewno() %></td>
      <td class='td_left'><%=reviewVO.getTitle() %></td>
      <td class='td_left'>
      <div style='padding-left:16px; font-size:0.8em;'>
      <%=Tool1.convertChar(reviewVO.getContent()) %>
      </div>
      </td>
      <td class='td_basic'><%=reviewVO.getRdate().substring(0,16) %></td>
      <td class='td_basic'> 
       <%
        if (AdminTool.isMaster(request)||AdminTool.isAdmin(request)) { // Master@회원
        %>
      
      <A href ='./update_form.jsp?reviewno=<%=reviewVO.getReviewno() %>'><IMG src='./images/update.png' style='width: 25px;'title=" 리뷰 수정"></A>
      &nbsp;<A href ='./delete_form.jsp?reviewno=<%=reviewVO.getReviewno() %>'><IMG src='./images/delete.png' style='width: 25px;'title=" 리뷰 삭제"></A>
      
      <% 
          }else{
      %>       
          [비회원]    
                     
          <% 
          }
          %>      
      
      </tr>
    <%  
    }
    %>
  
 
    </tbody>
  </TABLE>
 
   <%
        if (AdminTool.isMaster(request)||AdminTool.isAdmin(request)) { // Master@회원
        %>
   <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='./create_form.jsp'">등록</button>
   </DIV>
   
       <% 
          }
       %> 
  
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>
  
  