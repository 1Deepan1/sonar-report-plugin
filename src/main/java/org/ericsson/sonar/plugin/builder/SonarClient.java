package org.ericsson.sonar.plugin.builder;

import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.log4j.Logger;
import org.sonar.api.config.Settings;

public class SonarClient {

	private Settings settings;

	public SonarClient(Settings settings) {
		/*
		 * Properties prop = new Properties(); try { prop.load(SonarClient.class
		 * .getResourceAsStream("/sonar-report-plugin.properties")); } catch
		 * (IOException e) { throw new SonarReporException(e); } this.url =
		 * prop.getProperty("sonar-report-plugin.server.url"); this.name
		 * =prop.getProperty("sonar-report-plugin.admin.name");
		 * this.pwd=prop.getProperty("sonar-report-plugin.admin.pwd");
		 */
		this.settings = settings;

	}

	private static final Logger LOGGER = Logger.getLogger(SonarClient.class);

	public WebTarget getTarget(String path) {
		Client client = ClientBuilder.newClient().register(
				new BasicAuthentication("admin", settings.getProperties().get(
						"sonar.custom.report.pwd")));
		return client.target(settings.getProperties().get("sonar.host.url"))
				.path(path);
	}

}
