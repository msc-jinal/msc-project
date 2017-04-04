package edu.bv;

import java.io.File;
import java.util.Collection;

import org.wikipathways.client.WikiPathwaysCache;

public class WikiPathwayDownloader {
	
	public static void main(String[] args){
		File file = new File("C://testing");
		try{
			WikiPathwaysCache wikiPathwaysCache = new WikiPathwaysCache(file);
			Collection<File> resultFile = wikiPathwaysCache.update();
			System.out.println(resultFile.size());
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}
