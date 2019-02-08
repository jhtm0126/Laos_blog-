package nation.web.login;
/**
 * 
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.login
 * ���ϸ�           : LoginVO.java 2018. 12. 12.
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
public class LoginVO {

    private int loginno;
    private int admin4no;
    private String name;
    private String ip;
    private String logindate;
    
    public LoginVO() {
      
    }
    


  
    

    public LoginVO(int loginno, int admin4no, String name, String ip, String logindate) {
      this.loginno = loginno;
      this.admin4no = admin4no;
      this.name = name;
      this.ip = ip;
      this.logindate = logindate;
    }






    public String getName() {
      return name;
    }






    public void setName(String name) {
      this.name = name;
    }






    public int getAdmin4no() {
      return admin4no;
    }



    public void setAdmin4no(int admin4no) {
      this.admin4no = admin4no;
    }



    public int getLoginno() {
      return loginno;
    }

    public void setLoginno(int loginno) {
      this.loginno = loginno;
    }

  

   

    public String getIp() {
      return ip;
    }

    public void setIp(String ip) {
      this.ip = ip;
    }

    public String getLogindate() {
      return logindate;
    }

    public void setLogindate(String logindate) {
      this.logindate = logindate;
    }
    

}