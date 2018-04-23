
import static java.util.Arrays.binarySearch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Student
 */

public class MatrixSearcher {
	
	
	public static void main(String[] args)
	{
		int[][] matrix = {{1, 4, 4, 7, 8, 10, 10, 10, 11, 11},
						{12, 13, 14, 15, 15, 17, 17, 18, 18, 18},
						{18, 22, 23, 23, 24, 24, 24, 25, 27, 28},
						{28, 28, 29, 29, 29, 30, 31, 31, 32, 32},
						{32, 33, 33, 35, 36, 37, 38, 39, 39, 42},
						{42, 43, 43, 43, 44, 45, 49, 52, 52, 54},
						{54, 55, 55, 56, 58, 60, 60, 61, 62, 62},
						{64, 67, 68, 68, 70, 71, 72, 72, 73, 75},
						{76, 77, 78, 81, 81, 84, 84, 85, 86, 86},
						{86, 87, 90, 90, 91, 95, 96, 97, 97, 98}};
		
		
		System.out.println("Contains 55?:"+binaryFind(matrix, 55));
		System.out.println("Contains 1?:"+binaryFind(matrix, 1));
		System.out.println("Contains -1?:"+binaryFind(matrix, -1));
		System.out.println("Contains 98?:"+binaryFind(matrix, 98));
		System.out.println("Contains 100?:"+binaryFind(matrix, 100));
	}

    private static boolean binaryFind(int[][] matrix, int i) {
                      int lastCol = matrix[0].length-1;
    int row=0;
    while(row!=matrix.length && lastCol>=0){
        if(matrix[row][lastCol]==i){
            return true;
        }
        if(i<=-1)
        {
            return false;
        }
        if(matrix[row][lastCol]>i){
            lastCol--;
            
                return true;
            
        }
        row++;
    }
    return false;
//        Consider mid column and find maximum element in it.
//        Let index of mid column be ‘mid’, value of maximum element in mid column be ‘max’ and maximum element be at ‘mat[max_index][mid]’.
//        If max >= A[index][mid-1] & max >= A[index][pick+1], max is a peak, return max.
//        If max < mat[max_index][mid-1], recur for left half of matrix.
//        If max < mat[max_index][mid+1], recur for right half of matrix. true

       
}
}
 

// int i = 0, j = n-1;  //set indexes for top right
//                              // element
// 
//           while ( i < n && j >= 0 )
//           {
//              if ( mat[i][j] == x )
//              {
//                 System.out.print("n Found at "+ i + " " + j);
//                 return;
//              }
//              if ( mat[i][j] > x )
//                j--;
//              else //  if mat[i][j] < x
//                i++;
//           }
//          
//           System.out.print("n Element not found");
//           return;  // if ( i==n || j== -1 )
