<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

 

<%

// 로그인 후 이동할 주소

String url_address = Tool1.checkNull(request.getParameter("url_address"));

System.out.println("--> 3) login_form.jsp: url_address: " + url_address);

%>

 

<%

Cookie[] cookies = request.getCookies();

Cookie cookie = null;

 

String ck_email = ""; // email 저장 변수

String ck_email_save = ""; // email 저장 여부를 체크하는 변수

String ck_passwd = ""; // passwd 저장 변수

String ck_passwd_save = ""; // passwd 저장 여부를 체크하는 변수

 

for (int i=0; i < cookies.length; i++){

  cookie = cookies[i];

  

  if (cookie.getName().equals("ck_email")){

    ck_email = cookie.getValue();         // test1@mail.com

  }else if(cookie.getName().equals("ck_email_save")){

    ck_email_save = cookie.getValue();  // Y, N

  }else if (cookie.getName().equals("ck_passwd")){

    ck_passwd = cookie.getValue();         // 1234

  }else if(cookie.getName().equals("ck_passwd_save")){

    ck_passwd_save = cookie.getValue();  // Y, N

  }

}

%>

<!DOCTYPE html> 

<html lang="ko"> 

<head> 

<meta charset="UTF-8"> 

<title>로그인</title> 

 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript" src="../js/tool.js"></script>

 

</head> 

<body>

<DIV class='container'>

<jsp:include page="/menu/top.jsp" flush='false' />

<DIV class='content'>

 

<DIV class='aside_menu'>



<DIV class='aside_menu'>
  <ASIDE style='float: left; font-weight: bold; font-size:35px; '>로그인</ASIDE> 
  <ASIDE style='float: right; font-weight: bold; font-size:35px;'>
    <A href='./create_form.jsp'>회원가입</A>
  </ASIDE> 
  <ASIDE style='float: right;'>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV> 


 

<DIV style='width: 60%; margin: 10px auto;'>

<FORM name='frm' method='POST' action='./login_ck_proc.jsp'>

  <input type="hidden" name="url_address" value="<%=url_address %>">

  <div class='loginImg'>
      <img src='./images/login.jpg' >
      <h1>로그인</h1>
      <br>
      <p style='font-size:14px;'>
       이 서비스를 이용하시려면 로그인이 필요합니다.<br><br>
       회원이 아닌 분은 먼저 회원가입을 해주시기 바랍니다.
      </p>
  
  </div>

  <fieldset class='fieldset_no_line'>

    <ul>

      <li class='li_none'>

        <label class='label_basic_a' for='email'>
                        

        <input type='email' name='email' id='email' value='<%=ck_email %>' style='width: 100%;border:none;' autocomplete="off">
        </label>
  
      </li>

      <li class='li_none'>

        <label class='label_basic_a' for='passwd'>

        <input type='password' name='passwd' id='passwd' value='<%= ck_passwd%>' style='width: 100%;border:none;' autocomplete="off">
  
         </label>       
        
      </li>
        
        <li>
      <label class='label1'> 
   
        <input type='checkbox' name='email_save' value='Y' <%=(ck_email_save.equals("Y"))?"checked='checked'": "" %>>email 저장
        </label>
        <label class='label1'>  
        <input type='checkbox' name='passwd_save' value='Y' <%=(ck_passwd_save.equals("Y"))?"checked='checked'":"" %>>pw 저장 
        </label>
        </li>
        

      <li class='li_center'>

        <button type='submit' >로그인</button>

        <button type='button' onclick="history.back();">취소</button>

        

      </li>
      

    </ul>

  </fieldset>

</FORM>

</DIV>

 

</DIV> <!-- content END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />

</DIV> <!-- container END -->

</body>

 

</html> 