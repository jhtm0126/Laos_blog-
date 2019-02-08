<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="./ssi.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공지시항</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css"> 
<script type="text/JavaScript"
        src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 
<script type="text/javascript">

$(function(){
  $('#btn_send').html('등록'); // button label 지정
  $('#btn_send').on('click', create_proc); // click 이벤트 등록
  $('#btn_init').on('click', init);  // 취소 버튼 이벤트 등록
  // 복제되는 태그는 동적 이벤트가 복제되지 않음. ★
  $('#btn_panel_close').on('click', panel_close); // 메시지 레이어 닫기

  $('#btn_delete_proc').on('click', delete_proc); // 삭제 처리 진행 
  $('#btn_delete_close').on('click', function() { $('#panel_delete_confirm').hide(); }); // 삭제 취소
  $('#btn_list_add').on('click', list); // 더보기 버튼 이벤트등록 

  $('#title').on('keydown', function(key) {
    if (key.keyCode == 13) { // Enter
      $('#seqno').focus();
    }
  });
  
  $('#seqno').on('keydown', function(key) {
    if (key.keyCode == 13) { // Enter
      $('#ids').focus();
    }
  });
  $('#ids').on('keydown', function(key) {
    if (key.keyCode == 13) { // Enter
      $('#btn_send').focus();
    }
  });
  
  list();//목록 로딩

});

