package org.ericsson.sonar.plugin;

import java.util.ArrayList;
import java.util.List;

import org.ericsson.sonar.plugin.batch.SonarReportJob;
import org.sonar.api.SonarPlugin;

import com.google.common.collect.ImmutableList;

/**
 * This class is the entry point for all extensions
 */
public final class CustomSonarPlugin extends SonarPlugin {
	public static final String PASSWORD = "sonar.custom.report.pwd";	
	public static final String FLAG = "sonar.custom.report.flag";
	public static final String SUBJECT = "sonar.custom.report.subject";
	public static final String MAIL_IDS = "sonar.custom.report.emailids";

	// This is where you're going to declare all your Sonar extensions
	  public List getExtensions() {
	    /*return Arrays.asList(
	      // Definitions
	    //  ExampleMetrics.class,

	      // Batch
	     // ExampleSensor.class, RandomDecorator.class, IssueSensor.class, ListAllIssuesPostJob.class,
	    		SonarReportJob.class,Configuration.class,Configuration.getProperties()

	      // UI
	     // ExampleFooter.class, ExampleRubyWidget.class
	    		);*/
		  
		  List result = new ArrayList();
		    result.addAll(ImmutableList.of(SonarReportJob.class,Configuration.class));

		    result.addAll(Configuration.getProperties());
		    return result;
	  }
}
