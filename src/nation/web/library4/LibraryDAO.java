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
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.library4
 * 파일명           : LibraryDAO.java 2018. 12. 12.
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
public class LibraryDAO {
  
  Connection con = null;              // DBMS 연결   
  PreparedStatement pstmt = null; // SQL 실행
  ResultSet rs = null;                   // SELECT 결과를 저장
  StringBuffer sql = null;              // SQL 저장
  int count = 0; 
  
  DBOpen dbopen = null;
  DBClose dbclose = null;
  

  
  public LibraryDAO() {
    this.dbopen = new DBOpen();
    this.dbclose = new DBClose();
  }

  
  /**
   * 등록
   * @param pds4VO
   * @return 등록한 갯수
   */
  public int create(LibraryVO libraryVO){ // call by reference
    int count = 0;                     // 처리된 레코드 갯수
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
   * 모든 레코드
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
        LibraryVO libraryVO = new LibraryVO();//레코드 갯수만큼 객체 생성
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA 객체
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
 * 카테고리별 레코드 목록
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
        LibraryVO libraryVO = new LibraryVO();//레코드 갯수만큼 객체 생성
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA 객체
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
   * 조회수 증가
   * @param pdsno
   */
  public void increaseCnt(int libraryno){ // call by value
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();
      sql.append(" UPDATE library");
      sql.append(" SET hits = hits + 1"); 
      sql.append(" WHERE libraryno=?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
      pstmt.setInt(1, libraryno);
      count = pstmt.executeUpdate(); 

    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      dbclose.close(con, pstmt);
    }
   
    }
  /**
   * 조회
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
        libraryVO = new LibraryVO();//레코드 갯수만큼 객체 생성
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA 객체
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
     con= dbopen.getConnection(); // DBMS에 연결
      sql = new StringBuffer();
      sql.append(" SELECT COUNT(*) as cnt");
      sql.append(" FROM library");
      sql.append(" WHERE libraryno=? AND pw=?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
      pstmt.setInt(1,libraryno);
      pstmt.setString(2,pw);
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
  
  /**
   * 글 수정
   * @param pds4VO
   * @return 처리한 갯수
   */
  public int update(LibraryVO libraryVO){ // call by reference
    
    try {
      
      con = dbopen.getConnection();
      sql = new StringBuffer();
      
      sql.append(" UPDATE library");
      sql.append(" SET writer=?, spot=?,");
      sql.append(" content=?, site=?");
      sql.append(" WHERE libraryno=?");
      
      pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
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
   * Visible 모드 변경
   * @param pdsno
   * @param visible Y / N
   * @return 처리한 갯수
   */
  public int show_hide(int libraryno, String visible){
  try {
      
      con = dbopen.getConnection();
      sql = new StringBuffer();
      
      sql.append(" UPDATE library");
      sql.append(" SET visible =?");
      sql.append(" WHERE libraryno=?");
      
     pstmt = con.prepareStatement(sql.toString());//sql 실행 객체 생성
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
 * 신규 사진 등록
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

      pstmt = con.prepareStatement(sql.toString());// sql 실행 객체 생성
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
   * 글 삭제
   * 
   * @param 삭제할 PK 번호
   * @return 처리한 갯수
   */
  public int delete(int libraryno) {
    try {
      con = dbopen.getConnection();
      sql = new StringBuffer();

      sql.append(" DELETE FROM library");
      sql.append(" WHERE libraryno=?");

      pstmt = con.prepareStatement(sql.toString());// sql 실행 객체 생성
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

      pstmt = con.prepareStatement(sql.toString());// sql 실행 객체 생성
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

      pstmt = con.prepareStatement(sql.toString());// sql 실행 객체 생성
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

      pstmt = con.prepareStatement(sql.toString());// sql 실행 객체 생성
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
   * 전체 카테고리 검색
   * @param col 검색 컬럼
   * @param word 검색어
   * @return 검색 목록
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
        
      } else { // 검색하지 않는 경우
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        
      }
           
      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        LibraryVO libraryVO = new LibraryVO();//레코드 갯수만큼 객체 생성
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA 객체
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
   * 카테고리별 검색
   * @param categoryno 카테고리 번호
   * @param col 검색 컬럼
   * @param word 검색어
   * @return 검색 목록
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
        
      } else { // 검색하지 않는 경우
        sql.append(" WHERE categoryno = ?");
        sql.append(" ORDER BY libraryno DESC");
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1,  categoryno);
        
      }


      rs = pstmt.executeQuery(); // SELECT
 
      while (rs.next() == true) {
        LibraryVO libraryVO = new LibraryVO();//레코드 갯수만큼 객체 생성
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA 객체
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
   * 카테고리별로 검색된 레코드 갯수
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
      } else { // 검색하지 않는 경우
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
   * 카테고리별로 검색 및 페이징이 구현된 목록, SELECT된 목록중에
   * 페이징 기법을 이용하여 특정 레코드 부분(10개)만 가져옵니다. 
   * @param categoryno 카테고리 번호
   * @param col 검색 컬럼
   * @param word 검색어
   * @param offset skip할 레코드 갯수
   * @param recordPerPage 페이지당 출력할 레코드 갯수
   * @return 검색 목록
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
      } else { // 검색하지 않는 경우
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
        LibraryVO libraryVO = new LibraryVO();//레코드 갯수만큼 객체 생성
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA 객체
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
  //이전글 
  public LibraryVO pre_list(int libraryno, int categoryno) {
    LibraryVO libraryVO = null;
    
    try {
     con= dbopen.getConnection(); // DBMS에 연결
      sql = new StringBuffer();
      sql.append(" select max(libraryno)as libraryno,count(libraryno)as cnts,categoryno,writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" FROM library");
      sql.append(" where categoryno = ? and libraryno < ?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
      pstmt.setInt(1,categoryno);
      pstmt.setInt(2,libraryno);
      rs = pstmt.executeQuery(); // SELECT

      // true일 경우 순환, 레코드가 있는지 검사
      // next() 최초 호출시는 첫번째 레코드로 이동
      // next() 두번째 호출부터 다음 레코드로 이동하여 마지막 레코드까지 이동
      while (rs.next() == true) {
        libraryVO = new LibraryVO();//레코드 갯수만큼 객체 생성
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA 객체
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
  
  //이전글 
  public LibraryVO next_list(int libraryno, int categoryno) {
    LibraryVO libraryVO = null;
    
    try {
     con= dbopen.getConnection(); // DBMS에 연결
      sql = new StringBuffer();
      sql.append(" select min(libraryno)as libraryno,count(libraryno)as cnts,categoryno,writer,spot,content, pw,");
      sql.append(" hits, rdate,site, file_name, fstor1, thumb, size,");
      sql.append(" map_info, youtube,video, visible");
      sql.append(" FROM library");
      sql.append(" where categoryno = ? and libraryno > ?");
      
      pstmt = con.prepareStatement(sql.toString()); // SQL 실행 객체 생성
      pstmt.setInt(1,categoryno);
      pstmt.setInt(2,libraryno);
      rs = pstmt.executeQuery(); // SELECT

      // true일 경우 순환, 레코드가 있는지 검사
      // next() 최초 호출시는 첫번째 레코드로 이동
      // next() 두번째 호출부터 다음 레코드로 이동하여 마지막 레코드까지 이동
      while (rs.next() == true) {
        libraryVO = new LibraryVO();//레코드 갯수만큼 객체 생성
        libraryVO.setLibraryno(rs.getInt("libraryno")); // DBMS -> JAVA 객체
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
