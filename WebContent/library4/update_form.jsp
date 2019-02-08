<%@ page contentType="text/html; charset=UTF-8" %> 
 
<%@ include file = "./ssi.jsp"  %>
<%@ include file = "./master.jsp"  %>

 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
 
int libraryno = Integer.parseInt(request.getParameter("libraryno"));

LibraryVO libraryVO = libraryProc.read(libraryno);
%>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
        src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        
<script src="http://malsup.github.com/jquery.form.js"></script> 
 <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/javascript">


$(function(){
  CKEDITOR.replace('content');
  var frm2 = $('#frm2');
  var frm3 = $('#frm3');
  frm2.ajaxForm(send_response);
  frm3.ajaxForm(send_response_delete);
  $('#mark').on('click', img_update);
  $('#mark1').on('click', img_delete);
  
  });
//수정폼
function img_update() {//ok
  $('#update_div').appendTo($('#show'));
  $('#imgdiv').hide();
  $('#picture').hide();
  $('#update_div').show();

 }

//삭제폼
function img_delete() {//ok
  $('#delete_img').appendTo($('#show'));

  $('#imgdiv').hide();
  $('#picture').hide();
  $('#imgdiv').hide();
  $('#timg').hide();
  $('#close').hide();
  $('#delete_img').show(); 
  $('#new').hide();
  
}
function send_response_delete(rdata){
  var frm = $('#frm');
  
  $('#timg').html(rdata); // panel에 전송 결과 출력
  $('#timg').hide();
  $('#delete_img').hide(); 
  
  $('#new').appendTo($('#show'));
  $('#new').show();
}

function send_response(rdata){
  var frm = $('#frm');
  
  $('#timg').html(rdata); // panel에 전송 결과 출력
  $('#timg').show();

}

function send(){//ok
  $('#frm2').submit();

}

function send1(){//ok
  $('#frm3').submit();
  alert('삭제 됐습니다.');
 
}

</script>
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>  

   <!-- frm2 START------------------------------------------------------------- -->
 <DIV style="display:none;" id='update_div'>
     
      <SPAN> 기존에 등록된 파일은 삭제되고 새로운 파일이 등록됩니다.</SPAN> 
   <div style="width: 100%; margin: 0px auto; text-align: center;" id="update_img">
      <form name="frm2" id="frm2" action="img_update_proc.jsp" method="post" enctype="multipart/form-data" >
        <input type="file" name="file_name" id="file_name"><br>
        <input type='hidden' name='categoryno' value='<%=categoryno %>'>
        <input type='hidden' name='libraryno' value='<%=libraryno %>'>

      
        <div id='close' >
          <input type="button" value="수정" onclick="send();">
        </div>     
      </form>
   </DIV>
</DIV> 

  <!-- frm2 END ---------------------------------------------------------------------->
      
  <!-- frm3 START------------------------------------------------------------------- -->
   <div style="width: 100%; margin: 0px auto; text-align: center; display:none;" id="delete_img" >
     <FORM name='frm3' id ='frm3' method='POST' action='./img_delete_proc.jsp'>
          <input type='hidden' name='categoryno_del' value='<%=categoryno %>'>
          <input type='hidden' name='libraryno_del' value='<%=libraryno %>'>
 
      <fieldset class='fieldset_no_line'>
        <ul>
          <li class='li_center'>
          <label for='file1'> </label>
            <%
            if (Tool1.isImage(libraryVO.getFstor1())) { // 이미지 일경우
            %>
              <IMG src='./storage/<%=libraryVO.getFstor1() %>' style='width: 400px;'>
            <%  
            } else { // 이미지가 아닐 경우
              out.println(libraryVO.getFile_name());              
            }
            %>
        <div id= 'new'>
          <button type='button' onclick="location.href='./create_file1_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'">새로운 파일 등록시 클릭하세요.</button>
        </div>
        <input type="button" value="삭제" onclick="send1();">
        </li>        
      </ul>
      </fieldset>
  </FORM>
 </DIV>
 <!------ frm3 END ---------------------------------------------------------------------->
  
<DIV class='aside_menu'>
<ASIDE style='float: left;'><A href='../category4/list.jsp'>게시판</A>＞<A href='./list.jsp?categoryno=<%=categoryno %>'><%=category_title %></A>＞수정</ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'>펼쳐보기</A>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
</DIV>
 
