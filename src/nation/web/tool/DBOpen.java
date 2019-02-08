package nation.web.tool;
 
import java.sql.Connection;
import java.sql.DriverManager;
 
public class DBOpen {
  
  // MySQL Connection Pool ��� DBMS ���� 
  public Connection getConnection(){
    Connection con = null;          // DBMS ���� ��ü
 
    try{
      // blog_v2jq �� web.xml�� registerPool ������ ���̾�� �� 
      // <param-name>registerPool</param-name> <!-- ������ -->
      // <param-value>blog_v4jq</param-value>    <!-- ������ -->
      //String poolName = "jdbc:apache:commons:dbcp:laos";
      String poolName = "jdbc:apache:commons:dbcp:"+DB.registerPool;
      con = DriverManager.getConnection(poolName);
      System.out.println("Connection Mode 2: " + con.hashCode());
 
    }catch(Exception e){
      e.printStackTrace();
    }
    
    return con; // Void methods cannot return a value
  }
  
  public Connection getConnectionNormal(){
    Connection con = null; // �޸𸮰� �Ҵ���� ����.
    
    try{
      String jdbc = "org.mariadb.jdbc.Driver"; // MySQL ���� Drvier 
      String url = "jdbc:maria://localhost:5306/laos"; 
      String user = "sjaqj23"; 
      String pass = "jmy950729";
      
      Class.forName(jdbc); // memory�� ����̹� Ŭ������ �ε���.
      con = DriverManager.getConnection(url, user, pass); // MySQL ����
      System.out.println("Connection Mode 1: " + con.hashCode());
      
    }catch(Exception e){
      e.printStackTrace();
    }
    
    return con;
  }
  
  public static void main(String[] args){
    DBOpen dpopen = new DBOpen();
    System.out.println(dpopen.getConnectionNormal());
  }
}
 
 