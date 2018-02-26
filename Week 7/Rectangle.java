/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */

/*
 * The Rectangle class, subclass of Shape
 */

public class Rectangle extends Shape {
   // Private member variables
    private double area,length,width;
   //add your code here

   // Constructors
   public Rectangle() // Shapetester use this variable in this method
   {
       
        //length = 5.0; //คือมันใช้ได้
        
        //width  = 10.0 ;
        //add your code here

   }
   public Rectangle(String color, double length, double width) {
       super(color);
        this.length = length;
        this.width= width;
      //add your code here
   }

   @Override
   public String toString() {
      //add your code here
      return "Rectangle " +"["+"length = "+this.length +"," +"width = "+ this.width+","+"["+"color = "+super.getColor()+"]"+"]";
   }


   // Override the inherited getArea() to provide the proper implementation
   @Override
   public double getArea() {
        return this.getArea(length, width);
      //add your code here
   }

   public double getArea(double length, double width) {
	this.length = length;
        this.width= width;   	 
       area= length*width;
        return area; //add your code here
	   }
}