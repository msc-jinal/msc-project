package edu.bv;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bridgedb.Xref;
import org.bridgedb.bio.BioDataSource;
import org.pathvisio.wikipathways.webservice.WSPathwayInfo;
import org.pathvisio.wikipathways.webservice.WSSearchResult;
import org.wikipathways.client.WikiPathwaysClient;



public class WikipathwayClientTest {
	
	
	public static void main(String args[]){
		
		try{
			URL wsURL = new URL("http://webservice.wikipathways.org");
			WikiPathwaysClient client = new WikiPathwaysClient(wsURL);
			Xref ref1 = new Xref("ENSG00000010704", BioDataSource.ENSEMBL);
			//Xref ref2 = new Xref("ENSG00000131791", BioDataSource.ENSEMBL_HUMAN);
			
			ArrayList<Xref> list = new ArrayList<>();
			list.add(ref1);
			//list.add(ref2);
			
			Xref[] listArray = list.toArray(new Xref[0]);
					
			WSSearchResult[] results = client.findPathwaysByXref(listArray);
			
			HashMap hm = new HashMap();
			
			for(int i=0;i<results.length;i++){
			
				//System.out.println(results[i].getName()+"  "+results[i].getId());
				//System.out.println(results[i].getUrl());
				hm.put(results[i].getId(), results[i].getName());
				
			}
						
			// Get a set of the entries
		      Set<Map.Entry> set = hm.entrySet();
		      
		      // Get an iterator
		      Iterator<Map.Entry> i = set.iterator();
		      
		      // Display elements
		      while(i.hasNext()) {
		         Map.Entry me = i.next();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		      }
			
			
			System.out.println("End");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
	}

}
