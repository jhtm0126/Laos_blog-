<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file ='./ssi.jsp' %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>즐겨찾기</title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
  <DIV class='title_box'>즐겨찾기</DIV>
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
      <col style='width: 50%;'>
      <col style='width: 12%;'>
      <col style='width: 18%;'>
      <col style='width: 15%;'>
    </colgroup>
    <thead>
      <TR>
        <TH class='th_basic' style='border-left:none;'>NO</TH>
        <TH class='th_basic'>타이틀</TH>
        <TH class='th_basic'>주소</TH>
        <TH class='th_basic'>등록일</TH>
        <TH class='th_basic' style='border-right:none;'>기타</TH>
      </TR>
    </thead>
    <tbody>
      <%
       ArrayList<UrlVO> list =urlDAO.list();
      
      int count = list.size();
      
      for(int i= 0; i<count;i++){
        UrlVO urlVO=list.get(i);
      %>
      
              <tr>
                <td class='td_basic'><%=urlVO.getUrlno() %></td>
                <td class='td_left'><%=urlVO.getTitle() %></td>
                <td class='td_basic'><%=urlVO.getAddress() %></td>
                <td class='td_basic'><%=urlVO.getRdate() %></td>
              
                <td class='td_basic'> 
        <%
        if (AdminTool.isMaster(request)||AdminTool.isAdmin(request)) { // Master@회원
        %>
                  <A href ='./update_form.jsp?urlno=<%=urlVO.getUrlno()%>'>수정</A> 
                  /<A href ='./delete_form.jsp?urlno=<%=urlVO.getUrlno()%>'>삭제</A>
         <% 
          }else{
          %>       
             [비회원]    
                     
          <% 
          }
          %>              
            </td>
                   
                  
                   
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
  
  