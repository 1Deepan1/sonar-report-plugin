package org.ericsson.sonar.plugin.batch;

import org.ericsson.sonar.plugin.CustomSonarPlugin;
import org.ericsson.sonar.plugin.builder.SonarReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.CheckProject;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.issue.Issue;
import org.sonar.api.issue.ProjectIssues;
import org.sonar.api.resources.Project;

public class SonarReportJob implements org.sonar.api.batch.PostJob,
		CheckProject {
	
	private static final Logger LOG = LoggerFactory.getLogger(SonarReportJob.class);
	private Settings settings;
	private final ProjectIssues projectIssues;

	public SonarReportJob(Settings settings, ProjectIssues projectIssues) {
		this.settings = settings;
		this.projectIssues = projectIssues;
	}

	public boolean shouldExecuteOnProject(Project project) {
		
		return Boolean.TRUE;
	}

	public void executeOn(Project project, SensorContext context) {
		
		LOG.info("Inside the test custom plugin");
		LOG.info("Inside the test custom plugin:"+settings.getProperties());
		LOG.info("password:"+settings.getProperties().get(CustomSonarPlugin.PASSWORD));
		LOG.info("password:"+settings.getProperties().get(CustomSonarPlugin.FLAG));
		LOG.info("password:"+settings.getProperties().get(CustomSonarPlugin.MAIL_IDS));
		LOG.info("password:"+settings.getProperties().get(CustomSonarPlugin.SUBJECT));
		SonarReport report = new SonarReport(settings);
		report.generate();
		
	}
}
