package nation.web.login;

import java.util.ArrayList;

import nation.web.category4.CategoryVO;
import nation.web.login.LoginDAO;
import nation.web.login.LoginVO;


/**
 * 
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.login
 * ���ϸ�           : LoginProc.java 2018. 12. 12.
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
public class LoginProc {
  
  private LoginDAO loginDAO;
  
  public LoginProc() {
    loginDAO = new LoginDAO();
  }
  
  
  /**
   * ��ȸ
   * @param categoryno ��ȸ�� ī�װ� ��ȣ
   * @return ��ȸ�� ī�װ� ��ü
   */
  public LoginVO select_admin4(String email) {
    LoginVO loginVO = this.loginDAO.select_admin4(email);
    
    return loginVO;
  }
  /**
   * ���
   * @param pds4VO
   * @return ����� ����
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
   * �Ѱ��� ���ڵ� ��ȸ
   * @param categoryno ��ȸ�� ��ȣ
   * @return 1:���� 0:����
   */
  public LoginVO read(int loginno) {
    LoginVO loginVO = this.loginDAO.read(loginno);
    
    return loginVO;
  }
}