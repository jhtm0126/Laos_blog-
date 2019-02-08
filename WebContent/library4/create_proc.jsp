<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<%
String tempDir = application.getRealPath("/library4/temp");  // 파일이 올라갈때 잠깐 생김
String upDir = application.getRealPath("/library4/storage");  // 
 
   Upload upload = new Upload(request, -1, -1, tempDir);//
   int categoryno = Integer.parseInt(upload.getParameter("categoryno"));
   //String category_title = "해외 영화";
  CategoryVO categoryVO = categoryProc.read(categoryno);
  String category_title = categoryVO.getTitle();
%>
 
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<link href="../css/style.css" rel='Stylesheet' type='text/css'>
</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 
<DIV class='aside_menu'>
  <ASIDE style='float: left;'><A href='../category4/list.jsp'>게시판</A>＞<A href='./list.jsp?categoryno=<%=categoryno %>'><%=category_title %></A> >등록</ASIDE> 
  <ASIDE style='float: right;'>
    <A href='./list_category_table2.jsp?categoryno=<%=categoryno %>'>목록형</A> <span class='menu_divide'> |</span> 
    <A href='./list.jsp?categoryno=<%=categoryno %>'>앨범형</A> <span class='menu_divide'> |</span>
    <A href='./list_read.jsp'>펼쳐보기</A>
  </ASIDE> 
  <DIV class='menu_line'></DIV>
</DIV>
 
  <DIV class='message'>
<%
String spot = upload.toKor(upload.getParameter("spot"));//toKor 하지 않으면 한글이 깨짐
String content = upload.toKor(upload.getParameter("content"));
String site = upload.toKor(upload.getParameter("site"));
String writer = upload.toKor(upload.getParameter("writer"));
String pw = upload.toKor(upload.getParameter("pw"));
String map_info=upload.toKor(upload.getParameter("map_info"));
String youtube=upload.toKor(upload.getParameter("youtube"));
 
LibraryVO libraryVO =new LibraryVO();
libraryVO.setCategoryno(categoryno);
libraryVO.setSpot(spot);
libraryVO.setContent(content);
libraryVO.setSite(site);
libraryVO.setWriter(writer);
libraryVO.setPw(pw);
libraryVO.setMap_info(map_info);
libraryVO.setYoutube(youtube);
libraryVO.setVisible("Y");

  
// <input type='file' name='file1' style='width: 100%;'>
FileItem fileItem = upload.getFileItem("file_name"); 
String file_name = "";   // 사용자가 전송한 파일명
// String fstor1 = ""; // 실제 서버상의 디스크에 저장되는 파일명
String thumb = ""; // preview mini 이미지
long size = 0;      // 원본 파일의 크기
boolean file_max_error = false; // 파일 최대 크기 초과여부
 
// file1 = fileItem.getName(); // 원본 파일명
// pds4VO.setFile1(file1);
 
size = fileItem.getSize();  // 파일 크기
 
if (size > 0 && size<= 10485760) { // 1 ~ 10485760, 10 MB
  // fall.jpg --> fall_1.jpg --> fall_2.jpg
  file_name = upload.saveFile2(fileItem, upDir); // Tomcat이 전송받은 파일 서버에 저장//크롬이 보낸 파일을 서버에 저장
  //System.out.println("저장된 파일명: " + file1);
  // pds4VO.setFstor1(fstor1); // 새로 저장된 파일명
  libraryVO.setFile_name(file_name);         //파일명 저장
  libraryVO.setSize(size);       // 파일 사이즈 저장
    
  if (Tool1.isImage(file_name)) { // 이미지인경우 thumb 이미지 생성
    thumb = Tool1.preview(upDir, file_name, 200, 150); // thumb 이미지 200 X 150 생성
    libraryVO.setThumb(thumb);
  }
} else if (size > 10485760 ){ // 10 MB over
  file_max_error = true;
  %>
  <span class='span_warning'>
    전송 파일의 크기는 10MB를 넘을 수 없습니다. 다시 시도해주세요.
  </span>
  <%  
}
// --------------------------------------------------------------------
// MP4 파일 업로드 처리
// --------------------------------------------------------------------
FileItem fileItemMP4 = null; // 서버로 전송된 파일 객체
String video= "";   // 사용자가 전송한 파일명
long sizeMP4 = 0;      // 원본 파일의 크기

// <input type='file' name='mp4' style='width: 100%;'>
fileItemMP4 = upload.getFileItem("video");
sizeMP4 = fileItemMP4.getSize(); // 파일 크기

if (sizeMP4 > 0) {
 if (sizeMP4 < 1048576000) { // 1 ~ 1048576000 byte, 1000 MB
   video = upload.saveFile(fileItemMP4, upDir); // Tomcat이 전송받은 파일을 서버에 저장
   libraryVO.setVideo(video); // 저장된 파일명 VO 객체에 저장
 } else {
%>
   <span class='span_warning'>
   전송 파일의 크기는 1000 MB(1GB)를 넘을 수 없습니다. 다시 시도해주세요.
   </span>
<%    
 }
} 
//--------------------------------------------------------------------
 



if (file_max_error == false) { // 파일 관련 에러가 없는 경우
  int count = libraryProc.create(libraryVO); // DBMS
 
  if (count == 1) { 
    categoryProc.increaseCnt(categoryno);
  %>
    <span class='span_info'>
      자료를 등록했습니다.
    </span>
  <%    
  } else {
    // DBMS에 등록중에 실패했음으로 업로드 파일 삭제
    upload.deleteFile(upDir, file_name);
    upload.deleteFile(upDir, thumb);
         
  %>
    <span class='span_warning'>
      자료 등록에 실패했습니다. 다시 시도해 주세요.
    </span>
  <%
  }
}
%>
  </DIV>  
 
  <DIV class='bottom_menu'>
    <button type='button' onclick="location.href='./create_form.jsp?categoryno=<%=categoryno %>'">계속 등록</button>
    <button type='button' onclick="location.href='./list.jsp?categoryno=<%=categoryno %>'">목록</button>
  </DIV>
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>