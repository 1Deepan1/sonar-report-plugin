package org.ericsson.sonar.plugin.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SonarReport {
	
	private static final Logger log = LoggerFactory.getLogger(SonarReport.class);
	
	
	public void generate(){
		log.info("Test Custom Report Plugin....");
		SonarMetrics sonarMetrics = new SonarMetrics();
		String[] metrics= sonarMetrics.getMetricsName();
		SonarResources sonarResources = new SonarResources(metrics);
		System.out.println("<final>"+sonarResources.getAllResources()+"</final>");
	}
	
		
	public static void main(String[] args) {
		//System.setProperty(Simplelo.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		SonarReport r = new SonarReport();
		r.generate();
	}

}
