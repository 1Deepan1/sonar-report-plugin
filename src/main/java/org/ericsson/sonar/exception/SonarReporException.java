package org.ericsson.sonar.exception;

public class SonarReporException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1529408992520533258L;

	public SonarReporException() {
		super();
	}

	public SonarReporException(String message) {
		super(message);
	}

	public SonarReporException(Throwable cause) {
		super(cause);
	}

	public SonarReporException(String message, Throwable cause) {
		super(message, cause);
	}

}
