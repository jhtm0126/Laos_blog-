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
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.login
 * ���ϸ�           : LoginDAO.java 2018. 12. 12.
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
public class LoginDAO {
  private Connection con = null;              // DBMS ����   
  private PreparedStatement pstmt = null; // SQL ����
  private ResultSet rs = null;                   // SELECT ����� ����
  private StringBuffer sql = null;              // SQL ����
  int count = 0; 
  
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  public LoginDAO() {
    this.dbopen = new DBOpen();
    this.dbclose = new DBClose();
  }
  
  /**
   * ��ȸ
   * @param categoryno ��ȸ�� ī�װ� ��ȣ
   * @return ��ȸ�� ī�װ� ��ü
   */
  public LoginVO select_admin4(String email) {
    LoginVO loginVO = new LoginVO(); 
    
    try {
     con= dbopen.getConnection(); // DBMS�� ����
      sql = new StringBuffer();
      sql.append(" SELECT admin4no from admin4 where email='"+email+"' ");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
  
      rs = pstmt.executeQuery(); // SELECT
 
      // true�� ��� ��ȯ, ���ڵ尡 �ִ��� �˻�
      // next() ���� ȣ��ô� ù��° ���ڵ�� �̵�
      // next() �ι�° ȣ����� ���� ���ڵ�� �̵��Ͽ� ������ ���ڵ���� �̵�
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
   * ������ ���
   * @param admin4VO ������ ����
   * @return 1: ��� ����, 0: ��� ����
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
   * ��� ���ڵ�
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
        LoginVO loginVO = new LoginVO();//���ڵ� ������ŭ ��ü ����
        loginVO.setLoginno(rs.getInt("loginno")); // DBMS -> JAVA ��
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
   * ��ȸ
   * @param categoryno ��ȸ�� ī�װ� ��ȣ
   * @return ��ȸ�� ī�װ� ��ü
   */
  public LoginVO read(int loginno) {
    LoginVO loginVO = new LoginVO();
    
    try {
     con= dbopen.getConnection(); // DBMS�� ����
      sql = new StringBuffer();
      sql.append(" SELECT loginno, admin4no, name,ip, logindate");
      sql.append(" FROM login");
      sql.append(" WHERE loginno = ?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL ���� ��ü ����
      pstmt.setInt(1,loginno);
      rs = pstmt.executeQuery(); // SELECT
 
      // true�� ��� ��ȯ, ���ڵ尡 �ִ��� �˻�
      // next() ���� ȣ��ô� ù��° ���ڵ�� �̵�
      // next() �ι�° ȣ����� ���� ���ڵ�� �̵��Ͽ� ������ ���ڵ���� �̵�
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
