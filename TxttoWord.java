package com.rci.migration.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TxttoWord {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the folder path : ");
		String directoryPath = sc.nextLine();
		List<String> fileList = readFolder(directoryPath);
		
		FileWriter writer = new FileWriter("C:\\CognizantFiles\\poms"+".doc", true);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		int i=0;
		for(String path : fileList) {
			System.out.println(i + path);
			writer.write(i + "Java File " +path);
			writer.write("\n");
			//writer.newLine();
			readFile(path,writer);
			
			
			i++;
		}
		writer.close();
		System.out.println("Done");
		sc.close();

	}

	private static void readFile(String path, FileWriter writer) {
		 try {
	            FileReader reader = new FileReader(path);
	            FileInputStream inputStream = new FileInputStream(path);
	            BufferedReader bufferedReader = new BufferedReader(reader);
	            
	            StringBuilder str = new StringBuilder();
	            String line;
	 
	            InputStreamReader inputreader = new InputStreamReader(inputStream, "UTF-16");
	            int character;
	 
	            while ((character = reader.read()) != -1) {
	                //System.out.print((char) character);
	            	str.append((char) character);
	            }
	            System.out.println(str);
	            writer.write(str.toString());
//	            while ((line = bufferedReader.readLine()) != null) {
//	               // str.append(line);
//	                bufferedWriter.write(line);
//	            }
	          //  System.out.println(str);
	            reader.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}

	private static List<String> readFolder(String directoryPath) {
		List<String> result = new ArrayList<String>();
        try (Stream<Path> walk = Files.walk(Paths.get(directoryPath))) {

            result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".xml")).collect(Collectors.toList());


            //result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return result;
	}

}
