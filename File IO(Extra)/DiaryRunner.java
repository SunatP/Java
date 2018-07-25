/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sunat Praphanwong 6088130
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DiaryRunner {

	public static void newDiary(String diaryFilename , String date, String title, String content) throws Exception{
		//CODE HERE
             File File = new File(diaryFilename);
		try {
			if(!File.exists()){ 
                            File file = new File(diaryFilename);
                            try (BufferedWriter BufferWriter = new BufferedWriter(new FileWriter(file,false))) {
                                BufferWriter.write("[diary2.txt]");
                                BufferWriter.newLine();
                                BufferWriter.write("[DATE] : "+date);
                                BufferWriter.newLine();
                                BufferWriter.write("[TITLE] : "+title);
                                BufferWriter.newLine();
                                BufferWriter.write("[CONTENT] : "+content);
                            }
                        } else {
                        }
		} catch (IOException e) {
                 // TODO: handle exception
                 
		}
	}
	
	public static void readContent(String diaryFilename ) throws Exception{
		//CODE HERE
                File content = new File(diaryFilename);
            try (
                    BufferedReader reader = new BufferedReader(new FileReader(content))
                    )
            {
                String line = null;
                while((line = reader.readLine()) != null){
                    System.out.println(line);
                }
            }
	}
	
		
	public static void main(String[] args) {
		
		try{
			//---- TASK1 ----//
			System.out.println("TASK1");
			readContent("diary1.txt"); 
			System.out.println("----------------------------------");

			newDiary("diary2.txt", "01/03/2017", "A wonderful serenity", "A wonderful serenity has taken possession of my entire soul, like these sweet mornings of spring which I enjoy with my whole heart.");
			readContent("diary2.txt"); 
			System.out.println("----------------------------------");
			System.out.println();

			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

