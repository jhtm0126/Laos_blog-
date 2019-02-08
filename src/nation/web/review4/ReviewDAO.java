package nation.web.review4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nation.web.review4.ReviewVO;

import nation.web.tool.DBClose;
import nation.web.tool.DBOpen;


/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.review4
 * 파일명           : ReviewDAO.java 2018. 12. 12.
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
public class ReviewDAO {
  
  Connection con = null;              // DBMS 연결   
  PreparedStatement pstmt = null; // SQL 실행
  ResultSet rs = null;                   // SELECT 결과를 저장
  StringBuffer sql = null;              // SQL 저장
  int count = 0; 
  
  DBOpen dbopen = null;
  DBClose dbclose = null;
  
  public ReviewDAO() {
    this.dbopen = new DBOpen();
    this.dbclose = new DBClose();
  }
  
  public ArrayList<ReviewVO>list(){
    ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
    try {
      
      con = new DBOpen().getConnection(); // MySQL 연결
      sql = new StringBuffer();
      
      sql.append(" select reviewno,title,content,rdate");
      sql.append(" from review");
      sql.append(" order by reviewno desc");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
      rs = pstmt.executeQuery(); // SELECT SQL 실행
 
      // 최초 호출시 첫번째 레코드로 이동하며 그후 호출부터 다음 레코드로 자동 이동
      while(rs.next()) {
            ReviewVO reviewVO = new ReviewVO();
                reviewVO.setReviewno(rs.getInt("reviewno"));
                reviewVO.setTitle(rs.getString("title"));
                reviewVO.setContent(rs.getString("content"));
                reviewVO.setRdate(rs.getString("rdate"));
                
                list.add(reviewVO);
              
      
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      new DBClose().close(con, pstmt, rs);
    }
    return list;
  }
   public int create(ReviewVO reviewVO) {
     try {
       // memory로 클래스를 읽어옴, 객체는 생성하지 않음.
       con = new DBOpen().getConnection();  // MySQL 연결
       sql = new StringBuffer();
       
       sql.append(" insert into review(title,content,passwd,rdate)");
       sql.append(" values(?,?,?,now())");
       
       pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
       pstmt.setString(1, reviewVO.getTitle());
       pstmt.setString(2, reviewVO.getContent());
       pstmt.setString(3, reviewVO.getPasswd());
      
       count = pstmt.executeUpdate();
       
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       new DBClose().close(con, pstmt);
     }
       return count;
       
   }
   
   public ReviewVO read(int reviewno) {
     ReviewVO reviewVO = new ReviewVO();
     
     try {
     con =new DBOpen().getConnection();
     sql = new StringBuffer();
     
     sql.append(" select reviewno,title,content,passwd,rdate");
     sql.append(" from review");
     sql.append(" where reviewno=?");
     
     pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
     pstmt.setInt(1, reviewno);
     rs = pstmt.executeQuery();

     if (rs.next()) {
       reviewVO.setTitle(rs.getString("title"));
       reviewVO.setContent(rs.getString("content"));   
     }
     
   } catch (SQLException e) {
     e.printStackTrace();
   } finally {
     new DBClose().close(con, pstmt,rs);
   }
     return reviewVO;
   }
   
   public int update(ReviewVO reviewVO) {
     
     try {
       
       con = new DBOpen().getConnection();
       sql = new StringBuffer();
       
       sql.append(" update review");
       sql.append(" set title =?, content= ?");
       sql.append(" where reviewno=?");
       

       
       pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
       pstmt.setString(1, reviewVO.getTitle());
       pstmt.setString(2, reviewVO.getContent());
       pstmt.setInt(3, reviewVO.getReviewno());
       
      
       count = pstmt.executeUpdate();

     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       new DBClose().close(con, pstmt);
     }
     return count;
   }
   public int delete(int reviewno) {
     try {
    // memory로 클래스를 읽어옴, 객체는 생성하지 않음.
            con = new DBOpen().getConnection();  // MySQL 연결
            sql = new StringBuffer();
            
        
            sql.append(" delete from review");
            sql.append(" where reviewno =?");
            
            
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1,reviewno);
            count = pstmt.executeUpdate();
            
     } catch (SQLException e) {
       e.printStackTrace();
     } finally {
       new DBClose().close(con, pstmt);
     }
     return count;
   }
   
   /**
    * 패스워드 검사
    * @param visitorno 글번호
    * @param passwd 패스워드
    * @return
    */
   public int passwordCheck(int reviewno, String passwd) {
     int count =0;
     
     try {
      con= dbopen.getConnection(); // DBMS에 연결
       sql = new StringBuffer();
       sql.append(" SELECT COUNT(*) as cnt");
       sql.append(" FROM review");
       sql.append(" WHERE reviewno=? AND passwd=?");
       
       pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
       pstmt.setInt(1, reviewno);;
       pstmt.setString(2,passwd);
       rs = pstmt.executeQuery(); // SELECT

       // true일 경우 순환, 레코드가 있는지 검사
       // next() 최초 호출시는 첫번째 레코드로 이동
       // next() 두번째 호출부터 다음 레코드로 이동하여 마지막 레코드까지 이동
         if(rs.next()) {
           count  = rs.getInt("cnt");
       }

     } catch (SQLException e) {
       e.printStackTrace();
     }finally{
       dbclose.close(con, pstmt,rs);
     }
     
     return count;
   }

   public ArrayList<ReviewVO> list_home(int count) {
     ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();
     
     try {
      con= dbopen.getConnection(); // DBMS에 연결
       sql = new StringBuffer();
       sql.append(" SELECT reviewno,title,content, passwd, rdate");
       sql.append(" FROM review");
       sql.append(" ORDER BY reviewno DESC");
       sql.append(" LIMIT ?");
       
       pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
       pstmt.setInt(1, count);
       rs = pstmt.executeQuery(); // SELECT

       // true일 경우 순환, 레코드가 있는지 검사
       // next() 최초 호출시는 첫번째 레코드로 이동
       // next() 두번째 호출부터 다음 레코드로 이동하여 마지막 레코드까지 이동
       while(rs.next()) {
         ReviewVO reviewVO = new ReviewVO();
         reviewVO.setReviewno(rs.getInt("reviewno"));
         reviewVO.setTitle(rs.getString("title"));
         reviewVO.setContent(rs.getString("content"));
         reviewVO.setRdate(rs.getString("rdate"));
         
         list.add(reviewVO);
       }


     } catch (SQLException e) {
       e.printStackTrace();
     }finally{
       dbclose.close(con, pstmt,rs);
     }
     
     return list;
   }

  
}