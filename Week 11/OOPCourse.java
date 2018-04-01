/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sunat Praphanwong 6088130
 */
import java.util.HashMap;
import java.util.Map;

public class OOPCourse {
	public static void main(String[] args){
		// instantiate HashMap collection
		Map<String, GradedActivity> studentMidterm = new HashMap<>();
		
		// create midterm scores for each student
		GradedActivity firstStudent = new Midterm(6, 20);
		firstStudent.setScore(2);		// missed 2 questions
		GradedActivity secondStudent = new Midterm(6, 20);
		secondStudent.setScore(3);		// missed 3 questions
		GradedActivity thirdStudent = new Midterm(6, 20);
		thirdStudent.setScore(1);		// missed 1 questions
		GradedActivity forthStudent = new Midterm(6, 20);
		forthStudent.setScore(4);		// missed 4 questions
		
		// Add data into HashMap collection
		studentMidterm.put("5988000", firstStudent);
		studentMidterm.put("5988999", secondStudent);
		studentMidterm.put("5988998", thirdStudent);
		studentMidterm.put("5988997", forthStudent);
		
		//print the midterm's score and grade letter of all students
		System.out.println("Here is the list of all students and their midterm grade");
                studentMidterm.keySet().forEach((stuID) -> {
                    System.out.println("StudentID: " + stuID + " " + studentMidterm.get(stuID).toString());
            });
		
		
		// Task 1: print number of students who have midterm's score higher than 59
		System.out.println("Task 1 -------------------------------------------------");
		// CODE HERE
		int a =0;
		for(String GScore : studentMidterm.keySet()){ // get data from studentMidterm
                if(studentMidterm.get(GScore).getScore()>60) // if student score more than 60 
                {
                    a++; // increase a to add data to integer for count student number
                }
                }
                System.out.println("Number of students whose midterm's score is higher than 60 is " + a);
		
		
		// Task 2:
		System.out.println("Task 2 -------------------------------------------------");
		// suppose the average midterm grade is as follow
		GradedActivity avgMidterm = new Midterm(6, 20);
		avgMidterm.setScore(3);
		// print student ID, score and grade letter of students who are at the average grade (Hint: uses equals() method)
		// CODE HERE
		for(String GScore : studentMidterm.keySet()){ // get data from studentMidterm
                if(((Midterm)studentMidterm.get(GScore)).equals(avgMidterm)==true) // if student midterm score is true  
                {
                   System.out.println("StudentID: " + GScore+" "+ avgMidterm); // get score of average score
                }
                }
                
		
		
		
		
		
			
		// BONUS
		System.out.println("Bonus -------------------------------------------------");
		// You have to implement isGreater and isLessThan methods in Midterm class
		// print number of students who have higher rank than the average midterm grade (avgMidterm)
		// CODE HERE
                int b =0;
		for(String GScore : studentMidterm.keySet()){  // get data from studentMidterm
                if(((Midterm)studentMidterm.get(GScore)).isGreaterThan(avgMidterm)==true)// if student midterm score greater than is true  
                {
                    b++; // increase b to add data to integer for count student number
                }
                }
                System.out.println("Number of students whose midterm are higher than the average midterm grade is " + b);
		
	}
	
}
