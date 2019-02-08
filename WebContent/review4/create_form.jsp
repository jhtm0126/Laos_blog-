<%@ page contentType="text/html; charset=UTF-8" %>
 
 <%@ include file="./ssi.jsp" %>
 <%@ include file="./master.jsp" %>
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
    <fieldset class='fieldset_basic'>
      <legend class='legend_basic'>리뷰 등록(*: 필수)</legend>
      <ul>
       
        <li class='li_none'>
          <label for='title'>관광지: </label>
          <input type='text' name='title' id='title' value='라오스 관련 리뷰' 
                    style='width: 80%'><!-- name->jsp에서사용 id->javascripte에서 사용 -->
        </li>
        
        <li class='li_none'>
        <div style ='float:left; width:98%'>
              <label for='title'>내용: </label>
              <textarea name='content' id='content' rows='8' style='width: 99%;'>이곳은 너무너무 좋은 추억이였어요.</textarea>
        </div>
        
        <div style ='float:left; width:2%'>
            *
        </div>
        <div style='clear:both'></div>
          
        </li>
        

        
        

       <!--  <li class='li_none'>
          <label for='address'>주소: </label>
          <input type='text' name='address' id='address' value='http://www.naver.com' 
                    style='width: 80%'>
        </li> -->
       <li class='li_none'>
          <label for='title'>패스워드: </label>
          <input type='password' name='passwd' id='passwd' value='' 
                    style='width: 10%' placeholder="패스워드">*<!-- name->jsp에서사용 id->javascripte에서 사용 -->
        </li>
        
        
      </ul>
        
         <DIV style='margin: 30px auto; text-align: center;'>
      <button type='submit'>등록</button>
    </DIV>  
 
    </fieldset>
    
   
  </form>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>