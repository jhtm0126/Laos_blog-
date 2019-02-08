<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ include file="./ssi.jsp" %>
 
<DIV>
  <fieldset class='fieldset_home'>
    <legend class='legend_home'>리뷰 <A href='<%=root%>/review4/list2.jsp'> More...</A></legend>
    <ul>
    <%
    ArrayList<ReviewVO> list = reviewProc.list_home(5); //5개의 레코드만 가져오겟다.
    
    for (int index=0; index < list.size(); index++) {
     ReviewVO reviewVO = list.get(index);
    %> 
      <LI class='li_home' style='font-size:0.9em;'>
        <A href='<%= root %>/review4/list2.jsp'><%=Tool1.textLength(reviewVO.getContent(),15)%></A>
      
        <span style='font-size: 0.7em;'><!-- 부모 태그의 글꼴의 90% 크기로 출력-->
          [
          <%=Tool1.textLength(reviewVO.getTitle(),15)%><%=reviewVO.getRdate().substring(0,10)%>
          ]
        </span>
        
      </LI>
    <%
    }
    %>
    </ul> 
  </fieldset>
</DIV>