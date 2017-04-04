<%@ page language="java"%>
<%@ page import="edu.bv.business.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*" %>



<div class="listdiv"> 
<table class="listTable" cellspacing="0">
	<%
	out.println("<tr>");
	out.println("<th width='10%'>Database Name</th>");
	out.println("<th width='35%'>Pathway Name</th>");
	out.println("<th width='14%'>Genes</th>");
	out.println("<th width='14%'>Protein</th>");
	out.println("<th width='14%'>Metabolite</th>");
	out.println("<th width='8%'>Rna</th>");
	out.println("</tr>");
	for(int l=0;l<pathwayUrlList.size();l++)
	{
		ArrayList<String> pathwayRecord = pathwayUrlList.get(l);
		out.println("<tr>");
		out.println("<td>"+pathwayRecord.get(0)+"</td>");
		out.println("<td><a href="+pathwayRecord.get(2)+" target='blank'>"+pathwayRecord.get(1)+"</a></td>");
		out.println("<td>"+pathwayRecord.get(3)+"</td>");
		out.println("<td>"+pathwayRecord.get(4)+"</td>");
		out.println("<td>"+pathwayRecord.get(5)+"</td>");
		out.println("<td>"+pathwayRecord.get(6)+"</td>");
		out.println("</tr>");
	}

	%>
</table>
 </div>