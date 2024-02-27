
package practice.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class JdbcInsertDate {
    public static void main(String [] args)
    {
      Connection conn=null;  
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
                
                 PreparedStatement ps=conn.prepareStatement("Insert into emp values(?,?,?)");
                Scanner kb= new Scanner(System.in);
                System.out.println("Enter Id:");
                int id = kb.nextInt();
                
                System.out.println("Enter Name");
                kb.nextLine();
                String name= kb.nextLine();
                System.out.println("Enter HireDate(dd/mm/yyyy)");
               String hdate= kb.next();
               SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/YYYY");
               java.util.Date d1=sdf.parse(hdate);
               long ms=d1.getTime();
               java.sql.Date d2= new java.sql.Date(ms);
               ps.setInt(1, id);
               ps.setString(2, name);
               ps.setDate(3, d2);
               int ans=ps.executeUpdate();
             if(ans>0)
                    System.out.println("Record inserted:"+ans);
             else
                    System.out.println("Cannot insert the record");
                
               
                
             
     }
      catch(ParseException ex)
        {
            System.out.println("Invalid date!");
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
