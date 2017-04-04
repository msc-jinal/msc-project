package edu.bv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WikiPathwayFinder {

private ArrayList<String> getPathwaysFromFile(){
		
		ArrayList<String> fileDataList = new ArrayList<>();
		try{
			
			File file = loadFileFromClassPath();
			FileInputStream is = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr); 
			String line =null;			
			line = br.readLine();
			//System.out.println("File successfully found.");
			while(line!=null)
			{
				String[] tempString = line.split("#");
				if(tempString.length >3)
				{
					fileDataList.add(line);
				}
				line = br.readLine();
			}
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return fileDataList;
		
	}


	private File loadFileFromClassPath(){
		ClassLoader classLoader = GeneReader.class.getClassLoader();
		File file = new File(classLoader.getResource("WikiPathwaysData.csv").getFile());
		return file;
	}

	public ArrayList<ArrayList<String>> pathwayListByGenes(ArrayList<String> geneNameList)
	{
		ArrayList<ArrayList<String>> pathwayList = new ArrayList<ArrayList<String>>();
		ArrayList<String> pathwaysRecords = getPathwaysFromFile();
		
		String commaSepratedGenes=null;
		for(int j=0;j<geneNameList.size();j++)
		{
			String geneName = "'"+geneNameList.get(j)+"'";
			if(commaSepratedGenes!=null){
				commaSepratedGenes =  commaSepratedGenes+","+geneName;
			}else{
				commaSepratedGenes=geneName;
			}
		}
			
		String geneSql="select distinct pathway_id from tlb_pathway_datanode where product_type='GeneProduct' and product_name in("+commaSepratedGenes+")";
		String commaSepratedPathwayIds=null;
		try{
			Connection conn =  DBConnection.createDbConnection();
			PreparedStatement pstmt = conn.prepareStatement(geneSql);
			System.out.println(commaSepratedGenes);
			//pstmt.setString(1, commaSepratedGenes);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				String pathwayId = rs.getString(1);
				pathwayId = "'"+pathwayId+"'";
				if(commaSepratedPathwayIds!=null){
					commaSepratedPathwayIds =  commaSepratedPathwayIds+"," + pathwayId;
				}else{
					commaSepratedPathwayIds = pathwayId.toString();
				}

			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		
		
		String pathwaySql="select * from tlb_pathway where id in ("+commaSepratedPathwayIds+")";
		try{
			Connection conn =  DBConnection.createDbConnection();
			PreparedStatement pstmt = conn.prepareStatement(pathwaySql);
			//pstmt.setString(1, commaSepratedPathwayIds);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				ArrayList<String> pathway = new ArrayList<String>();
				pathway.add(rs.getString("pathway_db_name"));
				pathway.add(rs.getString("name"));
				pathway.add(rs.getString("url"));
				pathway.add(rs.getString("total_gene"));
				pathway.add(rs.getString("total_protein"));
				pathway.add(rs.getString("total_metabolite"));
				pathway.add(rs.getString("total_rna"));
				pathwayList.add(pathway);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	
		return pathwayList;
	}

	
}
