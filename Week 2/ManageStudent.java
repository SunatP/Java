
import java.util.Scanner;

public class ManageStudent {
    public static void main(String fdxzfdxf[]){
        StudentProfile std1 = new StudentProfile(6088130, "Sunat", 18, "123456asdsad");
        StudentProfile std2 = new StudentProfile(6088124, "Chawna", 2, "chsadsad55474");
        std1.getAllinfo();
        std2.getAllinfo();
        agediff(std1,std2);
    }
    public static void agediff (StudentProfile std1, StudentProfile std2)
    {
    	System.out.println(Math.abs(std1.getAge()-std2.getAge()));
    	
    }
    
}