<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<%
String tempDir = application.getRealPath("/library4/temp"); // WebContent 기준
String upDir = application.getRealPath("/library4/storage");
// request, 메모리에 저장할 크기, 업로드할 최대 파일 크기, 임시 저장 폴더
Upload upload = new Upload(request, -1, 10485760, tempDir);

/* col = Tool1.checkNull(upload.getParameter("col"));
word = upload.toKor(Tool1.checkNull(upload.getParameter("word")));
nowPage= Integer.parseInt(upload.getParameter("nowPage")); */
 
int categoryno = Integer.parseInt(upload.getParameter("categoryno"));

CategoryVO categoryVO = categoryProc.read(categoryno);
String category_title = categoryVO.getTitle();
int count = 0;

  int libraryno = Integer.parseInt(upload.getParameter("libraryno"));
 System.out.println("libraryno:"+libraryno);
 
 
  LibraryVO libraryVO = new LibraryVO();
  libraryVO.setLibraryno(libraryno);  
  
  //<input type='file' name='file1' style='width: 100%;'>
  FileItem fileItem = upload.getFileItem("file_name");
  String file_name = "";   // 사용자가 전송한 파일명
  String fstor1 = ""; // 실제 서버상의 디스크에 저장되는 파일명
  String thumb = ""; // preview mini 이미지
  long size = 0;      // 원본 파일의 크기
 
  file_name = fileItem.getName(); // 원본 파일명
  libraryVO.setFile_name(file_name);
 
  size = fileItem.getSize(); // 파일 크기
  file_name = fileItem.getName(); // 원본 파일명
  libraryVO.setFile_name(file_name);
 
   
    if (size>0&&size < 10485760) { // 1 ~ 10485760 byte
      // -----------------------------------------------------
      // 기존 파일 삭제
      // -----------------------------------------------------
      LibraryVO oldLibraryVO = libraryProc.read(libraryno);   // 등록된 파일정보 읽어오기
      Tool1.deleteFile(upDir, oldLibraryVO.getFstor1());
      Tool1.deleteFile(upDir, oldLibraryVO.getFile_name());// 저장된 파일 삭제
      Tool1.deleteFile(upDir,oldLibraryVO.getThumb()); // thumb 삭제
      // -----------------------------------------------------
      
      // 새로운 파일 저장
      fstor1 = upload.saveFile(fileItem, upDir); // Tomcat이 전송받은 파일을 서버에 저장
      libraryVO.setFstor1(file_name);
      libraryVO.setSize(size);
      
      // 업로드 디렉토리, 소스 파일명, width, height
      if (Tool1.isImage(file_name)) {
        libraryVO.setThumb(Tool1.preview(upDir, file_name, 200, 150)); // Thumbnail 이미지 생성
      }
      
      count= libraryProc.new_create_file_name(libraryVO); // DBMS 적용
      
      if(count==1){
        %>
        <IMG src='./storage/<%=libraryVO.getFile_name() %>' style='width: 200px;'>
      <%
      
      }else{
        
      }
    }


%>
 
