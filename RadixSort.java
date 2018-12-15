   import java.util.Scanner;
/*
   
    Powered by Netbeans IDE 8.2 With JDK & JRE 1.8.0_191 Compiler Environment System
    Sunat P. 6088130 
    Barameerak K. 6088156
    Poonkasem K. 6088071
   
*/
    public class RadixSort 
    {
        /** Radix Sort function
     * @param array **/
        public static void Radixsort(int[] array)
        {
            int i, m = array[0], exp = 1, n = array.length;
            int[] b = new int[n];
            for (i = 1; i < n; i++)
                if (array[i] > m)
                    m = array[i];
            
            while (m / exp > 0)
            {
                int[] bucket = new int[10];
                for (i = 0; i < n; i++)
                    bucket[(array[i] / exp) % 10]++;
                /** Divide by exp and mod by 10 before sort **/
                for (i = 1; i < 10; i++)
                    bucket[i] += bucket[i - 1];
                /** Remove value from array before sort **/
                for (i = n - 1; i >= 0; i--)
                    b[--bucket[(array[i] / exp) % 10]] = array[i];
                /** Array b remove value from array bucket **/
                for (i = 0; i < n; i++)
                    array[i] = b[i];
                /** Array name array equal to array b **/
                exp *= 10;        
            }
        }    
        /** Main method
     * @param args **/

        public static void main(String[] args) 
        {
            Scanner scan;        
            scan = new Scanner( System.in );
            System.out.println("Radix Sort Algorithm\n");
            int n, i;
            /** Accept number of elements **/
            System.out.println("Enter number of integer elements");
            n = scan.nextInt();
            /** Create integer array on n elements **/
            int arr[] = new int[ n ];
            /** Get elements into Array **/
            System.out.println("\nEnter "+ n +" integer elements");
            for (i = 0; i < n; i++)
                arr[i] = scan.nextInt();
            
            Radixsort(arr);
            /** Use Function Radix Sort by Ascending **/
            System.out.println("\nElements after sorting ");        
            for (i = 0; i < n; i++)
                System.out.print(arr[i]+" ");            
            System.out.println();                     
        }    
    }