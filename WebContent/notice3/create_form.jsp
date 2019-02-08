<%@ page contentType="text/html; charset=UTF-8" %>

 
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
 
  <form name='frm' action='./create_proc.jsp' method='POST'>
    <fieldset style='width: 70%; margin: 10px auto;'>
      <legend class='legend_basic'>즐겨찾기</legend>
      <ul>
        <li class='li_none'>
          <label for='title'>타이틀: </label>
          <input type='text' name='title' id='title' value='라오스 여행 관련' 
                    style='width: 80%'><!-- name->jsp에서사용 id->javascripte에서 사용 -->
        </li>
        <li class='li_none'>
          <label for='address'>주소: </label>
          <input type='text' name='address' id='address' value='http://www.naver.com' 
                    style='width: 80%'>
        </li>
      </ul>
         <DIV style='margin: 30px auto; text-align: center; background-color: #FFFFFF'>
      <button type='submit'>추가</button>
    </DIV>  
 
    </fieldset>
    
   
  </form>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>