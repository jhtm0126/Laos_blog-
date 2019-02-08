package nation.web.admin4;

 

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
/**
 * 
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.admin4
 * ���ϸ�           : AdminTool.java 2018. 12. 12.
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
 

public class AdminTool {

  /**

   * ������ �������� �˻��մϴ�.

   * @param request

   * @return true or false

   */

  public static synchronized boolean isAdmin(HttpServletRequest request){

    boolean sw = false;

    

    HttpSession session = request.getSession(); // java class���� session ��ü ����

    String act = (String)session.getAttribute("act"); // ���� ����

    

    if (act != null){

      if (act.equals("M") || act.equals("Y")){ // �α��� ����

        sw = true;

      }

    }

    return sw;

  }

 

  /**

   * ������ �������� �˻��մϴ�.

   * @param request

   * @return true: ������ ����

   */

  public static synchronized boolean isMaster(HttpServletRequest request){

    boolean sw = false;

    

    HttpSession session = request.getSession();

    String act = (String)session.getAttribute("act"); // ��Ȱ

    // System.out.println("--> Tool.java act: " + act);

    

    if (act != null){

      if (act.equals("M")){ // ���������� �˻�

        sw = true;

      }

    }

    return sw;

  }

  

}