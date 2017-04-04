package edu.gpa.biopax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.biopax.paxtools.io.BioPAXIOHandler;
import org.biopax.paxtools.io.SimpleIOHandler;
import org.biopax.paxtools.model.BioPAXElement;
import org.biopax.paxtools.model.BioPAXFactory;
import org.biopax.paxtools.model.BioPAXLevel;
import org.biopax.paxtools.model.Model;
import org.biopax.paxtools.model.level3.Gene;
import org.biopax.paxtools.model.level3.Protein;

public class BioPaxTest {
	
	public static void main(String args[]) throws FileNotFoundException{
		createTestModel();
	}
	
	public static void createTestModel()throws FileNotFoundException{
		
		BioPAXFactory factory =  BioPAXLevel.L3.getDefaultFactory();
		Model model = factory.createModel();
		
		model.setXmlBase("http://biopax.org/tutorial/");
		
		Protein protein1 = model.addNew(Protein.class, "http://biopax.org/tutorial/test1");
		protein1.addName("Test Protein");
		Gene gene1 = model.addNew(Gene.class, "http://biopax.org/tutorial/test2");
		gene1.addName("GeneName");
		
		
		
		
		
		BioPAXIOHandler handler = new SimpleIOHandler();
		
		OutputStream outputStream = new FileOutputStream(new File("C:/biopax/test.owl"));
		
		handler.convertToOWL(model, outputStream);

	}
	
	
	public static void convertWikiPathToBioPax() throws FileNotFoundException{
		
		BioPAXIOHandler handler = new SimpleIOHandler();
		InputStream inputStream = new FileInputStream(new File("D:/msc4/biopax/WP996_80789.owl"));
		Model model = handler.convertFromOWL(inputStream);
		
		
		
		System.out.println(model.containsID("id42"));
		
		/*
		Iterator<BioPAXElement> it =  model.getObjects().iterator();
		while(it.hasNext()){
			BioPAXElement element =  it.next();
			System.out.println(element.getUri());
		}
		*/
		
		/*
		OutputStream outputStream = new FileOutputStream(new File("D:/msc4/biopax/test.owl"));
		handler.convertToOWL(model, outputStream);
		*/
	}

}
