package nation.web.tool;
 
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
 
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;
 
public class DBCPServlet extends HttpServlet {
   String registerPool="";
    // ServletConfig config: web.xml�� ���ٰ��� ��ü
    @Override
    public void init(ServletConfig config) throws ServletException {
        String registerPool = ""; // Pool �̸�
        String jdbcDriver = "";   // Driver Class
        String jdbcURL = "";      // DBMS server URL
        String user = "";           // ���� ���̵�
        String password = "";     // ���� password
        
        int maxActive = 0;        // �ִ� ����� 
        int maxIdle = 0;           // ������� ����� 
        
        // web.xml���� ���� �����ɴϴ�.
        registerPool = config.getInitParameter("registerPool");
        
        DB.registerPool = registerPool;
        
        jdbcDriver = config.getInitParameter("jdbcDriver"); 
        jdbcURL = config.getInitParameter("jdbcURL");
        
        
        try {
          user =new AES256Util().aesDecode(config.getInitParameter("user"));
          password = new AES256Util().aesDecode(config.getInitParameter("password"));
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
        
        maxActive = Integer.parseInt(config.getInitParameter("maxActive"));
        maxIdle = Integer.parseInt(config.getInitParameter("maxIdle"));
        
        try {
            // JDBC ����̹� �ε�, oracle.jdbc.driver.OracleDriver
            Class.forName(jdbcDriver);
            
            // Connection Pool ����
            GenericObjectPool connectionPool = new GenericObjectPool(null);
            connectionPool.setMaxActive(maxActive); // �ִ� ���� ����� 
            connectionPool.setMaxIdle(maxIdle);     // ���� ��� �����
            
            // ���� DB���� Ŀ�ؼ��� �������ִ� ���丮 ����
            ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                jdbcURL,    // JDBC URL
                user,       // �����
                password);
            
            // Connection Pool�� PoolableConnection ��ü�� ������ �� �����
            // PoolableConnectionFactory ����
            PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
                connectionFactory,
                connectionPool,
                null,  // statement pool
                null,  // Ŀ�ؼ� �׽�Ʈ ����: Ŀ�ؼ��� ��ȿ���� �׽�Ʈ�� �� ���Ǵ� ����.
                false, // read only ����
                true); // auto commit ����
 
            // Pooling�� ���� JDBC ����̹� ���� �� ���
            PoolingDriver driver = new PoolingDriver();
            
            // JDBC ����̹��� Ŀ�ؼ� Ǯ ���
            // ora10g2�� Connection ���� �䱸�� ����ϰ� �˴ϴ�.
            driver.registerPool(registerPool, connectionPool);
            
            System.out.println("--------------------------------------------------------------------");
            System.out.println("blog_v6ja MariaDB Cafe24 DBCP Connection Pool start.");
            System.out.println("--------------------------------------------------------------------");
            
        } catch(Exception ex) {
            //throw new ServletException(ex);
            ex.printStackTrace();
        }
 
    }
}
   