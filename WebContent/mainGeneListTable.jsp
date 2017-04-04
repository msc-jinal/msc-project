<%@ page language="java"%>
<%@ page import="edu.bv.business.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*" %>


<div class="listdiv"> 
<table class="listTable" cellspacing="0">
	<form id="geneForm" action="index.jsp" method="post">
		<input type="hidden" name="tissueOption" id="tissueOption" value="<%=tissueName%>" />
		<input type="hidden" name="cutoffOption" id="cutoffOption" value="<%=cutoffOption%>" />
		
		<input type="hidden" name="selectedGenes" id="selectedGenes" value="" />
		<%
			for(int j=0;j<geneRecords.size();j++)
			{
				String[] temp = geneRecords.get(j);
				String geneName = temp[1];
				out.println("<tr>");	
				if(userSelectedGeneList!=null && userSelectedGeneList.contains(geneName)){
					out.println("<td><input name='geneName' type='checkbox' checked='true' value='"+temp[1]+"' /></td>");
				}else{
					out.println("<td><input name='geneName' type='checkbox' value='"+temp[1]+"' /></td>");	
				}
				
				out.println("<td>"+geneName+"</td>");
				out.println("<td>"+temp[2]+"</td>");
				out.println("</tr>");	
				
			}	
		%>
	</form>
</table>
</div>