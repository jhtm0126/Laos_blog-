<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

 

<%

String email = request.getParameter("email");

// Checkbox를 체크를안하고 넘어오면 null 값을 가지고 있게됨.

String email_save = Tool1.checkNull(request.getParameter("email_save"));

String passwd = request.getParameter("passwd");

//Checkbox를 체크를안하고 넘어오면 null 값을 가지고 있게됨.

String passwd_save = Tool1.checkNull(request.getParameter("passwd_save"));

LoginVO loginVO = loginProc.select_admin4(email);
int admin4no = loginVO.getAdmin4no();


%>

<!DOCTYPE html> 

<html lang="ko"> 

<head> 

<meta charset="UTF-8"> 

<title>로그인</title> 

 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

 

</head> 

 

<body>

<DIV class='container'>

<jsp:include page="/menu/top.jsp" flush='false' />

<DIV class='content'>

 

<DIV class='aside_menu'>

  <ASIDE style='float: left;'>회원 ＞ 로그인 </ASIDE> 

  <ASIDE style='float: right;'>

    <A href='./list.jsp'>목록</A> <span class='menu_divide'> |</span> 

    <A href='./create_form.jsp'>가입</A> <span class='menu_divide'> |</span>

    <A href='javascript: location.reload()'>새로고침</A> <span class='menu_divide'> |</span>

  </ASIDE> 

  <ASIDE style='float: right;'>

  </ASIDE> 

  <DIV class='menu_line' style='clear: both;'></DIV>

</DIV>

 

 

  <fieldset class='fieldset_no_line'>

    <ul>

      <li class='li_center'>

        <%

        int count = adminProc.login(email,passwd);

        // System.out.println("--> count: " + count);

        int cookie_time = 60 * 60; // 1 시간

        String act = adminProc.read(email).getAct(); // 권한: M, Y, N, H, C

        

        if (count == 1){ // 로그인 성공
                
          String ip = request.getRemoteAddr();
          
          loginVO.setAdmin4no(admin4no);
          loginVO.setName(email);
          loginVO.setIp(ip);

          loginProc.create(loginVO);
          
//         if ("MY".indexOf(act) >= 0){ // 로그인 권한 있음. M: Master, Y:일반 회원

 

          // Session 저장

          // ------------------------------------------------------------------

          session.setAttribute("email", email);

          session.setAttribute("passwd", passwd);

          session.setAttribute("act", act);

          // ------------------------------------------------------------------

         

          // email 저장 관련 쿠키 저장

          // ------------------------------------------------------------------

          if (email_save.equals("Y")){ // Checkbox 선택시 이메일을 저장

            Cookie ck_email = new Cookie("ck_email", email); // 이메일 저장

            ck_email.setMaxAge(cookie_time); // 초

            response.addCookie(ck_email);

              

          }else{ // 이메일 저장하지 않을 경우

            Cookie ck_email = new Cookie("ck_email", ""); // 이메일 삭제

            ck_email.setMaxAge(0); // 초

            response.addCookie(ck_email);

              

          }

            

          // 이메일 저장 여부를 결정하는 쿠기 기록, Y or "" 저장, Checkbox 상태 저장

          Cookie ck_email_save = new Cookie("ck_email_save", email_save);

          ck_email_save.setMaxAge(cookie_time); // 초

          response.addCookie(ck_email_save);

          // ------------------------------------------------------------------

         

          // passwd 저장 관련 쿠키 저장

          // ------------------------------------------------------------------

          if (passwd_save.equals("Y")){ // 패스워드 저장 할 경우

            Cookie ck_passwd = new Cookie("ck_passwd", passwd); // 이메일 저장

            ck_passwd.setMaxAge(cookie_time); // 초

            response.addCookie(ck_passwd);

              

          }else{ // 패스워드를 저장하지 않을 경우

            Cookie ck_passwd = new Cookie("ck_passwd", ""); // 패스워드 삭제

            ck_passwd.setMaxAge(0); // 초

            response.addCookie(ck_passwd);

              

          }

            

          // 패스워드 저장 여부를 결정하는 쿠기 기록, Y or "" 저장

          Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);

          ck_passwd_save.setMaxAge(cookie_time); // 초

          response.addCookie(ck_passwd_save);

          // ------------------------------------------------------------------

          

          response.sendRedirect(root +"/index.jsp");

               

        }else{

          if ("MY".indexOf(act) >= 0) { // 발견안되면 -1

          %>

            로그인 아이디와 패스워드가 일치하지 않습니다.<br>

            확인후 다시 로그인해주세요.<br>

          <%  

          } else if ("N".equals(act)) {

          %>

            현재 계정이 사용 불가합니다.<br>

            관리자에게 문의해주세요.<br>

          <%  

          } else if ("H".equals(act)) {

          %>

            현재 계정이 확인중에있습니다.<br>

            관리자에게 문의해주세요.<br>

          <%  

          } else if ("C".equals(act)) {

          %>

            탈퇴 회원으로 사용이 불가합니다.<br>

            관리자에게 문의해주세요.<br>

          <%  

          }

        }

        %> 

      </li>

      <li class='li_center'>

      <%

      if (count == 0){

      %>

        <button type='button' onclick="history.back();">다시 시도</button>

        <button type='button' onclick="location.href='./create_form.jsp'">회원 가입</button>

        <button type='button' onclick="location.href='../index.jsp';">홈페이지</button>

       <%

      }

      %>

      </li>

    </ul>

  </fieldset>

 

 

</DIV> <!-- content END -->

<jsp:include page="/menu/bottom.jsp" flush='false' />

</DIV> <!-- container END -->

</body>

</html> 

 