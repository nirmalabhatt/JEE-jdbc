
package practice.gui;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class BatchUpdateDemo2 {
    public static void main(String [] args)
    {
      Connection conn=null;  
      boolean result=true;
      Scanner kb= new Scanner(System.in);
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
              PreparedStatement ps=conn.prepareStatement("insert into allbooks values(?,?,?,?)");
               conn.setAutoCommit(false);
               String choice;
               do
               {
                   System.out.println("Enter BookID:");
                   int bid= kb.nextInt();
                   kb.nextLine();
                   System.out.println("enter BookName");
                   String bname= kb.nextLine();
                   System.out.println("Enter BOOKPRICE");
                   double amt=kb.nextDouble();
                   System.out.println("enter Subject");
                   kb.nextLine();
                   String subject=kb.nextLine();
                   ps.setInt( 1,bid);
                   ps.setString(2,bname);
                   ps.setDouble(3,amt);
                   ps.setString(4,subject);
                   ps.addBatch();
                   System.out.println("Do you Want to Continue YES/NO");
                   choice=kb.next();
                }while(choice.equalsIgnoreCase("YES"));
               int[]arr=ps.executeBatch();
               int qno=1;
               for(int x:arr)
               {
                  switch(x)
                  {
                        case Statement.SUCCESS_NO_INFO:
                          System.out.println("Query number " +qno+ " Effected unknown number of rows");
                          break;
                        case Statement.EXECUTE_FAILED:
                          System.out.println("Query number " +qno+ " Did not run properly");
                          break;
                        default:
                            System.out.println("Query Number " +qno+ " effected "+x+" Rows");
                  }
                  qno++;
               }
            }
    catch(ClassNotFoundException cnfe)
    {
        System.out.println("Cannot load the driver class");
        
    }
      catch(BatchUpdateException bue)
      {
          int[]arr=bue.getUpdateCounts();
          result=false;
          System.out.println( " in Query Number "+(arr.length+1)+ "  Batch generated  exception occuared "+bue.getMessage());
          
      }
    catch(SQLException ex)
   {
     System.out.println("Error in DB");
     ex.printStackTrace();       
   }
   finally
   {
       if(conn!=null)
       {
           try
           {
               if(result==true)
               {
                   conn.commit();
                   System.out.println("transaction committed!!");
               }
               else
               {
                   conn.rollback();
                   System.out.println("Trsnsaction Rollbacked");
               }
               conn.close();
               System.out.println("Connection Closed");
               
           }
       
              catch(SQLException ex)
              {
                 System.out.println("Problem in closing the connection");
             }
       }
   }
  }
    
}
