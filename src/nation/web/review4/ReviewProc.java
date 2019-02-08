package nation.web.review4;

import java.util.ArrayList;

import nation.web.review4.ReviewVO;

/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.review4
 * 파일명           : ReviewProc.java 2018. 12. 12.
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
public class ReviewProc {
  
  private ReviewDAO reviewDAO;
  
  public ReviewProc() {
    this.reviewDAO = new ReviewDAO();
  }
  /**
   * 목록
   * @param reviewVO 목록 내용
   * @return 성공:1 실패:0
   */
  public ArrayList<ReviewVO>list(){
    ArrayList<ReviewVO> list =  this.reviewDAO.list();
    
    return list;
 
  }
  public int create(ReviewVO reviewVO) {
    int count = this.reviewDAO.create(reviewVO);
  
   return count;
    }
  
  public ReviewVO read(int reviewno) {
    ReviewVO reviewVO = this.reviewDAO.read(reviewno);
    return reviewVO;
  }
  public int update(ReviewVO reviewVO) {
    int count = this.reviewDAO.update(reviewVO);
    return count;
  }
  public int delete(int reviewno) {
    int count = this.reviewDAO.delete(reviewno);
    return count;
  }
  public int passwordCheck(int reviewno, String passwd) {
    int count = this.reviewDAO.passwordCheck(reviewno, passwd);
    return count;
  }
  
  public ArrayList<ReviewVO> list_home(int count) {
    ArrayList<ReviewVO> list =  this.reviewDAO.list_home(count);
    return list;
  }
}
