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
  * ȸ�� ���� DAO class
  * <pre>
  * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
  * PMO, PM      : ���� �Ʒñ��� 
  * ��Ű����        : nation.web.category4
  * ���ϸ�           : CategoryDAO.java 2018. 12. 12.
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
public class CategoryDAO {
  private Connection con = null;              // DBMS ����   
  private PreparedStatement pstmt = null; // SQL ����
  private ResultSet rs = null;                   // SELECT ����� ����
  private StringBuffer sql = null;              // SQL ����
  
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  int count=0;
  
  public CategoryDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
    
    /**
     * ��ȸ
     * @param categoryVO
     * @return ����:1 ����:0
     */
    
    public int create(CategoryVO categoryVO) {
      try {
       
        con = dbopen.getConnection();
        sql = new StringBuffer();
        sql.append(" INSERT INTO category(title, seqno, visible, ids, cnt,rdate)");
        
        sql.append(" VALUES(?, ?, ?, ?, ?,now())"); 
        
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
        pstmt.setString(1, categoryVO.getTitle());
        pstmt.setInt(2, categoryVO.getSeqno());
        pstmt.setString(3, categoryVO.getVisible());
        pstmt.setString(4, categoryVO.getIds());
        pstmt.setInt(5, categoryVO.getCnt());
      

        
        count = pstmt.executeUpdate(); // INSERT, UPDATE, DELETE SQL ����
        
      } catch (SQLException e) {
        e.printStackTrace();
      } finally{
        dbclose.close(con, pstmt);
      }
      return count;
    }
    
