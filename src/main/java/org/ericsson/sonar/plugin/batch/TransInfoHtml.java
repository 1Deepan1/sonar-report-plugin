package org.ericsson.sonar.plugin.batch;

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

public class TransInfoHtml {
	public static void main(String args[]) {
		try {			
			String currentDir = System.getProperty("user.dir");
			System.out.println(currentDir);
			TransformerFactory tFactory=TransformerFactory.newInstance();

			Source xslDoc=new StreamSource("/src/main/resources/Sonar-Report.xsl");
			Source xmlDoc=new StreamSource("/src/main/resources/metrics.xml");

			String outputFileName=currentDir+"/src/main/resources/Report.html";

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