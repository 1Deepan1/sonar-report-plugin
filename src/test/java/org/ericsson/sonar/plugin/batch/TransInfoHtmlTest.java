package org.ericsson.sonar.plugin.batch;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

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
			String cssFile = "src/test/resources/style.txt";
			
			TransformerFactory tFactory=TransformerFactory.newInstance();

			Source xslDoc=new StreamSource("src/test/resources/Sonar-Report.xsl");
			Source xmlDoc=new StreamSource("src/test/resources/metrics.xml");

			String outputFileName="src/test/resources/Report.html";

			OutputStream htmlFile=new FileOutputStream(outputFileName);
			Transformer trasform=tFactory.newTransformer(xslDoc);
			trasform.transform(xmlDoc, new StreamResult(htmlFile));
			System.out.println("done");
			String cssString = readFile(cssFile);
			applyStyleSheet(outputFileName,cssString);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static String readFile(String pathname) throws Exception {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
	
	private static void applyStyleSheet(String htmlFile, String cssString){
		FileInputStream fis;
		String input = "";
		try {
			fis = new FileInputStream(htmlFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			String aLine;
			while ((aLine = in.readLine()) != null) {
				if(aLine.startsWith("<replace>")){
					input += cssString + System.lineSeparator();
				}else{
					input += aLine + System.lineSeparator();
				}
			}
			in.close();
			
			 FileOutputStream os = new FileOutputStream(htmlFile);
			 os.write(input.getBytes());
			 os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}