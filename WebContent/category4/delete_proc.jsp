

<%@ page contentType="text/plain; charset=UTF-8" %>
 
<%@ include file="./ssi-json.jsp" %>
 
<%
JSONObject json = new JSONObject();
ArrayList<String> msgs = new ArrayList<String>();
 
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
CategoryVO categoryVO = categoryProc.read(categoryno);
 
int count = categoryProc.delete(categoryno);
int countByCategory = categoryProc.countByCategory(categoryno);
if(count==1){
  msgs.add("공지사항을 삭제했습니다.");
}else if (countByCategory > 0||count==0) { 
  msgs.add("공지사항 삭제에 실패했습니다.");
  msgs.add(countByCategory+" 건 발견됐습니다.");
  msgs.add("("+categoryVO.getTitle()+" 게시판에서 발견됐습니다.)");
  
  msgs.add("카테고리 관련된 글을 삭제해주세요.");
  msgs.add("<A href=../library4/delete_category_form.jsp?categoryno="+categoryno+">[관련 레코드 삭제하러 가기]</A> ");

}
 
json.put("msgs", msgs);
out.println(json);
 
%>