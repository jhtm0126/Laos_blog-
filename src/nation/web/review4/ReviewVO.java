package nation.web.review4;
/**
 * 
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.review4
 * ���ϸ�           : ReviewVO.java 2018. 12. 12.
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
