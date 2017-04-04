package edu.bv;

import java.io.*;
import java.util.*;

public class GeneRecord{

	public static void main(String[] args){
	
		try{
			
			FileInputStream is = new FileInputStream("3315Express_dataset.csv"); 
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr); 
			String line =null;
			line = br.readLine();
			String[] data = line.split(",");
			int count=0;
			for(String w:data)
			{
				count++;
			}
			//System.out.println("---------"+count);
			String tissue="gallbladder"; 
								
			int j=0;
			int no=0;

			for (int i = 2; i < count; i++) 
			{ 
				String[] value = line.split(","); 
				if(tissue.equals(value[i]))
				{
					no=i;				
				}
				
			}
			//System.out.println("-------------------------"+no);
			ArrayList<String[]> club = new ArrayList<String[]>(); 
			 
			while(line!=null)
			{
				String[] value = line.split(",",-1);
				
				try{
					String[] temp = new String[3];
					temp[0]=value[0];
					temp[1]=value[1];
					temp[2]=value[no];
					club.add(temp);
				
					line = br.readLine();
					j++;
				}catch(Exception ex){
					System.out.println(Arrays.toString(value));
					break;
				}
							
			}
			 for(int k=0;k<10;k++)
			 {
				 String[] temp = club.get(k);
				 System.out.println(Arrays.toString(temp));
			 }
				
		}catch(Exception ex){System.out.println(ex);}
	
	
	}


}