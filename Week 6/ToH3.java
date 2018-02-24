/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sunat Praphanwong 6088130
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

 
public class ToH3 {
 
    static int step = 0;
    static Stack<Integer> s1 = new Stack<Integer>();
    static Stack<Integer> s2 = new Stack<Integer>();
    static Stack<Integer> s3 = new Stack<Integer>();
    static int numDisk;
 
    public static void main(String[] args) {
        numDisk = 3;
        for (int i = numDisk; i > 0; i--) {
            s1.push(i);
        }
        display(s1, s2, s3);
        Hanoi(numDisk, s1, s3, s2);
        
    }
 
    public static void display(Stack<Integer> s1, Stack<Integer> s2, Stack<Integer> s3) {
        String text = "\n";
        while (s1.size() < numDisk) {
            s1.add(0);
        }
        while (s2.size() < numDisk) {
            s2.add(0);
        }
        while (s3.size() < numDisk) {
            s3.add(0);
        }
        for (int i = numDisk - 1; i >= 0; i--) {
            text += "\t";
            if (s1.get(i) == 0) {
                text += "|\t";
            }
            else {
                text += s1.get(i);
                text += "\t";
            }
 
            if (s2.get(i) == 0) {
                text += "|\t";
            }
            else {
                text += s2.get(i);
                text += "\t";
            }
 
            if (s3.get(i) == 0) {
                text += "|\n";
            }
            else {
                text += s3.get(i);
                text += "\n";
            }
        }
 
        for (int i = numDisk - 1; i >= 0; i--) {
            if (s1.get(i) == 0) {
                s1.pop();
            }
            if (s2.get(i) == 0) {
                s2.pop();
            }
            if (s3.get(i) == 0) {
                s3.pop();
            }
        }
 
        text += "\t==================";
        text += "\n" + "\ts1\ts2\ts3";
        System.out.println(text);
    }
 
    public static void Hanoi(int disk, Stack<Integer> source, Stack<Integer> dest, Stack<Integer> temp) {
        int from;
        int to;
 
        if (source.equals(s1)) {
            from = 1;
        }
        else if (source.equals(s2)) {
            from = 2;
        }
        else {
            from = 3;
        }
 
        if (dest.equals(s1)) {
            to = 1;
        }
        else if (dest.equals(s2)) {
            to = 2;
        }
        else {
            to = 3;
        }
 
        if (disk == 1) {
            step++;
            System.out.println("\nStep " + step + ": Move disk from tower " + from + " to tower " + to);
            dest.push(source.pop());
            display(s1, s2, s3);
        }
        else {
            Hanoi(disk - 1, source, temp, dest);
            step++;
            System.out.println("\nStep " + step + ": Move disk from tower " + from + " to tower " + to);
            dest.push(source.pop());
            display(s1, s2, s3);
            Hanoi(disk - 1, temp, dest, source);
        }
    }
}