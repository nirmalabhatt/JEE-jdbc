
package practice.gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcScroolRES {
     public static void main(String [] args)
    {
      Connection conn=null;  
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
                
                Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs=st.executeQuery("Select bookid,bookname,bookprice,subject from allbooks");
                rs.moveToInsertRow();
                rs.updateInt(1,110);
                rs.updateString(2,"Network");
                rs.updateInt(3, 700);
                rs.updateString(4,"Pro");
                rs.insertRow();
                rs.moveToCurrentRow();
                
                
               // st.executeUpdate("insert into allbooks values(110,'Network Programming',950,'Programming')");
               /*while(rs.next())
               {
                   String bname=rs.getString(1);
                   double bprice=1000+rs.getDouble(2);
                   rs.updateDouble(2, bprice);
                   rs.updateRow();
                   
                   
               }
*/
               
     }
    catch(ClassNotFoundException cnfe)
    {
        System.out.println("Cannot load the driver class");
        
    }
    catch(SQLException ex)
   {
     System.out.println("Error in DB");
     ex.printStackTrace();       
   }
     
   finally
   {
	try
               {
                      if(conn!=null)
                   {
                      conn.close();
                      System.out.println("Connection closed successfully!");
                 }
              }
              catch(SQLException ex)
              {
                 System.out.println("Problem in closing the connection");
              }
   }
  }
    
    
}
