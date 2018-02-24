

/**
 *
 * @author Sunat Praphanwong 6088130
 */
public class MyDate {
    public int year;
    public int month;
    public int day;
    public int objNumber;
    
    private static int objCounter = 0;
    private static String[] strMonths  ={"January","February","March","April","May","June","July","August","September","October","November","December"};   
    
    MyDate()
    {
        year =1900;
        month = 1;
        day = 1 ;
        objCounter++;
        objNumber = objCounter;
    }
    MyDate(int aYear, int aMonth, int aDay)
    {
        year = aYear;
        month = aMonth;
        day = aDay;
        objCounter++;
        objNumber = objCounter;
    }
    public int getObjectNumber()
    {
        return objNumber;
    }
    public void setDate(int aYear , int aMonth , int aDay)
    {
        year = aYear;
        month = aMonth;
        day = aDay;
    }
    public void setYear(int aYear)
    {
        year = aYear;
    }
    public void setMonth(int aMonth)
    {
        month = aMonth;
    }
    public void setDay(int aDay)
    {
        day = aDay;
    }
    public int getYear()
    {
        return year;
    }
    public int getMonth()
    {
        return month;
    }
    public int getDay()
    {
        return day;
    }
    public String toString()
    {
        return getDay()+" "+strMonths[month-1]+" "+getYear();
    }
    public MyDate nextday(){
        if(month == 12 && day == 31)
        {
            year++;
            month = 1;
            day = 1;
        }
        else if(month == 4 || month == 6 || month == 11){
            if(day==30)
            {
            month++;
            day = 1;         
            }
            else day++;
        }
        else{
            if(isLeapYear(year) && day == 29)
            {
                month++;
                day = 1;
            }
            else if(isLeapYear(year) == false && day == 28)
            {
                month++;
                day = 1;
            }
            else 
                {
                day++;  
                }
        }
    return this;
    }
    public MyDate nextMonth()
    {
        if(month == 12)
        {
            month = 1;
        }
        else
        {
            month++;
        }
        return this;
    }
    public MyDate nextYear()
    {
        if(month == 2 && day == 29)
        {
            year++;
            day++;
        }
        else 
        {
            year++;
        }
        return this;
    }
    public MyDate previousDay()
    {
        if(month == 1 && day ==1)
        {
            year--;
            month =12;
            day =31;
        }
        else if(month == 5 || month == 7 || month == 10 || month == 12)
        {
            if(day == 1)
            {
            //year--;
            month--;
            day = 30;
        }
            else
            {
                day --;
            }
        }
        
        else if(month != 3){
            if(day ==1)
                {
                month --;
                day =31;
                }
                else
                {
                day --;
                }
            }
        else
        {
         if(isLeapYear(year)&&day ==1)
                {
                month--;
                day =29;
                }
         else if(day == 1)
                {
                month--;
                day = 28;
                }
                else
                {
                day--;
                }
        }
        return this;
    }
    public static boolean isLeapYear(int year)
    {
        if(year %4 !=0)
        {
            return false;
        }
        else if(year %100 != 0)
        {
            return false;
        }
        else if(year %400 !=0)
        {
            return false;
        }
        else{
            return true;
        }
    }
    public void Getleapyear(){
        if(isLeapYear(year))
                {
                    System.out.println("Leap Year");
                }
        else
                {
                    System.out.println("Not Leap Year");
                }
    }
}