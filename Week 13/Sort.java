
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer-
 */
public class Sort {
    public static ArrayList<String> myarr = new ArrayList<String>();
    public static void main(String[] args)
    {

            method1("text.txt");
            method2();
            method3();
            System.out.print("\n");
            bonus();
    }

    public static ArrayList<String> method1(String file)
    {       String line = null;
        int n=0;
        int j=0;

    String Line;

            try {
//                FileReader fileReader = new FileReader(file);
//                BufferedReader buff = new BufferedReader(fileReader);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            //                FileReader fileReader = new FileReader(file);
//                BufferedReader buff = new BufferedReader(fileReader);
while ((line = br.readLine())!=null)
{
    Line=line;
    String p="([a-zA-Z]+)";
    
    Pattern pattern = Pattern.compile(p);
    Matcher m =pattern.matcher(line);
    
    while(m.find()==true)
        
    {
        String string = m.group(0);   
        myarr.add(string);
        
    }

}
        }
            }
            catch(FileNotFoundException ex)
            {
                System.out.println("unable to open file"+file);
                ex.printStackTrace();

            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return myarr;
    }
    public static void method2()
    {
            for(int i=0;i<myarr.size(); )
            {
                System.out.print(myarr.get(i)+" ");
               i++;
            }
            System.out.print("\n");


    }
    public static void method3() //call word from file
    {
            for(int u=0;u<8;u++) 
            for(int y=0;y<12;y++) {
                {

                    if (myarr.get(y).charAt(0) < myarr.get(y + 1).charAt(0)) {
                        //int n = y + 1;
                        String temp = myarr.get(y);

                        myarr.set(y, myarr.get(y + 1));


                        myarr.set(y + 1, temp);



                    } else if (myarr.get(y).charAt(0) == myarr.get(y + 1).charAt(0)) {
                        if (myarr.get(y).compareTo(myarr.get(y + 1)) < 0) {
                            String temp = myarr.get(y);

                            myarr.set(y, myarr.get(y + 1));


                            myarr.set(y + 1, temp);


                        }

                    }
                    System.out.println(" ");

                    method2();
                    
                }

            }
    }
    public static void bonus()
    {
        int[] count = new int[13];
            for(int i=0;i<13;i++)
            {       //int k=0;

                for(int j=0;j<13;j++)
                {


                    if(myarr.get(i).equals(myarr.get(j)))
                    {


                            count[i]++;


                    }


                }
            }

                for(int i=0;i<13;i++)
                {
                        for(int j=0;j<12;j++)
                        {
                                if(count[i]>count[j])
                                {
                                    int temp =count[i];
                                    count[i] = count[j];
                                    count[j] = temp;

                                    String tempp = myarr.get(i);

                                    myarr.set(i, myarr.get(j));


                                    myarr.set(j, tempp);


                                }
                        }
                }
                for(int i=1;i<=10;i++)
                {
                    System.out.println("Word "+myarr.get(i)+" Frequency is "+count[i]);


                }

    }
}


