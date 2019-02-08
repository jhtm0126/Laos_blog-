<%@ page contentType="text/html; charset=UTF-8" %>



 
 <%
 String root = request.getContextPath();
 %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>laos Blog ver07</title>
<link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css">

</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />

<DIV class='content'>

  <div style='width: 30%; margin: 20px auto; font-size: 34px; text-align: center; font-family:Nanum Pen Script;'>
       LAOS 여행
  </DIV>
 
  <TABLE id='index_table' style='width: 100%; margin: 30px auto; border-collapse: collapse;'>
    <tr>
      <td style='width: 25%; padding:1px;'>
      <a href='./menu/images/01.jpg' target='_blank'><img src='./menu/images/01.jpg' style='width: 100%; display: block;'></a>
      </td>
      <td style='width: 25%; padding:1px;'>
      <a href='./menu/images/01.jpg' target='_blank'><img src='./menu/images/02.jpg' style='width: 100%; display: block;'></a>
      <td style='width: 25%; padding:1px;'>
      <a href='./menu/images/01.jpg' target='_blank'><img src='./menu/images/03.jpg' style='width: 100%; display: block;'></a>
      <td style='width: 25%; padding:1px;'>
      <a href='./menu/images/01.jpg' target='_blank'><img src='./menu/images/04.jpg' style='width: 100%; display: block;'></a>
    </tr>
  </TABLE>
  
     <DIV style='margin: 0px auto; width: 100%;'>
    <DIV style='float: left; width: 50%;'>
      <jsp:include page="/notice3/list_home.jsp" flush='false' />
     </DIV>
     <DIV style='float: left; width: 50%;'>
       <jsp:include page="/review4/list_home.jsp" flush='false' />
    </DIV>  
  </DIV>
  

 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>