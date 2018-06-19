/**
*
* @author Sunat Praphanwong 6088130
*/
class MyDate {
  var year:Int = 0
  var month:Int = 0
  var day:Int = 0
  var objectNumber:Int = 0
  internal constructor() {
    year = 1900
    month = 1
    day = 1
    objCounter++
    objectNumber = objCounter
  }
  internal constructor(aYear:Int, aMonth:Int, aDay:Int) {
    year = aYear
    month = aMonth
    day = aDay
    objCounter++
    objectNumber = objCounter
  }
  fun setDate(aYear:Int, aMonth:Int, aDay:Int) {
    year = aYear
    month = aMonth
    day = aDay
  }
  public override fun toString():String {
    return day + " " + strMonths[month - 1] + " " + year
  }
  fun nextday():MyDate {
    if (month == 12 && day == 31)
    {
      year++
      month = 1
      day = 1
    }
    else if (month == 4 || month == 6 || month == 11)
    {
      if (day == 30)
      {
        month++
        day = 1
      }
      else
      day++
    }
    else
    {
      if (isLeapYear(year) && day == 29)
      {
        month++
        day = 1
      }
      else if (isLeapYear(year) == false && day == 28)
      {
        month++
        day = 1
      }
      else
      {
        day++
      }
    }
    return this
  }
  fun nextMonth():MyDate {
    if (month == 12)
    {
      month = 1
    }
    else
    {
      month++
    }
    return this
  }
  fun nextYear():MyDate {
    if (month == 2 && day == 29)
    {
      year++
      day++
    }
    else
    {
      year++
    }
    return this
  }
  fun previousDay():MyDate {
    if (month == 1 && day == 1)
    {
      year--
      month = 12
      day = 31
    }
    else if (month == 5 || month == 7 || month == 10 || month == 12)
    {
      if (day == 1)
      {
        //year--;
        month--
        day = 30
      }
      else
      {
        day--
      }
    }
    else if (month != 3)
    {
      if (day == 1)
      {
        month--
        day = 31
      }
      else
      {
        day--
      }
    }
    else
    {
      if (isLeapYear(year) && day == 1)
      {
        month--
        day = 29
      }
      else if (day == 1)
      {
        month--
        day = 28
      }
      else
      {
        day--
      }
    }
    return this
  }
  fun Getleapyear() {
    if (isLeapYear(year))
    {
      println("Leap Year")
    }
    else
    {
      println("Not Leap Year")
    }
  }
  companion object {
    private val objCounter = 0
    private val strMonths = arrayOf<String>("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    fun isLeapYear(year:Int):Boolean {
      if (year % 4 != 0)
      {
        return false
      }
      else if (year % 100 != 0)
      {
        return false
      }
      else if (year % 400 != 0)
      {
        return false
      }
      else
      {
        return true
      }
    }
  }
}
