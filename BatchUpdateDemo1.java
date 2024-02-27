
package practice.gui;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class BatchUpdateDemo1 {
    public static void main(String [] args)
    {
      Connection conn=null;  
      boolean result=true;
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
               Statement st= conn.createStatement();
               conn.setAutoCommit(false);
               st.addBatch("insert into allbooks values(106,'learning SQL',900,'oracle')");
               st.addBatch("update emp set name ='sudha' where id=2");
               st.addBatch("delete from employees where id>=101");
               int[]arr=st.executeBatch();
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
