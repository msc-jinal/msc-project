package edu.bv;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bridgedb.Xref;
import org.bridgedb.bio.BioDataSource;
import org.bridgedb.bio.Organism;
import org.pathvisio.core.model.ConverterException;
import org.pathvisio.core.model.Pathway;
import org.pathvisio.core.model.PathwayElement;
import org.pathvisio.core.model.PathwayElement.Comment;
import org.pathvisio.wikipathways.webservice.WSOntologyTerm;
import org.pathvisio.wikipathways.webservice.WSPathway;
import org.pathvisio.wikipathways.webservice.WSPathwayInfo;
import org.wikipathways.client.WikiPathwaysClient;

public class WikiPathwayToDB {
	
	public static void main(String[] args) throws Exception{
		
		URL wsURL = new URL("http://webservice.wikipathways.org");
		WikiPathwaysClient client = new WikiPathwaysClient(wsURL);
		
		WSPathwayInfo[] pathwayArray = client.listPathways(Organism.HomoSapiens);
		
		for(int i=0;i<pathwayArray.length;i++)
		{
			WSPathwayInfo wsPathwayInfo = pathwayArray[i];
			
			String pathwayId = wsPathwayInfo.getId();
			String pathwayUrl = wsPathwayInfo.getUrl();
			boolean isDownloaded = isPathwayDownloaded(pathwayUrl);
			if(isDownloaded==false){
				WSPathway wsPathway =  client.getPathway(pathwayId);
				insertIntoDb(wsPathway);
			}
		}
	}
	
	
	private static void insertIntoDb(WSPathway wsPathway) throws ConverterException{
		String pathwayName = wsPathway.getName();
		String pathwayUrl = wsPathway.getUrl();
		String pathwayDbName = "WikiPathways";
		String pathwayId ="WP_"+wsPathway.getId();
		int total_gene=0;
		int total_protein=0;
		int total_metabolite=0;
		int total_rna=0;
		
		// Converting WSPathway object to Path-Visio object
		Pathway pathway = WikiPathwaysClient.toPathway(wsPathway);
		List<PathwayElement> elementList =  pathway.getDataObjects();
		
		//Empty ArrayList, which used to extract required datanodes which stored in database
		List<PathwayElement> requiredProductList =  new ArrayList<PathwayElement>();
		
		
		for(int i=0;i<elementList.size();i++)
		{
			PathwayElement dataNode = elementList.get(i);
			
			if(dataNode.getDataNodeType().equals("GeneProduct")){
				total_gene++;
				requiredProductList.add(dataNode);
			}else if(dataNode.getDataNodeType().equals("Protein")){
				total_protein++;
				requiredProductList.add(dataNode);
			}else if(dataNode.getDataNodeType().equals("Metabolite")){
				total_metabolite++;
				requiredProductList.add(dataNode);
			}else if(dataNode.getDataNodeType().equals("Rna")){
				total_rna++;
				requiredProductList.add(dataNode);
			}
			
		}
		
	
		// insert main pathway entry into database
		String sql="insert into tlb_pathway(id, name, url, pathway_db_name,total_gene, total_protein,total_metabolite,total_rna) values(?,?,?,?,?,?,?,?)";
		try{
			Connection conn =  DBConnection.createDbConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pathwayId);
			pstmt.setString(2, pathwayName);
			pstmt.setString(3, pathwayUrl);
			pstmt.setString(4, pathwayDbName);
			pstmt.setInt(5, total_gene);
			pstmt.setInt(6, total_protein);
			pstmt.setInt(7, total_metabolite);
			pstmt.setInt(8, total_rna);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		// insert all datanodes for given pathway
		for(int i=0;i<requiredProductList.size();i++)
		{
			PathwayElement dataNode = requiredProductList.get(i);
			String nodeType = dataNode.getDataNodeType();
			String nodeLabel = dataNode.getTextLabel();
			String nodeGeneId = dataNode.getGeneID();
			String nodeDatasource="";
			if(dataNode.getDataSource()!=null){
				nodeDatasource = dataNode.getDataSource().toString();
			}
			
			//Prepare query to insert Data nodes into db.
			String geneSql="insert into tlb_pathway_datanode(pathway_id,product_type,product_name,gene_id, datasource) values(?,?,?,?,?)";
			try{
				Connection conn =  DBConnection.createDbConnection();
				PreparedStatement pstmt = conn.prepareStatement(geneSql);
				pstmt.setString(1, pathwayId);
				pstmt.setString(2, nodeType);
				pstmt.setString(3, nodeLabel);
				pstmt.setString(4, nodeGeneId);
				pstmt.setString(5, nodeDatasource);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}	
		}	
	}

	
	private static boolean isPathwayDownloaded(String pathwayUrl){
		
		boolean exist = false; 
		String sql="select * from tlb_pathway where url='"+pathwayUrl+"'";
		try
		{
			Connection conn =  DBConnection.createDbConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				exist =  true;
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return exist;
	}
	
}
