package edu.bv;
import java.io.*;
import java.util.*;


public class GeneReader{
	
	public ArrayList<String[]> getGeneData(){
		ArrayList<String[]> myList = new ArrayList<>();
		try{
			
			//FileInputStream is = new FileInputStream(file); 
			//FileInputStream is = new FileInputStream("3358Express_dataset.csv"); 
			//FileInputStream is = new FileInputStream("C://apache-tomcat-7.0.73/webapps/geneExpress/WEB-INF/classes/2836Express_dataset.csv"); 
			
			File file = loadFileFromClassPath();
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr); 
			String line =null;
			
			line = br.readLine();		 
			while(line != null)
			{
				String[] value = line.split(",",-1);
				myList.add(value);
				line = br.readLine();
											
			}
			 					
		}catch(Exception ex){
			System.out.println("Exception while reading data from File:"+ex.getMessage());
		}
		return myList;
	}
	
	private File loadFileFromClassPath(){
		ClassLoader classLoader = GeneReader.class.getClassLoader();
		File file = new File(classLoader.getResource("2836Express_dataset.csv").getFile());
		return file;
	}

}