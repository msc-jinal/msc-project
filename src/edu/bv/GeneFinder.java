package edu.bv;
import java.util.*;
import java.io.*;

public class GeneFinder{

	
	public static ArrayList<String[]> findGeneByTissue(String tissue,int cutoff){
		
		GeneReader geneDataReader = new GeneReader();
		ArrayList<String[]> geneDataRecord = geneDataReader.getGeneData();
		//**** Data Records ****
		//Gene ID,Gene Name,amygdala,appendix,artery,....
		//ENSG00000000003,TSPAN6,,1,,8,......
	
		
		// All Headings
		String[] headerLineArray = geneDataRecord.get(0);
		
		// Finding Matching column for tissue
		int matchingColNo=0;
		for(int i=0;i<headerLineArray.length;i++)
		{
			if(tissue.equals(headerLineArray[i]))
			{
				matchingColNo=i;				
			}
		}
		
		
		//Stores all final data for tissue
		ArrayList<String[]> resultData = new ArrayList<String[]>(); 
		
		// If matchingColNo=0 means there is no gene available for selected Tissue name in selected DataSet
		if(matchingColNo==0)
		{
			return resultData;
		}
		
		//int cutoff=5;
		for(int i=1;i<geneDataRecord.size();i++)
		{
			String[] firstRecord = geneDataRecord.get(i);
			String[] temp = new String[3];

			if(!firstRecord[matchingColNo].equals(""))
			{
				float tempValue=Float.parseFloat(firstRecord[matchingColNo]); 

				if(tempValue >= cutoff)
				{
					temp[0]=firstRecord[0];
					temp[1]=firstRecord[1];
					temp[2]=firstRecord[matchingColNo];
					resultData.add(temp);
					
				}

			}

		}
		
		return resultData;
	}


}