package nation.web.tool;

import java.util.ArrayList;

// ���� ���� ���
public class UriList {
  public static ArrayList<String> getGuest_list(){ // class�� �޸𸮿� �ε��ɶ� �ѹ� ����
    ArrayList<String> guest_list = new ArrayList<String>();
guest_list.add("/index.jsp");
    
   // guest_list.add("/review4/list2.jsp");    // ��������
    guest_list.add("/category4/create_proc.jsp");   
    
    //guest_list.add("/notice3/list.jsp");    // ��������

    
    guest_list.add("/library4/list_total_table.jsp"); // �Խ��� ���
    guest_list.add("/library4/list.jsp");

    
    guest_list.add("/library4/read.jsp");                     // �Խ��� ��ȸ 
    
    guest_list.add("/admin4/login_ck_form.jsp");  
    guest_list.add("/admin4/login_ck_proc.jsp");  
    guest_list.add("/admin4/logout_proc.jsp");   
    

    

    
    return guest_list;
  }
  
}