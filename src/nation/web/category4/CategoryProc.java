package nation.web.category4;

import java.util.ArrayList;
 /**
  * 
  * 회원 관련 DAO class
  * <pre>
  * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
  * PMO, PM      : 지도 훈련교사 
  * 패키지명        : nation.web.category4
  * 파일명           : CategoryProc.java 2018. 12. 12.
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
public class CategoryProc {
  private CategoryDAO categoryDAO;
  
  public CategoryProc() {
    categoryDAO = new CategoryDAO();
  }
  
  public int create(CategoryVO categoryVO) {
    int count = this.categoryDAO.create(categoryVO);
    
    return count;
  }
  /**
   * 전체 레코드 갯수
   * @return 전체 레코드 갯수
   */
  public int count() {
  int count=this.categoryDAO.count();
  
  return count;
  }
  
  /**
   * 페이징 목록 출력
   * @param nowPage 현재 페이지
   * @return 레코드 목록
   */
  public ArrayList<CategoryVO> list(int nowPage) {
    ArrayList<CategoryVO> list = null; 
   list= this.categoryDAO.list(nowPage);
   return list;
  }
  /**
   * 모든 카테고리 목록
   * @return
   */
  public ArrayList<CategoryVO> list() {
    ArrayList<CategoryVO> list = null;
    
    list = this.categoryDAO.list();
    
    return list;
    
  }
  
  /**
   * 한건의 레코드 조회
   * @param categoryno 조회할 번호
   * @return 1:성공 0:실패
   */
  public CategoryVO read(int categoryno) {
    CategoryVO categoryVO = this.categoryDAO.read(categoryno);
    
    return categoryVO;
  }
 
  /**
   * 카테고리 수정
   * @param categoryVO
   * @return 수정된 갯수 1 or 0
   */
  public int update(CategoryVO categoryVO) {
    int count = categoryDAO.update(categoryVO);
    
    return count;
  }
  /**
   * 삭제   
   * @param categoryno 삭제할 카테고리 번호
   * @return 1:성공 0:실패
   */
  
  public int delete(int categoryno) {
    int count = this.categoryDAO.delete(categoryno);
  
    return count;
  }
  
  public int increaseSeqno(int categoryno) {
    int count = categoryDAO.increaseSeqno(categoryno);
    
    return count;
  }
  
  public int decreaseSeqno(int categoryno) {
    int count = 0;
    
    count = categoryDAO.decreaseSeqno(categoryno);
    
    return count;
  }
  
  /**
   * 화면에 출력
   * @param categoryno 출력할 레코드 PK 번호
   * @return 처리된 레코드 갯수
   */
  public int show(int categoryno) { 
    int count = this.categoryDAO.show(categoryno);
    
    return count;
  }
  
  /**
   * 화면에서 숨기기
   * @param categoryno 숨길 레코드 PK 번호
   * @return 처리된 레코드 갯수
   */
  public int hide(int categoryno) { 
    int count = this.categoryDAO.hide(categoryno);
    
    return count;
  }
  
  /**
   * 출력 순서 최대값 계산
   * @return
   */
  public int getMaxSeqno() { 
    int seqno = this.categoryDAO.getMaxSeqno();
    
    return seqno;
  }
  
  /**
   * 관련 게시판 글수의 증가
   * @param categoryno
   * @return
   */
  public int increaseCnt(int categoryno) {
    int count = 0;                     // 처리된 레코드 갯수
    
    count = categoryDAO.increaseCnt(categoryno);
    
    return count;
  }
 
  /**
   * 관련 게시판 글수의 감소
   * @param categoryno
   * @return
   */
  public int decreaseCnt(int categoryno) { 
    int count = 0;                     // 처리된 레코드 갯수
    
    count = categoryDAO.decreaseCnt(categoryno);
    
    return count;
  }
  
  public int countByCategory(int categoryno) {
    int count=categoryDAO.countByCategory(categoryno);
    
    return count;
  }
} 
