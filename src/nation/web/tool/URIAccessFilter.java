package nation.web.tool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class URIAccessFilter implements Filter {
  private String charSet = null;

  @Override
  public void init(FilterConfig config) throws ServletException {
    // /WEB-INF/web.xml������ <init-param>�±��� ��
    charSet = config.getInitParameter("charSet");

    //System.out.println("������������������������");
   // System.out.println(" URIAccessFilter start...");
  //  System.out.println("������������������������");
  }

  // ��û�� ���� �ڵ� ����
  public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    DBOpen dbopen = null;
    DBClose dbclose = null;
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    Connection con = null;
    PreparedStatement pstmt = null;
    request.setCharacterEncoding(charSet); // �ѱ� ���ڼ� ����
    String uri = request.getRequestURI(); // ��û uri ���� �κ�, /blog_v6jq/index.jsp... 
    boolean login = false;

    // ------------------------------------------------------------------------------
    // URL DBMS ���
    // ------------------------------------------------------------------------------
    String ip = request.getRemoteAddr();
    // System.out.println("ip: " + ip);

    try {
      dbopen = new DBOpen();
      dbclose = new DBClose();
      con = dbopen.getConnection();

      StringBuffer sql = new StringBuffer();
      sql.append(" INSERT INTO uriLog(id, ip, uri, uridate)");
      sql.append(" VALUES(?, ?, ?, now())");

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, "guest"); // session���� �����ϼ���.
      pstmt.setString(2, ip);
      pstmt.setString(3, uri);

      pstmt.executeUpdate();
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt);
    }

    // ------------------------------------------------------------------------------
    // ���� ���� URL üũ, DBMS ��ȸ�� ���� ����
    // ------------------------------------------------------------------------------
    String conpath = request.getContextPath();
    // System.out.println("conpath: " + conpath); // /blog_v6jq
    
    int pos = uri.indexOf(conpath);
    if (pos >= 0) { // ���� �����, /blog_v6jq 
      uri = uri.substring(conpath.length(), uri.length()); // ���� ���ڿ� ~ �����ڿ� - 1
    }
    
    ArrayList<String> guest_list = UriList.getGuest_list();
    for (int index = 0; index < guest_list.size(); index++) {
      if (guest_list.get(index) .equals(uri)) {
        login = true;
      }
    }
    //System.out.println("uri: " + uri);
    //System.out.println("login: " + login);
    // ------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------
    // session�� ��� ��
    // ------------------------------------------------------------------------------
    // ���ο� ������ ���������ʰ� ������ ���� ��ü�� ��ȯ
    
    HttpSession session = request.getSession(false);
     
    if (session != null) {
      if (session.getAttribute("admin4no") != null && session.getAttribute("email") != null) { 
        login = true; 
      } 
    }
    
    if (login) { 
      chain.doFilter(request, response); // �ٸ� ���� ����, ��û JSP ���� 
      
    } else { // �մ��̸� �α��� �������� �̵�, ������Ʈ�� ���� 
      RequestDispatcher dispatcher = request.getRequestDispatcher("/admin4/login_ck_form.jsp");
      dispatcher.forward(request, response); 
    }
     
    // ------------------------------------------------------------------------------

  }

  public void destroy() {

  }
}
