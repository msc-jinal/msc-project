<%@ page language="java"%>
<%@ page import="edu.bv.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*" %>
<html>
<head>
		<style>
		
		body, html{
			font-family: "Segoe UI", Helvetica, Arial, sans-serif;
			font-size:10pt;
		}
		
		th, td {  
			padding: 2px;  
			text-align: left;
			vertical-align: top;
		} 
		
		.listdiv {
		  border: #808080 1px dotted;
		  padding:0px;
		  display:block;
		  overflow:auto;
		  height:340px;
		  width:100%;
		}
		
		.border-dotted{
			border: #808080 1px dotted;
		}
		
		table.listTable {
			font-size:13px;
			text-align: left;
			vertical-align: top;
			width:100%;
		}
		
        .listTable tr:hover  {background-color: #B0C4DE}
        .listTable th, .listTable td {
    		border-bottom: 1px solid #ddd;
    		padding-top: 5px;
    		padding-bottom: 5px;
		}
        
        .button {
		    background-color: 008CBA; /* Green */
		    border: none;
		    color: white;
		    padding: 5px 10px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
			font-size: 12px;
		}
		
		
		
		.listbox{
			font-size: 12px;
			padding: 2px 0px;
		}
		
		.text1{
			font-size:12px;
		}

		</style>  
	<script>
	
		function filterByGene(chkboxName) {
		  var checkboxes = document.getElementsByName('geneName');
		  var selectedGenes = '';
		  // loop over them all
		  for (var i=0; i<checkboxes.length; i++) {
		     // And stick the checked ones onto an array...
		     if(selectedGenes!=''){
		    	 selectedGenes = selectedGenes +",";
		     }
		     if (checkboxes[i].checked) {
		    	 selectedGenes = selectedGenes + checkboxes[i].value;
		     }
		  }
		  document.getElementById("selectedGenes").value=selectedGenes;
		  document.getElementById("geneForm").submit();
		}

	</script>	
</head>
<body>
<span style="color:blue;">
<H2>Gene-Pathway Analysis</center></H2>
</span>
<% 
	String tissueName= request.getParameter("tissueOption");
	String userSelectedGenes= request.getParameter("selectedGenes");
	String cutoffOption= request.getParameter("cutoffOption");
	ArrayList<String[]> geneRecords = new ArrayList<String[]>();
	ArrayList<ArrayList<String>> pathwayUrlList = new ArrayList<ArrayList<String>>();
	
	ArrayList<String> userSelectedGeneList =null;
	if(tissueName != null && userSelectedGenes ==null)
	{
		int cutoffValue=Integer.parseInt(cutoffOption);
		
		GeneFinder geneFinder = new GeneFinder();
		geneRecords = geneFinder.findGeneByTissue(tissueName,cutoffValue);
		
		ArrayList<String> geneNameList = new ArrayList<String>();
		for(int i=0;i<geneRecords.size();i++)
		{
			String[] tempStringRecord = geneRecords.get(i);
			geneNameList.add(tempStringRecord[1]);
			
		}
		WikiPathwayFinder  wikiPathwayFinder = new WikiPathwayFinder();
		pathwayUrlList = wikiPathwayFinder.pathwayListByGenes(geneNameList);
	}else if (tissueName != null && userSelectedGenes!=null)
	{
		int cutoffValue=Integer.parseInt(cutoffOption);
		
		GeneFinder geneFinder = new GeneFinder();
		geneRecords = geneFinder.findGeneByTissue(tissueName,cutoffValue);
		
		userSelectedGeneList = new ArrayList<String>(Arrays.asList(userSelectedGenes.split(",")));
		WikiPathwayFinder  wikiPathwayFinder = new WikiPathwayFinder();
		pathwayUrlList = wikiPathwayFinder.pathwayListByGenes(userSelectedGeneList);
	}
	
%>
<table>
	<tr>
		<td>
			<form action='index.jsp' method="post">
				<table class="border-dotted">
					<tr>
						<td colspan="4">Select your criteria to Search list of Genes</td>
					</tr>
					<tr></tr>
					<tr>
						<td>Species: </td>
						<td>Dataset: </td>
						<td>Tissue: </td>
						<td>CutOff: </td>
						<td></td>
					</tr>
					<tr>
						<td>
							<select name="speciesOption" class="listbox">
							<option value="human">Human</option>
							</select>
						
						</td>
						<td>
							<select name="datasetOption" class="listbox">
							<option value="E-MTAB-2836">E-MTAB-2836</option>
							<option value="E-MTAB-2919">E-MTAB-2919</option>
							<option value="E-MTAB-3358">E-MTAB-3358</option>
							</select>
						
						</td>
						<td>
							<select name="tissueOption" class="listbox">
							<option value="amygdala">amygdala</option>
							<option value="appendix">appendix</option>
							<option value="artery">artery</option>
							<option value="bone marrow">bone marrow</option>
							<option value="bladder">bladder</option>
							<option value="brain">brain</option>
							<option value="breast">breast</option>
							<option value="caudate nucleus">caudate nucleus</option>
							<option value="cerebellum">cerebellum</option>
							<option value="cerebral meninges">cerebral meninges</option>
							<option value="cervix">cervix</option>
							<option value="colon">colon</option>
							<option value="diencephalon">diencephalon</option>
							<option value="dura mater">dura mater</option>
							<option value="epididymis">epididymis</option>
							<option value="gallbladder">gallbladder</option>
							<option value="globus pallidus">globus pallidus</option>
							<option value="heart">heart</option>
							<option value="hippocampus">hippocampus</option>
							<option value="kidney">kidney</option>
							<option value="left atrium">left atrium</option>
							<option value="left ventricle">left ventricle</option>
							<option value="locus coeruleus">locus coeruleus</option>
							<option value="lung">lung</option>
							<option value="lymph node">lymph node</option>
							<option value="medulla oblongata">medulla oblongata</option>
							<option value="middle frontal gyrus">middle frontal gyrus</option>
							<option value="middle temporal gyrus">middle temporal gyrus</option>
							<option value="mitral valve">mitral valve</option>
							<option value="occipital cortex">occipital cortex</option>
							<option value="occipital lobe">occipital lobe</option>
							<option value="olfactory apparatus">olfactory apparatus</option>
							<option value="occipital lobe">occipital lobe</option>
							<option value="olfactory apparatus">olfactory apparatus</option>
							<option value="ovary">ovary</option>
							<option value="pancreas">pancreas</option>
							<option value="parietal lobe">parietal lobe</option>
							<option value="parotid gland">parotid gland</option>
							<option value="penis">penis</option>
							<option value="pineal gland">pineal gland</option>
							<option value="pituitary gland">pituitary gland</option>
							<option value="placenta">placenta</option>
							<option value="prostate">prostate</option>
							<option value="pulmonary valve">pulmonary valve</option>
							<option value="putamen">putamen</option>
							<option value="seminal vesicle">seminal vesicle</option>
							<option value="skin">skin</option>
							<option value="smooth muscle">smooth muscle</option>
							<option value="spinal cord">spinal cord</option>
							<option value="spleen">spleen</option>
							<option value="submandibular gland">submandibular gland</option>
							<option value="substantia nigra">substantia nigra</option>
							<option value="testis">testis</option>
							<option value="thalamus">thalamus</option>
							<option value="tongue">tongue</option>
							<option value="triscuspid valve">triscuspid valve</option>
							<option value="uterus">uterus</option>
							<option value="vagina">vagina</option>
							<option value="vas deferens">vas deferens</option>
							</select>
						
						</td>
						<td>
							<select name="cutoffOption" class="listbox" style="width:50px;">
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
							</select>
						</td>
						<td><input type='submit' value="Search Genes" class="button"/></td>
					</tr>
				</table>	
			</form>
		</td>
		<td>
			<%
			if(tissueName != null){
			%>
				<span style="color:blue;">Tissue: <% out.println(tissueName); %></span>
			<%} %>
		</td>
	</tr>
</table>
<%
if(tissueName != null){
%>
	<table width="100%">
		<tr>
			<td>Gene List</td>
			<td>Pathway List</td>
		</tr>
		<tr>
			<td style="width:12%;"><%@ include file="mainGeneListTable.jsp" %></td>
			<td style="width:88%;"><%@ include file="mainPathwayTable.jsp" %></td>
		</tr>
		<tr>
			<td class="text1">Total Genes: <b><% out.println(geneRecords.size()); %></b></td>
			<td class="text1">Total Pathways: <b><% out.println(pathwayUrlList.size()); %></b></td>
		</tr>
		<tr>
			<td><input type='submit' value="Filter Pathways" class="button" style="width:160px;" onclick="javascript:filterByGene()"/></td>
			<td></td>
		</tr>	
	</table>
<%
}
 %>
</body>
</html>