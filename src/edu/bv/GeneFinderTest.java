package edu.bv;
import java.util.*;
import java.io.*;

public class GeneFinderTest{

	public static void main(String[] args){
		
		GeneFinder geneFinder = new GeneFinder();
		//String str = "artery";
		ArrayList<String[]> geneReord = geneFinder.findGeneByTissue("bladder",4);
		
		for(int k=0;k<50;k++)
		{
			String[] temp = geneReord.get(k);
			System.out.println(Arrays.toString(temp));
		}
		
	}


}