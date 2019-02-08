package nation.web.library4;
/**
 * 
 * 회원 관련 DAO class
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM      : 지도 훈련교사 
 * 패키지명        : nation.web.library4
 * 파일명           : LibraryVO.java 2018. 12. 12.
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
public class LibraryVO {
    private int libraryno;
    private int categoryno;
    private String writer;
    private String spot;
    private String content;
    private String pw;
    private int hits;
    private String rdate;
    private String site;
    private String file_name;
    private String fstor1;
    private long size;
    private String map_info;
    private String youtube;
    private String video;
    private String visible;
    private String thumb;
    private int cnts;
    
    
    public LibraryVO() {
      
    }
    
    

    public LibraryVO(int libraryno, int categoryno, String writer, String spot, String content, String pw, int hits,
        String rdate, String site, String file_name, String fstor1, long size, String map_info, String youtube,
        String video, String visible, String thumb,int cnts) {
      this.libraryno = libraryno;
      this.categoryno = categoryno;
      this.writer = writer;
      this.spot = spot;
      this.content = content;
      this.pw = pw;
      this.hits = hits;
      this.rdate = rdate;
      this.site = site;
      this.file_name = file_name;
      this.fstor1 = fstor1;
      this.size = size;
      this.map_info = map_info;
      this.youtube = youtube;
      this.video = video;
      this.visible = visible;
      this.thumb = thumb;
      this.cnts = cnts;
    }

    

    public int getCnts() {
      return cnts;
    }



    public void setCnts(int cnts) {
      this.cnts = cnts;
    }



    public String getThumb() {
      return thumb;
    }



    public void setThumb(String thumb) {
      this.thumb = thumb;
    }



    public int getLibraryno() {
      return libraryno;
    }


    public void setLibraryno(int libraryno) {
      this.libraryno = libraryno;
    }


    public int getCategoryno() {
      return categoryno;
    }


    public void setCategoryno(int categoryno) {
      this.categoryno = categoryno;
    }


    public String getWriter() {
      return writer;
    }


    public void setWriter(String writer) {
      this.writer = writer;
    }


    public String getSpot() {
      return spot;
    }


    public void setSpot(String spot) {
      this.spot = spot;
    }


    public String getContent() {
      return content;
    }


    public void setContent(String content) {
      this.content = content;
    }


    public String getPw() {
      return pw;
    }


    public void setPw(String pw) {
      this.pw = pw;
    }


    public int getHits() {
      return hits;
    }


    public void setHits(int hits) {
      this.hits = hits;
    }


    public String getRdate() {
      return rdate;
    }


    public void setRdate(String rdate) {
      this.rdate = rdate;
    }


    public String getSite() {
      return site;
    }


    public void setSite(String site) {
      this.site = site;
    }


    public String getFile_name() {
      return file_name;
    }


    public void setFile_name(String file_name) {
      this.file_name = file_name;
    }


    public String getFstor1() {
      return fstor1;
    }


    public void setFstor1(String fstor1) {
      this.fstor1 = fstor1;
    }


    public long getSize() {
      return size;
    }


    public void setSize(long size) {
      this.size = size;
    }


    public String getMap_info() {
      return map_info;
    }


    public void setMap_info(String map_info) {
      this.map_info = map_info;
    }


    public String getYoutube() {
      return youtube;
    }


    public void setYoutube(String youtube) {
      this.youtube = youtube;
    }


    public String getVideo() {
      return video;
    }


    public void setVideo(String video) {
      this.video = video;
    }


    public String getVisible() {
      return visible;
    }


    public void setVisible(String visible) {
      this.visible = visible;
    }
    
    
    
    
    
    
}
