package org.ericsson.sonar.plugin.builder;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import javax.ws.rs.core.MediaType;

import org.sonar.api.config.Settings;

public class SonarResources extends SonarClient {

	private String[] metrics;

	public SonarResources(String[] metrics,Settings settings) {
		super(settings);
		this.metrics = metrics;
	}

	public String getResourcesByNames(String queryParam) {
		return (getTarget("api/resources").queryParam("metrics", queryParam)
				.request(MediaType.APPLICATION_XML_TYPE).get(String.class))
				.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();

	}

	public String getAllResources() {
		final ForkJoinPool pool = new ForkJoinPool();
		final GetResourcesTask resources = new GetResourcesTask(metrics);
		return (pool.invoke(resources));
	}

	public class GetResourcesTask extends RecursiveTask<String> {

		private static final long serialVersionUID = -7331334368023353511L;
		private String[] metrics;
		private int start;
		private int end;

		public GetResourcesTask(String[] metrics, int start, int end) {
			this.metrics = metrics;
			this.start = start;
			this.end = end;

			System.out.println(start + "," + end);
		}

		public GetResourcesTask(String[] metrics) {
			this(metrics, 0, metrics.length);
		}

		@Override
		protected String compute() {
			final int length = end - start;
			if (length < 10) {
				return computeDirectly();
			}

			final int split = length / 2;
			final GetResourcesTask left = new GetResourcesTask(metrics, start,
					start + split);
			left.fork();
			final GetResourcesTask right = new GetResourcesTask(metrics, start
					+ split, end);

			return (right.compute() + left.join());

			/*
			 * final int split = length / 2; final GetResourcesTask tasks = new
			 * GetResourcesTask(metrics, start, start + split);
			 */

		}

		private String computeDirectly() {
			System.out.println(Thread.currentThread() + " computing: " + start
					+ " to " + end);
			String queryParam = "";
			for (int i = start; i < end; i++) {
				queryParam = queryParam + metrics[i] + ",";
			}
			System.out.println("-------" + queryParam);
			return getResourcesByNames(queryParam);
		}

	}

}
