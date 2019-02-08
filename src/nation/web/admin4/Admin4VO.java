/*
 * Copyright 2016 JAVA CBD Project 1조, Inc. All rights reserved.
*/

package nation.web.admin4;
 
/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.admin4
 * 파일명           : Admin4VO.java 2018. 12. 12.
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
public class Admin4VO {
  private int admin4no;
  private String mname;
  private String email;
  private String passwd;
  private String tel;
  private String zipcode;
  private String address1;
  private String address2;
  private String auth;
  private String act;
  private String confirm;
  private String mdate;
  
  public Admin4VO() {
 
  }
 
  public int getAdmin4no() {
    return admin4no;
  }
 
  public void setAdmin4no(int admin4no) {
    this.admin4no = admin4no;
  }
 
  public String getMname() {
    return mname;
  }
 
  public void setMname(String mname) {
    this.mname = mname;
  }
 
  public String getEmail() {
    return email;
  }
 
  public void setEmail(String email) {
    this.email = email;
  }
 
  public String getPasswd() {
    return passwd;
  }
 
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
 
  public String getTel() {
    return tel;
  }
 
  public void setTel(String tel) {
    this.tel = tel;
  }
 
  public String getZipcode() {
    return zipcode;
  }
 
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
 
  public String getAddress1() {
    return address1;
  }
 
  public void setAddress1(String address1) {
    this.address1 = address1;
  }
 
  public String getAddress2() {
    return address2;
  }
 
  public void setAddress2(String address2) {
    this.address2 = address2;
  }
 
  public String getAuth() {
    return auth;
  }
 
  public void setAuth(String auth) {
    this.auth = auth;
  }
 
  public String getAct() {
    return act;
  }
 
  public void setAct(String act) {
    this.act = act;
  }
 
  public String getConfirm() {
    return confirm;
  }
 
  public void setConfirm(String confirm) {
    this.confirm = confirm;
  }
 
  public String getMdate() {
    return mdate;
  }
 
  public void setMdate(String mdate) {
    this.mdate = mdate;
  }
  
    
 
}