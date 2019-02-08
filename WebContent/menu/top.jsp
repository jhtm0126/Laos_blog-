<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.ArrayList" %>

<%@ page import="nation.web.category4.CategoryVO" %>
<%@ page import="nation.web.category4.CategoryProc" %>

<%@ page import="nation.web.admin4.Admin4VO" %>
<%@ page import="nation.web.admin4.AdminProc" %>

<%@ page import="nation.web.admin4.AdminTool" %>


  <% // System.out.println("root: " + request.getContextPath()); %>
  
  <%
  String root = request.getContextPath();
  CategoryProc categoryProc = new CategoryProc();
  AdminProc adminProc = new AdminProc();
  Admin4VO admin4VO = new Admin4VO();
  %>

<DIV class='top_menu'
        style=" background-image: url('<%=root %>/menu/images/fin2.jpg')" >
  <div class='top_menu_label' style='font-family: Nanum Pen Script;'>Laos blog 0.9</div>
  
  <div class='top_menu_list' >
    <A class='menu_link' href='<%=root %>/index.jsp'>HOME</A><span class='top_menu_sep'> |</span>
    <%-- <A class='menu_link'  href='<%=root %>/notice2/list.jsp'>즐겨찾기2</A><span class='top_menu_sep'>|</span> --%>
    <A class='menu_link'  href='<%=root %>/notice3/list.jsp'>즐겨찾기</A><span class='top_menu_sep'>|</span>
    <A class='menu_link'  href='<%=root %>/review4/list2.jsp'>리뷰작성</A><span class='top_menu_sep'>|</span>
    
    
     <%
    ArrayList<CategoryVO> list = categoryProc.list();
    
    int cnt = list.size();
    for (int index=0; index < cnt; index++) {
      CategoryVO categoryVO = list.get(index);
      int categoryno = categoryVO.getCategoryno();
      String title = categoryVO.getTitle();
    %>
    <A class='menu_link'  href='<%= root %>/library4/list.jsp?categoryno=<%=categoryno %>'><%=title %></A><span class='top_menu_sep'>|</span>
    <% 
    }
    %>
    <A class='menu_link'  href='<%=root %>/library4/list_total_table.jsp'>전체글</A><span class='top_menu_sep'>|</span>
    

      <%//마스터 일경우
      if (AdminTool.isMaster(request)) { 
      %>
        <A class='menu_link'  href='<%=root %>/category4/list.jsp'>Category</A><span class='top_menu_sep'>|</span>
        <A class='menu_link' href='<%=root %>/admin4/list.jsp'>Member</A><span class='top_menu_sep'>|</span>
        
        <%
        int admin4no = admin4VO.getAdmin4no();
        %> 
         <A class='menu_link'  href='<%= root %>/admin4/list_visit.jsp'>방문 내역</A><span class='top_menu_sep'>|</span>

        
        <span class='email'> [<%=session.getAttribute("email")%>]</span><span class='top_menu_sep'>|</span>
      <A class='menu_link' href='<%=root %>/admin4/logout_proc.jsp'>Logout</A><span class='top_menu_sep'>|</span>
      <%  
      }else if (AdminTool.isAdmin(request)) { 
      %>
      <span> [<%=session.getAttribute("email")%>]</span><span class='top_menu_sep'>|</span>
   
       
      <A class='menu_link' href='<%=root %>/admin4/logout_proc.jsp'>Logout</A>
    <%
     }else {
    %>
    <A class='menu_link' href='<%=root %>/admin4/login_ck_form.jsp'>Login</A><span class='top_menu_sep'>|</span>
    <%  
    }
    %>
   
  </div>
</DIV>  



 

