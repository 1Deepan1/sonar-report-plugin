package org.ericsson.sonar.plugin.builder;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.log4j.Logger;
import org.ericsson.sonar.exception.SonarReporException;

public class SonarClient {

	private final String url;
	private final String name;
	private final String pwd;
	protected Properties props;

	public SonarClient()  {
		Properties prop = new Properties();
		try {
			prop.load(SonarClient.class
					.getResourceAsStream("/sonar-report-plugin.properties"));
		} catch (IOException e) {
			throw new SonarReporException(e);
		}      
		this.url = prop.getProperty("sonar-report-plugin.server.url");
		this.name =prop.getProperty("sonar-report-plugin.admin.name");
		this.pwd=prop.getProperty("sonar-report-plugin.admin.pwd");
	}

	private static final Logger LOGGER = Logger.getLogger(SonarClient.class);

	public WebTarget getTarget(String path) {
		System.out.println(name);
		System.out.println(pwd);
		Client client = ClientBuilder.newClient().register(
				new BasicAuthentication(name,pwd));
		return client.target(url).path(path);
	}

}
