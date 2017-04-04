package edu.bv;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bridgedb.Xref;
import org.bridgedb.bio.BioDataSource;
import org.pathvisio.wikipathways.webservice.WSSearchResult;
import org.wikipathways.client.WikiPathwaysClient;

public class WikiPathwayFinderWSTest {

	public static void main(String args[]){
		
		WikipathwayFinderWS wikiPathwaysFinder = new WikipathwayFinderWS();
		GeneFinder geneFinder = new GeneFinder();
		ArrayList<String[]> geneRecords = geneFinder.findGeneByTissue("bladder",4);
		ArrayList<String> geneDataRecords = new ArrayList<String>();
		
		
		for(int i=0;i<geneRecords.size();i++)
		{
			String[] firstRecord = geneRecords.get(i);
			geneDataRecords.add(firstRecord[0]);
		}
		/*
		for(int j=0;j<geneDataRecords.size();j++)
		{
			System.out.println(geneDataRecords.get(j));
		}
		*/
		
		/*ArrayList<String> geneRcd = new ArrayList<String>();
		geneRcd.add("ENSG00000131828");
		geneRcd.add("ENSG00000131791");
		
		for(int k=0;k<geneRcd.size();k++)
		{
			System.out.println(geneRcd.get(k));
		}*/
		
		
		HashMap hm = wikiPathwaysFinder.dataExtract(geneDataRecords);
		Set<Map.Entry> set = hm.entrySet();
		Iterator<Map.Entry> i = set.iterator();
		while(i.hasNext()) {
	         Map.Entry me = i.next();
	         System.out.print(me.getKey() + ": ");
	         ArrayList<String> arrayList = (ArrayList<String>)me.getValue();
	         System.out.println(arrayList.get(0));
	         System.out.println(arrayList.get(1));
	    }
	      
	}
	
}
