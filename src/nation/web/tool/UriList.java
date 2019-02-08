package nation.web.tool;

import java.util.ArrayList;

// 접근 가능 목록
public class UriList {
  public static ArrayList<String> getGuest_list(){ // class가 메모리에 로딩될때 한번 실행
    ArrayList<String> guest_list = new ArrayList<String>();
guest_list.add("/index.jsp");
    
   // guest_list.add("/review4/list2.jsp");    // 공지사항
    guest_list.add("/category4/create_proc.jsp");   
    
    //guest_list.add("/notice3/list.jsp");    // 공지사항

    
    guest_list.add("/library4/list_total_table.jsp"); // 게시판 목록
    guest_list.add("/library4/list.jsp");

    
    guest_list.add("/library4/read.jsp");                     // 게시판 조회 
    
    guest_list.add("/admin4/login_ck_form.jsp");  
    guest_list.add("/admin4/login_ck_proc.jsp");  
    guest_list.add("/admin4/logout_proc.jsp");   
    

    

    
    return guest_list;
  }
  
}