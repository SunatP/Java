---
layout: default
---

# Introduce OOP 
OOP คือใน JAVA คือ Object-Oriended Programming ตัวอย่าง หรือกดที่ [Lab 1 (LetterPrinter)](https://github.com/SunatP/Java/blob/master/Week%201/LetterPrinter.java)
  
```java
    struct Books {           // Struct คือการทำโครงสร้างในการเก็บข้อมูลรูปแบบหนึ่ง
   char  title[50];          // เก็บข้อมูล title ในรูป character
   char  author[50];         // เก็บข้อมูล author ในรูป character
   int  page[100];           // เก็บข้อมูล page ในรูป integer
   int   book_id;            // เก็บข้อมูล book_id ในรูป integer 
};
int main( ) {
   struct Books Book1;         // ประกาศโครงสร้าง Books ที่ชื่อ Book1 
   struct Books Book2;         // ประกาศโครงสร้าง Books ที่ชื่อ Book2
}
```
---
## โครงสร้างของ JAVA
### Package: เป็นกลุ่มของคลาสหรือไลบรารี่มาตรฐานของภาษา Java ที่มีฟังก์ชันต่างๆ ให้ใช้มากมาย 
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
