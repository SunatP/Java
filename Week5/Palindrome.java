/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sunat Praphanwong 
 */
import java.util.Scanner;
public class Palindrome {

    /**
     * @param args the command line arguments
     */
   /* public static void main(String[] args) {
        Scanner keyboard;
      String input = "No lemon, no melon";
      keyboard = new Scanner(System.in);
        String wort = "123321";
        char[] warray2 = input.toCharArray(); 
        char[] warray = wort.toCharArray(); 
        System.out.println(input);
        System.out.println("This word is a palindrome :"+" "+istPalindrom(warray2));  
        System.out.println(wort);
         System.out.println("This word is a palindrome :"+" "+istPalindrom(warray));    
    }
    public static boolean istPalindrom(char[] word){
    int i1 = 0;
    int i2 = word.length - 1;
    while (i2 > i1) {
        if (word[i1] == word[i2]) {
            return false;
        }
        ++i1;
        --i2;
    }
    return true;
}
    
}*/
     
   public static void main(String args[])
  {
    String inputString;
    Scanner in = new Scanner(System.in);
 
    System.out.println("Input a string");
    inputString = in.nextLine();
 
    int length  = inputString.length();
    int i, begin, end, middle;
 
    begin  = 0;
    end    = length - 1;
    middle = (begin + end)/2;
 
    for (i = begin; i <= middle; i++) {
      if (inputString.charAt(begin) == inputString.charAt(end)) {
        begin++;
        end--;
      }
      else {
        break;
      }
    }
    if (i == middle + 1) {
      System.out.println("Enter a word or phrase to check if it is a palindrome: " + inputString);
      System.out.println("The input word: " + inputString + " is a palindrome");
    }
    else {
      System.out.println("Enter a word or phrase to check if it is a palindrome: " + inputString);
      System.out.println("The input word: " + inputString + " is not a palindrome");
    } 	
  }
}