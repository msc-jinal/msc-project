package edu.bv;

import java.util.ArrayList;

public class WikiPathwayFinderTest {

public static void main(String[] args){
		
		GeneFinder geneFinder = new GeneFinder();
		
		ArrayList<String[]> geneRecords = geneFinder.findGeneByTissue("bladder",6);
		System.out.println("Total Number of Genes : "+geneRecords.size());
		
		ArrayList<String> geneNameList = new ArrayList<>();
		for(int i=0;i<geneRecords.size();i++)
		{
			String[] tempStringRecord = geneRecords.get(i);
			geneNameList.add(tempStringRecord[1]);
			
		}
		
		WikiPathwayFinder wikiPathwayReader = new WikiPathwayFinder();
		ArrayList<ArrayList<String>> pathwayUrlList = wikiPathwayReader.pathwayListByGenes(geneNameList);
		for(int l=0;l<pathwayUrlList.size();l++)
		{
			ArrayList<String> pathwayRecord = pathwayUrlList.get(l);
			System.out.println(pathwayRecord.get(2));
		}
		System.out.println(pathwayUrlList.size());
		
	}
}
