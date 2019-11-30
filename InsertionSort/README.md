# Insertion sort with removing duplicate Data

เริ่มอธิบายเลยละกัน

```java
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
        LinkedHashSet<Integer> set  = new LinkedHashSet<>(); 
  
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
  
        // Print the elements of LinkedHashSet 
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


```

## void sort()

ฟังก์ชั่น sort ตัวนี้จะเอาไว้เรียงค่าตามปกตินั่นแหละก็คือ sort ตามนี้

1. Create an empty sorted (or result) list
2. Traversed the given list, do following for every node.
    a. Insert current node in sorted way in sorted or result list.
3. Change head of given linked list to head of sorted (or result) list.

1. เราจะสร้างอาเรย์เปล่าสำหรับการ sort
2. ก่อนที่เราจะทำการ sort เราจะต้องดูค่าแต่ละตัวใน node ทุกครั้งที่ sort
   1. เราจะทำการใส่ค่าปัจจุบันที่ได้จากการ sort หลังจากที่เราได้ผลลัพธ์มาแล้ว
3. เปลี่ยนตำแหน่งที่ต้องการจะ sort เป็นตำแหน่งใหม่หลังจากที่เรา sort ไปได้สักพักนึงแล้ว

```java
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
  
        // Print the elements of LinkedHashSet 
        System.out.println(set.toString().replace("[","").replace("]","")); // Replace bracket with empty to remove the bracket out
    } 
```

ตรงนี้ **HashSet<Integer> set  = new HashSet<>();** มีความสำคัญมาก ทำไมถึงต้องใช้ในการ sort ครั้งนี้ เพราะว่า HashSet นั้นจะไม่สนใจข้อมูลที่ซ้ำกัน หากมี/พบค่าที่ซ้ำกันในอาเรย์หรืออะไรก็ตามที่ใช้ HashSet ตัวนี้จะทำการข้ามตัวที่ซ้ำไปเลย เช่น

```bash
Before remove duplicate value
12 11 13 5 6 6 7 13 21 12 12 14 15 16 18 20 0 12 
After remove duplicate value
0, 5, 6, 7, 11, 12, 13, 14, 15, 16, 18, 20, 21
```

เราสั่งให้ค่าจาก array ที่ทำการ sort ไว้ก่อนหน้านั้นใส่ค่าเข้าไปใน HashSet เพื่อทำการ remove duplicate หรือกำจัดค่าที่ซ้ำออกไปในช่อง Result แค่นี้แหละ