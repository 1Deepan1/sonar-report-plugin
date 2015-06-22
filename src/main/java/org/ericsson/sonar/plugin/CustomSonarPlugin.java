package org.ericsson.sonar.plugin;

import java.util.Arrays;
import java.util.List;

import org.ericsson.sonar.plugin.batch.SonarReportJob;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.PropertyField;
import org.sonar.api.PropertyType;
import org.sonar.api.SonarPlugin;

/**
 * This class is the entry point for all extensions
 */
@Properties({ @Property(key = CustomSonarPlugin.PASSWORD, name = "Password", description = "Admin Password" , type=PropertyType.PASSWORD), @Property(key = CustomSonarPlugin.FLAG, name = "Flag", description = "set true to generate report else false" ),@org.sonar.api.Property(key="sonar.custom.report.emailids", name="emailIds", description="Configurable list for emailing comma seperated", type=PropertyType.TEXT),@org.sonar.api.Property(key="sonar.custom.report.subject", name="Subject", description="Subject of the report email. ", defaultValue="Source Code Quality Report",type=PropertyType.TEXT)})
public final class CustomSonarPlugin extends SonarPlugin {
	public static final String PASSWORD = "sonar.custom.report.pwd";	
	public static final String FLAG = "sonar.custom.report.flag";
	public static final String SUBJECT = "sonar.custom.report.subject";
	public static final String MAIL_IDS = "sonar.custom.report.emailids";

	// This is where you're going to declare all your Sonar extensions
	  public List getExtensions() {
	    return Arrays.asList(
	      // Definitions
	    //  ExampleMetrics.class,

	      // Batch
	     // ExampleSensor.class, RandomDecorator.class, IssueSensor.class, ListAllIssuesPostJob.class,
	    		SonarReportJob.class

	      // UI
	     // ExampleFooter.class, ExampleRubyWidget.class
	    		);
	  }
}
