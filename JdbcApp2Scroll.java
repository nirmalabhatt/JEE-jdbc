package practice.gui;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JdbcApp2Scroll {
     public static void main(String [] args)
    {
      Connection conn=null;  
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
                
                Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY
                );
                ResultSet rs=st.executeQuery("Select name,salary from employees");
                while(rs.next())
               {
                     String name=rs.getString(1);
                     int sal=rs.getInt(2);
                     System.out.println(name+"\t"+sal);
              }
                System.out.println("Press the button to move data to bacword direction:");
                //system.in.read method is used to pause the screen:
                System.in.read();
                //previous method use to show data in backword:
                while(rs.previous()){
                    String name=rs.getString(1);
                     int sal=rs.getInt(2);
                     System.out.println(name+"\t"+sal);
                    
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
      catch(IOException ex){
          System.out.println("Cant read");
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
