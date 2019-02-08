package nation.web.library4;

import java.util.ArrayList;

import nation.web.library4.LibraryDAO;
import nation.web.library4.LibraryVO;

import nation.web.tool.Tool1;

/**
 * 
 * ȸ�� ���� DAO class
 * <pre>
 * ������Ʈ��     : (��)�ֵ���ũ IT �������� JAVA CBD Project 1��
 * PMO, PM      : ���� �Ʒñ��� 
 * ��Ű����        : nation.web.library4
 * ���ϸ�           : LibraryProc.java 2018. 12. 12.
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
public class LibraryProc {

  private LibraryDAO libraryDAO;
  
  public LibraryProc() {
    libraryDAO = new LibraryDAO();
  }
  
  /**
   * ���
   * @param pds4VO
   * @return ����� ����
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
 * ī�װ��� ���ڵ� ���
 * 
 * @return
 */
public ArrayList<LibraryVO> list_category(int categoryno) {
  ArrayList<LibraryVO> list =this.libraryDAO.list_category(categoryno);
  
  return list;
  }

/**
 * ��ȸ�� ����
 * @param pdsno
 */
public void increaseCnt(int libraryno){
  this.libraryDAO.increaseCnt(libraryno);
  }

/**
 * ��ȸ
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
 * ���� ����
 * 
 * @param libraryno
 *          ������ ���ڵ� ��ȣ
 * @return ó�� ����
 */
