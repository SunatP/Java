/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sunat Praphanwong 6088130
 */
import java.util.Arrays;

public class StackTester {
	
	public static void testCharacter()
	{
		char[] cs = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};
		Stack<Character> myStack = new Stack<>(5);
		for(char c: cs)
		{	StringBuilder str = new StringBuilder();
			if(myStack.push(c) == true)
			{
				str.append("Pushing ");
			}
			else
			{
                    StringBuilder append = str.append("Stack Full:").append(Arrays.toString(myStack.toArray())).append(" The top element is ").append(myStack.peek().toString()).append(". Cannot push ");
			}
                    StringBuilder append = str.append(c).append("\n").append(myStack.toString());// ใช้ chain
			System.out.println(str.toString());
		}
		
		for(int i = 0; i < cs.length; i++)
		{	StringBuilder str = new StringBuilder();
			Character c = null;
			if((c = myStack.pop()) != null)
			{
                    StringBuilder append = str.append("Popping ").append(c.toString());// ใช้ chain
			}
			else
			{
				str.append("Stack empty!");
			}
			str.append("\n").append(myStack.toString());
			System.out.println(str.toString());
		}
	}
	
	public static void testString()
	{
		String[] ss = new String[]{"Think?", "You", "Don't", "Cool", "is", "Java", "Oops!"};
		Stack<String> myStack = new Stack<>();
		for(String s: ss)
		{	StringBuilder str = new StringBuilder();
			if(myStack.push(s))
			{
				str.append("Pushing ");
			}
			else
			{
				str.append("Stack Full. The top element is ").append(myStack.peek()).append(". Cannot push ");
			}
                    StringBuilder append = str.append(s).append("\n").append(myStack.toString()); // ใช้ chain
			System.out.println(str.toString());
		}
		
		for(int i = 0; i < ss.length+1; i++)
		{	StringBuilder str = new StringBuilder();
			String s = null;
			if((s = myStack.pop()) != null)
			{
				str.append("Popping ").append(s);
			}
			else
			{
				str.append("Stack empty!");
			}
			str.append("\n").append(myStack.toString());
			System.out.println(str.toString());
		}
	}
	
	
	public static void main(String[] args)
	{
		testCharacter();
		
		System.out.println("--------------------------------");
		
		testString();
	}
}
