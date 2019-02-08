package nation.web.category4;

import java.util.ArrayList;
 /**
  * 
  * ȸ�� ���� DAO class
  * <pre>
  * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
  * PMO, PM      : ���� �Ʒñ��� 
  * ��Ű����        : nation.web.category4
  * ���ϸ�           : CategoryProc.java 2018. 12. 12.
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
   * ��ü ���ڵ� ����
   * @return ��ü ���ڵ� ����
   */
  public int count() {
  int count=this.categoryDAO.count();
  
  return count;
  }
  
  /**
   * ����¡ ��� ���
   * @param nowPage ���� ������
   * @return ���ڵ� ���
   */
  public ArrayList<CategoryVO> list(int nowPage) {
    ArrayList<CategoryVO> list = null; 
   list= this.categoryDAO.list(nowPage);
   return list;
  }
  /**
   * ��� ī�װ� ���
   * @return
   */
  public ArrayList<CategoryVO> list() {
    ArrayList<CategoryVO> list = null;
    
    list = this.categoryDAO.list();
    
    return list;
    
  }
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * @param categoryno ��ȸ�� ��ȣ
   * @return 1:���� 0:����
   */
  public CategoryVO read(int categoryno) {
    CategoryVO categoryVO = this.categoryDAO.read(categoryno);
    
    return categoryVO;
  }
 
  /**
   * ī�װ� ����
   * @param categoryVO
   * @return ������ ���� 1 or 0
   */
  public int update(CategoryVO categoryVO) {
    int count = categoryDAO.update(categoryVO);
    
    return count;
  }
  /**
   * ����   
   * @param categoryno ������ ī�װ� ��ȣ
   * @return 1:���� 0:����
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
   * ȭ�鿡 ���
   * @param categoryno ����� ���ڵ� PK ��ȣ
   * @return ó���� ���ڵ� ����
   */
  public int show(int categoryno) { 
    int count = this.categoryDAO.show(categoryno);
    
    return count;
  }
  
  /**
   * ȭ�鿡�� �����
   * @param categoryno ���� ���ڵ� PK ��ȣ
   * @return ó���� ���ڵ� ����
   */
  public int hide(int categoryno) { 
    int count = this.categoryDAO.hide(categoryno);
    
    return count;
  }
  
  /**
   * ��� ���� �ִ밪 ���
   * @return
   */
  public int getMaxSeqno() { 
    int seqno = this.categoryDAO.getMaxSeqno();
    
    return seqno;
  }
  
  /**
   * ���� �Խ��� �ۼ��� ����
   * @param categoryno
   * @return
   */
  public int increaseCnt(int categoryno) {
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = categoryDAO.increaseCnt(categoryno);
    
    return count;
  }
 
  /**
   * ���� �Խ��� �ۼ��� ����
   * @param categoryno
   * @return
   */
  public int decreaseCnt(int categoryno) { 
    int count = 0;                     // ó���� ���ڵ� ����
    
    count = categoryDAO.decreaseCnt(categoryno);
    
    return count;
  }
  
  public int countByCategory(int categoryno) {
    int count=categoryDAO.countByCategory(categoryno);
    
    return count;
  }
} 
