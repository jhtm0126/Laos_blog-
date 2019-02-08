package nation.web.login;

import java.util.ArrayList;

import nation.web.category4.CategoryVO;
import nation.web.login.LoginDAO;
import nation.web.login.LoginVO;


/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.login
 * 파일명           : LoginProc.java 2018. 12. 12.
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
public class LoginProc {
  
  private LoginDAO loginDAO;
  
  public LoginProc() {
    loginDAO = new LoginDAO();
  }
  
  
  /**
   * 조회
   * @param categoryno 조회할 카테고리 번호
   * @return 조회한 카테고리 객체
   */
  public LoginVO select_admin4(String email) {
    LoginVO loginVO = this.loginDAO.select_admin4(email);
    
    return loginVO;
  }
  /**
   * 등록
   * @param pds4VO
   * @return 등록한 갯수
   */
  public int create(LoginVO loginVO){ 
    int count = this.loginDAO.create(loginVO);
    return count;
  }
  public ArrayList<LoginVO> list() {
    ArrayList<LoginVO> list =  this.loginDAO.list();
    
    return list;
  }
  
  public int delete(int loginno){
    int count = loginDAO.delete(loginno);
    
    return count;
  }
  
  /**
   * 한건의 레코드 조회
   * @param categoryno 조회할 번호
   * @return 1:성공 0:실패
   */
  public LoginVO read(int loginno) {
    LoginVO loginVO = this.loginDAO.read(loginno);
    
    return loginVO;
  }
}