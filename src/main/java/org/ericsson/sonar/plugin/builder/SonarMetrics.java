package org.ericsson.sonar.plugin.builder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.ericsson.sonar.exception.SonarReporException;
import org.sonar.api.config.Settings;
import org.sonar.api.utils.SonarException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SonarMetrics extends SonarClient {

	static XPathFactory factory;
	static XPath xpath;

	public SonarMetrics(Settings settings) {
		super(settings);
		factory = XPathFactory.newInstance();
		xpath = factory.newXPath();

	}

	public String get() {
		return getTarget("api/metrics").request(MediaType.APPLICATION_XML_TYPE)
				.get(String.class);
	}

	public String[] getMetricsName() {
		String[] tmpArray = null;
		String metricsXml = get();
		try {
			Document doc = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(new ByteArrayInputStream(metricsXml
							.getBytes("utf-8")));

			XPathExpression expr = xpath.compile("//metric/key");
			NodeList tmp = (NodeList) expr
					.evaluate(doc, XPathConstants.NODESET);
			tmpArray = new String[tmp.getLength()];
			for (int i = 0; i < tmp.getLength(); i++) {
				Node name = tmp.item(i);
				tmpArray[i] = name.getTextContent();
			}

		} catch (Exception e) {
			throw new SonarReporException(e);
		}

		return tmpArray;
	}

}
