package nation.web.admin4;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import nation.web.tool.DBClose;
import nation.web.tool.DBOpen;

/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.admin4
 * 파일명           : AdminDAO.java 2018. 12. 12.
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
 
public class AdminDAO {
  private Connection con = null;              // DBMS 연결   
  private PreparedStatement pstmt = null; // SQL 실행
  private ResultSet rs = null;                   // SELECT 결과를 저장
  private StringBuffer sql = null;              // SQL 저장
  
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  public AdminDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
  
  /**
   * 검색된 레코드 갯수를 구합니다.
   * @param col 검색 컬럼
   * @param word 검색어
   * @return 검색된 갯수
   */
  public int count(String col, String word){ 
    int count = 0;
    
    try {
      con = dbopen.getConnection();
  
      sql = new StringBuffer();
      
      sql.append(" SELECT COUNT(*) as cnt");
      sql.append(" FROM admin4");
      
      if(col.equals("email")) {
        sql.append(" WHERE email = ?");
      }
      

      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, word);
  
      rs = pstmt.executeQuery(); // SELECT
  
      if (rs.next() == true) {
        count = rs.getInt("cnt");
      }
  
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
  
    return count;
    
  }
  
  /**
   * Master 계정이 있는지 검사합니다.
   * @return 1: 마스터 계정 존재, 0: 존재하지 않음.
   */
  public int isMaster(){
    int count = 0;              // 레코드 갯수
 
    try{
      con = dbopen.getConnection();
      sql = new StringBuffer();
      
      sql.append(" SELECT COUNT(*) as cnt");
      sql.append(" FROM admin4");
      sql.append(" WHERE act = 'M'");

      

      pstmt = con.prepareStatement(sql.toString());
      rs= pstmt.executeQuery();  // SELECT
      rs.next();  // 첫번째 레코드로 이동, 0 or 
      count = rs.getInt("cnt");     
 
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt, rs);
    }
 