public int delete_file1(int libraryno) {
  LibraryVO libraryVO = new LibraryVO(); // ���ϸ��� �����ϱ����� ��ü ����
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
 * Map �ű� ���
 * @param pdsno
 * @return
 */
public int new_create_map_info(LibraryVO libraryVO) {
  int count = this.libraryDAO.update_map_info(libraryVO);
  return count;
}
/**
 * Map �ű� ����
 * @param pdsno
 * @return
 */
public int delete_map_info(int libraryno) {
  LibraryVO libraryVO = new LibraryVO(); // ���ϸ��� �����ϱ����� ��ü ����
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
 * youtube �ű� ���
 * @param pdsno
 * @return
 */
public int new_create_youtube(LibraryVO libraryVO) {
  int count = this.libraryDAO.update_youtube(libraryVO);
  return count;
}
/**
 * youtube �ű� ����
 * @param pdsno
 * @return
 */
public int delete_youtube(int libraryno) {
  LibraryVO libraryVO = new LibraryVO(); // ���ϸ��� �����ϱ����� ��ü ����
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
 * �ű� MP4 ���
 * @param pdsno
 * @return
 */
public int new_create_video(LibraryVO libraryVO) {
  int count = libraryDAO.update_video(libraryVO);
  return count;
}

/**
 * MP4 ����
 * @param pdsno
 * @return
 */
public int delete_video(int libraryno) {
  LibraryVO libraryVO = new LibraryVO(); // ������ �����ϱ����� ��ü ����
  libraryVO.setVideo(""); // ���� ���� script�� �����մϴ�.
  libraryVO.setLibraryno(libraryno);
  
  int count = libraryDAO.update_video(libraryVO);
  
  return count;
}

/**
 * MP4 ����
 * @param pdsno
 * @return
 */
public int replace_video(LibraryVO libraryVO) {
  int count = libraryDAO.update_video(libraryVO);
  return count;
}

/**
 * ��ü ī�װ� �˻�
 * @param col �˻� �÷�
 * @param word �˻���
 * @return �˻� ���
 */
public ArrayList<LibraryVO> list(String col, String word) {
  ArrayList<LibraryVO> list = libraryDAO.list(col, word);
  return list;
}

/**
 * ī�װ��� �˻�
 * @param categoryno ī�װ� ��ȣ
 * @param col �˻� �÷�
 * @param word �˻���
 * @return �˻� ���
 */
public ArrayList<LibraryVO> list_category(int categoryno, String col, String word) {
  ArrayList<LibraryVO> list = libraryDAO.list_category(categoryno, col, word);
  return list;
}

/* ��Ͽ� ������ �����ϸ� SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
* ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
*
* @param listFile ��� ���ϸ�
* @param recordCount �˻� ���ڵ�� 
* @param nowPage     ���� ������
* @param recordPerPage �������� ���ڵ� ��
* @param col �˻� �÷�  
* @param word �˻���
* @param categoryno ī�װ� �׷�
* @return ����¡ ���� ���ڿ�
*/ 
public String paging4(String listFile, int recordCount, int nowPage, int recordPerPage, String col, String word, int categoryno){ 
 int pagePerBlock = 10; // ���� ������ �� 
 
 // ���� 450���� ���ڵ� �����Ѵٰ� ����
 // totalPage = 450 / 10 -> 45���� ������
 int totalPage = (int)(Math.ceil((double)recordCount/recordPerPage)); // ��ü ������
 
 // 45 / 10 -> 5���� �׷�
 int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ��ü �׷� 
 
 // 5 / 10 -> 0.5 -> 1
 // 15 / 10 -> 1.5 -> 2
 // 25 / 10 -> 2.5 -> 3
 int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ���� �׷� 
 
 // 1 group: ((1 - 1) * 10) + 1 -> start Page: 1
 //              (1 * 10)            -> end Page: 10
 
 // 2 group: ((2 - 1) * 10) + 1 -> start Page: 11
 //              (2 * 10)            -> end Page: 20

 // 3 group: ((3 - 1) * 10) + 1 -> start Page: 21
 //              (2 * 10)            -> end Page: 30
 
 int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // Ư�� �׷��� ������ ��� ����  
 int endPage = (nowGrp * pagePerBlock);             // Ư�� �׷��� ������ ��� ����   
  
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
 str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
 str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
 str.append("  }"); 
 str.append("  .span_box_2{"); 
 str.append("    text-align: center;");    
 str.append("    background-color: #668db4;"); 
 str.append("    color: #FFFFFF;"); 
 str.append("    font-size: 1em;"); 
 str.append("    border: 1px;"); 
 str.append("    border-style: solid;"); 
 str.append("    border-color: #cccccc;"); 
 str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
 str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
 str.append("  }"); 
 str.append("</style>"); 
 str.append("<DIV id='paging'>"); 
// str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 

 int _nowPage = (nowGrp-1) * pagePerBlock; // 10�� ���� �������� �̵� 
 if (nowGrp >= 2){ 
   str.append("<span class='span_box_1'><A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"&categoryno=" + categoryno + "&recordPerPage="+recordPerPage+"'>����</A></span>"); 
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
  
 _nowPage = (nowGrp * pagePerBlock)+1; // 10�� ���� �������� �̵� 
 if (nowGrp < totalGrp){ 
   str.append("<span class='span_box_1'><A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"&categoryno="+categoryno+"&recordPerPage="+recordPerPage+"'>����</A></span>"); 
 } 
 str.append("</DIV>"); 
  
 return str.toString(); 
} 

/** 
* ��Ͽ� ������ �����ϸ� �ؽ�Ʈ��� ����¡ ����, 1 ���������� ���� 
* ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
*
* @param listFile ��� ���ϸ�
* @param recordCount �˻� ���ڵ�� 
* @param nowPage     ���� ������
* @param recordPerPage �������� ���ڵ� ��
* @param col �˻� �÷�  
* @param word �˻���
* @param categoryno ī�װ� �׷�
* @return ����¡ ���� ���ڿ�
*/ 
public String paging5(String listFile, int recordCount, int nowPage, int recordPerPage, String col, String word, int categoryno){ 
 int pagePerBlock = 10; // ���� ������ ��, ��: ���������� ���� 
 int totalPage = (int)(Math.ceil((double)recordCount/recordPerPage)); // ��ü ������  
 int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));   // ��ü �׷� 
 int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ���� �׷� 
 int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // Ư�� �׷��� ������ ��� ����  
 int endPage = (nowGrp * pagePerBlock);              // Ư�� �׷��� ������ ��� ����   
  
 StringBuffer str = new StringBuffer(); 
  
 str.append("<style type='text/css'>"); 
 str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
 str.append("  #paging A:link {text-decoration:none; font-size: 1em;}"); 
 str.append("  #paging A:hover{text-decoration:underline; background-color: #ffffff; font-size: 1em;}"); 
 str.append("  #paging A:visited {text-decoration:none; font-size: 1em;}"); 
 str.append("</style>"); 

 str.append("<DIV id='paging'>"); 
// str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 

 int _nowPage = (nowGrp-1) * pagePerBlock; // 10�� ���� �������� �̵� 
 if (nowGrp >= 2){ 
   str.append("[<A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"&categoryno=" + categoryno + "&recordPerPage="+recordPerPage+"'>����</A>]"); 
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
  
 _nowPage = (nowGrp * pagePerBlock)+1; // 10�� ���� �������� �̵� 
 if (nowGrp < totalGrp){ 
   str.append(" [<A href='./"+listFile+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"&categoryno="+categoryno+"&recordPerPage="+recordPerPage+"'>����</A>] "); 
 } 
 str.append("</DIV>"); 
  
 return str.toString(); 
} 


/**
 * ī�װ����� �˻��� ���ڵ� ����
 * 
 * @return
 */
public int count(int categoryno, String col, String word) {
  int count = 0;
  
  count = libraryDAO.count(categoryno, col, word);
  
  return count;
}

/**
 * ī�װ����� �˻� �� ����¡�� ������ ���, SELECT�� ����߿�
 * ����¡ ����� �̿��Ͽ� Ư�� ���ڵ� �κ�(10��)�� �����ɴϴ�. 
 *  1 ������: (nowPage 1 - 1) *  10 �� 0
 *  2 ������: (nowPage 2 - 1) *  10 �� 10 
 *  3 ������: (nowPage 3 - 1) *  10 �� 20 
 *  4 ������: (nowPage 4 - 1) *  10 �� 30 
 *  5 ������: (nowPage 5 - 1) *  10 �� 40  
 * @param categoryno ī�װ� ��ȣ
 * @param col �˻� �÷�
 * @param word �˻���
 * @param nowPage ����������
 * @param offset skip�� ���ڵ� ����
 * @param recordPerPage �������� ����� ���ڵ� ����
 * @return �˻� ���
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
   *  1 ������: (nowPage 1 - 1) *  10 �� 0
   *  2 ������: (nowPage 2 - 1) *  10 �� 10 
   *  3 ������: (nowPage 3 - 1) *  10 �� 20 
   *  4 ������: (nowPage 4 - 1) *  10 �� 30 
   *  5 ������: (nowPage 5 - 1) *  10 �� 40 
   */
  int offset  = (nowPage - 1) * recordPerPage; // skip�� ���ڵ� ��
  
  list = libraryDAO.list_category(categoryno, col, word, offset, recordPerPage);
  
  return list;
}

/**
 * ī�װ��� ���ڵ� ����
 * 
 * @return
 */
public int countByCategory(int categoryno) {
  int count = libraryDAO.countByCategory(categoryno);
  
  return count;
}

/**
 * ������
 * @param libraryno
 * @param categoryno
 * @return libraryVO
 */

public LibraryVO pre_list(int libraryno, int categoryno) {
  LibraryVO libraryVO=this.libraryDAO.pre_list(libraryno, categoryno);
  
  return libraryVO;
  }

/**
 * ������
 * @param libraryno
 * @param categoryno
 * @return libraryVO
 */

public LibraryVO next_list(int libraryno, int categoryno) {
  LibraryVO libraryVO=this.libraryDAO.next_list(libraryno, categoryno);
  
  return libraryVO;
  }
}


