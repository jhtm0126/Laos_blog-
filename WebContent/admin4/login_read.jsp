<%@ page contentType="text/html; charset=UTF-8" %> 
 
<%@ include file = "./ssi.jsp"  %>
 
<%


int admin4no = Integer.parseInt(request.getParameter("admin4no"));
Admin4VO admin4VO = adminProc.read(admin4no);

 

%>
<!DOCTYPE html>  
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>방문 내역</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>  
 
<DIV class='aside_menu'>
  <ASIDE style='float: left; font-weight: bold; font-size:35px; '>회원정보</ASIDE> 
  <ASIDE style='float: right; font-weight: bold; font-size:35px;'>
    <A href='./list_visit.jsp'>방문 목록</A>
  </ASIDE> 
  <ASIDE style='float: right;'>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV> 
<TABLE class='table_basic'>
  <colgroup>
    <col style='width: 10%;' />
    <col style='width: 20%;' />
    <col style='width: 30%;' />
    <col style='width: 20%;' />

    <col style='width: 20%;' />

    
  </colgroup>
  <thead>
    <TR>
      <TH class='th_basic' style='border-left:none;'>admin4no</TH>
      <TH class='th_basic'>회원이름</TH>
      <TH class='th_basic'>Email</TH>
      <TH class='th_basic'>계정 상태</TH>

      <TH class='th_basic'>가입일</TH>

      
    </TR>
  </thead>
  <tbody>

      <tr>
        <td class='td_basic' ><%=admin4no %></td>
        <td class='td_basic' ><%=admin4VO.getMname() %></td>
        <td class='td_basic' ><%=admin4VO.getEmail() %></td>
        <td class='td_basic' ><%=admin4VO.getAct() %></td>
       
        <td class='td_basic'><%=admin4VO.getMdate().substring(0,10)%></td>
      
      </tr>

  </tbody>
  <tfoot>
  </tfoot>
</TABLE>
 
<DIV class='bottom_menu'>
  
</DIV>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 