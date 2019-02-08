package nation.web.url3;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nation.web.url3.UrlVO;
import nation.web.tool.DBClose;
import nation.web.tool.DBOpen;

public class UrlDAO {
  
  Connection con = null;              // DBMS ����   
  PreparedStatement pstmt = null; // SQL ����
  ResultSet rs = null;                   // SELECT ����� ����
  StringBuffer sql = null;              // SQL ����
  int count = 0;   
  
  DBOpen dbopen = null;
  DBClose dbclose = null;
  
  public UrlDAO() {
    this.dbopen = new DBOpen();
    this.dbclose = new DBClose();
  }
  public int create(UrlVO urlVO) {
  try {
    // memory�� Ŭ������ �о��, ��ü�� �������� ����.
    con = new DBOpen().getConnection();  // MySQL ����
    sql = new StringBuffer();
    
    sql.append(" insert into url(title,address,rdate)");
    sql.append(" values(?,?,now())");
    
    pstmt = con.prepareStatement(sql.toString());//sql ���� ��ü ����
    pstmt.setString(1, urlVO.getTitle());
    pstmt.setString(2, urlVO.getAddress());
   
    
    count = pstmt.executeUpdate();
    
  } catch (SQLException e) {
    e.printStackTrace();
  } finally {
    new DBClose().close(con, pstmt);
  }
  return count;
    }
  
  public ArrayList<UrlVO> list(){
    ArrayList<UrlVO> list = new ArrayList<UrlVO>();
    
    try {
      // memory�� Ŭ������ �о��, ��ü�� �������� ����.
     con = new DBOpen().getConnection(); // MySQL ����
     sql = new StringBuffer();
     
     sql.append(" select urlno,title,address,rdate");
     sql.append(" from url");
     sql.append(" order by urlno desc");
     


     pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
     rs = pstmt.executeQuery(); // SELECT SQL ����

     // ���� ȣ��� ù��° ���ڵ�� �̵��ϸ� ���� ȣ����� ���� ���ڵ�� �ڵ� �̵�
     while(rs.next()) {
       UrlVO urlVO = new UrlVO();
       
       urlVO.setUrlno(rs.getInt("urlno"));
       urlVO.setTitle(rs.getString("title"));
       urlVO.setAddress(rs.getString("address"));
       
       urlVO.setRdate(rs.getString("rdate").substring(0,  10));
       
       list.add(urlVO);
     }
   } catch (Exception e) {
     e.printStackTrace();
   } finally {
     new DBClose().close(con, pstmt, rs);
   }
    return list;
  }
  public UrlVO read(int urlno) {
    UrlVO urlVO = new UrlVO();
    try {
      
      con =new DBOpen().getConnection();
      sql = new StringBuffer();
      
      sql.append(" select urlno,title,address,rdate");
      sql.append(" from url");
      sql.append(" where urlno=?");
      
      pstmt = con.prepareStatement(sql.toString());//sql ���� ��ü ����
      pstmt.setInt(1, urlno);
      
      
      rs = pstmt.executeQuery();

      if (rs.next()) {
        urlVO.setTitle(rs.getString("title"));
        urlVO.setAddress(rs.getString("address"));
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      new DBClose().close(con, pstmt,rs);
    }
    return urlVO;
  }
  public int delete(int urlno)
  {
    try {

      con = new DBOpen().getConnection();  // MySQL ����
      sql = new StringBuffer();
      
      sql.append(" delete from url");
      
      sql.append(" where urlno =? ");
      
      
      pstmt = con.prepareStatement(sql.toString());//sql ���� ��ü ����
    
      pstmt.setInt(1, urlno );
      

      count = pstmt.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      new DBClose().close(con, pstmt);
    }
    return count;
  }
  
  public int update(UrlVO urlVO) {
    try {
      
      con = new DBOpen().getConnection();
      sql = new StringBuffer();
      
      sql.append(" update url set ");
      sql.append(" title =?, address= ? ");
      sql.append(" where urlno=?");
      
     
      
      pstmt = con.prepareStatement(sql.toString());//sql ���� ��ü ����
      pstmt.setString(1, urlVO.getTitle());
      pstmt.setString(2, urlVO.getAddress());
      pstmt.setInt(3, urlVO.getUrlno());
     
      count = pstmt.executeUpdate();
      
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      new DBClose().close(con, pstmt);
    }
    return count;
  }
  public ArrayList<UrlVO> list_home(int count){
    ArrayList<UrlVO> list = new ArrayList<UrlVO>();
    
    try {
      // memory�� Ŭ������ �о��, ��ü�� �������� ����.
     con = new DBOpen().getConnection(); // MySQL ����
     sql = new StringBuffer();
     
     sql.append(" select urlno,title,address,rdate");
     sql.append(" from url");
     sql.append(" order by urlno desc");
     sql.append(" limit ?");
     
     


     pstmt = con.prepareStatement(sql.toString()); 
     pstmt.setInt(1, count);
     rs = pstmt.executeQuery(); // SELECT SQL ����
     
  
     // ���� ȣ��� ù��° ���ڵ�� �̵��ϸ� ���� ȣ����� ���� ���ڵ�� �ڵ� �̵�
     while(rs.next()) {
       UrlVO urlVO = new UrlVO();
       
       urlVO.setUrlno(rs.getInt("urlno"));
       urlVO.setTitle(rs.getString("title"));
       urlVO.setAddress(rs.getString("address"));
       urlVO.setRdate(rs.getString("rdate").substring(0,  10));
       
       list.add(urlVO);
     }
   } catch (SQLException e) {
     e.printStackTrace();
   } finally {
     new DBClose().close(con, pstmt, rs);
   }
    return list;
  }
  
  
  
}
