/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */

public class Circle extends Shape{
 private double radius , height , area;
    
   //add your code here

    public Circle() {
        super();
        //radius = 5.0;
        //height = 10.0;
        //this("blue",4,5);
        //this.color = "yellow";
        //this.base = 5;
        //this.height = 10;
    }

    Circle(String blue, double d) {
        super(blue);
       this.radius =d;
    }

   @Override
   public String toString() 
   {
    return "Circle " +"["+"Radius = "+this.radius +","+"["+"color = "+super.getColor()+"]"+"]";
      //add your code here
   }

   // Override the inherited getArea() to provide the proper implementation
  
 @Override
   public double getArea() 
   {  
      area= 3.14*Math.pow(radius, 2);
      //add your code here
        return this.getArea(radius);
   }
    public double getArea(double radius) {
       this.radius =radius;
        area= 3.14*Math.pow(radius, 2);
        return area;
    }
}

