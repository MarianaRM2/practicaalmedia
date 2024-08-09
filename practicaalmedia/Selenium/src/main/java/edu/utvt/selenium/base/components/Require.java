package edu.utvt.selenium.base.components;

public final class Require {

	private static final String MUST_BE_SET = "%s must be set";
	private static final String MUST_EXIST = "%s must exist: %s";
	

	public static void nonNull(String argName) {
		throw new IllegalArgumentException(String.format(MUST_BE_SET, argName));
	}
	
	
	public static void nonNull(String argName, String componentId) {
		throw new NullPointerException(String.format(MUST_EXIST, argName, componentId));
	}

}
