package nation.web.library4;

import java.util.ArrayList;

import nation.web.library4.LibraryDAO;
import nation.web.library4.LibraryVO;

import nation.web.tool.Tool1;

/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.library4
 * 파일명           : LibraryProc.java 2018. 12. 12.
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
public class LibraryProc {

  private LibraryDAO libraryDAO;
  
  public LibraryProc() {
    libraryDAO = new LibraryDAO();
  }
  
  /**
   * 등록
   * @param pds4VO
   * @return 등록한 갯수
   */
  public int create(LibraryVO libraryVO){ 
    int count = this.libraryDAO.create(libraryVO);
    return count;
  }
  public ArrayList<LibraryVO> list() {
    ArrayList<LibraryVO> list =  this.libraryDAO.list();
    
    return list;
  }
  /**
 * 카테고리별 레코드 목록
 * 
 * @return
 */
public ArrayList<LibraryVO> list_category(int categoryno) {
  ArrayList<LibraryVO> list =this.libraryDAO.list_category(categoryno);
  
  return list;
  }

/**
 * 조회수 증가
 * @param pdsno
 */
public void increaseCnt(int libraryno){
  this.libraryDAO.increaseCnt(libraryno);
  }

/**
 * 조회
 * 
 * @return
 */
public LibraryVO read(int libraryno){
  LibraryVO libraryVO=this.libraryDAO.read(libraryno);
  return libraryVO;
}

/**
 * 
 * @param pdsno
 * @param visible
 * @return
 */
public int show_hide(int libraryno, String visible){
  if(visible.equals("Y")) {
    visible="N";
  }else {
    visible="Y";
  }
  int count=this.libraryDAO.show_hide(libraryno, visible);
  return count;
}

public int update(LibraryVO libraryVO){ // call by reference
  int count = this.libraryDAO.update(libraryVO);
  return count;
}
public int passwordCheck(int libraryno, String pw) {
  int count =this.libraryDAO.passwordCheck(libraryno, pw);
  return count;
}

public int new_create_file_name(LibraryVO libraryVO) {
  int count = this.libraryDAO.update_file_name(libraryVO);
  return count;
}
public int delete(int libraryno) {
  int count =this.libraryDAO.delete(libraryno);
  return count;
}

/**
 * 파일 삭제
 * 
 * @param libraryno
 *          삭제할 레코드 번호
 * @return 처리 갯수
 */
public int delete_file1(int libraryno) {
  LibraryVO libraryVO = new LibraryVO(); // 파일명을 삭제하기위한 객체 생성
  libraryVO.setFile_name("");
  libraryVO.setFstor1("");
  libraryVO.setSize(0);
  libraryVO.setLibraryno(libraryno);

  int count = libraryDAO.update_file_name(libraryVO);
  return count;

}

public int replace_file1(LibraryVO libraryVO) {
  int count = this.libraryDAO.update_file_name(libraryVO);
  return count;
}


/**
 * Map 신규 등록
 * @param pdsno
 * @return
 */
public int new_create_map_info(LibraryVO libraryVO) {
  int count = this.libraryDAO.update_map_info(libraryVO);
  return count;
}
/**
 * Map 신규 삭제
 * @param pdsno
 * @return
 */
public int delete_map_info(int libraryno) {
  LibraryVO libraryVO = new LibraryVO(); // 파일명을 삭제하기위한 객체 생성
  libraryVO.setMap_info("");
  libraryVO.setLibraryno(libraryno);

  int count = libraryDAO.update_map_info(libraryVO);
  return count;

}
public int replace_map_info(LibraryVO libraryVO) {
  int count = this.libraryDAO.update_map_info(libraryVO);
  return count;
}

/**
 * youtube 신규 등록
 * @param pdsno
 * @return
 */
public int new_create_youtube(LibraryVO libraryVO) {
  int count = this.libraryDAO.update_youtube(libraryVO);
  return count;
}
/**
 * youtube 신규 삭제
 * @param pdsno
 * @return
 */
public int delete_youtube(int libraryno) {
  LibraryVO libraryVO = new LibraryVO(); // 파일명을 삭제하기위한 객체 생성
  libraryVO.setYoutube("");
  libraryVO.setLibraryno(libraryno);

  int count = libraryDAO.update_youtube(libraryVO);
  return count;

}
public int replace_youtube(LibraryVO libraryVO) {
  int count = this.libraryDAO.update_youtube(libraryVO);
  return count;
}

/**
 * 신규 MP4 등록
 * @param pdsno
 * @return
 */
public int new_create_video(LibraryVO libraryVO) {
  int count = libraryDAO.update_video(libraryVO);
  return count;
}

/**
 * MP4 삭제
 * @param pdsno
 * @return
 */
public int delete_video(int libraryno) {
  LibraryVO libraryVO = new LibraryVO(); // 지도를 삭제하기위한 객체 생성
  libraryVO.setVideo(""); // 지도 관련 script를 삭제합니다.
  libraryVO.setLibraryno(libraryno);
  
  int count = libraryDAO.update_video(libraryVO);
  
  return count;
}

/**
 * MP4 변경
 * @param pdsno
 * @return
 */
public int replace_video(LibraryVO libraryVO) {
  int count = libraryDAO.update_video(libraryVO);
  return count;
}

/**
 * 전체 카테고리 검색
 * @param col 검색 컬럼
 * @param word 검색어
 * @return 검색 목록
 */
public ArrayList<LibraryVO> list(String col, String word) {
  ArrayList<LibraryVO> list = libraryDAO.list(col, word);
  return list;
}

/**
 * 카테고리별 검색
 * @param categoryno 카테고리 번호
 * @param col 검색 컬럼
 * @param word 검색어
 * @return 검색 목록
 */
public ArrayList<LibraryVO> list_category(int categoryno, String col, String word) {
  ArrayList<LibraryVO> list = libraryDAO.list_category(categoryno, col, word);
  return list;
}

/* 목록용 파일을 설정하며 SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
* 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
*
* @param listFile 목록 파일명
* @param recordCount 검색 레코드수 
* @param nowPage     현재 페이지
* @param recordPerPage 페이지당 레코드 수
* @param col 검색 컬럼  
* @param word 검색어
* @param categoryno 카테고리 그룹
* @return 페이징 생성 문자열
*/ 
public String paging4(String listFile, int recordCount, int nowPage, int recordPerPage, String col, String word, int categoryno){ 
 int pagePerBlock = 10; // 블럭당 페이지 수 
 
 // 현재 450개의 레코드 존재한다고 가정
 // totalPage = 450 / 10 -> 45개의 페이지
 int totalPage = (int)(Math.ceil((double)recordCount/recordPerPage)); // 전체 페이지
 
 // 45 / 10 -> 5개의 그룹
 int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// 전체 그룹 
 
 // 5 / 10 -> 0.5 -> 1
 // 15 / 10 -> 1.5 -> 2
 // 25 / 10 -> 2.5 -> 3
 int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // 현재 그룹 
 
 // 1 group: ((1 - 1) * 10) + 1 -> start Page: 1
 //              (1 * 10)            -> end Page: 10
 
 // 2 group: ((2 - 1) * 10) + 1 -> start Page: 11
 //              (2 * 10)            -> end Page: 20

 // 3 group: ((3 - 1) * 10) + 1 -> start Page: 21
 //              (2 * 10)            -> end Page: 30
 
 int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작  
 int endPage = (nowGrp * pagePerBlock);             // 특정 그룹의 페이지 목록 종료   
  
 StringBuffer str = new StringBuffer(); 
  
 str.append("<style type='text/css'>"); 
 str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
 str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
 str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
 str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
 str.append("  .span_box_1{"); 
 str.append("    text-align: center;");    
 str.append("    font-size: 1em;"); 
 str.append("    border: 1px;"); 
 str.append("    border-style: solid;"); 
 str.append("    border-color: #cccccc;"); 
 str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
 str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
 str.append("  }"); 
 str.append("  .span_box_2{"); 
 str.append("    text-align: center;");    
 str.append("    background-color: #668db4;"); 
 str.append("    color: #FFFFFF;"); 
 str.append("    font-size: 1em;"); 
 str.append("    border: 1px;"); 
 str.append("    border-style: solid;"); 
 str.append("    border-color: #cccccc;"); 
 str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
 str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
 str.append("  }"); 
 str.append("</style>"); 
 str.append("<DIV id='paging'>"); 
// str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 

 int _nowPage = (nowGrp-1) * pagePerBlock; // 10개 이전 페이지로 이동 
 if (nowGrp >= 2){ 
   str.append("<span class='span_box_1'><A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"&categoryno=" + categoryno + "&recordPerPage="+recordPerPage+"'>이전</A></span>"); 
 } 

 for(int i=startPage; i<=endPage; i++){ 
   if (i > totalPage){ 
     break; 
   } 

   if (nowPage == i){ 
     str.append("<span class='span_box_2'>"+i+"</span>"); 
   }else{ 
     str.append("<span class='span_box_1'><A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+i+"&categoryno="+categoryno+"&recordPerPage="+recordPerPage+"'>"+i+"</A></span>");   
   } 
 } 
  
 _nowPage = (nowGrp * pagePerBlock)+1; // 10개 다음 페이지로 이동 
 if (nowGrp < totalGrp){ 
   str.append("<span class='span_box_1'><A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"&categoryno="+categoryno+"&recordPerPage="+recordPerPage+"'>다음</A></span>"); 
 } 
 str.append("</DIV>"); 
  
 return str.toString(); 
} 

/** 
* 목록용 파일을 설정하며 텍스트기반 페이징 지원, 1 페이지부터 시작 
* 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
*
* @param listFile 목록 파일명
* @param recordCount 검색 레코드수 
* @param nowPage     현재 페이지
* @param recordPerPage 페이지당 레코드 수
* @param col 검색 컬럼  
* @param word 검색어
* @param categoryno 카테고리 그룹
* @return 페이징 생성 문자열
*/ 
public String paging5(String listFile, int recordCount, int nowPage, int recordPerPage, String col, String word, int categoryno){ 
 int pagePerBlock = 10; // 블럭당 페이지 수, 블럭: 페이지들의 집합 
 int totalPage = (int)(Math.ceil((double)recordCount/recordPerPage)); // 전체 페이지  
 int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));   // 전체 그룹 
 int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // 현재 그룹 
 int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작  
 int endPage = (nowGrp * pagePerBlock);              // 특정 그룹의 페이지 목록 종료   
  
 StringBuffer str = new StringBuffer(); 
  
 str.append("<style type='text/css'>"); 
 str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
 str.append("  #paging A:link {text-decoration:none; font-size: 1em;}"); 
 str.append("  #paging A:hover{text-decoration:underline; background-color: #ffffff; font-size: 1em;}"); 
 str.append("  #paging A:visited {text-decoration:none; font-size: 1em;}"); 
 str.append("</style>"); 

 str.append("<DIV id='paging'>"); 
// str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 

 int _nowPage = (nowGrp-1) * pagePerBlock; // 10개 이전 페이지로 이동 
 if (nowGrp >= 2){ 
   str.append("[<A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"&categoryno=" + categoryno + "&recordPerPage="+recordPerPage+"'>이전</A>]"); 
 } 

 for(int i=startPage; i<=endPage; i++){ 
   if (i > totalPage){ 
     break; 
   } 

   if (nowPage == i){ 
     str.append(" <span style='font-size: 1.2em; font-weight: bold;'>"+i+"</span> "); 
   }else{ 
     str.append(" <A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+i+"&categoryno="+categoryno+"&recordPerPage="+recordPerPage+"'>"+i+"</A> ");   
   } 
 } 
  
 _nowPage = (nowGrp * pagePerBlock)+1; // 10개 다음 페이지로 이동 
 if (nowGrp < totalGrp){ 
   str.append(" [<A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"&categoryno="+categoryno+"&recordPerPage="+recordPerPage+"'>다음</A>] "); 
 } 
 str.append("</DIV>"); 
  
 return str.toString(); 
} 


/**
 * 카테고리별로 검색된 레코드 갯수
 * 
 * @return
 */
public int count(int categoryno, String col, String word) {
  int count = 0;
  
  count = libraryDAO.count(categoryno, col, word);
  
  return count;
}

/**
 * 카테고리별로 검색 및 페이징이 구현된 목록, SELECT된 목록중에
 * 페이징 기법을 이용하여 특정 레코드 부분(10개)만 가져옵니다. 
 *  1 페이지: (nowPage 1 - 1) *  10 → 0
 *  2 페이지: (nowPage 2 - 1) *  10 → 10 
 *  3 페이지: (nowPage 3 - 1) *  10 → 20 
 *  4 페이지: (nowPage 4 - 1) *  10 → 30 
 *  5 페이지: (nowPage 5 - 1) *  10 → 40  
 * @param categoryno 카테고리 번호
 * @param col 검색 컬럼
 * @param word 검색어
 * @param nowPage 현재페이지
 * @param offset skip할 레코드 갯수
 * @param recordPerPage 페이지당 출력할 레코드 갯수
 * @return 검색 목록
 */
public ArrayList<LibraryVO> list_category(
    int categoryno, 
    String col, 
    String word, 
    int nowPage,
    int recordPerPage) {
  ArrayList<LibraryVO> list = new ArrayList<LibraryVO>();
  
  col = Tool1.checkNull(col);
  word = Tool1.checkNull(word);
  
  /*
   *  1 페이지: (nowPage 1 - 1) *  10 → 0
   *  2 페이지: (nowPage 2 - 1) *  10 → 10 
   *  3 페이지: (nowPage 3 - 1) *  10 → 20 
   *  4 페이지: (nowPage 4 - 1) *  10 → 30 
   *  5 페이지: (nowPage 5 - 1) *  10 → 40 
   */
  int offset  = (nowPage - 1) * recordPerPage; // skip할 레코드 수
  
  list = libraryDAO.list_category(categoryno, col, word, offset, recordPerPage);
  
  return list;
}

/**
 * 카테고리별 레코드 갯수
 * 
 * @return
 */
public int countByCategory(int categoryno) {
  int count = libraryDAO.countByCategory(categoryno);
  
  return count;
}

/**
 * 이전글
 * @param libraryno
 * @param categoryno
 * @return libraryVO
 */

public LibraryVO pre_list(int libraryno, int categoryno) {
  LibraryVO libraryVO=this.libraryDAO.pre_list(libraryno, categoryno);
  
  return libraryVO;
  }

/**
 * 이전글
 * @param libraryno
 * @param categoryno
 * @return libraryVO
 */

public LibraryVO next_list(int libraryno, int categoryno) {
  LibraryVO libraryVO=this.libraryDAO.next_list(libraryno, categoryno);
  
  return libraryVO;
  }
}


