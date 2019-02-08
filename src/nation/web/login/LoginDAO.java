package nation.web.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nation.web.admin4.Admin4VO;
import nation.web.category4.CategoryVO;
import nation.web.login.LoginVO;

import nation.web.tool.DBClose;
import nation.web.tool.DBOpen;
 
/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.login
 * 파일명           : LoginDAO.java 2018. 12. 12.
 * 작성자           : 뚱이(jmy)
 * 작성자 email   : sjaqj23@naver.com
 * 수정내용
 * ------------------------------------------------------------------
 * 수정 이력
 * ------------------------------------------------------------------ 
 * 수정일        수정자  연락처               수정 내용
 * ------------------------------------------------------------------ 
 * 2016-05-01 아로미  mail@mail.com  회원 등록 변경
 *
 * ------------------------------------------------------------------
 * 
 *</pre>
 */
public class LoginDAO {
  private Connection con = null;              // DBMS 연결   
  private PreparedStatement pstmt = null; // SQL 실행
  private ResultSet rs = null;                   // SELECT 결과를 저장
  private StringBuffer sql = null;              // SQL 저장
  int count = 0; 
  
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  public LoginDAO() {
    this.dbopen = new DBOpen();
    this.dbclose = new DBClose();
  }
  
  /**
   * 조회
   * @param categoryno 조회할 카테고리 번호
   * @return 조회한 카테고리 객체
   */
  public LoginVO select_admin4(String email) {
    LoginVO loginVO = new LoginVO(); 
    
    try {
     con= dbopen.getConnection(); // DBMS에 연결
      sql = new StringBuffer();
      sql.append(" SELECT admin4no from admin4 where email='"+email+"' ");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
  
      rs = pstmt.executeQuery(); // SELECT
 
      // true일 경우 순환, 레코드가 있는지 검사
      // next() 최초 호출시는 첫번째 레코드로 이동
      // next() 두번째 호출부터 다음 레코드로 이동하여 마지막 레코드까지 이동
        if(rs.next()) {
        
          
          loginVO.setAdmin4no(rs.getInt("admin4no"));
          
         
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt,rs);
    }
    
    return loginVO;
  }
  
  
  
  


  /**
   * 관리자 등록
   * @param admin4VO 관리자 내용
   * @return 1: 등록 성공, 0: 등록 실패
   */
  public int create(LoginVO loginVO){
    
    dbopen = new DBOpen();
    dbclose = new DBClose();
    
    int count = 0;
    
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" INSERT INTO login(admin4no,name,ip, logindate)");
      sql.append(" VALUES(?, ?, ?,now())");
     
      
      pstmt = con.prepareStatement(sql.toString());
 
     /* pstmt.setInt(1, loginVO.getAdmin4no());*/
    
      pstmt.setInt(1,loginVO.getAdmin4no());
      pstmt.setString(2,loginVO.getName());
      pstmt.setString(3,loginVO.getIp());
      

     
      count = pstmt.executeUpdate();
      
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
 
    return count;
  }
  
  /**
   * 모든 레코드
   * 
   * @return
   */
  public ArrayList<LoginVO> list() {
    ArrayList<LoginVO> list = new ArrayList<LoginVO>();
 
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" select loginno,admin4no, name,ip,logindate");
      sql.append(" from login");
      sql.append(" ORDER BY loginno DESC");

      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        LoginVO loginVO = new LoginVO();//레코드 갯수만큼 객체 생성
        loginVO.setLoginno(rs.getInt("loginno")); // DBMS -> JAVA 객
        loginVO.setAdmin4no(rs.getInt("admin4no"));
        loginVO.setName(rs.getString("name"));
        loginVO.setIp(rs.getString("ip"));
        loginVO.setLogindate(rs.getString("logindate"));
       
 
        list.add(loginVO);
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return list;
  }
  
  /**
   * 조회
   * @param categoryno 조회할 카테고리 번호
   * @return 조회한 카테고리 객체
   */
  public LoginVO read(int loginno) {
    LoginVO loginVO = new LoginVO();
    
    try {
     con= dbopen.getConnection(); // DBMS에 연결
      sql = new StringBuffer();
      sql.append(" SELECT loginno, admin4no, name,ip, logindate");
      sql.append(" FROM login");
      sql.append(" WHERE loginno = ?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
      pstmt.setInt(1,loginno);
      rs = pstmt.executeQuery(); // SELECT
 
      // true일 경우 순환, 레코드가 있는지 검사
      // next() 최초 호출시는 첫번째 레코드로 이동
      // next() 두번째 호출부터 다음 레코드로 이동하여 마지막 레코드까지 이동
        if(rs.next()) {
        
          
          loginVO.setLoginno(rs.getInt("loginno"));
          loginVO.setAdmin4no(rs.getInt("admin4no"));
          loginVO.setName(rs.getString("name"));
          loginVO.setIp(rs.getString("ip"));
          loginVO.setLogindate(rs.getString("logindate"));
         
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt,rs);
    }
    
    return loginVO;
  }
  
  public int delete(int loginno){
    int count = 0;
    
    try{
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" DELETE FROM login");
      sql.append(" WHERE loginno=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, loginno);
      
      count = pstmt.executeUpdate();
      
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
 
    return count;
  }
  
}
