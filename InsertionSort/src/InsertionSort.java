
import java.util.HashSet;
/**
 *
 * @author Sunat Praphanwong
 * @Environment NetBeans IDE 8.2 with JRE 1.8.0
 */
public class InsertionSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code here
        // Initial Array with insertionSort
        int arr[] = { 12, 11, 13,5,6,6,7,13,21,12,12,14,15,16,18,20,0,12 }; 
        System.out.println("Before remove duplicate value");
        printArray(arr); 
        
        InsertionSort insertsort = new InsertionSort(); 
        System.out.println("After remove duplicate value");
        insertsort.sort(arr);
  
    }
     void sort(int arr[]) 
    { 
        HashSet<Integer> set  = new HashSet<>(); 
  
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 

        // Adding elements to HashSet 
        for (int i = 0; i < arr.length; i++) 
            set.add(arr[i]); 
  
        // Print the elements of HashSet 
        System.out.println(set.toString().replace("[","").replace("]","")); // Replace bracket with empty to remove the bracket out
    } 
  
    /* A utility function to print array of size n */
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n; ++i) 
            System.out.print(arr[i] + " "); 
  
        System.out.println(); 
    } 
}
