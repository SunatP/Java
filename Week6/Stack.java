/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sunat Praphanwong 6088130
 */
import java.util.*;
import java.util.ArrayList;


public class Stack<T> extends ArrayList<T>{
int Capacity; 

public Stack() // Already create
{
   super();
   Capacity = 0;
    //this.push(); คือการเพิ่มค่า หรือเอาค่าเข้ามาใส่
    //this.pop();  คือการลดค่า หรือเอาค่าที่มีอยู่เอาออก
}     	
    //your code here

public Stack(int capacity) // Already create
{
   super();
   Capacity = capacity;
}

public boolean push(T object) // Already create
{
   if(Capacity> 0&&this.size()>=Capacity) // equal == size
   {
       return false;
   }
   else{
       this.add(object);
   return true;
   }
}    

public T peek() // Already create
{
    T obj ;
    if (this.isEmpty())// ใช้.isEmpty หรือ คือ == 0 
    {
        return null;
    }
    else{

    return this.get(this.size()-1);
    }
}

public T pop() 
{
    T obj;
            if (this.isEmpty()) { // ใช้.isEmpty หรือ คือ == 0 
                    obj = null;
            } 
            else {
                    obj = this.get(this.size()-1);
                    this.remove(this.size() - 1);
            }
            return obj;
}

public T[] toArrays()
{
    return toArrays(); // No Effect toT[]
}
@Override
public String toString()
{
    String text = "";
            for (int i = this.size() - 1; i >= 0; i--) {
                    text += "|" + this.get(i).toString() + "|\n";
            }
            text += "===\n";
            return text;
}
}