    /**
     * ��� ī�װ� ���
     * @return
     */
    public ArrayList<CategoryVO> list() {
      ArrayList<CategoryVO> list = null;
      
      try {
        con = new DBOpen().getConnection();
        sql = new StringBuffer();
        sql.append(" SELECT categoryno, title, seqno, visible, ids, cnt,rdate"); // �� �տ� ���� �� ĭ�� �־����.
        sql.append(" FROM category");
        sql.append(" ORDER BY seqno ASC");
   
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
        rs = pstmt.executeQuery(); // SELECT SQL ����
   
        list = new ArrayList<CategoryVO>();
        
        // ���� ȣ��� ù��° ���ڵ�� �̵��ϸ� ���� ȣ����� ���� ���ڵ�� �ڵ� �̵�
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
     * ��ȸ
     * @param categoryno ��ȸ�� ī�װ� ��ȣ
     * @return ��ȸ�� ī�װ� ��ü
     */
    public CategoryVO read(int categoryno) {
      CategoryVO categoryVO = new CategoryVO();
      
      try {
       con= dbopen.getConnection(); // DBMS�� ����
        sql = new StringBuffer();
        sql.append(" SELECT categoryno, title, seqno, visible, ids, cnt,rdate");
        sql.append(" FROM category");
        sql.append(" WHERE categoryno = ?");
        
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
        pstmt.setInt(1,categoryno);
        rs = pstmt.executeQuery(); // SELECT
   
        // true�� ��� ��ȯ, ���ڵ尡 �ִ��� �˻�
        // next() ���� ȣ��ô� ù��° ���ڵ�� �̵�
        // next() �ι�° ȣ����� ���� ���ڵ�� �̵��Ͽ� ������ ���ڵ���� �̵�
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
        
        
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
        pstmt.setString(1, categoryVO.getTitle());
        pstmt.setInt(2, categoryVO.getSeqno());
        pstmt.setString(3, categoryVO.getVisible());
        pstmt.setString(4, categoryVO.getIds());
        pstmt.setInt(5, categoryVO.getCategoryno());

        
        count = pstmt.executeUpdate(); // INSERT, UPDATE, DELETE SQL ����
        
      } catch (SQLException e) {
        e.printStackTrace();
      } finally{
        dbclose.close(con, pstmt);
      }
      return count;
    }
    
    public int delete(int categoryno) {
      
      
      try {
       con= dbopen.getConnection(); // DBMS�� ����
        sql = new StringBuffer();
        
        sql.append(" delete from category");
        sql.append(" where categoryno =?");
        
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
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
      
      pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
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
      
      pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
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
     * ȭ�鿡 ���
     * @param categoryno ����� ���ڵ� PK ��ȣ
     * @return ó���� ���ڵ� ����
     */
    public int show(int categoryno) { 
      try {
        con = dbopen.getConnection(); // �����ͺ��̽� ����
        sql = new StringBuffer();
        sql.append(" UPDATE category");
        sql.append(" SET visible='Y'");
        sql.append(" WHERE categoryno=?");
        
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
        pstmt.setInt(1, categoryno);
        
        count = pstmt.executeUpdate(); // INSERT, UPDATE, DELETE SQL ����
        
      } catch (SQLException e) {
        e.printStackTrace();
      }finally{
        dbclose.close(con, pstmt);  // �����ͺ��̽� ���� ����
      }
      
      return count;
      
    }
   
    /**
     * ȭ�鿡�� �����
     * @param categoryno ���� ���ڵ� PK ��ȣ
     * @return ó���� ���ڵ� ����
     */
    public int hide(int categoryno) { 
      try {
        con = dbopen.getConnection(); // �����ͺ��̽� ����
        sql = new StringBuffer();
        sql.append(" UPDATE category");
        sql.append(" SET visible='N'");
        sql.append(" WHERE categoryno=?");
        
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
        pstmt.setInt(1, categoryno);
        
        count = pstmt.executeUpdate(); // INSERT, UPDATE, DELETE SQL ����
        
      } catch (SQLException e) {
        e.printStackTrace();
      }finally{
        dbclose.close(con, pstmt);  // �����ͺ��̽� ���� ����
      }
      
      return count;
      
    }
    
    /**
     * ��� ���� �ִ밪 ���
     * @return
     */
    public int getMaxSeqno() { 
      int seqno = 0;
      
      try {
        con = new DBOpen().getConnection();
        sql = new StringBuffer();
        sql.append(" SELECT IFNULL(MAX(seqno), 0)+1 as seqno"); // �� �տ� ���� �� ĭ�� �־����.
        sql.append(" FROM category");
   
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
        rs = pstmt.executeQuery(); // SELECT SQL ����
   
        // ���� ȣ��� ù��° ���ڵ�� �̵��ϸ� ���� ȣ����� ���� ���ڵ�� �ڵ� �̵�
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

     * ���� �Խ��� �ۼ��� ����

     * @param categoryno

     * @return

     */

    public int increaseCnt(int categoryno) { // call by reference

      int count = 0;                     // ó���� ���ڵ� ����

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

     * ���� �Խ��� �ۼ��� ����

     * @param categoryno

     * @return

     */

    public int decreaseCnt(int categoryno) { // call by reference

      int count = 0;                     // ó���� ���ڵ� ����

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
     * ��ü ���ڵ� ����
     * @return ��ü ���ڵ� ����
     */
    public int count() {
      try {
        con = dbopen.getConnection(); // �����ͺ��̽� ����
        sql = new StringBuffer();
        sql.append(" SELECT COUNT(categoryno) as cnt");      
        sql.append(" FROM category "); 
        
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
        rs = pstmt.executeQuery(); // SELECT
   
        if (rs.next()) {
          count = rs.getInt("cnt");
        }
   
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        dbclose.close(con, pstmt, rs);  // �����ͺ��̽� ���� ����
      }
      
      return count;
    }
    
    /**
     * ����¡ ��� ���
     * @param nowPage ���� ������
     * @return ���ڵ� ���
     */
    public ArrayList<CategoryVO> list(int nowPage) {
      ArrayList<CategoryVO> list = null; // ���� ����
      int skip = (nowPage - 1) * 5; // �ǳʶ� ���ڵ� ���� ����
      try {
        con = new DBOpen().getConnection();
        sql = new StringBuffer();
        sql.append(" SELECT categoryno, title, seqno, visible, ids, cnt,rdate"); // �� �տ� ���� �� ĭ�� �־����.
        sql.append(" FROM category");
        sql.append(" ORDER BY seqno ASC");
        sql.append(" LIMIT ?, ?");
   
        pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
        pstmt.setInt(1, skip);
        pstmt.setInt(2, 5);
        rs = pstmt.executeQuery(); // SELECT SQL ����
   
        list = new ArrayList<CategoryVO>();
        while(rs.next()) { // ���ڵ� �̵�
          CategoryVO categoryVO = new CategoryVO(); // ���ڵ忡 ������ ��ü ����
          // DBMS �÷��� �� -> JAVA ������ ������ �����մϴ�.
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
     * ī�װ��� ���ڵ� ����
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
