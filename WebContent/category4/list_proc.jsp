<%@ page contentType="text/plain; charset=UTF-8" %> 
 
<%@ include file = "./ssi-json.jsp" %>
 
<%
int totalCount = categoryProc.count(); // 전체 레코드 갯수
System.out.println("totalCount: " + totalCount);
int totalPage =  (int)(Math.ceil(totalCount/5.0));
System.out.println("totalPage: " + totalPage);
 
int nowPage = 1; // 시작 페이지
if (request.getParameter("nowPage") != null){
  nowPage = Integer.parseInt(request.getParameter("nowPage"));
 
}
System.out.println("nowPage: " + nowPage);
 
JSONObject main_obj = null;//글 목록, 전체 페이지수
 
JSONArray jsonList = new JSONArray();//글 목록
 
JSONObject obj = null;//한건의 글
 
main_obj = new JSONObject();
 
ArrayList<CategoryVO> list = categoryProc.list(nowPage);
int count = list.size();
System.out.println("count: " + count);
 
// noticeno, title, rname, rdate
for (int i=0; i < count; i++) {
  CategoryVO categoryVO = list.get(i);
  
  obj = new JSONObject();
  obj.put("categoryno",categoryVO.getCategoryno());
  obj.put("title", categoryVO.getTitle());
  obj.put("seqno", categoryVO.getSeqno());
  obj.put("visible", categoryVO.getVisible());
  obj.put("ids", categoryVO.getIds());
  obj.put("cnt", categoryVO.getCnt());

  
 
  jsonList.add(obj);
}
 
main_obj.put("totalPage", totalPage); // 전체 페이지수
main_obj.put("list", jsonList);//글 목록
 
out.print(main_obj);
/*
{
  "list":[
         {"noticeno":42,"rname":"5","rdate":"2018-10-23","title":"5"},
         {"noticeno":41,"rname":"5","rdate":"2018-10-23","title":"5"},
         {"noticeno":40,"rname":"4","rdate":"2018-10-23","title":"4"}
  ]
  ,"nowPage":1
}
*/
%>