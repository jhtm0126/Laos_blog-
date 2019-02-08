package nation.web.review4;

import java.util.ArrayList;

import nation.web.review4.ReviewVO;

/**
 * 
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.review4
 * ���ϸ�           : ReviewProc.java 2018. 12. 12.
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
public class ReviewProc {
  
  private ReviewDAO reviewDAO;
  
  public ReviewProc() {
    this.reviewDAO = new ReviewDAO();
  }
  /**
   * ���
   * @param reviewVO ��� ����
   * @return ����:1 ����:0
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
