/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */
public class Midterm extends GradedActivity implements Comparable {
	int numQuestions, pointPerQuestion;
	
	public Midterm(int numQuestions, int pointPerQuestion){
		this.numQuestions = numQuestions;
		this.pointPerQuestion = pointPerQuestion;
	}

    
	
        @Override
	public void setScore(int numMissedQuestions){
		// calculate the total score and store in score field
		super.setScore((this.numQuestions - numMissedQuestions)*this.pointPerQuestion);
	}
	
	@Override
	public boolean equals(GradedActivity a) {
            
            if (this.getLetterGrade()==a.getLetterGrade()) {
                return true;
            } else {
                return false;
            } // CODE HERE
            // two midterms are equals when both of them have the same letter grade
		
	}


	@Override
	public boolean isGreaterThan(GradedActivity a) {
            if (this.getLetterGrade()<a.getLetterGrade()) {
                return true;
            } else {
                return false;
            }
		// BONUS: compare midterm by checking at letter grade
		// A is higher than B, B is higher than C, C is higher than D, and D is higher than F
		// You may refer to Comparable interface for more information
		// === CODE HERE ===

	}

	@Override
	public boolean isLessThan(GradedActivity a) {
            if (this.getLetterGrade()>a.getLetterGrade()) {
                return true;
            } else {
                return false;
            }
		// BONUS: compare midterm by checking at letter grade
		// F is less than D, D is less than C, C is less than B, and B is less than A
		// You may refer to Comparable interface for more information
		// === CODE HERE ===
		
	}

	@Override
	public char getLetterGrade() {
		if(getScore() >= 80)		return 'A';
		else if(getScore() >= 70)	return 'B';
		else if(getScore() >= 60)	return 'C';
		else if(getScore() >= 50)	return 'D';
		else return 'F';
		
	}
	
}

