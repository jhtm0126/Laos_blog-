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
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.review4
 * ���ϸ�           : ReviewDAO.java 2018. 12. 12.
 * �ۼ���           : ����(jmy)
 * �ۼ��� email   : sjaqj23@naver.com
 * ��������
 * ------------------------------------------------------------------
 * ���� �̷�
 * ------------------------------------------------------------------ 
 * ������        ������  ����ó               ���� ����
 * ------------------------------------------------------------------ 
 * 2016-05-01 �Ʒι�  mail@mail.com  ȸ�� ��� ����
 *
 * ------------------------------------------------------------------
 * 
 *</pre>
 */
public class ReviewDAO {
  
  Connection con = null;              // DBMS ����   
  PreparedStatement pstmt = null; // SQL ����
  ResultSet rs = null;                   // SELECT ����� ����
  StringBuffer sql = null;              // SQL ����
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
      
      con = new DBOpen().getConnection(); // MySQL ����
      sql = new StringBuffer();
      
      sql.append(" select reviewno,title,content,rdate");
      sql.append(" from review");
      sql.append(" order by reviewno desc");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
      rs = pstmt.executeQuery(); // SELECT SQL ����
 
      // ���� ȣ��� ù��° ���ڵ�� �̵��ϸ� ���� ȣ����� ���� ���ڵ�� �ڵ� �̵�
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
       // memory�� Ŭ������ �о��, ��ü�� �������� ����.
       con = new DBOpen().getConnection();  // MySQL ����
       sql = new StringBuffer();
       
       sql.append(" insert into review(title,content,passwd,rdate)");
       sql.append(" values(?,?,?,now())");
       
       pstmt = con.prepareStatement(sql.toString());//sql ���� ��ü ����
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
     
     pstmt = con.prepareStatement(sql.toString());//sql ���� ��ü ����
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
       

       
       pstmt = con.prepareStatement(sql.toString());//sql ���� ��ü ����
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
    // memory�� Ŭ������ �о��, ��ü�� �������� ����.
            con = new DBOpen().getConnection();  // MySQL ����
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
    * �н����� �˻�
    * @param visitorno �۹�ȣ
    * @param passwd �н�����
    * @return
    */
   public int passwordCheck(int reviewno, String passwd) {
     int count =0;
     
     try {
      con= dbopen.getConnection(); // DBMS�� ����
       sql = new StringBuffer();
       sql.append(" SELECT COUNT(*) as cnt");
       sql.append(" FROM review");
       sql.append(" WHERE reviewno=? AND passwd=?");
       
       pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
       pstmt.setInt(1, reviewno);;
       pstmt.setString(2,passwd);
       rs = pstmt.executeQuery(); // SELECT

       // true�� ��� ��ȯ, ���ڵ尡 �ִ��� �˻�
       // next() ���� ȣ��ô� ù��° ���ڵ�� �̵�
       // next() �ι�° ȣ����� ���� ���ڵ�� �̵��Ͽ� ������ ���ڵ���� �̵�
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
      con= dbopen.getConnection(); // DBMS�� ����
       sql = new StringBuffer();
       sql.append(" SELECT reviewno,title,content, passwd, rdate");
       sql.append(" FROM review");
       sql.append(" ORDER BY reviewno DESC");
       sql.append(" LIMIT ?");
       
       pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
       pstmt.setInt(1, count);
       rs = pstmt.executeQuery(); // SELECT

       // true�� ��� ��ȯ, ���ڵ尡 �ִ��� �˻�
       // next() ���� ȣ��ô� ù��° ���ڵ�� �̵�
       // next() �ι�° ȣ����� ���� ���ڵ�� �̵��Ͽ� ������ ���ڵ���� �̵�
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