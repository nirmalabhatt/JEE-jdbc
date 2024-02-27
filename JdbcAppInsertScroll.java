
package practice.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class JdbcAppInsertScroll {
    public static void main(String[] args) {
        Connection conn=null;  
        try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                 conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
               
                 Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs=st.executeQuery("Select bookid, bookname,bookprice,subject from allbooks");
                 Scanner kb= new Scanner(System.in);
                 System.out.println("Enter bookid:");
                 int bid=kb.nextInt();
                 
                 System.out.println("Enter book name:");
                 kb.nextLine();
                 String bname=kb.nextLine();

               System.out.println("Enter bookprice:");
               double amt=kb.nextDouble();
	
              System.out.println("Enter subject:");
              kb.nextLine();
              String subject=kb.nextLine();
              rs.moveToInsertRow();

             rs.updateInt(1, bid);
             rs.updateString(2, bname);
             rs.updateDouble(3, amt);
             rs.updateString(4, subject);
             rs.insertRow();
              rs.moveToCurrentRow();
             System.out.println("Row INSERTED Successfully:10");
                 
                 
                
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
