
# Lab 11 : File Input READ, WRITE (File I/O)
โดยในแล็ปนี้จะต้องทำการสร้างไฟล์ขึ้นมาสองไฟล์ ไฟล์ที่แรกชื่อว่า StudentProfile กดดูโค้ดได้ที่ [StudentProfile](https://github.com/SunatP/Java/blob/master/Week%202/SourceCode/StudentProfile.java) </br>
ไฟล์สองชื่อ ManageStudent กดดูโค้ดได้ที่ [ManageStudent](https://github.com/SunatP/Java/blob/master/Week%202/SourceCode/ManageStudent.java) เป็นไฟล์คลาสหลักควบคุม </br>

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

![How-to](https://github.com/SunatP/Java/blob/master/Week%202/picture/Getter%20Setter.png)

กด Select All แล้วกด OK หรือ Enter ที่คีย์บอร์ดจะได้โค้ดประมาณนี้ </br>
```
public void setStudentID(int id)
{
 StudentID = id;
}
public void setName(String name)
{
Name = name;
}
public void setAge(int age)
{
Age = age;
}
public void setAddress(String address)
{
 Address = address;
}

public String getAddress()
{
 return Address;
}
public int getStudentID()
{
 return StudentID;
}
public String getName()
{
return Name;
}
public int getAge()
{
return  Age;
}
```
</br>

### Task 2: Implement method **getAllInfo()** in StudentProfile to print all student information in the following format:
เราจะต้องสร้างเมธอดขึ้นมาอีกอันชื่อว่า GetAllInfo ขึ้นมาเพื่อแสดงผลลัพธ์ให้ถูกต้อง ตัวอย่าง
```
5888023 : Peter Copper, 22 26 Waterloo street, Leamington spa CV312RT
```
โค้ดที่เราจะสร้างเมธอดนี้เพื่อแสดงผลลัพธ์ตามด้านบนจะมีลักษณะประมาณนี้
```
public void getAllinfo()
{
    System.out.println(StudentID + Name + Age + Address);
}
```
### Task 3: Implement a class ManageStudent in ManageStudent.java. In the main method of this class, instantiate two StudentProfile objects to store your and your friend’s information, then print it out via getAllInfo().
เราจะต้องสร้างคลาสอีกคลาสนึงที่ชื่อว่า **ManageStudent** ขึ้นมาเพื่อเรียกใช้คลาส **StudentProfile** โดยจะต้องสร้างให้อยู่ในตำแหน่งเดียวกันกับไฟล์ **StudentProfile** เมื่อสร้างแล้ววิธีการเรียกจะมีดังนี้
```
public class ManageStudent {
    public static void main(String fdxzfdxf[]){
        StudentProfile std1 = new StudentProfile(6088130, "Sunat", 18, "123456asdsad");
        StudentProfile std2 = new StudentProfile(5888023, "Peter Copper", 22, "26 Waterloo street, Leamington spa CV312RT");
        std1.getAllinfo();
        std2.getAllinfo();
    }
}
```
</br>
**StudentProfile std1** ตรง StudentProfile นี้เรียกว่าการดึงคลาส ของ StudentProfile เข้ามา แล้วสร้างชื่อตัวแปรให้มันว่า **std1** หลังจากชื่อตัวแปรที่มี = new StudentProfile(); นั้นคือการป้อนข้อมูลให้กับคลาสที่จะนำมาใช้
</br>

### Challenge Bonus (Optional):
For the class StudentProfile, use static variable to count number of students that are created. Implement the method ageDiff in class ManageStudent to calculate different between ages of any two students. Becareful!!, the result should always return a positive value. Show the results to an instructor to get the bonus score.  :blush: </br>

อันนี้จะเป็นการเอาอายุทั้งสองคนมาลบกันโดยใช้คลาสของ **StudentProfile** และ **ManageStudent** มาเกี่ยวข้องโดยสร้างเมธอดไว้ในคลาส **ManageStudent** ชื่ออะไรก็ได้ เช่น AgeDifferent , AgeDiff ตัวอย่าง
```
 public static void agediff (StudentProfile std1, StudentProfile std2)
    {
    	System.out.println(Math.abs(std1.getAge()-std2.getAge()));
    	
    }
    
```
</br>
แล้วเรียกใช้เมธอด(Method)นี้โดยการเอาชื่อเมธอด ไปใส่แล้วเติม (ในวงเล็บต้องมีข้อมูลทั้งสองคนด้วย) เช่น agediff(std1,std2); จะได้โค้ดประมาณนี้

```
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
```