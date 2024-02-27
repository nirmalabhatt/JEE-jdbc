
package practice.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class JdbcImgInsert {
     public static void main(String [] args)
    {
      Connection conn=null; 
      FileInputStream fin= null;
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
               conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
               
               PreparedStatement ps=conn.prepareStatement("Insert into movie values(?,?)");
 
                
                 Scanner kb=new Scanner(System.in);
                 System.out.println("Enter Image Path:");
                 String imgPath=kb.nextLine();
                 File fobj= new File(imgPath);
                 String fname= fobj.getName();
                 int dotPos= fname.lastIndexOf(".");
                 String moviname= fname.substring(0,dotPos);
                 ps.setString(1, moviname);
                 fin= new  FileInputStream(fobj);
                ps.setBinaryStream(2, fin, (int)fobj.length());
                 
                 

            int result=ps.executeUpdate();
             if(result>0)
                    System.out.println("Image Inserted Successfully");
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
      catch(FileNotFoundException fno)
      {
          System.out.println("File not found"); 
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
                      if(fin!=null){
                          fin.close();
                          System.out.println("File closed !!");
                      }
              }
              catch(SQLException ex)
              {
                 System.out.println("Problem in closing the connection");
              }
        catch(IOException iex)
              {
                 System.out.println("Problem in closing the File");
              }
   }
  }
    
    
}
