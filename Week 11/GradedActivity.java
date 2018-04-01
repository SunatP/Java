/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */

public abstract class GradedActivity {
	private int score;	// numeric score
	
	public GradedActivity(){
		score = 0;
	}
	
	// The setScore method stores its argument in score field.
	public void setScore(int score){
		this.score = score;
	}
	
	// The getScore method returns the score field.
	public int getScore(){
		return score;
	}
	
	// The getGrade method returns a letter grade determined from the score field.
	public abstract char getLetterGrade();
	
        @Override
	public String toString(){
		return "score: " + this.getScore() + ", grade: " + this.getLetterGrade();
	}
	
}
