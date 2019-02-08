
package nation.web.category4;
 /**
  * 
  * 회원 관련 DAO class
  * <pre>
  * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
  * PMO, PM      : 지도 훈련교사 
  * 패키지명        : nation.web.category4
  * 파일명           : CategoryVO.java 2018. 12. 12.
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

public class CategoryVO {
  private int categoryno;
  private String title;
  private int seqno;
  private String visible;
  private String ids;
  private int cnt;
  private String rdate;
  
  public CategoryVO() {
 
  }
 
  public CategoryVO(int categoryno, String title, int seqno, String visible, String ids, int cnt,String rdate) {
    this.categoryno = categoryno;
    this.title = title;
    this.seqno = seqno;
    this.visible = visible;
    this.ids = ids;
    this.cnt = cnt;
    this.rdate = rdate;
  }
  
  public String getRdate() {
    return rdate;
  }
 
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
 
  public int getCategoryno() {
    return categoryno;
  }
 
  public void setCategoryno(int categoryno) {
    this.categoryno = categoryno;
  }
 
  public String getTitle() {
    return title;
  }
 
  public void setTitle(String title) {
    this.title = title;
  }
 
  public int getSeqno() {
    return seqno;
  }
 
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
 
  public String getVisible() {
    return visible;
  }
 
  public void setVisible(String visible) {
    this.visible = visible;
  }
 
  public String getIds() {
    return ids;
  }
 
  public void setIds(String ids) {
    this.ids = ids;
  }
 
  public int getCnt() {
    return cnt;
  }
 
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  
} 