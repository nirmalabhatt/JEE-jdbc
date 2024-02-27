
package practice.gui;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CurrrentDate {
    public static void main(String[] args) {
        Date btoday= new Date();
        SimpleDateFormat sfm= new SimpleDateFormat( "dd-MMMM-YYYY");
        String fmd= sfm.format(btoday);
       
        
        System.out.println(fmd );
    }
}
