<%@ page contentType="text/plain; charset=UTF-8" %> 

<%@ include file = "./ssi-json.jsp" %>

<jsp:useBean id="categoryVO" class="nation.web.category4.CategoryVO" />  
<jsp:setProperty name="categoryVO" property="*" />
  

      <%
      JSONObject json = new JSONObject();
      ArrayList<String> msgs = new ArrayList<String>();//JSON 배열로 저장
       
      
      int count = categoryProc.create(categoryVO); // 처리된 레코드 갯수
      
      if (count == 1) {
        msgs.add("공지사항을 등록했습니다.");
  
      } else {
        msgs.add("공지사항 등록에 실패했습니다.");
        msgs.add("다시한번 시도해주세요.");
      }
   
      json.put("msgs", msgs);
      out.print(json);

%>
