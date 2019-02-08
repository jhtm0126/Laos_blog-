package nation.web.review4;
/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.review4
 * 파일명           : ReviewVO.java 2018. 12. 12.
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
public class ReviewVO {
  private int reviewno;
  private String title;
  private String content;
  private String rdate;
  private String passwd;
  
  
  public ReviewVO() {
    
  }


  public int getReviewno() {
    return reviewno;
  }


  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
  }


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }


  public void setContent(String content) {
    this.content = content;
  }


  public String getRdate() {
    return rdate;
  }


  public void setRdate(String rdate) {
    this.rdate = rdate;
  }


  public String getPasswd() {
    return passwd;
  }


  public void setPasswd(String passwd) {
    this.passwd = passwd;
  } 
  
 
}
