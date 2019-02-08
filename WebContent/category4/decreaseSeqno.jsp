<%@ page contentType="text/plain; charset=UTF-8" %>

<%@ include file="./ssi-json.jsp" %>

<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));

categoryProc.decreaseSeqno(categoryno);
CategoryVO categoryVO = categoryProc.read(categoryno);



JSONObject json = new JSONObject();
json.put("seqno",categoryVO.getSeqno());
System.out.println("seqno:"+categoryVO.getSeqno());
out.println(json);
%>


