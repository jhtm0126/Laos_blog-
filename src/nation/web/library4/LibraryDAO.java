package nation.web.library4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nation.web.library4.LibraryVO;

import nation.web.tool.DBClose;
import nation.web.tool.DBOpen;
/**
 * 
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.library4
 * ���ϸ�           : LibraryDAO.java 2018. 12. 12.
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
public class LibraryDAO {
  
  Connection con = null;              // DBMS ����   
  PreparedStatement pstmt = null; // SQL ����
  ResultSet rs = null;                   // SELECT ����� ����
  StringBuffer sql = null;              // SQL ����
  int count = 0; 
  
  DBOpen dbopen = null;
  DBClose dbclose = null;
  

  
  public LibraryDAO() {
    this.dbopen = new DBOpen();
    this.dbclose = new DBClose();
  }

  
  /**
   * ���
   * @param pds4VO
   * @return ����� ����
   */
  public int create(LibraryVO libraryVO){ // call by reference
    int count = 0;                     // ó���� ���ڵ� ����
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" INSERT INTO library(categoryno, writer,spot, content,");
      sql.append(" pw, hits, rdate, site, file_name, fstor1, thumb, size,youtube,video,map_info,visible)");
      sql.append(" VALUES(?, ?, ?, ?, ?, 0, now(), ?, ?, ?,? ,?,?, ?,?,?)");
      

      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, libraryVO.getCategoryno());
      pstmt.setString(2, libraryVO.getWriter());
      pstmt.setString(3, libraryVO.getSpot());
      pstmt.setString(4, libraryVO.getContent());
      pstmt.setString(5, libraryVO.getPw());
      pstmt.setString(6, libraryVO.getSite());
      pstmt.setString(7, libraryVO.getFile_name());
      pstmt.setString(8, libraryVO.getFstor1());
      pstmt.setString(9, libraryVO.getThumb());
      pstmt.setLong(10, libraryVO.getSize());
      pstmt.setString(11, libraryVO.getYoutube());
      pstmt.setString(12, libraryVO.getVideo());
      pstmt.setString(13, libraryVO.getMap_info());
      pstmt.setString(14, libraryVO.getVisible());
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    
    return count;
  }
  
  /**
   * ��� ���ڵ�
   * 
   * @return
   */
  public ArrayList<LibraryVO> list() {
    ArrayList<LibraryVO> list = new ArrayList<LibraryVO>();
 
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      sql.append(" select libraryno,categoryno, writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" from library");
      sql.append(" ORDER BY libraryno DESC;");

      pstmt = con.prepareStatement(sql.toString());
      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        LibraryVO libraryVO = new LibraryVO();//���ڵ� ������ŭ ��ü ����
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA ��ü
        libraryVO.setCategoryno(rs.getInt("categoryno"));
        libraryVO.setWriter(rs.getString("writer"));
        libraryVO.setSpot(rs.getString("spot"));
        libraryVO.setContent(rs.getString("content"));
        libraryVO.setHits(rs.getInt("Hits"));
        libraryVO.setRdate(rs.getString("rdate"));
        libraryVO.setSite(rs.getString("site"));
        
        libraryVO.setFile_name(rs.getString("file_name"));
        libraryVO.setFstor1(rs.getString("fstor1"));
        libraryVO.setThumb(rs.getString("thumb"));
        libraryVO.setSize(rs.getLong("size"));
        libraryVO.setYoutube(rs.getString("youtube"));
        libraryVO.setVideo(rs.getString("video"));
        libraryVO.setMap_info(rs.getString("map_info"));
        libraryVO.setVisible(rs.getString("visible"));
 
        list.add(libraryVO);
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return list;
  }
  
  /**
 * ī�װ��� ���ڵ� ���
 * 
 * @return
 */
  public ArrayList<LibraryVO> list_category(int categoryno) {
    ArrayList<LibraryVO> list = new ArrayList<LibraryVO>();
 
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
      
      sql.append(" select libraryno,categoryno, writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" FROM library");
      sql.append(" where categoryno=?");
      sql.append(" ORDER BY libraryno DESC;");

 
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1, categoryno);
      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        LibraryVO libraryVO = new LibraryVO();//���ڵ� ������ŭ ��ü ����
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA ��ü
        libraryVO.setCategoryno(rs.getInt("categoryno"));
        libraryVO.setWriter(rs.getString("writer"));
        libraryVO.setSpot(rs.getString("spot"));
        libraryVO.setContent(rs.getString("content"));
        
        libraryVO.setHits(rs.getInt("Hits"));
        libraryVO.setRdate(rs.getString("rdate"));
        libraryVO.setSite(rs.getString("site"));
       
        libraryVO.setFile_name(rs.getString("file_name"));
        libraryVO.setFstor1(rs.getString("fstor1"));
        libraryVO.setThumb(rs.getString("thumb"));
        libraryVO.setSize(rs.getLong("size"));
        libraryVO.setYoutube(rs.getString("youtube"));
        libraryVO.setVideo(rs.getString("video"));
        libraryVO.setMap_info(rs.getString("map_info"));
        libraryVO.setVisible(rs.getString("visible"));
 
        list.add(libraryVO);
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return list;
  }
  
  /**
   * ��ȸ�� ����
   * @param pdsno
   */
  public void increaseCnt(int libraryno){ // call by value
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE library");
      sql.append(" SET hits = hits + 1"); 
      sql.append(" WHERE libraryno=?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
      pstmt.setInt(1, libraryno);
      count = pstmt.executeUpdate(); 

    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
   
    }
  /**
   * ��ȸ
   * 
   * @return
   */
  public LibraryVO read(int libraryno){
    LibraryVO libraryVO = null;
    
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
     
      sql.append(" select libraryno,categoryno, writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" FROM library");
      sql.append(" where libraryno=?");


      pstmt = con.prepareStatement(sql.toString());
      pstmt.setInt(1,libraryno);
      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        libraryVO = new LibraryVO();//���ڵ� ������ŭ ��ü ����
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA ��ü
        libraryVO.setCategoryno(rs.getInt("categoryno"));
        libraryVO.setWriter(rs.getString("writer"));
        libraryVO.setSpot(rs.getString("spot"));
        libraryVO.setContent(rs.getString("content"));
        libraryVO.setHits(rs.getInt("Hits"));
        libraryVO.setRdate(rs.getString("rdate"));
        libraryVO.setPw(rs.getString("pw"));
        libraryVO.setSite(rs.getString("site"));
        libraryVO.setFile_name(rs.getString("file_name"));
        libraryVO.setFstor1(rs.getString("fstor1"));
        libraryVO.setThumb(rs.getString("thumb"));
        libraryVO.setYoutube(rs.getString("youtube"));
        libraryVO.setVideo(rs.getString("video"));
        libraryVO.setMap_info(rs.getString("map_info"));
        libraryVO.setSize(rs.getLong("size"));
        libraryVO.setVisible(rs.getString("visible"));
        
      

      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return libraryVO;
  }
  
  
  public int passwordCheck(int libraryno, String pw) {
    int count =0;
    
    try {
     con= dbopen.getConnection(); // DBMS�� ����
      sql = new StringBuffer();
      sql.append(" SELECT COUNT(*) as cnt");
      sql.append(" FROM library");
      sql.append(" WHERE libraryno=? AND pw=?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
      pstmt.setInt(1,libraryno);
      pstmt.setString(2,pw);
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
  
  /**
   * �� ����
   * @param pds4VO
   * @return ó���� ����
   */
  public int update(LibraryVO libraryVO){ // call by reference
    
    try {
      
      con = dbopen.getConnection();
      sql = new StringBuffer();
      
      sql.append(" UPDATE library");
      sql.append(" SET writer=?, spot=?,");
      sql.append(" content=?, site=?");
      sql.append(" WHERE libraryno=?");
      
      pstmt = con.prepareStatement(sql.toString());//sql ���� ��ü ����
      pstmt.setString(1, libraryVO.getWriter());
      pstmt.setString(2, libraryVO.getSpot());
      pstmt.setString(3, libraryVO.getContent());
      pstmt.setString(4, libraryVO.getSite());
      pstmt.setInt(5, libraryVO.getLibraryno());
      
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally{
      dbclose.close(con, pstmt);
    }
    return count;
  }
  
  /**
   * Visible ��� ����
   * @param pdsno
   * @param visible Y / N
   * @return ó���� ����
   */
  public int show_hide(int libraryno, String visible){
  try {
      
      con = dbopen.getConnection();
      sql = new StringBuffer();
      
      sql.append(" UPDATE library");
      sql.append(" SET visible =?");
      sql.append(" WHERE libraryno=?");
      
     pstmt = con.prepareStatement(sql.toString());//sql ���� ��ü ����
     pstmt.setString(1,visible); 
     pstmt.setInt(2,libraryno);
    
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally{
      dbclose.close(con, pstmt);
    }
    return count;
  }
  
/**
 * �ű� ���� ���
 * @param libraryVO
 * @return
 */
  public int update_file_name(LibraryVO libraryVO) {
    try {

      con = dbopen.getConnection();
      sql = new StringBuffer();

      sql.append(" UPDATE library");
      sql.append(" SET file_name=?, thumb=?, size=?");
      sql.append(" WHERE libraryno=?");

      pstmt = con.prepareStatement(sql.toString());// sql ���� ��ü ����
      pstmt.setString(1, libraryVO.getFile_name());
      pstmt.setString(2, libraryVO.getThumb());
      pstmt.setLong(3, libraryVO.getSize());
      pstmt.setInt(4, libraryVO.getLibraryno());

      count = pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    return count;
  }
  
  /**
   * �� ����
   * 
   * @param ������ PK ��ȣ
   * @return ó���� ����
   */
  public int delete(int libraryno) {
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();

      sql.append(" DELETE FROM library");
      sql.append(" WHERE libraryno=?");

      pstmt = con.prepareStatement(sql.toString());// sql ���� ��ü ����
      pstmt.setInt(1, libraryno);

      count = pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    return count;
  }
  
  public int update_map_info(LibraryVO libraryVO) {
    try {

      con = dbopen.getConnection();
      sql = new StringBuffer();

      sql.append(" UPDATE library ");
      sql.append(" SET map_info=?");
      sql.append(" WHERE libraryno=?");

      pstmt = con.prepareStatement(sql.toString());// sql ���� ��ü ����
      pstmt.setString(1, libraryVO.getMap_info());
      pstmt.setInt(2, libraryVO.getLibraryno());

      count = pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    return count;
  }
  
  public int update_youtube(LibraryVO libraryVO) {
    try {

      con = dbopen.getConnection();
      sql = new StringBuffer();

      sql.append(" UPDATE library");
      sql.append(" SET youtube=?");
      sql.append(" WHERE libraryno=?");

      pstmt = con.prepareStatement(sql.toString());// sql ���� ��ü ����
      pstmt.setString(1, libraryVO.getYoutube());
      pstmt.setInt(2, libraryVO.getLibraryno());

      count = pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    return count;
  }
  
  public int update_video(LibraryVO libraryVO) {
    try {

      con = dbopen.getConnection();
      sql = new StringBuffer();

      sql.append(" UPDATE library");
      sql.append(" SET video=?");
      sql.append(" WHERE libraryno=?");

      pstmt = con.prepareStatement(sql.toString());// sql ���� ��ü ����
      pstmt.setString(1, libraryVO.getVideo());
      pstmt.setInt(2, libraryVO.getLibraryno());

      count = pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt);
    }
    return count;
  }
  
  /**
   * ��ü ī�װ� �˻�
   * @param col �˻� �÷�
   * @param word �˻���
   * @return �˻� ���
   */
  public ArrayList<LibraryVO> list(String col, String word) {
    ArrayList<LibraryVO> list = new ArrayList<LibraryVO>();
 
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
 
      sql.append(" select libraryno,categoryno, writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" from library");
      
     
      if (col.equals("writer")) {
        sql.append(" WHERE writer LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1,  "%" + word + "%");
        
      } else if (col.equals("spot")) {
        sql.append(" WHERE spot LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1,  "%" + word + "%");
        
      } else if (col.equals("content")) {
        sql.append(" WHERE content LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1,  "%" + word + "%");
        
      } else if (col.equals("title_content")) {
        sql.append(" WHERE spot LIKE ? OR content LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1,  "%" + word + "%");
        pstmt.setString(2,  "%" + word + "%");
        
      } else { // �˻����� �ʴ� ���
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        
      }
           
      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        LibraryVO libraryVO = new LibraryVO();//���ڵ� ������ŭ ��ü ����
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA ��ü
        libraryVO.setCategoryno(rs.getInt("categoryno"));
        libraryVO.setWriter(rs.getString("writer"));
        libraryVO.setSpot(rs.getString("spot"));
        libraryVO.setHits(rs.getInt("Hits"));
        libraryVO.setRdate(rs.getString("rdate"));
        libraryVO.setSite(rs.getString("site"));
        libraryVO.setFile_name(rs.getString("file_name"));
        libraryVO.setContent(rs.getString("content"));
        libraryVO.setFstor1(rs.getString("fstor1"));
        libraryVO.setThumb(rs.getString("thumb"));
        libraryVO.setSize(rs.getLong("size"));
        libraryVO.setYoutube(rs.getString("youtube"));
        libraryVO.setVideo(rs.getString("video"));
        libraryVO.setMap_info(rs.getString("map_info"));
        libraryVO.setVisible(rs.getString("visible"));
 
        list.add(libraryVO);
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return list;
  }
  
  /**
   * ī�װ��� �˻�
   * @param categoryno ī�װ� ��ȣ
   * @param col �˻� �÷�
   * @param word �˻���
   * @return �˻� ���
   */
  public ArrayList<LibraryVO> list_category(int categoryno, String col, String word) {
    ArrayList<LibraryVO> list = new ArrayList<LibraryVO>();
 
    try {
      con = dbopen.getConnection();
 
      sql = new StringBuffer();
 
      sql.append(" select libraryno,categoryno, writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" from library");
 
      if (col.equals("writer")) {
        sql.append(" WHERE categoryno = ? AND writer LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1,  categoryno);
        pstmt.setString(2,  "%" + word + "%");
        
      } else if (col.equals("spot")) {
        sql.append(" WHERE categoryno = ? AND spot LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1,  categoryno);
        pstmt.setString(2,  "%" + word + "%");
        
      } else if (col.equals("content")) {
        sql.append(" WHERE categoryno = ? AND content LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1,  categoryno);
        pstmt.setString(2,  "%" + word + "%");
        
      } else if (col.equals("title_content")) {
        sql.append(" WHERE categoryno = ? AND spot LIKE ? OR content LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1,  categoryno);
        pstmt.setString(2,  "%" + word + "%");
        pstmt.setString(3,  "%" + word + "%");
        
      } else { // �˻����� �ʴ� ���
        sql.append(" WHERE categoryno = ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1,  categoryno);
        
      }


      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        LibraryVO libraryVO = new LibraryVO();//���ڵ� ������ŭ ��ü ����
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA ��ü
        libraryVO.setCategoryno(rs.getInt("categoryno"));
        libraryVO.setWriter(rs.getString("writer"));
        libraryVO.setSpot(rs.getString("spot"));
        libraryVO.setHits(rs.getInt("Hits"));
        libraryVO.setRdate(rs.getString("rdate"));
        libraryVO.setSite(rs.getString("site"));
        libraryVO.setFile_name(rs.getString("file_name"));
        libraryVO.setContent(rs.getString("content"));
        libraryVO.setFstor1(rs.getString("fstor1"));
        libraryVO.setThumb(rs.getString("thumb"));
        libraryVO.setSize(rs.getLong("size"));
        libraryVO.setYoutube(rs.getString("youtube"));
        libraryVO.setVideo(rs.getString("video"));
        libraryVO.setMap_info(rs.getString("map_info"));
        libraryVO.setVisible(rs.getString("visible"));
 
        list.add(libraryVO);
      }
 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
    }
 
    return list;
  } 
  
  /**
   * ī�װ����� �˻��� ���ڵ� ����
   * 
   * @return
   */
  public int count(int categoryno, String col, String word) {
    int count = 0;
  
    try {
      con = dbopen.getConnection();
  
      sql = new StringBuffer();
      sql.append(" SELECT COUNT(libraryno) as cnt ");
      sql.append(" FROM library");
      
      if (col.equals("writer")) {
        sql.append(" WHERE categoryno = ? AND writer LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
      } else if (col.equals("spot")) {
        sql.append(" WHERE categoryno = ? AND  spot LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
      } else if (col.equals("content")) {
        sql.append(" WHERE categoryno = ? AND  content LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
      } else if (col.equals("title_content")) {
        sql.append(" WHERE categoryno = ? AND (spot LIKE ? OR content LIKE ?)");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setString(3, "%" + word + "%");
      } else { // �˻����� �ʴ� ���
        sql.append(" WHERE categoryno = ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
      }
  
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
   * ī�װ����� �˻� �� ����¡�� ������ ���, SELECT�� ����߿�
   * ����¡ ����� �̿��Ͽ� Ư�� ���ڵ� �κ�(10��)�� �����ɴϴ�. 
   * @param categoryno ī�װ� ��ȣ
   * @param col �˻� �÷�
   * @param word �˻���
   * @param offset skip�� ���ڵ� ����
   * @param recordPerPage �������� ����� ���ڵ� ����
   * @return �˻� ���
   */
  public ArrayList<LibraryVO> list_category(
      int categoryno, 
      String col, 
      String word, 
      int offset,
      int recordPerPage) {
    ArrayList<LibraryVO> list = new ArrayList<LibraryVO>();
 
    try {
      con = dbopen.getConnection();
  
      sql = new StringBuffer();
      sql.append(" select libraryno,categoryno, writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" from library");
      
      if (col.equals("writer")) {
        sql.append(" WHERE categoryno = ? AND writer LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        // sql.append(" LIMIT " + offset + ", " + record_per_page);
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setInt(3, offset);
        pstmt.setInt(4, recordPerPage);
      } else if (col.equals("spot")) {
        sql.append(" WHERE categoryno = ? AND spot LIKE ?");
        sql.append(" ORDER BY libraryno DESC");
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setInt(3, offset);
        pstmt.setInt(4, recordPerPage);
      } else if (col.equals("content")) {
        sql.append(" WHERE categoryno = ? AND  content LIKE ?");
        sql.append(" ORDER BY pdsno DESC");
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setInt(3, offset);
        pstmt.setInt(4, recordPerPage);
      } else if (col.equals("title_content")) {
        sql.append(" WHERE categoryno = ? AND (spot LIKE ? OR content LIKE ?)");
        sql.append(" ORDER BY libraryno DESC");
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setString(2, "%" + word + "%");
        pstmt.setString(3, "%" + word + "%");
        pstmt.setInt(4, offset);
        pstmt.setInt(5, recordPerPage);
      } else { // �˻����� �ʴ� ���
        sql.append(" WHERE categoryno = ?");
        sql.append(" ORDER BY libraryno DESC");
        sql.append(" LIMIT ?, ?");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, categoryno);
        pstmt.setInt(2, offset);
        pstmt.setInt(3, recordPerPage);
      }
  
      rs = pstmt.executeQuery(); // SELECT
  
      while (rs.next() == true) {
        LibraryVO libraryVO = new LibraryVO();//���ڵ� ������ŭ ��ü ����
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA ��ü
        libraryVO.setCategoryno(rs.getInt("categoryno"));
        libraryVO.setWriter(rs.getString("writer"));
        libraryVO.setSpot(rs.getString("spot"));
        libraryVO.setHits(rs.getInt("Hits"));
        libraryVO.setRdate(rs.getString("rdate"));
        libraryVO.setSite(rs.getString("site"));
        libraryVO.setFile_name(rs.getString("file_name"));
        libraryVO.setContent(rs.getString("content"));
        libraryVO.setFstor1(rs.getString("fstor1"));
        libraryVO.setThumb(rs.getString("thumb"));
        libraryVO.setSize(rs.getLong("size"));
        libraryVO.setYoutube(rs.getString("youtube"));
        libraryVO.setVideo(rs.getString("video"));
        libraryVO.setMap_info(rs.getString("map_info"));
        libraryVO.setVisible(rs.getString("visible"));
 
        list.add(libraryVO);
      }
  
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dbclose.close(con, pstmt, rs);
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
  //������ 
  public LibraryVO pre_list(int libraryno, int categoryno) {
    LibraryVO libraryVO = null;
    
    try {
     con= dbopen.getConnection(); // DBMS�� ����
      sql = new StringBuffer();
      sql.append(" select max(libraryno)as libraryno,count(libraryno)as cnts,categoryno,writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" FROM library");
      sql.append(" where categoryno = ? and libraryno < ?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
      pstmt.setInt(1,categoryno);
      pstmt.setInt(2,libraryno);
      rs = pstmt.executeQuery(); // SELECT

      // true�� ��� ��ȯ, ���ڵ尡 �ִ��� �˻�
      // next() ���� ȣ��ô� ù��° ���ڵ�� �̵�
      // next() �ι�° ȣ����� ���� ���ڵ�� �̵��Ͽ� ������ ���ڵ���� �̵�
      while (rs.next() == true) {
        libraryVO = new LibraryVO();//���ڵ� ������ŭ ��ü ����
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA ��ü
        libraryVO.setCategoryno(rs.getInt("categoryno"));
        libraryVO.setWriter(rs.getString("writer"));
        libraryVO.setSpot(rs.getString("spot"));
        libraryVO.setContent(rs.getString("content"));
        libraryVO.setHits(rs.getInt("Hits"));
        libraryVO.setRdate(rs.getString("rdate"));
        libraryVO.setSite(rs.getString("site"));
        libraryVO.setFile_name(rs.getString("file_name"));
        libraryVO.setFstor1(rs.getString("fstor1"));
        libraryVO.setThumb(rs.getString("thumb"));
        libraryVO.setYoutube(rs.getString("youtube"));
        libraryVO.setVideo(rs.getString("video"));
        libraryVO.setMap_info(rs.getString("map_info"));
        libraryVO.setSize(rs.getLong("size"));
        libraryVO.setVisible(rs.getString("visible"));
        libraryVO.setCnts(rs.getInt("cnts"));
        
      

      }

    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt,rs);
    }
    return libraryVO;
  }
  
  //������ 
  public LibraryVO next_list(int libraryno, int categoryno) {
    LibraryVO libraryVO = null;
    
    try {
     con= dbopen.getConnection(); // DBMS�� ����
      sql = new StringBuffer();
      sql.append(" select min(libraryno)as libraryno,count(libraryno)as cnts,categoryno,writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" FROM library");
      sql.append(" where categoryno = ? and libraryno > ?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
      pstmt.setInt(1,categoryno);
      pstmt.setInt(2,libraryno);
      rs = pstmt.executeQuery(); // SELECT

      // true�� ��� ��ȯ, ���ڵ尡 �ִ��� �˻�
      // next() ���� ȣ��ô� ù��° ���ڵ�� �̵�
      // next() �ι�° ȣ����� ���� ���ڵ�� �̵��Ͽ� ������ ���ڵ���� �̵�
      while (rs.next() == true) {
        libraryVO = new LibraryVO();//���ڵ� ������ŭ ��ü ����
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA ��ü
        libraryVO.setCategoryno(rs.getInt("categoryno"));
        libraryVO.setWriter(rs.getString("writer"));
        libraryVO.setSpot(rs.getString("spot"));
        libraryVO.setContent(rs.getString("content"));
        libraryVO.setHits(rs.getInt("Hits"));
        libraryVO.setRdate(rs.getString("rdate"));
        libraryVO.setSite(rs.getString("site"));
        libraryVO.setFile_name(rs.getString("file_name"));
        libraryVO.setFstor1(rs.getString("fstor1"));
        libraryVO.setThumb(rs.getString("thumb"));
        libraryVO.setYoutube(rs.getString("youtube"));
        libraryVO.setVideo(rs.getString("video"));
        libraryVO.setMap_info(rs.getString("map_info"));
        libraryVO.setSize(rs.getLong("size"));
        libraryVO.setVisible(rs.getString("visible"));
        libraryVO.setCnts(rs.getInt("cnts"));
      
      

      }

    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt,rs);
    }
    return libraryVO;
  }
  
}
