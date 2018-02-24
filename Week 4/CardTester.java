
package card;

import java.util.Scanner;
import java.util.*;

/**
 *
 * @author Sunat Praphanwong 6088130
 */

public class CardTester {
   public static final String Quit = "q";
   public static void main(String[] args)
   {
       
      Scanner keyboard;
      String input = "";
      keyboard = new Scanner(System.in);
    

      System.out.println("Please Input (Enter 'q' to End): ");
      for(int i = 1 ; i< 50 ; i++){
          input = keyboard.nextLine();
          if(input.length() == 2){
              if(input.equals("q"))  break;
          }else
          {
              System.out.println("Thank you! Good Bye"); break;
              //System.out.println("Invalid Notation!");
             
                 

      }
      
      
      Card card = new Card(input);
      System.out.println(card.getDescription());
      System.out.println("");
      System.out.println("Please Input (Enter 'q' to End): ");
      }
   }
}

