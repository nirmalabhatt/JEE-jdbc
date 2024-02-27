
package practice.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
class FolderNotCreatedException extends Exception
{
    public FolderNotCreatedException(String msg){
        super(msg);
    }
}

public class JdbcImgRetreve {
    public static void main(String [] args)
    {
      Connection conn=null; 
      
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
               conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
               
              Statement st= conn.createStatement();
              ResultSet rs= st.executeQuery("Select * from movie");
              File mydir=new File("D:/Amovies");
              if(mydir.exists()==false)
              {
                    boolean result=mydir.mkdir();
                    if(result==false)
                {
                    throw new FolderNotCreatedException("Could Not Create the Directory"); 
                }
                    System.out.println("Folder Created");
              }
              System.out.println("Folder Already exisest");
              while(rs.next())
              {
                  String movname=rs.getString(1);
                  Blob blob=rs.getBlob(2);
                  byte[]arr=blob.getBytes(1, (int)blob.length());
                  FileOutputStream fout= new FileOutputStream(mydir.getAbsolutePath()+"//"+movname+".jpg");
                 fout.write(arr);
                 fout.close();
                  
              }
                
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
      catch(IOException io){
          System.out.println("Exception");
      }
      catch(FolderNotCreatedException fno )
      {
          System.out.println("Exception"+fno.getMessage());  
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
