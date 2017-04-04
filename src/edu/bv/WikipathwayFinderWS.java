package edu.bv;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bridgedb.Xref;
import org.bridgedb.bio.BioDataSource;
import org.bridgedb.bio.Organism;
import org.pathvisio.core.debug.Logger;
import org.pathvisio.core.model.ConverterException;
import org.pathvisio.core.util.FileUtils;
import org.pathvisio.wikipathways.webservice.WSPathwayInfo;
import org.pathvisio.wikipathways.webservice.WSSearchResult;
import org.wikipathways.client.WikiPathwaysClient;

public class WikipathwayFinderWS {

	public static HashMap dataExtract(ArrayList<String> geneRecords){
		HashMap<String, ArrayList> hm = new HashMap();
		try{
			URL wsURL = new URL("http://webservice.wikipathways.org");
			WikiPathwaysClient client = new WikiPathwaysClient(wsURL);
			//ArrayList<String[]> geneRecords = geneFinder.findGeneByTissue("artery",5);
			
			//ArrayList<String[]> resultData = new ArrayList<String[]>(); 
			//int cutoff=5;
			ArrayList<Xref> list = new ArrayList<>();
			int batchSize=0;
			for(int i=1;i<geneRecords.size();i++)
			{
				
				String geneRecord = geneRecords.get(i);
		
				Xref ref1 = new Xref(geneRecord, BioDataSource.ENSEMBL);
				list.add(ref1);
				batchSize++;
				if(batchSize==250 || i==geneRecords.size())
				{
					Xref[] listArray = list.toArray(new Xref[0]);
					
					WSSearchResult[] results = client.findPathwaysByXref(listArray);
					for(int j=1;j<results.length;j++){
						String id = results[j].getId();
						String name = results[j].getName();
						String url = results[j].getUrl();
						
						ArrayList arrayList = new ArrayList();
						arrayList.add(name);
						arrayList.add(url);
						hm.put(id, arrayList);
						
					}
					batchSize=0;
				}
				
				
			}
						
			/*Xref[] listArray = list.toArray(new Xref[0]);
						
			WSSearchResult[] results = client.findPathwaysByXref(listArray);
				
			for(int i=0;i<results.length;i++){
			
				//System.out.println(results[i].getName()+"  "+results[i].getId());
				//System.out.println(results[i].getUrl());
				hm.put(results[i].getId(), results[i].getName());
				
			}*/
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return hm;	
	}
	
	public HashMap listAllPathways(){
		HashMap<String, ArrayList> hm = new HashMap();
		try{
			URL wsURL = new URL("http://webservice.wikipathways.org");
			WikiPathwaysClient client = new WikiPathwaysClient(wsURL);

			WSPathwayInfo[] results = client.listPathways(Organism.HomoSapiens);
			for(int j=1;j<results.length;j++){
				String id = results[j].getId();
				String name = results[j].getName();
				String url = results[j].getUrl();
				
				ArrayList arrayList = new ArrayList();
				arrayList.add(name);
				arrayList.add(url);
				hm.put(id, arrayList);
				
			}
		}catch(Exception ex){
			System.out.println("Unable to get all pathways from WikiPathwasy");
			ex.printStackTrace();
		}
		return hm;
	}
	
	public static HashMap dataExtractFromCache(ArrayList<String> geneRecords){
		HashMap<String, ArrayList> hm = new HashMap();
		return hm;
	}
}
