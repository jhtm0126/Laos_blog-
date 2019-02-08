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
    // /WEB-INF/web.xml파일의 <init-param>태그의 값
    charSet = config.getInitParameter("charSet");

    //System.out.println("────────────");
   // System.out.println(" URIAccessFilter start...");
  //  System.out.println("────────────");
  }

  // 요청이 오면 자동 실행
  public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    DBOpen dbopen = null;
    DBClose dbclose = null;
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    Connection con = null;
    PreparedStatement pstmt = null;
    request.setCharacterEncoding(charSet); // 한글 문자셋 변경
    String uri = request.getRequestURI(); // 요청 uri 추출 부분, /blog_v6jq/index.jsp... 
    boolean login = false;

    // ------------------------------------------------------------------------------
    // URL DBMS 기록
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
      pstmt.setString(1, "guest"); // session에서 추출하세요.
      pstmt.setString(2, ip);
      pstmt.setString(3, uri);

      pstmt.executeUpdate();
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      dbclose.close(con, pstmt);
    }

    // ------------------------------------------------------------------------------
    // 접근 가능 URL 체크, DBMS 조회로 변경 가능
    // ------------------------------------------------------------------------------
    String conpath = request.getContextPath();
    // System.out.println("conpath: " + conpath); // /blog_v6jq
    
    int pos = uri.indexOf(conpath);
    if (pos >= 0) { // 개발 모드라면, /blog_v6jq 
      uri = uri.substring(conpath.length(), uri.length()); // 시작 문자열 ~ 끝문자열 - 1
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
    // session의 사용 ★
    // ------------------------------------------------------------------------------
    // 새로운 세션을 생성하지않고 기존의 세션 객체를 반환
    
    HttpSession session = request.getSession(false);
     
    if (session != null) {
      if (session.getAttribute("admin4no") != null && session.getAttribute("email") != null) { 
        login = true; 
      } 
    }
    
    if (login) { 
      chain.doFilter(request, response); // 다른 필터 실행, 요청 JSP 실행 
      
    } else { // 손님이면 로그인 페이지로 이동, 프로젝트명 생략 
      RequestDispatcher dispatcher = request.getRequestDispatcher("/admin4/login_ck_form.jsp");
      dispatcher.forward(request, response); 
    }
     
    // ------------------------------------------------------------------------------

  }

  public void destroy() {

  }
}
