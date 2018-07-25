
# Lab 11 : File I/O
ไฟล์แลปนี้ได้ทำการสร้าง Class มาให้แล้วเพียงแค่ทำการเติมโค้ดให้ครบทั้งสอง Method จึงจะสมบูรณ์ กดดูคลิก [DiaryRunner](https://github.com/SunatP/Java/blob/master/File%20IO(Extra)/DiaryRunner.java) </br>


### Task 1: File Input READ, WRITE  </br>

ขั้นตอนในของแลปนี้คือทำให้โค้ดในเมธอด **public static void readContent** ซึ่งเมธอดนี้จะให้ทำการเติมโค้ดให้สามารถอ่านไฟล์ที่ชื่อว่า **Diary1.txt** ได้ โค้ดก่อนที่จะทำการแก้ไขมีดังนี้

```java
 public static void readContent(String diaryFilename) throws Exception
 {
    //CODE HERE
 }
```
จากนั้นให้อิมพอร์ต ไลบรารี่ที่ทำหน้าที่อ่านไฟล์ได้คือ **File** </br> 

```java
import java.io.File;
import java.io.BufferedReader;
```
กลับมาที่เมธอด **readContent** ไลบรารี่ที่เราได้ทำการอิมพอร์ตเข้าไปนั้นจะนำมาใช้งานในส่วนนี้ซึ่ง **java.io.File** นี้จะนำมาอ่านไฟล์ที่มีตัวแปรชื่อว่า **diaryFilename**
โดยโค้ดที่จะเขียนมีลักษณะประมาณนี้
```java
    File content = new file(diaryFilename); //content คือชื่อตัวแปรสามารถตั้งชื่ออะไรก็ได้
```
จากนั้นเราจะใช้ **Try กับ catch** มาใช้ด้วยเนื่องจากเมธอดนี้ได้มีการ **Throw Exception** หากเกิดข้อผิดพลาดระหว่างอ่านไฟล์จะสามารถแจ้งให้ทราบได้
โดยวิธีการอ่านไฟล์โดยใช้ **Try กับ catch** จะได้โค้ดประมาณนี้
```java
 try (BufferedReader reader = new BufferedReader(new FileReader(content))) //try ตัวนี้นำมาอ่านไฟล์โดยใช้ Bufferreader เพื่อนำไฟล์ที่อ่านค่าได้มาเก็บค่าไว้ก่อน
            {
                String line = null; // กำหนดให้ String ชื่อ line นั้นไม่มีค่าอะไรเลย
                while((line = reader.readLine()) != null){ // while loop ตัวนี้จะทำการอ่านค่าไปเรื่อยๆโดยใช้ line ที่เป็นสตริงมาเป็นตัวอ่านข้อความจนเสร็จ
                    System.out.println(line); // แสดงข้อความที่อ่านออกมาได้
                }
            }
```

เมื่อทำเสร็จจะได้เมธอด **readContent** ประมาณนี้
```java
public static void readContent(String diaryFilename ) throws Exception{
		//CODE HERE DO THIS FIRST BEFORE DO METHOD newDiary
                File content = new File(diaryFilename);
            try (
                    BufferedReader reader = new BufferedReader(new FileReader(content))
                    )
            {
                String line = null;
                while((line = reader.readLine()) != null){
                    System.out.println(line);
                }
            }
	}
```
จากนั้นกลับไปทำเมธอดที่ชื่อว่า **newDiary** ต่อ โดยในไฟล์บอกว่าให้ทำการสร้างไฟล์ขึ้นมาและตรวจสอบว่ามีไฟล์แล้วหรือยัง เมธอดนี้จะมีรูปร่างประมาณนี้
```java
public static void newDiary(String diaryFilename , String date, String title, String content) throws Exception{
		//CODE HERE
	}
```
ให้เราทำการอิมพอร์ทไลบรารี่นี้เข้าไป
```java
import java.io.BufferedWriter;
import java.io.FileWriter;
```
เมื่ออิมพอร์ตเสร็จแล้วให้กลับมาทำโค้ดที่เมธอดต่อโดยเราจะใช้ **File** มาใช้ต่อเนื่องจากเราเมธอดนี้เราจะทำการสร้างไฟล์ใหม่ขึ้นมาอีกอันนึงโค้ดจะมีหน้าตาประมาณนี้
```java
             File File = new File(diaryFilename);
```
จากนั้นเราจะใช้ **Try Catch** มาช่วยต่อเนื่องจากเมธอดนี้ได้มีการใส่ **Throw Exception** เอาไว้โดยโค้ดจะประมาณแบบนี้
```java
try {
			if(!File.exists()){  //ถ้าไฟล์ยังไม่ีมีให้เข้า loop if มาสร้างไฟล์
                            File file = new File(diaryFilename);
                            try (BufferedWriter BufferWriter = new BufferedWriter(new FileWriter(file,false))) { // ใช้ bufferwriter มาเขียนไฟล์หากไฟล์ไม่มี
                                BufferWriter.write("[diary2.txt]"); // สร้างไฟล์ชื่อ diary2.txt
                                BufferWriter.newLine(); // ในไฟล์นั้นจะให้ขึ้นบรรทัดใหม่
                                BufferWriter.write("[DATE] : "+date); // ใส่ข้อความวันที่ลงไป
                                BufferWriter.newLine(); // ในไฟล์นั้นจะให้ขึ้นบรรทัดใหม่
                                BufferWriter.write("[TITLE] : "+title); // ใส่ไตเติ้ลลงไป
                                BufferWriter.newLine(); // ในไฟล์นั้นจะให้ขึ้นบรรทัดใหม่
                                BufferWriter.write("[CONTENT] : "+content); // ใส่เนื้อหาลงไป
                            }
                        } else {
                        }
		} catch (IOException e) {
                 // TODO: handle exception
                 
		}
```
