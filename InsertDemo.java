
package practice.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class InsertDemo {
    public static void main(String[] args) {
        Connection conn=null;  
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
               conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-BMH1KMQ:1521/XE","Nirmala","niru");
                System.out.println("Connection opened successfully!");
               
                  Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("insert into employees values(13,'lilawati',500,126)");
                System.out.println("Row inserted Successfully");
                ResultSet rst= st.executeQuery("Select * from employees");
                while(rst.next()){
                    int id =rst.getInt(1);
                    String name= rst.getString(2);
                    int sal= rst.getInt(3);
                    int sm= rst.getInt(4);
                    System.out.println(id+"\t"+name+"\t"+sal+"\t"+sm);
                }
                
        }catch(Exception e)
        {
            System.out.println("Exception in program"+e);
        }
      
    }
    
}
