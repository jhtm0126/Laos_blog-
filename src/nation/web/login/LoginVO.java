package nation.web.login;
/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.login
 * 파일명           : LoginVO.java 2018. 12. 12.
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