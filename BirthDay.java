package practice.gui;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class BirthDay {
    public static void main(String[] args) {
        Scanner kb=new Scanner(System.in);
        System.out.println("Enter your DOB(dd/MM/yyyy)");
        String datestr=kb.next();
        
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Date bday=sdf.parse(datestr);
            SimpleDateFormat sdf2=new SimpleDateFormat("EEEE");
            String day=sdf2.format(bday);
            System.out.println("You were born on :"+day);
        }
        catch(ParseException ex)
        {
            System.out.println("Invalid date!");
        }
    }
    
}
