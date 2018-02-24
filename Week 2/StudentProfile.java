

import static java.time.Clock.system;
import java.util.Scanner;

public class StudentProfile {
    private int StudentID ;
    private String Name ;
    private int Age ;
    private String Address;
    private static int count;

    public StudentProfile(int ID, String name, int age, String address){
    StudentID = ID;
    Name = name;
    Age = age;
    Address = address;
    
}

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
public void getAllinfo()
{
    System.out.println(StudentID + Name + Age + Address);
}
}