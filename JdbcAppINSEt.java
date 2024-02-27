
package practice.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class JdbcAppINSEt {
    public static void main(String [] args)
    {
      Connection conn=null;  
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
               conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
               
                 PreparedStatement ps=conn.prepareStatement("Insert into allbooks values(?,?,?,?)");
 
                
                 Scanner kb=new Scanner(System.in);
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

             ps.setInt( 1,bid);
             ps.setString(2,bname);
             ps.setDouble(3,amt);
             ps.setString(4,subject);

            int ans=ps.executeUpdate();
             if(ans>0)
                    System.out.println("Rec inserted:"+ans);
             else
                    System.out.println("Cannot insert the record");
                
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