<FORM name='frm' method='POST' action='update_proc.jsp'>
  <input type='hidden' name='categoryno' value='<%=categoryno %>'>
  <input type='hidden' name='libraryno' value='<%=libraryno %>'>
  <input type='hidden' name='col' value='<%=col %>'>
  <input type='hidden' name='word' value='<%=word %>'>
  <input type='hidden' name='nowPage' value='<%=nowPage %>'>
  <input type ='hidden' name='image' id='image' value='' >
  <input type ='hidden' name='thumb' id='thumb' value='' >
  
  
  
  
  <fieldset class='fieldset_no_line'>
    <ul>
      <li class='li_none'>
        <label for='spot'>타이틀: </label>
        <input class='input_basic' type='text' name='spot' id='spot' value='<%=Tool1.convertChar(libraryVO.getSpot()) %>' style='width: 80%;'>
      </li>
      <li class='li_none'>
        <label for='content'>내용: </label><br>
        <TEXTAREA name='content' id='content' style="font-size:12; color:#000000;border:1px solid; width: 100%" rows="30"><%=libraryVO.getContent() %></TEXTAREA>
      </li>  
      <li class='li_none'>
        <label for='site'>WEB URL: </label>
        <input class='input_basic'  type="text" name='site' id='site' size='50' value='<%=libraryVO.getSite()%>'>
      </li>
      <li class='li_none'>
        <label for='writer'>작성자: </label>
        <input class='input_basic'  type='text' name='writer' id='writer' value='<%=libraryVO.getWriter()%>' size='10'>
 
        
        <label for='pw'>패스워드: </label>
        <input class='input_basic'  type='password' name='pw' id='pw' value='' size='5'>
      
        <button type="submit">변경된 내용 저장</button>
        <button type="button" onclick="location.href='./list.jsp?categoryno=<%=categoryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'">수정 취소</button>
      </li>      
      <li class='li_none'>
        <HR class='hr_dotted'>
        <br>
        추가 컨텐츠 변경
        <HR class='hr_dotted'>
      </li>        
 
      <li class='li_center'  id='show'>
       <label for='file_name'>참고 파일: </label>
       
        <%
        if (libraryVO.getSize() > 0) { // 파일이 존재하는 경우
          if (Tool1.isImage(libraryVO.getFile_name())) { // 이미지 일경우
          %>
            <div id='imgdiv'><IMG src='./storage/<%=libraryVO.getFile_name()%>' style='width: 200px;'></div>
             <div style="width: 200px; margin: 0px auto; text-align: center;" id="timg"></div>
          <%  
          } else { // 이미지가 아닐 경우
            out.println(libraryVO.getFile_name());              
          }
        %>
        <div id='picture' >
           <IMG src='./images/update.png' id='mark' title='이미지 수정'>
           <IMG src='./images/delete.png' id='mark1' title='이미지 삭제'>
          
       </div>
        <%
        } else { // 파일이 없는 경우
        %>
          <!-- 
          <input class='input_basic'  type="file" name='file1' id='file1' size='50'> (10 MB 이하만 전송 가능)
          -->
          <button type='button' onclick="location.href='./create_file1_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno %>&col=<%=col%>&word=<%=word%>&nowPage=<%=nowPage%>'">새로운 파일 등록시 클릭하세요.</button>
        <%  
        }
        %>        
     <br>
      </li>
      
      
      
      <!-- ******************* Youtube 관련 시작 ****************** -->
      <li class='li_center'>
        <HR class='hr_dotted'>
        
        <%
        // -------------------------- Youtube -------------------------- 
        String youtube = libraryVO.getYoutube();
        if (youtube == null) { // checkNull 메소드로 변경 예정
          youtube = "";
        }
        if (youtube.trim().length() > 0) { // 지도가 있는 경우만 출력
        %>
        <DIV style='width:900px; margin: 0px auto;'>
          <DIV style='width:854px; float: left'>
            <%=libraryVO.getYoutube() %> 
          </DIV>
          <DIV style='margin: 0px auto; float: left'>
            <A href="./replace_youtube_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno%>"><IMG src='./images/update.png' title="지도 변경"></A>
            <A href="./delete_youtube_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno%>"><IMG src='./images/delete.png' title="지도 삭제"></A>   
          </DIV>
        </DIV>
        
        <%
        } else {
        %>
          <A class='button' style='text-decoration: none; ' href='./create_youtube_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno%>'>새로운 Youtube 영상 등록</A>
        <%  
        }
        %>
      </li>
      <!-- ******************* Youtube 관련 종료 ****************** -->
      
      <!-- ********************** MP4 관련 시작 ******************** -->
      <li class='li_center'>
        <HR class='hr_dotted'>
        <%
        // -------------------------- MP4 파일 -------------------------- 
        String video = Tool1.checkNull(libraryVO.getVideo());
        if (video.trim().length() > 0) {
        %>
        <DIV style='width:620px; margin: 0px auto;'>
          <DIV style='width:570px; margin: 0px auto; float: left;'>
            <VIDEO controls src='./storage/<%=video %>' style='width: 80%;'></VIDEO>
          </DIV>
          <DIV style='width:50px; margin: 0px auto; float: left'>
            <A href="./replace_mp4_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno%>"><IMG src='./images/update.png' title="MP4 변경"></A>
            <A href="./delete_mp4_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno%>"><IMG src='./images/delete.png' title="MP4 삭제"></A>   
          </DIV>        
        </DIV>
        <%
        } else {
        %>
          <A class='button' style='text-decoration: none; ' href='./create_mp4_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno%>'>새로운 MP4파일 등록</A>
        <%  
        }
        %>
      </li> 
      <!-- ********************** MP4 관련 종료 ******************** -->
      
      
        <!-- ******************** Map 관련 시작 ******************** -->
      <li class='li_center'>
        <HR class='hr_dotted'>
      
       <%
        // -------------------------- Map -------------------------- 
        String map_info = Tool1.checkNull(libraryVO.getMap_info());
        
        if (map_info.trim().length() > 0) { // 지도가 있는 경우만 출력
        %>
        <DIV style='width:570px; margin: 0px auto;'>
          <DIV style='width:520px; float: left'>
            <%=libraryVO.getMap_info() %> 
          </DIV>
          <DIV style='width:50px; margin: 0px auto; float: left'>
            <A href="./replace_map_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno%>"><IMG src='./images/update.png' title="지도 변경"></A>
            <A href="./delete_map_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno%>"><IMG src='./images/delete.png' title="지도 삭제"></A>   
          </DIV>
        </DIV>
        
        <%
        } else {
        %>
          <A class='button' style='text-decoration: none; ' href='./create_map_form.jsp?categoryno=<%=categoryno %>&libraryno=<%=libraryno%>'>새로운 지도 등록</A>
        <%  
        }
        %>
        
      </li>     
      <!-- ******************** Map 관련 종료 ******************** -->
      
    </ul>
  </fieldset>
 
</FORM>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html> 
 