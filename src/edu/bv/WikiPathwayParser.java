package edu.bv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiPathwayParser {

	
	
	String wikiPathwayString;
	
	public WikiPathwayParser(String wikiPathwayString){
		this.wikiPathwayString = wikiPathwayString;
	}

	private ArrayList<String> getGeneDataNodes(String fileString)
	{
		ArrayList<String> list = new ArrayList<>();
		String pattern = "<DataNode(.*?)</DataNode>";
		Pattern pr = Pattern.compile(pattern);
		Matcher m = pr.matcher(fileString);
		while(m.find()) 
		{
			// System.out.print("Found value: " + m.group(0) );
			// System.out.println();
			// System.out.println();
			String dataNode=m.group(0);
			if(dataNode.contains("Type=\"GeneProduct\""))
			{
				list.add(dataNode);
			}
								
		}
		return list;	 
	}
	
	private ArrayList<String> getMetaboliteDataNodes(String fileString)
	{
		ArrayList<String> list = new ArrayList<>();
		String pattern = "<DataNode(.*?)</DataNode>";
		Pattern pr = Pattern.compile(pattern);
		Matcher m = pr.matcher(fileString);
		while(m.find()) 
		{
			// System.out.print("Found value: " + m.group(0) );
			// System.out.println();
			// System.out.println();
			String dataNode=m.group(0);
			if(dataNode.contains("Type=\"Metabolite\""))
			{
				list.add(dataNode);
			}
								
		}
		return list;	 
	}
	
	private ArrayList<String> getProteinDataNodes(String fileString)
	{
		ArrayList<String> list = new ArrayList<>();
		String pattern = "<DataNode(.*?)</DataNode>";
		Pattern pr = Pattern.compile(pattern);
		Matcher m = pr.matcher(fileString);
		while(m.find()) 
		{
			// System.out.print("Found value: " + m.group(0) );
			// System.out.println();
			// System.out.println();
			String dataNode=m.group(0);
			if(dataNode.contains("Type=\"Protein\""))
			{
				list.add(dataNode);
			}
								
		}
		return list;	 
	}
	
	public ArrayList<String> getMetaboliteList(){
		String fileString = this.wikiPathwayString;	
		ArrayList<String> metaboliteDataNodes = getMetaboliteDataNodes(fileString);

		String pattern = "TextLabel=\"(.*?)\" GraphId";
		Pattern pr = Pattern.compile(pattern);

		ArrayList<String> metaboliteList = new ArrayList<>();
		for(int i=0;i<metaboliteDataNodes.size();i++){
			String dataNode =  metaboliteDataNodes.get(i);	
			Matcher m = pr.matcher(dataNode);
			while(m.find()){
				String geneId = m.group(1);
				//geneId = geneId.substring(1, geneId.length()-2);
				metaboliteList.add(geneId);
			}
		}
		return metaboliteList;
	}
	
	public ArrayList<String> getGeneList(){
		String fileString = this.wikiPathwayString;	
		ArrayList<String> geneDataNodes = getGeneDataNodes(fileString);

		String pattern = "TextLabel=\"(.*?)\" GraphId";
		Pattern pr = Pattern.compile(pattern);

		ArrayList<String> geneList = new ArrayList<>();
		for(int i=0;i<geneDataNodes.size();i++){
			String dataNode =  geneDataNodes.get(i);	
			Matcher m = pr.matcher(dataNode);
			while(m.find()){
				String geneId = m.group(1);
				//geneId = geneId.substring(1, geneId.length()-2);
				geneList.add(geneId);
			}
		}
		return geneList;
	}
	
	public ArrayList<String> getProteinList(){
		String fileString = this.wikiPathwayString;		
		ArrayList<String> proteinDataNodes = getProteinDataNodes(fileString);

		String pattern = "TextLabel=\"(.*?)\" GraphId";
		Pattern pr = Pattern.compile(pattern);

		ArrayList<String> proteinList = new ArrayList<>();
		for(int i=0;i<proteinDataNodes.size();i++){
			String dataNode =  proteinDataNodes.get(i);	
			Matcher m = pr.matcher(dataNode);
			while(m.find()){
				String geneId = m.group(1);
				//geneId = geneId.substring(1, geneId.length()-2);
				proteinList.add(geneId);
			}
		}
		return proteinList;
	}
	
	


}