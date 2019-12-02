/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */




/*  The structure of Triangle class is similar to Rectangle */
public  class Triangle extends Shape {
    //private String color;
    private double base , height , area;
    
   //add your code here

    public Triangle() {
        super();
        base = 5.0;
        height = 10.0;
        //this("blue",4,5);
        //this.color = "yellow";
        //this.base = 5;
        //this.height = 10;
    }

   
    


   public Triangle(String color, double base, double height) 
   {
        super(color);
        this.base = base;
        this.height = height;
        //add your code here
   }

   @Override
   public String toString() 
   {
    return "Triangle " +"["+"base = "+this.base +"," +"height = "+ this.height+","+"["+"color = "+super.getColor()+"]"+"]";
      //add your code here
   }

   // Override the inherited getArea() to provide the proper implementation
  
    @Override
   public double getArea() 
   {
       
      area = 0.5*base*height;
      //add your code here
        return this.getArea(base, height);
   }
    public double getArea(double base, double height) {
      this.base = base;
        this.height = height;
        area= 0.5*base*height;
        return area;
    }
}

