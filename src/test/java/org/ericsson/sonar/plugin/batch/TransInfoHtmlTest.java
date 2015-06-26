package org.ericsson.sonar.plugin.batch;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransInfoHtmlTest {
	public static void main(String args[]) {
		try {		
			System.out.println(System.getProperty("user.dir"));
			
			TransformerFactory tFactory=TransformerFactory.newInstance();

			Source xslDoc=new StreamSource("src/test/resources/Sonar-Report.xsl");
			Source xmlDoc=new StreamSource("src/test/resources/metrics.xml");

			String outputFileName="src/test/resources/Report.html";

			OutputStream htmlFile=new FileOutputStream(outputFileName);
			Transformer trasform=tFactory.newTransformer(xslDoc);
			trasform.transform(xmlDoc, new StreamResult(htmlFile));
			System.out.println("done");
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}