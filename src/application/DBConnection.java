package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBConnection {
    public static Connection dbConn;
    
        public static Connection getConnection()
        {
            Connection conn = null;
            try {
                String user = "kxv1379"; 
                String pw = "rla016";
                String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                
                Class.forName("oracle.jdbc.driver.OracleDriver");        
                conn = DriverManager.getConnection(url, user, pw);
                
                System.out.println("Database접속 완료");
                
            } catch (ClassNotFoundException cnfe) {
                System.out.println("DB 접속오류 :"+cnfe.toString());
            } catch (SQLException sqle) {
                System.out.println("DB 접속오류 : "+sqle.toString());
            } catch (Exception e) {
                System.out.println("Unkonwn error");
                e.printStackTrace();
            }
            return conn;     
        }
}
