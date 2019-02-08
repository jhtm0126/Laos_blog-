<%@ page contentType="text/plain; charset=UTF-8" %>
 
<%@ include file="./ssi-json.jsp" %>
 
<jsp:useBean id="categoryVO" class="nation.web.category4.CategoryVO" />
<jsp:setProperty name="categoryVO"  property="*" />

 
<%
JSONObject json = new JSONObject();
ArrayList<String> msgs = new ArrayList<String>();

System.out.println("==>title:"+categoryVO.getTitle());
System.out.println("seqno:"+categoryVO.getSeqno());
System.out.println("visible:"+categoryVO.getVisible());
System.out.println("ids:"+categoryVO.getIds());
System.out.println("categoryno:"+categoryVO.getCategoryno());

/* 
int categoryno = Integer.parseInt(request.getParameter("categoryno")); */

int count = categoryProc.update(categoryVO); // 수정 처리
 
if (count == 1) {
  msgs.add("공지사항을 수정했습니다.");
} else {
  msgs.add("공지사항 수정에 실패했습니다.");
  msgs.add("다시한번 시도해주세요.");
}
 
json.put("msgs", msgs);
out.println(json);
%>