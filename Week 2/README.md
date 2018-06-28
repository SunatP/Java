
# Lab 2 : Classes, Objects, Methods
โดยในแล็ปนี้จะต้องทำการสร้างไฟล์ขึ้นมาสองไฟล์ ไฟล์ที่แรกชื่อว่า StudentProfile กดดูโค้ดได้ที่ [StudentProfile](https://github.com/SunatP/Java/blob/master/Week%202/StudentProfile.java) </br>
ไฟล์สองชื่อ ManageStudent กดดูโค้ดได้ที่ [ManageStudent](https://github.com/SunatP/Java/blob/master/Week%202/ManageStudent.java) เป็นไฟล์คลาสหลักควบคุม </br>

### Task 1: Implement class **StudentProfile** in StudentProfile.java to store **studentID**, **name**, **age**, and **address**. Please make sure that these variables cannot be accessed directly by other classes. Then, implement methods to store and retrieve all these variables. For example, **setStudentID(int id)** and **getStudentID()**. </br>

โดยอันดับแรกจะต้องสร้างไฟล์ที่มีชื่อคลาสว่า **StudentProfile** ขึ้นมา แล้วประกาศตัวแปรขึ้นมาเก็บค่า **studentID** , **name** , **age** , **address** เมื่อสร้างแล้วจะมีโค้ดหน้าตาประมาณนี้

```java
  public class StudentProfile {
    private int StudentID ;
    private String Name ;
    private int Age ;
    private String Address;

    public StudentProfile(int ID, String name, int age, String address){
    StudentID = ID;
    Name = name;
    Age = age;
    Address = address; 
}
```
</br>
จากนั้นให้ทำการสร้างเมธอด(Method) ที่รับและส่งค่าที่มีชื่อว่า **set** และ **get** เมธอด(Method)อยู่สองวิธีที่คือ คลิกขวาเลือก Source -> Generate Getters and Setters...

![How-to](https://i.stack.imgur.com/Ki2JB.png)


## โครงสร้างของ JAVA
### Package: เป็นกลุ่มของคลาสหรือไลบรารี่มาตรฐานของภาษา Java ที่มีฟังก์ชันต่างๆ ให้ใช้มากมาย 
```
import java.util.something อะไรพวกนี้ เรียกว่า package
```
### Class: ในส่วนของการประกาศคลาส จะต้องประกาศคลาสให้ชื่อตรงกับไฟล์เสมอ นอกจาก Inner คลาสที่อยู่ในคลาสเดียวกัน โดยชื่อคลาสนั้นควรจะขึ้นต้นด้วยตัวใหญ่ และถ้ามีหลายคำให้ใช้ตัวพิมพ์ใหญ่แบ่ง ตัวอย่าง
 ```java
 public class ClassName { // นี่คือการประกาศชื่อ Class ที่ชื่อว่า ClassName
    ...
}
```

### Method: หลังจากคลาสสร้างแล้ว จะเป็นประกาศเมธอดภายในคลาส โดยในการที่จะรันโปรแกรมได้จะต้องมีเมธอดที่ชื่อว่า Main ดังตัวอย่างในโปรแกรมด้านบน มันเป็นที่แรกที่โปรแกรมจะเริ่มทำงาน

```java
 public static void main (String[] args) { // เมธอดตัวนี้เป็นตัวสำคัญที่ทำให้โปรแกรมเรารันได้
        ...
    }
```

### การใช้ Comment
คอมเมนต์ คือการทำเครื่องหมายสำหรับให้โปรแกรมเมอร์เข้าใจโปรแกรมของพวกเขามากขึ้น หรือป้องกันในกรณีที่อาจจะเกิดการลืมได้ การคอมเมนต์นั้นจะเป็นคำสั่งที่ไม่มีผลต่อการทำงานของโปรแกรม ตัวอย่าง
```
// Single line comment คอมเม้นแบบนี้สามารถกด Ctrl + /   

/* Multiline comment1
Multiline comment2 */
```

### Literals

Literals คือค่าของข้อมูลใดๆ ที่กำหนดให้กับตัวแปรได้ เราเรียกมันว่า Constant Literals ทุกค่าที่เป็นไปได้ เช่น **"MarcusCode"** เป็น String Literals 10 เป็น Integer Literals หรือ true เป็น Boolean Literals โดย Literals เป็นได้แค่ Primitive data type เท่านั้น ตัวอย่างการกำหนดค่าหรือ Literals ให้กับตัวแปร
```
int age = 18;
String name = "Marcus";
float pi = 3.14f;
double money = 45.2;
```
