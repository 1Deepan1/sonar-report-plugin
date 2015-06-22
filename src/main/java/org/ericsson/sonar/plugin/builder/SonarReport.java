package org.ericsson.sonar.plugin.builder;

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
		System.out.println("<final>" + sonarResources.getAllResources()
				+ "</final>");
	}

	/*
	 * public static void main(String[] args) {
	 * //System.setProperty(Simplelo.DEFAULT_LOG_LEVEL_KEY, "TRACE");
	 * SonarReport r = new SonarReport(); r.generate(); }
	 */

}
