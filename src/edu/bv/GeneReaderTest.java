package edu.bv;
import java.io.*;
import java.util.*;


public class GeneReaderTest{


	public static void main(String[] args){
		GeneReader geneDataReader = new GeneReader();
		
		//File file= new File("2919Express_dataset.csv");
		ArrayList<String[]>  myArray = geneDataReader.getGeneData();
		
		for(int k=0;k<5;k++)
		{
			String[] temp = myArray.get(k);
			System.out.println(Arrays.toString(temp));
		}
		
	}
}