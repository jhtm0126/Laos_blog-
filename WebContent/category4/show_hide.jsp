<%@ page contentType="text/plain; charset=UTF-8" %>
 
<%@ include file="./ssi-json.jsp" %>

<%
int categoryno = Integer.parseInt(request.getParameter("categoryno"));
String visible = request.getParameter("visible");


CategoryVO categoryVO = categoryProc.read(categoryno);
JSONObject json = new JSONObject();

if(categoryVO.getVisible().equals("Y")){
  categoryProc.hide(categoryno);
  json.put("visible",categoryVO.getVisible());
    }else if (categoryVO.getVisible().equals("N")) {
      categoryProc.show(categoryno);
  json.put("visible",categoryVO.getVisible());
}


out.println(json);
%>










