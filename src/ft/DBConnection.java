package ft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

   String driver = "org.mariadb.jdbc.Driver";
   Connection conn;

   public void DBconn() {
      String url = "jdbc:mariadb://localhost:3306:ft";
      String user = "root";
      String password = "gkstjstn02";
      try {
         Class.forName(driver);
         System.out.println("����̹� ���� ����");
         conn = DriverManager.getConnection(url, user, password);

         if (conn != null) {
            System.out.println("DB ���� ����");
         } else {
            System.out.println("DB ���� ����");
         }
      } catch (Exception e) {
         System.out.println("DB ���� ����");
      }
   }

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      DBConnection dbconn = new DBConnection();
      dbconn.DBconn();
   }
}