<%@ page contentType="text/plain; charset=UTF-8" %>
 
<%@ include file="./ssi-json.jsp" %>
 
<%

JSONObject json = new JSONObject();
ArrayList<String> msgs = new ArrayList<String>();

int libraryno = Integer.parseInt(request.getParameter("libraryno")); 
LibraryVO libraryVO = libraryProc.read(libraryno); // 조회
/* int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
String File_name= libraryVO.getFile_name(); */


 

/* LibraryVO libraryVO = libraryProc.read(libraryno);  */
System.out.println("libraryno:"+libraryno);


 msgs.add("기존에 등록된 파일은 삭제되고 새로운 파일이 등록됩니다.");

/*  msgs.add("<IMG src='./storage/"+File_name+" 'style='width:200px'>");  */

 msgs.add("패스워드:<input class='input_basic'  type='password' name='pw' id='pw' value='' size='5'>");
 msgs.add("<input class='input_basic'  type='file' name='file_name' id='file_name' size='50'>10MB이하");


json.put("msgs", msgs);
out.println(json);
%>

  
      