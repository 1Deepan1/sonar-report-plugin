package org.ericsson.sonar.plugin;

import java.util.Arrays;
import java.util.List;

import org.ericsson.sonar.plugin.batch.SonarReportJob;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

/**
 * This class is the entry point for all extensions
 */
@Properties({ @Property(key = CustomSonarPlugin.MY_PROPERTY, name = "Plugin Property", description = "A property for the plugin", defaultValue = "Hello World!") })
public final class CustomSonarPlugin extends SonarPlugin {
	public static final String MY_PROPERTY = "sonar.example.myproperty";	

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
