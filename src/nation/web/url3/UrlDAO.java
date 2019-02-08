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
  
  Connection con = null;              // DBMS 연결   
  PreparedStatement pstmt = null; // SQL 실행
  ResultSet rs = null;                   // SELECT 결과를 저장
  StringBuffer sql = null;              // SQL 저장
  int count = 0;   
  
  DBOpen dbopen = null;
  DBClose dbclose = null;
  
  public UrlDAO() {
    this.dbopen = new DBOpen();
    this.dbclose = new DBClose();
  }
  public int create(UrlVO urlVO) {
  try {
    // memory로 클래스를 읽어옴, 객체는 생성하지 않음.
    con = new DBOpen().getConnection();  // MySQL 연결
    sql = new StringBuffer();
    
    sql.append(" insert into url(title,address,rdate)");
    sql.append(" values(?,?,now())");
    
    pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
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
      // memory로 클래스를 읽어옴, 객체는 생성하지 않음.
     con = new DBOpen().getConnection(); // MySQL 연결
     sql = new StringBuffer();
     
     sql.append(" select urlno,title,address,rdate");
     sql.append(" from url");
     sql.append(" order by urlno desc");
     


     pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
     rs = pstmt.executeQuery(); // SELECT SQL 실행

     // 최초 호출시 첫번째 레코드로 이동하며 그후 호출부터 다음 레코드로 자동 이동
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
      
      pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
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

      con = new DBOpen().getConnection();  // MySQL 연결
      sql = new StringBuffer();
      
      sql.append(" delete from url");
      
      sql.append(" where urlno =? ");
      
      
      pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
    
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
      
     
      
      pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
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
      // memory로 클래스를 읽어옴, 객체는 생성하지 않음.
     con = new DBOpen().getConnection(); // MySQL 연결
     sql = new StringBuffer();
     
     sql.append(" select urlno,title,address,rdate");
     sql.append(" from url");
     sql.append(" order by urlno desc");
     sql.append(" limit ?");
     
     


     pstmt = con.prepareStatement(sql.toString()); 
     pstmt.setInt(1, count);
     rs = pstmt.executeQuery(); // SELECT SQL 실행
     
  
     // 최초 호출시 첫번째 레코드로 이동하며 그후 호출부터 다음 레코드로 자동 이동
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
