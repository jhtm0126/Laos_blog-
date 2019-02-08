<%@ page contentType="text/plain; charset=UTF-8" %>
 
<%@ include file="./ssi-json.jsp" %>
 
<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
 
CategoryVO categoryVO = categoryProc.read(categoryno);

JSONObject json = new JSONObject();
 
json.put("categoryno",categoryVO.getCategoryno());
json.put("title", categoryVO.getTitle());
json.put("seqno", categoryVO.getSeqno());
json.put("visible", categoryVO.getVisible());
json.put("ids", categoryVO.getIds());


 
out.println(json);
%>