
package practice.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class JdbcAppEx3 {
    public static void main(String[] args) {
        Connection conn=null;  
        try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                 conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
               
                 Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                 ResultSet rs=st.executeQuery("Select subject,bookprice, bookname from allbooks");
                 
                 ArrayList<Ex3Book> bookList=new ArrayList<>();
                 while(rs.next())
                 {
                     String subject=rs.getString(1);
                     if(subject.equalsIgnoreCase("JEE"))
                     {
                         double oldamt=rs.getDouble(2);
                         double newamt=oldamt+oldamt*0.1;
                         rs.updateDouble(2, newamt);
                         rs.updateRow();
                         String bname= rs.getString(3);
                        Ex3Book obj= new Ex3Book(bname,oldamt,newamt); 
                        bookList.add(obj);
                         
                         
                     }
                 }
                 if(bookList.size()==0)
                     System.out.println("No books updated!");
                 else
                 {
                    System.out.println(bookList.size()+" books updated!"); 
                     System.out.println("BOOKNAME\t\t OLDPRICE\t\t NEWPRICE");
                    for(Ex3Book b:bookList)
                         System.out.println(b);
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
