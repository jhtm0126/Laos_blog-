<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
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
 
<DIV class='title_box'>방문내역</DIV>
 
<TABLE class='table_basic'>
  <colgroup>
    <col style='width: 10%;' />
    <col style='width: 10%;' />
    <col style='width: 20%;' />
    <col style='width: 30%;' />
    <col style='width: 20%;' />
    <col style='width: 100%;' />

    
  </colgroup>
  <thead>
    <TR>
      <TH class='th_basic' style='border-left:none;'>Loginno</TH>
      <TH class='th_basic'>Admin4no</TH>
      <TH class='th_basic'>Email</TH>
      <TH class='th_basic'>Ip address</TH>
      <TH class='th_basic'>접속시간</TH>
      <TH class='th_basic'>기타</TH>

      
    </TR>
  </thead>
  <tbody>
    <% 
    ArrayList<LoginVO> list =loginProc.list();
    
    int cnt = list.size();
    for (int index=0; index < cnt; index++) {
      LoginVO loginVO = list.get(index);
      int loginno = loginVO.getLoginno();
    %>
      <tr>
        <td class='td_basic' ><%=loginno %></td>
        <td class='td_basic'><A href ='./login_read.jsp?admin4no=<%=loginVO.getAdmin4no()%>'><%=loginVO.getAdmin4no()%></A></td>
        <td class='td_left'><%=loginVO.getName() %></td>
        <td class='td_left'><%=loginVO.getIp() %></td>
        <td class='td_basic'><%=loginVO.getLogindate().substring(0,16)%></td>
        <td class='td_basic'><A href ='./visit_del_f.jsp?loginno=<%=loginVO.getLoginno()%>'>삭제</A></td>
      
      </tr>
    <%  
    }
    %>
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