    return count;
  }
  
 
  /**
   * 관리자 등록
   * @param admin4VO 관리자 내용
   * @return 1: 등록 성공, 0: 등록 실패
   */
  public int create(Admin4VO admin4VO){
    int count = 0;
    
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" INSERT INTO admin4(mname, email, passwd, tel, zipcode,");
      sql.append("                              address1, address2,");
      sql.append("                              auth, act, confirm, mdate)");
      sql.append(" VALUES(?, ?, ?, ?, ?,");
      sql.append("            ?, ?,");
      sql.append("            ?, ?, ?, now())");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1,  admin4VO.getMname());
      pstmt.setString(2, admin4VO.getEmail());
      pstmt.setString(3, admin4VO.getPasswd());
      pstmt.setString(4, admin4VO.getTel());
      pstmt.setString(5, admin4VO.getZipcode());
      pstmt.setString(6, admin4VO.getAddress1());
      pstmt.setString(7, admin4VO.getAddress2());
      pstmt.setString(8, admin4VO.getAuth());
      pstmt.setString(9, admin4VO.getAct());
      pstmt.setString(10, admin4VO.getConfirm());
      
      count = pstmt.executeUpdate();
      
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
 
    return count;
  }
  
  /**
   * 사용자가 회원 가입 이메일 링크를 눌렀을 경우의 처리
   * @param email 이메일
   * @param auth 인증 코드
   * @return 처리된 레코드 갯수
   */
  public int confirm(String email, String auth){
    int count = 0;
    
    try{
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      
      sql.append(" UPDATE admin4");
      sql.append(" SET confirm = 'Y'");
      sql.append(" WHERE email=? AND auth=?");
      
   
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, email);
      pstmt.setString(2, auth);
      
      count = pstmt.executeUpdate();
      
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
 
    return count;
  }  
  
  /**
   * 단순 목록을 구합니다.
   * @return 목록
   */
  public ArrayList<Admin4VO> list(){
    ArrayList<Admin4VO> list = null;
    
    try{
      con = dbopen.getConnection();
      sql = new StringBuffer();
      
      

      sql.append(" SELECT admin4no, mname, email, passwd, tel, zipcode, address1, address2,");
      sql.append(" auth, act, confirm, mdate");
      sql.append(" FROM admin4");
      sql.append(" ORDER BY email ASC");


 
        
      pstmt = con.prepareStatement(sql.toString());
      rs= pstmt.executeQuery();  // SELECT
      
      list = new ArrayList<Admin4VO>();
      while(rs.next() == true){
        Admin4VO admin4VO = new Admin4VO();
        admin4VO.setAdmin4no(rs.getInt("admin4no"));
        admin4VO.setMname(rs.getString("mname"));
        admin4VO.setEmail(rs.getString("email"));
        admin4VO.setPasswd(rs.getString("passwd"));
        admin4VO.setTel(rs.getString("tel"));
        admin4VO.setZipcode(rs.getString("zipcode"));
        admin4VO.setAddress1(rs.getString("address1"));
        admin4VO.setAddress2(rs.getString("address2"));
        admin4VO.setAuth(rs.getString("auth"));
        admin4VO.setAct(rs.getString("act"));
        admin4VO.setConfirm(rs.getString("confirm"));
        admin4VO.setMdate(rs.getString("mdate"));
        
        list.add(admin4VO);
      }
 
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt, rs);
    }
 
    return list;
  }
  
  /**
   * 권한을 변경합니다.
   * @param admin4no 변경할 회원 번호
   * @param act 권한
   * @return 변경된 레코드 수
   */
  public int updateAct(int admin4no, String act){
    int count = 0;            // 처리된 레코드 갯수
 
    try{
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      
      
      sql.append(" UPDATE admin4 ");
      sql.append(" SET  act=?");
      sql.append(" WHERE admin4no=?");


 
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, act);
      pstmt.setInt(2, admin4no);
      
      count = pstmt.executeUpdate();
      
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
 
    return count;
  }
 
  
  /**
   * 패스워드가 일치하는지 확인합니다.
   * @param admin4no 회원 번호
   * @param passwd 패스워드
   * @return 1: 일치, 0: 불일치
   */
  public int countPasswd(int admin4no, String passwd){
    int count = 0;
    
    try{
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" SELECT COUNT(*) as cnt ");
      sql.append(" FROM admin4 ");
      sql.append(" WHERE admin4no=? AND passwd=?");
        
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, admin4no);
      pstmt.setString(2, passwd);
      
      rs= pstmt.executeQuery();  // SELECT
      rs.next();  // 첫번째 레코드로 이동, 0 or 
      count = rs.getInt("cnt");     
 
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt, rs);
    }
 
    return count;
  }  
 
 
 /**
   * 패스워드를 변경합니다.
   * @param admin4no 변경할 회원 번호
   * @param passwd 번경할 패스워드
   * @return 1: 변경 성공, 2: 변경 실패
   */
  public int updatePasswd(int admin4no, String passwd){
    int count = 0;
    
    try{
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" UPDATE admin4");
      sql.append(" SET passwd=?");
      sql.append(" WHERE admin4no=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, passwd);
      pstmt.setInt(2, admin4no);
      
      count = pstmt.executeUpdate();
      
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
 
    return count;
  }
  
  /**
   * 한건의 레코드 조회
   * @param admin4no 조회할 번호
   * @return
   */
  public Admin4VO read(int admin4no){
    Admin4VO admin4VO = null;
    
    try{
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" SELECT admin4no, mname, email, tel, zipcode, address1, address2, ");
      sql.append("              auth, act, confirm, mdate");
      sql.append(" FROM admin4 ");
      sql.append(" WHERE admin4no = ?");
        
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, admin4no);
      rs= pstmt.executeQuery();  // SELECT
      
      admin4VO = new Admin4VO();
      if(rs.next() == true){
        admin4VO.setAdmin4no(rs.getInt("admin4no"));
        admin4VO.setMname(rs.getString("mname"));
        admin4VO.setEmail(rs.getString("email"));
        admin4VO.setTel(rs.getString("tel"));
        admin4VO.setZipcode(rs.getString("zipcode"));
        admin4VO.setAddress1(rs.getString("address1"));
        admin4VO.setAddress2(rs.getString("address2"));
        admin4VO.setAuth(rs.getString("auth"));
        admin4VO.setAct(rs.getString("act"));
        admin4VO.setConfirm(rs.getString("confirm"));
        admin4VO.setMdate(rs.getString("mdate"));
      }
 
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt, rs);
    }
 
    return admin4VO;
  }
 
  /**
   * 회원 수정
   * @param admin4VO 회원 내용
   * @return 1: 등록 성공, 0: 등록 실패
   */
  public int update(Admin4VO admin4VO){
    int count = 0;
    
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" UPDATE admin4");
      sql.append(" SET mname=?, tel=?, zipcode=?, address1=?, address2=?");
      sql.append(" WHERE admin4no = ?");
 
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, admin4VO.getMname());
      pstmt.setString(2, admin4VO.getTel());
      pstmt.setString(3, admin4VO.getZipcode());
      pstmt.setString(4, admin4VO.getAddress1());
      pstmt.setString(5, admin4VO.getAddress2());
      pstmt.setInt(6, admin4VO.getAdmin4no());
      
      count = pstmt.executeUpdate();
      
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
 
    return count;
  }
  
  /**
   * 회원을 삭제합니다.
   * @param admin4no 삭제할 회원 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int admin4no){
    int count = 0;
    
    try{
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" DELETE FROM admin4");
      sql.append(" WHERE admin4no=?");
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, admin4no);
      
      count = pstmt.executeUpdate();
      
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
 
    return count;
  }
  
  /**

   * 로그인 처리

   * @param email 이메일

   * @param passwd 패스워드

   * @return 레코드 갯수

   */

  public int login(String email, String passwd){

    int count = 0;              // 레코드 갯수

 

    try{

      con = dbopen.getConnection();

      sql = new StringBuffer();

      sql.append(" SELECT COUNT(*) as cnt ");

      sql.append(" FROM admin4 ");

      sql.append(" WHERE email=? AND passwd=? AND act IN ('M', 'Y')");

        

      pstmt = con.prepareStatement(sql.toString());

      pstmt.setString(1, email);

      pstmt.setString(2, passwd);

      

      rs= pstmt.executeQuery();  // SELECT

      rs.next();  // 첫번째 레코드로 이동, 0 or 

      count = rs.getInt("cnt");     

 

    }catch(Exception e){

      e.printStackTrace();

    }finally{

      dbclose.close(con, pstmt, rs);

    }

 

    return count;

  }

 

 

  /**

   * 한건의 레코드 조회

   * @param email 조회할 이메일

   * @return

   */

  public Admin4VO read(String email){

    Admin4VO admin4VO = null;

    

    try{

      con = dbopen.getConnection();

      sql = new StringBuffer();


      sql.append(" SELECT admin4no, mname, email, tel, zipcode, address1, address2, ");

      sql.append("           auth, act, confirm, mdate");

      sql.append(" FROM admin4 ");

      sql.append(" WHERE email = ?");

        

      pstmt = con.prepareStatement(sql.toString());

      pstmt.setString(1, email);

      rs= pstmt.executeQuery();  // SELECT

      

      admin4VO = new Admin4VO();

      if(rs.next() == true){


        admin4VO.setAdmin4no(rs.getInt("admin4no"));

        admin4VO.setMname(rs.getString("mname"));

        admin4VO.setEmail(rs.getString("email"));

        admin4VO.setTel(rs.getString("tel"));

        admin4VO.setZipcode(rs.getString("zipcode"));

        admin4VO.setAddress1(rs.getString("address1"));

        admin4VO.setAddress2(rs.getString("address2"));

        admin4VO.setAuth(rs.getString("auth"));

        admin4VO.setAct(rs.getString("act"));

        admin4VO.setConfirm(rs.getString("confirm"));

        admin4VO.setMdate(rs.getString("mdate"));

      }

 

    }catch(Exception e){

      e.printStackTrace();

    }finally{

      dbclose.close(con, pstmt, rs);

    }

 

    return admin4VO;

  }
 
}