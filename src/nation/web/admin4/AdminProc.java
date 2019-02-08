package nation.web.admin4;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import nation.web.tool.AES256Util;
/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.admin4
 * 파일명           : AdminProc.java 2018. 12. 12.
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
public class AdminProc {
  private AdminDAO adminDAO;
  
  public AdminProc() {
    adminDAO = new AdminDAO();
  }
  
  /**
   * 중복 이메일 갯수를 구합니다.
   * @param col 검색 컬럼
   * @param email 검사할 이메일
   * @return 검색된 갯수
   */
  public int EmailCount(String email){ 
    int count = 0;
    
    count = adminDAO.count("email", email);
    
    return count;
  }
  
  /**
   * Master 계정이 있는지 검사합니다.
   * @return true: 마스터 계정 존재, false: 존재하지 않음.
   */
  public boolean isMaster(){
    boolean sw = false;
    
    if (adminDAO.isMaster() >= 1 ){ // 마스터 계정이 이미 있다면
      sw = true; // 계정 존재
    }
    
    return sw;
  }

  /**
   * 관리자 등록, 패스워드를 암호화
   * @param vo 관리자 내용
   * @return 1: 등록 성공, 0: 등록 실패
   */
  public int create(Admin4VO admin4VO){
    String passwd ="";
    try {
      // web.xml -> java
      //System.out.println("passwd:"+admin4VO.getPasswd());
      passwd = new AES256Util().aesEncode(admin4VO.getPasswd());
      admin4VO.setPasswd(passwd);
      //System.out.println("crypto passwd:"+admin4VO.getPasswd());
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    int count = adminDAO.create(admin4VO);
    
    return count;
  }
  
  /**
   * 키를 조합합니다. 예) ABC + 현재시간: ABC1234567890123
   * @return
   */
  public String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26자)
    Random rnd = new Random();
    int su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su; // 문자로 변환
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
 
    code = code + new Date().getTime(); // 1970년 1월 1일 이후 1000을 1초로 표현
    // System.out.println("CODE: " + code); // CODE: XGL1449819012230 
    
    return code;
  }
  
  /**
   * 사용자가 회원 가입 이메일 링크를 눌렀을 경우의 처리
   * @param email 이메일
   * @param auth 인증 코드
   * @return 처리된 레코드 갯수
   */
  public int confirm(String email, String auth){
    int count = adminDAO.confirm(email, auth);
    
    return count;
  }
  
  /**
   * 단순 목록을 구합니다.
   * @return 목록
   */
  public ArrayList<Admin4VO> list(){
    ArrayList<Admin4VO> list = adminDAO.list();
    
    return list;
  }
   
  /**
   * 권한을 변경합니다.
   * @param admin4no 변경할 회원 번호
   * @param act 권한
   * @return 변경된 레코드 수
   */
  public int updateAct(int admin4no, String act){
    int count = adminDAO.updateAct(admin4no, act);
    
    return count;
    
  }
  
  /**
   * 패스워드가 일치하는지 확인합니다.
   * @param admin4no 회원 번호
   * @param passwd 패스워드
   * @return 1: 일치, 0: 불일치
   */
  public int countPasswd(int admin4no, String passwd){
    try {
      // web.xml -> java
      System.out.println("passwd:"+passwd);
      passwd = new AES256Util().aesEncode(passwd);
      System.out.println("암호화된passwd:"+passwd);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    int count = adminDAO.countPasswd(admin4no, passwd);
    return count;
  }
  
  /**
   * 패스워드를 변경합니다.
   * @param admin4no 변경할 회원 번호
   * @param passwd 번경할 패스워드
   * @return 1: 변경 성공, 2: 변경 실패
   */
  public int updatePasswd(int admin4no, String passwd){

    try {
      // web.xml -> java

      passwd = new AES256Util().aesEncode(passwd);

    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    int count = adminDAO.updatePasswd(admin4no, passwd);
    return count;
  }
  
  /**
   * 한건의 레코드 조회
   * @param admin1no 조회할 번호
   * @return
   */
  public Admin4VO read(int admin4no){
    return adminDAO.read(admin4no);
    
  }
 
  /**
   * 회원 수정
   * @param admin4VO 회원 내용
   * @return 1: 등록 성공, 0: 등록 실패
   */
  public int update(Admin4VO admin4VO){
    int count = adminDAO.update(admin4VO);
    
    return count;
  }
  
  /**
   * 회원을 삭제합니다.
   * @param admin4no 삭제할 회원 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int admin4no){
    int count = adminDAO.delete(admin4no);
    
    return count;
  }
  

/**

   * 로그인 처리

   * @param email 이메일

   * @param passwd 패스워드

   * @return 레코드 갯수

   */

  public int login(String email, String passwd){

    int count = 0;              // 레코드 갯수

      try {
        AES256Util aes256 = new AES256Util();
        passwd= aes256.aesEncode(passwd);
        
        count = adminDAO.login(email, passwd);
      } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InvalidKeyException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (NoSuchPaddingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InvalidAlgorithmParameterException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalBlockSizeException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (BadPaddingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    return count;

  }

   

  /**

   * 한건의 레코드 조회

   * @param email 조회할 이메일

   * @return

   */

  public Admin4VO read(String email){

    Admin4VO admin4VO = null;

    

    admin4VO = adminDAO.read(email);

    

    return admin4VO;

  }
}