//등록 요청
function create_proc() {
  $.ajax({
    url: "./create_proc.jsp",
    type: "post", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    data: $('#frm').serialize(),  // 보내는 데이터
    success: function(rdata) { 
      var msgs = rdata.msgs; // JSON
      var dismsgs = '';          // 출력 메시지 
      for (var i=0; i < msgs.length; i++) {
        dismsgs = dismsgs + msgs[i] + '<br>';
      }
      $('#panel_content').html(dismsgs); // 메시지 출력
      // var panel_close = $('#panel_close').clone();  // 닫기 버튼 태그 복제
      // $('#btn_panel_close').on('click', panel_close); 
      // $('#panel').append(panel_close.show());  // 복제된 태그를 show하여 마지막 자식태그로 붙임.
      $('#panel').show(); // 메인 패널 출력
      $('#btn_panel_close').focus(); // 버튼에 focus 지정
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
init();
}

function init() {

  $('#title').val('');
  $('#ids').val('');
  $('#title').focus();

/* setTimeout(function() { $('#title').focus(); }, 100);  */
  
  $('#btn_send').html('등록');
  $('#btn_send').off('click'); 
  $('#btn_send').on('click', create_proc); // click 이벤트 등록
  
  $('#btn_init').html('취소');
  
}

//수정폼
function update_form(categoryno) {
  $.ajax({
    url: "./update_form.jsp",
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    data: "categoryno="+ categoryno,
    success: function(rdata) {
      $('#categoryno').val(rdata.categoryno);
      $('#title').val(rdata.title);
      $('#seqno').val(rdata.seqno);
      $('#visible').val(rdata.visible);
      $('#ids').val(rdata.ids);
      $('#cnt').val(rdata.cnt);

     
      
      $('#btn_send').html('수정');
      $('#btn_send').off('click'); // 등록된 이벤트의 해제
      $('#btn_send').on('click', update_proc); // click 이벤트 등록
      
      $('#btn_init').html('수정 취소');
      
      $('#title').focus();
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
}

// 수정 처리
function update_proc() {
  $.ajax({
    url: "./update_proc.jsp",
    type: "post", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json",// 응답 형식, html, xml...
   data: $('#frm').serialize(),  // 보내는 데이터
    success: function(rdata) {
      var msgs = rdata.msgs; // JSON
      var dismsgs = '';          // 출력 메시지 
      for (var i=0; i < msgs.length; i++) {
        dismsgs = dismsgs + msgs[i] + '<br>';
      }
      $('#panel_content').html(dismsgs); // 메시지 출력
      $('#panel').show(); // 메인 패널 출력
      $('#btn_panel_close').focus(); // 버튼에 focus 지정
     
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
}

//삭제 폼
function delete_form(categoryno) {
  $.ajax({
    url: "./delete_form.jsp",
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    data: "categoryno=" + categoryno,
    success: function(rdata) {
      $('#categoryno').val(rdata.categoryno);
      $('#title').val(rdata.title);
      $('#seqno').val(rdata.seqno);
      $('#visible').val(rdata.visible);
      $('#ids').val(rdata.ids);
      $('#cnt').val(rdata.cnt);

      $('#btn_send').html('삭제');
      $('#btn_send').off('click'); // 등록된 이벤트의 해제
      $('#btn_send').on('click', delete_proc_confirm); // 삭제 확인창 출력

      $('#btn_init').html('삭제 취소');
      
      $('#btn_send').focus();

    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
}

// 삭제 확인
function delete_proc_confirm() {
  $('#panel_delete_confirm').show();
}

// 삭제 처리 진행
function delete_proc() {
  var frm = $('#frm');
  
  $.ajax({
    url: "./delete_proc.jsp",
    type: "post", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    data: 'categoryno=' + $('#categoryno', frm).val(),  // frm 폼에서 noticeno 태그 검색
    success: function(rdata) {
      var msgs = rdata.msgs; // JSON
      var dismsgs = '';          // 출력 메시지 
      for (var i=0; i < msgs.length; i++) {
        dismsgs = dismsgs + msgs[i] + '<br>';
      }
      
      $('#panel_delete_confirm').hide(); // 삭제 확인 메시지창 닫기
      
      $('#panel_content').html(dismsgs); // 메시지 출력
      $('#panel').show(); // 메인 패널 출력
      $('#btn_panel_close').focus(); // 버튼에 focus 지정
     
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
}
function panel_close() { // 메시지 레이어 닫기
  // alert('panel_close() called.');

  $('#panel').hide();
  $('#list').empty(); // id 가 list인 태그의 자식 태그 삭제

  // 목록을 새롭게 출력하기위해 페이지를 1로 지정
  $('#panel_btn_list_add').data('now-page', 1); 

  list();
  init();
}
/* function list() {
  var str = '';
  
  $.ajax({
    url: "./list_proc.jsp",
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    //data:'nowPage='+nowPage,
    success: function(rdata) {
      //$('#list').html('');
      for (var i = 0 ; i < rdata.list.length;  i++) { // 0 ~
        // console.log('rdata[i].title: ' + rdata[i].title);
        str += "<TR>";
        str += "  <TD class='td_basic'>" + rdata.list[i].seqno + "</TD>";
        str += "  <TD class='td_left'>" + rdata.list[i].title + "</TD>";
        str += "  <TD class='td_basic'>" + rdata.list[i].ids + "</TD>";
        str += "  <TD class='td_basic'>" + rdata.list[i].visible + "</TD>";
        str += "  <TD class='td_basic'>" + rdata.list[i].cnt + "</TD>";
        str += "  <TD class='td_basic'>";
        str += "    <A href='javascript: update_form(" + rdata.list[i].categoryno + ");'><IMG src='./images/update.png' style='width: 16px;'></A> / ";
        str += "    <A href='javascript: delete_form(" + rdata.list[i].categoryno + ");'><IMG src='./images/delete.png' style='width: 16px;'></A>";
        str +="</TD>"; 
        str += "</TR>";
      console.log(rdata.list[i].seqno);
      }
      $(str).appendTo('#list'); // id가 list인 태그의 자식 태그로 붙임.
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
} */

//더보기 목록
function list () {
  var str = '';
  var nowPage = $('#panel_btn_list_add').data('now-page');
  console.log('nowPage: ' + nowPage);

  $.ajax({
    url: "./list_proc.jsp",
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    data: 'nowPage=' + nowPage,
    success: function(rdata) {
      //$('#list').html('');
      for (var i = 0 ; i < rdata.list.length;  i++) { // 0 ~
        // console.log('rdata[i].title: ' + rdata[i].title);
        str += "<TR>";
        str += "  <TD class='td_basic'>" + rdata.list[i].seqno + "</TD>";
        str += "  <TD class='td_left'>";
        str += " <A href=../library4/list.jsp?categoryno="+rdata.list[i].categoryno+">"+rdata.list[i].title+"</A> ";
        str +=   "</TD>";
        str += "  <TD class='td_basic'>" + rdata.list[i].ids + "</TD>";
        str += "  <TD class='td_basic'>";
        str += " <A href='javascript:show_hide("+rdata.list[i].categoryno+",\" "+rdata.list[i].visible+" \");'>"+rdata.list[i].visible+"</A> ";
        
       /*  str += " <A href='javascript:show_hide(   30   ,'  +Y+   ');'>Y</A> "; */
        
       /*  str += " <A href=../category4/show_hide.jsp?categoryno="+rdata.list[i].categoryno+"&visible="+rdata.list[i].visible+">"+rdata.list[i].visible+"</A> "; */
        str += "</TD>";
        str += "   <TD class='td_basic'>" + rdata.list[i].cnt + "</TD>";
        str += "   <TD class='td_basic'>";
        str +="    <A href='javascript: decreaseSeqno(" + rdata.list[i].categoryno + ");'><IMG src='./images/up.jpg' style='width: 16px;' title='우선순위 높임'></A>  ";
        str +="    <A href='javascript: increaseSeqno(" + rdata.list[i].categoryno + ");'><IMG src='./images/down.jpg' style='width: 16px;' title='우선순위 낮춤'></A>";
        str += "   <A href='javascript: update_form(" + rdata.list[i].categoryno + ");'><IMG src='./images/update.png' style='width: 16px;'></A>";
        str += "   <A href='javascript: delete_form(" + rdata.list[i].categoryno + ");'><IMG src='./images/delete.png' style='width: 16px;'></A>";
        str +="</TD>"; 
        str += "</TR>";
      console.log(rdata.list[i].seqno);
      }
      $(str).appendTo('#list'); // id가 list인 태그의 자식 태그로 붙임.        
      if (nowPage < rdata.totalPage) {
        nowPage = nowPage + 1;  
        $('#panel_btn_list_add').show();
        
      } else {
        $('#panel_btn_list_add').hide();
      }
      
      $('#panel_btn_list_add').data('now-page', nowPage);

    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
}

//show_hide
function show_hide(categoryno,visible) {
  $.ajax({
    url: "./show_hide.jsp",
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    data: "categoryno="+ categoryno+"&visible="+visible,
    success: function(rdata) {

      $('#visible').html(rdata.visible);
      
     $('#list').empty(); // id 가 list인 태그의 자식 태그 삭제 

      // 목록을 새롭게 출력하기위해 페이지를 1로 지정
      $('#panel_btn_list_add').data('now-page', 1); 
 			list();
 			init();
		
    
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
}

//show_hide
function decreaseSeqno(categoryno) {
  $.ajax({
    url: "./decreaseSeqno.jsp",
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    data: "categoryno="+ categoryno,
    success: function(rdata) {

      $('#seqno').html(rdata.seqno);
      
     $('#list').empty(); // id 가 list인 태그의 자식 태그 삭제 

      // 목록을 새롭게 출력하기위해 페이지를 1로 지정
      $('#panel_btn_list_add').data('now-page', 1); 
 			list();
 			init();
		
    
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
}

//show_hide
function increaseSeqno(categoryno) {
  $.ajax({
    url: "./increaseSeqno.jsp",
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    
    
    dataType: "json", // 응답 형식, html, xml...
    data: "categoryno="+ categoryno,
    success: function(rdata) {

      $('#seqno').html(rdata.seqno);
      
     $('#list').empty(); // id 가 list인 태그의 자식 태그 삭제 

      // 목록을 새롭게 출력하기위해 페이지를 1로 지정
      $('#panel_btn_list_add').data('now-page', 1); 
 			list();
 			init();
		
    
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';
      console.log(msg);
    }
  });
}

</script>

  </head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
  <!-- 전송시 체크된 값 관련 메시지 출력 -->
  <DIV id='panel' class='popup1' style='display: none;  height: 250px;'>
    <DIV id='panel_content' style='text-align: center; padding: 0.5%; color: #FFFFFF;'></DIV>
    <DIV id='panel_close' style='margin: 0px au           o; text-align: center; height:50px;'>
      <br><button type='button' id='btn_panel_close' >닫기</button> 
    </DIV>
  </DIV>
 
  <!--  삭제 처리 여부 메시지 출력 -->
  <DIV id='panel_delete_confirm' class='popup1' style='display: none; height: 150px;'>
    <form name='frm_delete'>
      <DIV style='color: #FFFFFF;'>   
        삭제된 글은 복구 할 수 없습니다.<br>
        삭제를 진행 하시겠습니까?
      </DIV>
      <button type='button' id='btn_delete_proc'>삭제 진행</button>
      <button type='button' id='btn_delete_close'>취소</button>
    </form> 
  </DIV>

  <form name='frm' id='frm' action='' method='POST'>
      <input type='hidden' name='categoryno' id='categoryno' value=''>
    <fieldset class='fieldset_basic'>
      <legend class='legend_basic'>카테고리 등록(*: 필수)</legend>
      <DIV style='text-align: center;'>
      <ul>
        <li class='li_none'>
          <input type='text' name='title' id='title' value='' style='width: 25%;' placeholder="게시판 제목">*순서 <input type='number' name='seqno' id='seqno' value='<%=categoryProc.getMaxSeqno() %>' min='1' max='1000' step='1' style='width: 5%;'>
           View 
          <label>
            <input type='radio' name='visible' id='visible' value='Y' checked="checked"> Y
          </label>
          <label>
            <input type='radio' name='visible' id='visible' value='N'> N
          </label>
          <input type='text' name='ids' id='ids' value='admin'  style='width: 20%;' placeholder="접근 계정">*
                     
          <button type='button' id='btn_send' style='background-color: #ffa2a2; width:50px;border:none; padding: 3px 0;border-radius:10px;color:white;' >등록</button>
          <button type='button' id='btn_init' style='background-color: #ffa2a2; width:50px;border:none; padding: 3px 0;border-radius:10px; color:white; ' >취소</button>
        </li>
      </ul>
      </DIV>
    </fieldset>
    
  </form>

  <TABLE class='table_basic'>
    <colgroup>
      <col style='width: 5%;' />   <!-- 출력 순서 -->
      <col style='width: 30%;' /> <!-- 제목 -->
      <col style='width: 30%;' /> <!-- 접근 계정 -->
      <col style='width: 10%;' /> <!-- 출력 선택 -->
      <col style='width: 10%;' /> <!-- 등록된 자료수 -->
      <col style='width: 15%;' /> <!-- 기타 -->
    </colgroup>
        <thead>
    <TR>
      <TH class='th_basic' style='border-left:none;'>순서</TH>
      <TH class='th_basic'>제목</TH>
      <TH class='th_basic'>계정</TH>
      <TH class='th_basic'>출력</TH>      
      <TH class='th_basic'>자료수</TH>
      <TH class='th_basic' style='border-right:none;'>기타</TH>
    </TR>
      </thead>
    <tbody id='list'>
      
    
    </tbody>
  </TABLE>
  
   <DIV id='panel_btn_list_add' data-now-page='1' 
         style='border: solid 1px #AAAAAA; margin: 0px auto; width: 100%; background-color: #AAAAAA;'>
    <button id='btn_list_add' style='width: 100%;'>더보기 ▽</button>     
  </DIV>  

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html>

 
