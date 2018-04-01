/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */

public interface Comparable {
	/*
	 * check whether the input GradedActivity equals to this object or not
	 * inputs: GradedActivity A
	 * output: return true if this object equals to object A, otherwise return false
	 */
	boolean equals(GradedActivity A);
	
	/*
	 * check whether the input object is greater/larger than GradedActivity A
	 * input: GradedActivity A
	 * output: return true if this object is greater than GradedActivity A, otherwise return false
	 */
	boolean isGreaterThan(GradedActivity A);
	
	/*
	 * check whether the input object is less/smaller than GradedActivity A
	 * input: GradedActivity A
	 * output: return true if this object is greater than GradedActivity A, otherwise return false
	 */
	boolean isLessThan(GradedActivity A);

    void setScore(int i);
	
}
