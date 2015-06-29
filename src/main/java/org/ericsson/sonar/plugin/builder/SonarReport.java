package org.ericsson.sonar.plugin.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Scanner;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.ericsson.sonar.exception.SonarReporException;
import org.ericsson.sonar.plugin.util.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.config.Settings;

public class SonarReport {

	private static final Logger log = LoggerFactory
			.getLogger(SonarReport.class);

	private Settings settings;

	public SonarReport(Settings settings) {
		this.settings = settings;
	}

	public void generate() {
		log.info("Test Custom Report Plugin....");
		SonarMetrics sonarMetrics = new SonarMetrics(settings);
		String[] metrics = sonarMetrics.getMetricsName();
		SonarResources sonarResources = new SonarResources(metrics,settings);
		StreamSource source = new StreamSource(new StringReader("<final>" + sonarResources.getAllResources()+ "</final>"));
		generateHtml(source);
	}
	
	public void generateHtml(StreamSource xmlSource){
		try {		
			String projectHome = settings.getProperties().get("sonar.working.directory");
			String projectName = settings.getProperties().get("sonar.projectName");
			TransformerFactory tFactory=TransformerFactory.newInstance();
			Source xslDoc=new StreamSource("resources/Sonar-Report.xsl");
			String outputFileName=projectHome+File.separator+projectName+"_Report.html";

			OutputStream htmlFile=new FileOutputStream(outputFileName);
			Transformer trasform=tFactory.newTransformer(xslDoc);
			trasform.transform(xmlSource, new StreamResult(htmlFile));
			
			String cssString = readFile("resources/style.txt");
			applyStyleSheet(outputFileName,cssString);
			
			log.info("Report generated in "+outputFileName);
			
			
			SendEmail email = new SendEmail(settings);
			email.sendEmail(outputFileName);
		} 
		catch (Exception e) {
			throw new SonarReporException(e);
		}
	}
	
	private String readFile(String pathname) throws Exception {

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
	
	private void applyStyleSheet(String htmlFile, String cssString){
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
