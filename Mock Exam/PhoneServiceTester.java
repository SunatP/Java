import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhoneServiceTester {
	
	public static void main(String[] args){
		PhonePackage a = new PhonePackage('A', 599.00, 450, 1.50);
		PhonePackage b = new PhonePackage('B', 799.00, 900, 1.20);
		PhonePackage c = new PhonePackage('C', 999.00, Integer.MAX_VALUE, 0.00);
		
		ArrayList<PhonePackage> packages = new ArrayList<PhonePackage>();
		packages.add(a);
		packages.add(b);
		packages.add(c);
		
		System.out.println("----- Task 1 -----");
                System.out.println("Please enter # of minutes used:");
                Scanner min = new Scanner(System.in);
                int minute = min.nextInt();
                System.out.println("Please enter the phone package:");
                char num = min.next().charAt(0);
                double amount = -1;
                /*for(PhonePackage p: packages){
			if(p.getName() == num){
				amount = p.getMonthlyBill(minute);
				System.out.println("Monthly Due is " + amount);
			}
		}*/
                switch(num)
                {
                    case 'A':
                       amount = a.getMonthlyBill(minute);
                       System.out.println("Monthly Due is " + amount);
                       break;
                    case 'B':
                        amount =b.getMonthlyBill(minute);
                       System.out.println("Monthly Due is " + amount );
                       break;
                    case 'C' :
                        amount = c.getMonthlyBill(minute);
                       System.out.println("Monthly Due is " + amount );
                       break;
                        default: System.out.println("Cannot compute"); break;
                }
                  /* if(num == 'A')
                   {
                       a.getMonthlyBill(minute);
                       System.out.println("Monthly Due is " + a.getMonthlyBill(minute));
                      
                   }
                   else if(num == 'B')
                   {
                       b.getMonthlyBill(minute);
                       System.out.println("Monthly Due is " + b.getMonthlyBill(minute));
                   }
                   else if(num == 'C')
                   {
                       c.getMonthlyBill(minute);
                       System.out.println("Monthly Due is " + c.getMonthlyBill(minute));
                   }
                   else System.out.println("error");*/
		// ---- Task 1 ----
		// Write a program to take input from a user
		// The first input is the number of minutes used
		// The second input is the phone package that the user chooses
		// Hint. Use charAt(0) to get the first character from input string
		
		// Calculate and display the amount of money the user have to pay 
		// according to the package that he/she chooses.
		// Your code must be flexible and be able to work with different list of package
		
		
		System.out.println("----- Task 2 -----");
                        PhonePackage test = getBestPackage(packages, minute);
               if(test.getName() != num){
			System.out.println("The best phone package for you is " + test);
			
			System.out.println("You would save " + (amount - test.getMonthlyBill(minute)));
		}
             
                // ---- Task 2 ----
		// Is there any savings, if the user chooses different phone package?
		// Find the best package for the user, and display the amount of money he/she would save.
		// If there would be no savings, no message should be printed.
		
		
	}
	
	public static PhonePackage getBestPackage(ArrayList<PhonePackage> packageList, int minutes){
          
           double bill = packageList.get(0).getMonthlyBill(minutes);
		PhonePackage bestPackage = packageList.get(0);
		for(int i = 1; i < packageList.size(); i++){
			if(packageList.get(i).getMonthlyBill(minutes) < bill){
				bill = packageList.get(i).getMonthlyBill(minutes);
				bestPackage = packageList.get(i);
			}
		}
		return bestPackage;
	}
}
