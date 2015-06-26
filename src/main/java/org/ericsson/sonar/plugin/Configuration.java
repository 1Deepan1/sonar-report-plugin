package org.ericsson.sonar.plugin;

import java.util.List;

import javax.annotation.CheckForNull;

import org.sonar.api.BatchComponent;
import org.sonar.api.PropertyType;
import org.sonar.api.batch.InstantiationStrategy;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.config.Settings;

import com.google.common.collect.ImmutableList;

@InstantiationStrategy("PER_BATCH")
public class Configuration implements BatchComponent {
	
	
	public static final String PASSWORD = "sonar.custom.report.pwd";	
	public static final String FLAG = "sonar.custom.report.flag";
	public static final String SUBJECT = "sonar.custom.report.subject";
	public static final String MAIL_IDS = "sonar.custom.report.emailids";
	// public static final String CONFIG_DIR_PROP_KEY = "sonar.svn.config_dir";
	// public static final String TRUST_SERVER_PROP_KEY =
	// "sonar.svn.trust_server_cert";
	// public static final String USE_MERGE_HISTORY_KEY =
	// "sonar.svn.use_merge_history";
	private final Settings settings;

	public Configuration(Settings settings) {
		this.settings = settings;
	}

	public static List<PropertyDefinition> getProperties() {
		return ImmutableList
				.of(PropertyDefinition
						.builder(FLAG)
						.name("Flag").defaultValue("true")
						.description(
								"true or false - default true - sends the report via email")
						.type(PropertyType.STRING)
						.onQualifiers("TRK", new String[0]).category("Sonar Report Plugin").build(),
						PropertyDefinition
								.builder(PASSWORD)
								.name("Password")
								.description(
										"Admin password")
								.type(PropertyType.PASSWORD)
								.onQualifiers("TRK", new String[0])
								.category("Sonar Report Plugin").build(),
								PropertyDefinition
								.builder(SUBJECT)
								.name("Subject")
								.description(
										"Subject of the Mail")
								.type(PropertyType.STRING)
								.onQualifiers("TRK", new String[0])
								.category("Sonar Report Plugin").build(),
								PropertyDefinition
								.builder(MAIL_IDS)
								.name("Mail Ids")
								.description(
										"Comma seperated Mail Ids")
								.type(PropertyType.TEXT)
								.onQualifiers("TRK", new String[0])
								.category("Sonar Report Plugin").build()
						);
	}

	@CheckForNull
	public String username() {
		return this.settings.getString(PASSWORD);
	}

	
	
}