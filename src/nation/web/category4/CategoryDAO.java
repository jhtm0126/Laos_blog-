package nation.web.category4;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nation.web.tool.DBClose;
import nation.web.tool.DBOpen;

import nation.web.category4.CategoryVO;

 /**
  * 
  * 회원 관련 DAO class
  * <pre>
  * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
  * PMO, PM      : 지도 훈련교사 
  * 패키지명        : nation.web.category4
  * 파일명           : CategoryDAO.java 2018. 12. 12.
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
public class CategoryDAO {
  private Connection con = null;              // DBMS 연결   
  private PreparedStatement pstmt = null; // SQL 실행
  private ResultSet rs = null;                   // SELECT 결과를 저장
  private StringBuffer sql = null;              // SQL 저장
  
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  int count=0;
  
  public CategoryDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
    
    /**
     * 조회
     * @param categoryVO
     * @return 성공:1 실패:0
     */
    
    public int create(CategoryVO categoryVO) {
      try {
       
        con = dbopen.getConnection();
        sql = new StringBuffer();
        sql.append(" INSERT INTO category(title, seqno, visible, ids, cnt,rdate)");
        
        sql.append(" VALUES(?, ?, ?, ?, ?,now())"); 
        
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        pstmt.setString(1, categoryVO.getTitle());
        pstmt.setInt(2, categoryVO.getSeqno());
        pstmt.setString(3, categoryVO.getVisible());
        pstmt.setString(4, categoryVO.getIds());
        pstmt.setInt(5, categoryVO.getCnt());
      

        
        count = pstmt.executeUpdate(); // INSERT, UPDATE, DELETE SQL 실행
        
      } catch (SQLException e) {
        e.printStackTrace();
      } finally{
        dbclose.close(con, pstmt);
      }
      return count;
    }
    
    /**
     * 모든 카테고리 목록
     * @return
     */
    public ArrayList<CategoryVO> list() {
      ArrayList<CategoryVO> list = null;
      
      try {
        con = new DBOpen().getConnection();
        sql = new StringBuffer();
        sql.append(" SELECT categoryno, title, seqno, visible, ids, cnt,rdate"); // 맨 앞에 공백 한 칸이 있어야함.
        sql.append(" FROM category");
        sql.append(" ORDER BY seqno ASC");
   
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        rs = pstmt.executeQuery(); // SELECT SQL 실행
   
        list = new ArrayList<CategoryVO>();
        
        // 최초 호출시 첫번째 레코드로 이동하며 그후 호출부터 다음 레코드로 자동 이동
        while(rs.next()) {
          CategoryVO categoryVO = new CategoryVO();
          // DBMS -> JAVA
          categoryVO.setCategoryno(rs.getInt("categoryno"));
          categoryVO.setTitle(rs.getString("title"));
          categoryVO.setSeqno(rs.getInt("seqno"));
          categoryVO.setVisible(rs.getString("visible"));
          categoryVO.setIds(rs.getString("ids"));
          categoryVO.setCnt(rs.getInt("cnt"));
          categoryVO.setRdate(rs.getString("rdate"));
          
          list.add(categoryVO);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        new DBClose().close(con, pstmt, rs);
      }
      
      return list;
    }
    /**
     * 조회
     * @param categoryno 조회할 카테고리 번호
     * @return 조회한 카테고리 객체
     */
    public CategoryVO read(int categoryno) {
      CategoryVO categoryVO = new CategoryVO();
      
      try {
       con= dbopen.getConnection(); // DBMS에 연결
        sql = new StringBuffer();
        sql.append(" SELECT categoryno, title, seqno, visible, ids, cnt,rdate");
        sql.append(" FROM category");
        sql.append(" WHERE categoryno = ?");
        
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        pstmt.setInt(1,categoryno);
        rs = pstmt.executeQuery(); // SELECT
   
        // true일 경우 순환, 레코드가 있는지 검사
        // next() 최초 호출시는 첫번째 레코드로 이동
        // next() 두번째 호출부터 다음 레코드로 이동하여 마지막 레코드까지 이동
          if(rs.next()) {
          
            
            categoryVO.setCategoryno(rs.getInt("categoryno"));
            categoryVO.setTitle(rs.getString("title"));
            categoryVO.setSeqno(rs.getInt("seqno"));
            categoryVO.setVisible(rs.getString("visible"));
            categoryVO.setIds(rs.getString("ids"));
            categoryVO.setCnt(rs.getInt("cnt"));
            categoryVO.setRdate(rs.getString("rdate"));
        }

      } catch (SQLException e) {
        e.printStackTrace();
      }finally{
        dbclose.close(con, pstmt,rs);
      }
      
      return categoryVO;
    }
    


    
    public int update(CategoryVO categoryVO) {
      try {
       
        con = dbopen.getConnection();
        sql = new StringBuffer();
        sql.append(" UPDATE category");
        sql.append(" SET title =?, seqno=?, visible=?, ids=?"); 
        sql.append(" WHERE categoryno=?");
        
        
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        pstmt.setString(1, categoryVO.getTitle());
        pstmt.setInt(2, categoryVO.getSeqno());
        pstmt.setString(3, categoryVO.getVisible());
        pstmt.setString(4, categoryVO.getIds());
        pstmt.setInt(5, categoryVO.getCategoryno());

        
        count = pstmt.executeUpdate(); // INSERT, UPDATE, DELETE SQL 실행
        
      } catch (SQLException e) {
        e.printStackTrace();
      } finally{
        dbclose.close(con, pstmt);
      }
      return count;
    }
    
    public int delete(int categoryno) {
      
      
      try {
       con= dbopen.getConnection(); // DBMS에 연결
        sql = new StringBuffer();
        
        sql.append(" delete from category");
        sql.append(" where categoryno =?");
        
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        pstmt.setInt(1, categoryno);
        count = pstmt.executeUpdate(); 

      } catch (SQLException e) {
        e.printStackTrace();
      }finally{
        dbclose.close(con, pstmt);
      }
      return count;
    }
    
    public int increaseSeqno(int categoryno) {
      try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE category");
      sql.append(" SET seqno = seqno + 1"); 
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
      pstmt.setInt(1, categoryno);
      count = pstmt.executeUpdate(); 

    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
    return count;
  }
    
    public int decreaseSeqno(int categoryno) {
      try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE category");
      sql.append(" SET seqno = seqno - 1"); 
      sql.append(" WHERE categoryno=?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
      pstmt.setInt(1, categoryno);
      count = pstmt.executeUpdate(); 

    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
    return count;
  }
    
    /**
     * 화면에 출력
     * @param categoryno 출력할 레코드 PK 번호
     * @return 처리된 레코드 갯수
     */
    public int show(int categoryno) { 
      try {
        con = dbopen.getConnection(); // 데이터베이스 연결
        sql = new StringBuffer();
        sql.append(" UPDATE category");
        sql.append(" SET visible='Y'");
        sql.append(" WHERE categoryno=?");
        
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        pstmt.setInt(1, categoryno);
        
        count = pstmt.executeUpdate(); // INSERT, UPDATE, DELETE SQL 실행
        
      } catch (SQLException e) {
        e.printStackTrace();
      }finally{
        dbclose.close(con, pstmt);  // 데이터베이스 연결 해제
      }
      
      return count;
      
    }
   
    /**
     * 화면에서 숨기기
     * @param categoryno 숨길 레코드 PK 번호
     * @return 처리된 레코드 갯수
     */
    public int hide(int categoryno) { 
      try {
        con = dbopen.getConnection(); // 데이터베이스 연결
        sql = new StringBuffer();
        sql.append(" UPDATE category");
        sql.append(" SET visible='N'");
        sql.append(" WHERE categoryno=?");
        
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        pstmt.setInt(1, categoryno);
        
        count = pstmt.executeUpdate(); // INSERT, UPDATE, DELETE SQL 실행
        
      } catch (SQLException e) {
        e.printStackTrace();
      }finally{
        dbclose.close(con, pstmt);  // 데이터베이스 연결 해제
      }
      
      return count;
      
    }
    
    /**
     * 출력 순서 최대값 계산
     * @return
     */
    public int getMaxSeqno() { 
      int seqno = 0;
      
      try {
        con = new DBOpen().getConnection();
        sql = new StringBuffer();
        sql.append(" SELECT IFNULL(MAX(seqno), 0)+1 as seqno"); // 맨 앞에 공백 한 칸이 있어야함.
        sql.append(" FROM category");
   
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        rs = pstmt.executeQuery(); // SELECT SQL 실행
   
        // 최초 호출시 첫번째 레코드로 이동하며 그후 호출부터 다음 레코드로 자동 이동
        if (rs.next()) {
          seqno = rs.getInt("seqno");
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        new DBClose().close(con, pstmt, rs);
      }
      
      return seqno;
      
      
    }
    
    /**

     * 관련 게시판 글수의 증가

     * @param categoryno

     * @return

     */

    public int increaseCnt(int categoryno) { // call by reference

      int count = 0;                     // 처리된 레코드 갯수

      try {

        con = dbopen.getConnection();

        sql = new StringBuffer();

        sql.append(" UPDATE category");

        sql.append(" SET cnt = cnt + 1");

        sql.append(" WHERE categoryno=?");

        

        pstmt = con.prepareStatement(sql.toString());

        pstmt.setInt(1, categoryno);

    

        count = pstmt.executeUpdate();

        

      } catch (SQLException e) {

        e.printStackTrace();

      } finally {

        dbclose.close(con, pstmt);

      }

      

      return count;

    }

   

    /**

     * 관련 게시판 글수의 감소

     * @param categoryno

     * @return

     */

    public int decreaseCnt(int categoryno) { // call by reference

      int count = 0;                     // 처리된 레코드 갯수

      try {

        con = dbopen.getConnection();

        sql = new StringBuffer();

        sql.append(" UPDATE category");

        sql.append(" SET cnt = cnt - 1");

        sql.append(" WHERE categoryno=?");

        

        pstmt = con.prepareStatement(sql.toString());

        pstmt.setInt(1, categoryno);

    

        count = pstmt.executeUpdate();

        

      } catch (SQLException e) {

        e.printStackTrace();

      } finally {

        dbclose.close(con, pstmt);

      }

      

      return count;

    }
    
    /**
     * 전체 레코드 갯수
     * @return 전체 레코드 갯수
     */
    public int count() {
      try {
        con = dbopen.getConnection(); // 데이터베이스 연결
        sql = new StringBuffer();
        sql.append(" SELECT COUNT(categoryno) as cnt");      
        sql.append(" FROM category "); 
        
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        rs = pstmt.executeQuery(); // SELECT
   
        if (rs.next()) {
          count = rs.getInt("cnt");
        }
   
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        dbclose.close(con, pstmt, rs);  // 데이터베이스 연결 해제
      }
      
      return count;
    }
    
    /**
     * 페이징 목록 출력
     * @param nowPage 현재 페이지
     * @return 레코드 목록
     */
    public ArrayList<CategoryVO> list(int nowPage) {
      ArrayList<CategoryVO> list = null; // 지역 변수
      int skip = (nowPage - 1) * 5; // 건너띨 레코드 갯수 산출
      try {
        con = new DBOpen().getConnection();
        sql = new StringBuffer();
        sql.append(" SELECT categoryno, title, seqno, visible, ids, cnt,rdate"); // 맨 앞에 공백 한 칸이 있어야함.
        sql.append(" FROM category");
        sql.append(" ORDER BY seqno ASC");
        sql.append(" LIMIT ?, ?");
   
        pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
        pstmt.setInt(1, skip);
        pstmt.setInt(2, 5);
        rs = pstmt.executeQuery(); // SELECT SQL 실행
   
        list = new ArrayList<CategoryVO>();
        while(rs.next()) { // 레코드 이동
          CategoryVO categoryVO = new CategoryVO(); // 레코드에 대응할 객체 생성
          // DBMS 컬럼의 값 -> JAVA 변수의 값으로 대입합니다.
          categoryVO.setCategoryno(rs.getInt("categoryno"));
          categoryVO.setSeqno(rs.getInt("seqno")); 
          categoryVO.setTitle(rs.getString("title"));
          categoryVO.setIds(rs.getString("ids"));
          categoryVO.setVisible(rs.getString("visible"));
          categoryVO.setCnt(rs.getInt("cnt"));
          categoryVO.setRdate(rs.getString("rdate").substring(0, 10));
         
          list.add(categoryVO);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        new DBClose().close(con, pstmt, rs);
      }
      
      return list;
      
    }
    
    /**
     * 카테고리별 레코드 갯수
     * 
     * @return
     */
    public int countByCategory(int categoryno) {
      int count = 0;
    
      try {
        con = dbopen.getConnection();
    
        sql = new StringBuffer();
        sql.append(" SELECT COUNT(libraryno) as cnt ");
        sql.append(" FROM library");
        sql.append(" WHERE categoryno = ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
    
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

} 
