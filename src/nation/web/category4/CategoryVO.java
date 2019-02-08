
package nation.web.category4;
 /**
  * 
  * ȸ�� ���� DAO class
  * <pre>
  * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
  * PMO, PM      : ���� �Ʒñ��� 
  * ��Ű����        : nation.web.category4
  * ���ϸ�           : CategoryVO.java 2018. 12. 12.
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