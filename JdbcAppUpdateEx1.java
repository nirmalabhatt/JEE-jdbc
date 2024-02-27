
package practice.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcAppUpdateEx1 {
     public static void main(String[] args) {
        Connection conn=null;  
        try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                 conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
               
                 Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs=st.executeQuery("Select subject,bookprice from allbooks");
                 int count=0;
                 while(rs.next())
                 {
                     String subject=rs.getString(1);
                     if(subject.equalsIgnoreCase("JEE"))
                     {
                         double amt=rs.getDouble(2);
                         amt=amt+amt*0.1;
                         rs.updateDouble(2, amt);
                         rs.updateRow();
                         ++count;
                         
                     }
                 }
                 if(count==0)
                     System.out.println("No books updated!");
                 else
                     System.out.println(count+" books updated!");
                
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
