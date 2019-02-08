<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<DIV>
  <fieldset class='fieldset_home'>
    <legend class='legend_home'>공지사항 <A href='<%=root%>/notice3/list.jsp'>More...</A></legend>
    <ul>
    <%
    ArrayList<UrlVO> list = urlDAO.list_home(5); //5개의 레코드만 가져오겟다.
    
    for (int index=0; index < list.size(); index++) {
     UrlVO urlVO = list.get(index);
    %> 
      <LI class='li_home' style='font-size:0.9em;'>
        <A href='<%= root %>/review4/list2.jsp'><%=Tool1.textLength(urlVO.getAddress(), 15)%></A>
      
        <span style='font-size: 0.7em;'><!-- 부모 태그의 글꼴의 90% 크기로 출력-->
       [
          <%=Tool1.textLength(urlVO.getTitle(),15)%><%=urlVO.getRdate().substring(0,10)%>
          ]
        </span>
        
      </LI>
    <%
    }
    %>
    </ul> 
  </fieldset>
</DIV>